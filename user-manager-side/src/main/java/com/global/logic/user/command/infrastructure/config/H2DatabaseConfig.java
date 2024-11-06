package com.global.logic.user.command.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class H2DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return  new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                //.H2
                .setName("testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false")
                .addScript("db/h2/sql/schema-hsqldb.sql")
                .addScript("db/h2/sql/data-hsqldb.sql")
                .build();
    }
}
