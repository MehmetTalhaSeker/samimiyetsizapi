package com.samimiyetsiz.samimiyetsizapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories("com.samimiyetsiz.samimiyetsizapi.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {


}
