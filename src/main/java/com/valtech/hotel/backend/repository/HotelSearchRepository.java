package com.valtech.hotel.backend.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valtech.hotel.backend.entity.Hotel;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class HotelSearchRepository {
    private static final Logger LOG = LoggerFactory.getLogger(HotelSearchRepository.class);
    private final Client elasticsearchClient;
    private final String indexName;

    public HotelSearchRepository(@Autowired Client elasticsearchClient, @Autowired Environment env) {
        this.elasticsearchClient = elasticsearchClient;
        this.indexName = env.getProperty("index.name");
    }

    public List<Hotel> findHotel(String searchText, String sortOrder) {
        List<Hotel> hotels = new ArrayList<Hotel>();
        final QueryBuilder matchQueryBuilder = createQuery(searchText);
        final SearchResponse searchResponse = elasticsearchClient.prepareSearch(indexName).setQuery(matchQueryBuilder)
                .addSort(sortOrder, SortOrder.DESC)
                .get();

        final SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                final Hotel hotel = mapper.readValue(hit.source(), Hotel.class);
                hotels.add(hotel);
            } catch (IOException e) {
                LOG.error("Failed to read response from hit {}", hit.getSourceAsString(), e);
            }
        }

        return hotels;
    }

    private QueryBuilder createQuery(String searchText) {
        if (StringUtils.isEmpty(searchText)) {
            return QueryBuilders.matchAllQuery();
        }
        return QueryBuilders.multiMatchQuery(searchText, "name", "description").type(MultiMatchQueryBuilder.Type.MOST_FIELDS);
    }
}
