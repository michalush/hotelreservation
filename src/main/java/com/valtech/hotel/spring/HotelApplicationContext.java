package com.valtech.hotel.spring;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@ComponentScan(basePackages = {"com.valtech.hotel.backend.*"})
public class HotelApplicationContext implements EnvironmentAware {
    private static final Logger LOG = LoggerFactory.getLogger(HotelApplicationContext.class);
    private Environment env;

    @Bean(destroyMethod = "close")
    public Client client() throws IOException, InterruptedException {
        final int port = env.getProperty("elasticsearch.port", Integer.class);
        final String host = env.getProperty("elasticsearch.host");
        final String clusterName = env.getProperty("elasticsearch.clusterName");
        LOG.info("Using external Elasticsearch node cluster name '{}', host '{}', and port '{}'",
                clusterName, host, port);
        final Settings tSettings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.ping_timeout", "20s").build();
        final TransportClient client = new PreBuiltTransportClient(tSettings);
        client.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress(host, port)));
        return client;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder().additionalMessageConverters(new SourceHttpMessageConverter<Source>());
    }
}
