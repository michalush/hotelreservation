package com.valtech.hotel.backend.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valtech.hotel.backend.entity.Hotel;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

@Repository
public class HotelIndexRepository {
    private static final Logger LOG = LoggerFactory.getLogger(HotelIndexRepository.class);
    private final Client elasticsearchClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String indexName;

    public HotelIndexRepository(@Autowired Client elasticsearchClient, @Autowired Environment env) {
        this.elasticsearchClient = elasticsearchClient;
        this.indexName = env.getProperty("index.name");
    }

    public void add(Hotel hotel) {
        LOG.info("adding hotel to search index: {}", hotel);
        try {
            IndexRequestBuilder indexRequestBuilder = createIndexRequestBuilder(hotel);
            IndexResponse response = elasticsearchClient.index(indexRequestBuilder.request()).actionGet();
            LOG.info("entry added to index {}, type {}, doc-version: {}, doc-id: {}, created: {}",
                    response.getIndex(), response.getType(), response.getVersion(), response.getId(), response.status());
        } catch (JsonProcessingException e) {
            LOG.error("error in processing json!", e);
        }
    }

    private IndexRequestBuilder createIndexRequestBuilder(Hotel hotel) throws JsonProcessingException {
        IndexRequestBuilder indexRequestBuilder = elasticsearchClient.prepareIndex(indexName, Hotel.TYPE, hotel.getId().toString());
        indexRequestBuilder.setSource(objectMapper.writeValueAsBytes(hotel));
        return indexRequestBuilder;
    }

    public void addHotels(List<Hotel> hotels) {
        LOG.info("adding hotels to search index: {}", hotels.size());
        final BulkRequestBuilder bulkRequestBuilder = elasticsearchClient.prepareBulk();
        try {
            for (Hotel hotel : hotels) {
                bulkRequestBuilder.add(createIndexRequestBuilder(hotel));
            }
            final BulkResponse response = bulkRequestBuilder.get();

            final Iterator<BulkItemResponse> iterator = response.iterator();

            while (iterator.hasNext()) {
                final BulkItemResponse bulkItemResponse = iterator.next();
                LOG.info("entry added to index {}, type {}, doc-version: {}, doc-id: {}, created: {}",
                        bulkItemResponse.getIndex(), bulkItemResponse.getType(), bulkItemResponse.getVersion(),
                        bulkItemResponse.getItemId(), bulkItemResponse.status());
            }
        } catch (JsonProcessingException e) {
            LOG.error("error in processing json!", e);
        }
    }

    public void createIndex() {
        try {
            Resource resource = new ClassPathResource("/hotels-settings.json");
            Path path = resource.getFile().toPath();
            Settings indexSettings = Settings.builder().loadFromPath(path).build();

            Resource mappingResource = new ClassPathResource("/hotels-mappings.json");
            final String mappings = FileUtils.readFileToString(mappingResource.getFile(), StandardCharsets.UTF_8);

            elasticsearchClient.admin().indices().prepareCreate(indexName)
                    .setSettings(indexSettings)
                    .addMapping(Hotel.TYPE, mappings)
                    .get();
        } catch (IOException e) {
            LOG.error("Unable to read settings file", e);
        }
    }

    public void deleteIndex() {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest(indexName);
        elasticsearchClient.admin().indices().delete(indexRequest).actionGet();
    }

    public GetResponse getHotelById(String id) {
        return elasticsearchClient.prepareGet(indexName, Hotel.TYPE, id).get();
    }
}
