package com.valtech.hotel.backend.repository;

import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelRepository {

    private ElasticsearchClient elasticsearchClient;

    public void setElasticsearchClient(@Autowired ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }
}
