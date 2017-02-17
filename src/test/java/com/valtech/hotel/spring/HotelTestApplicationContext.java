package com.valtech.hotel.spring;

import com.tngtech.jgiven.integration.spring.EnableJGiven;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableJGiven
@ComponentScan(basePackages = {"com.valtech.hotel.backend.*"})
public class HotelTestApplicationContext extends HotelApplicationContext {
}
