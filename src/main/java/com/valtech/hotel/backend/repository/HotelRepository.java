package com.valtech.hotel.backend.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valtech.hotel.backend.entity.Hotel;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HotelRepository {
    private static final Logger LOG = LoggerFactory.getLogger(HotelRepository.class);
    private static final String HOTEL = "hotels";
    private static final String TYPE = "hotel";
    private final Client elasticsearchClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HotelRepository(@Autowired Client elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public void add(Hotel hotel) {
        LOG.info("adding beer to search index: %s\n", hotel);
        IndexRequestBuilder indexRequestBuilder = elasticsearchClient.prepareIndex(HOTEL, TYPE, hotel.getId());
        try {
            indexRequestBuilder.setSource(objectMapper.writeValueAsBytes(hotel));
            IndexResponse response = elasticsearchClient.index(indexRequestBuilder.request()).actionGet();
            LOG.info("entry added to index {}, type {}, doc-version: '%s', doc-id: '%s', created: %s\n",
                    response.getIndex(), response.getType(), response.getVersion(), response.getId(), response.status());
        } catch (JsonProcessingException e) {
            LOG.error("error in processing json!", e);
        }
    }
}
