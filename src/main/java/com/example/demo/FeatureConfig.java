package com.example.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;


@Configuration
@Import({FeatureConfig.WithoutDataBaseIntegration.class, FeatureConfig.WithDataBaseIntegration.class})
public class FeatureConfig {

    @Profile("db")
    @EnableAutoConfiguration(
            exclude = {DataSourceAutoConfiguration.class,
                    DataSourceTransactionManagerAutoConfiguration.class,
                    HibernateJpaAutoConfiguration.class})
    static class WithoutDataBaseIntegration {

    }

    @Profile("!db")
    @EnableAutoConfiguration
    static class WithDataBaseIntegration {

    }
}


