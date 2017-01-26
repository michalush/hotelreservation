package com.valtech.hotel.spring;

import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelSpringApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HotelSpringApplication.class, args);
    }

    @Bean
    public WebApplication webApplication() {
        return new WicketApplication();
    }
}
