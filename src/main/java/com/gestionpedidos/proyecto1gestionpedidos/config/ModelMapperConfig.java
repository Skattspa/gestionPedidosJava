package com.gestionpedidos.proyecto1gestionpedidos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
 /*public class DataSourceConfig {
    @Bean
    // este es configuration properties para usar hikari
   @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }
}*/
