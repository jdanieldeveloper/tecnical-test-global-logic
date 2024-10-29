package com.global.logic.user.command.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 *
 * Mantiene la configuracion de la aplicacion y los beans correspondientes.
 *
 * @author daniel.carvajal
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.global.logic.user"})
@MapperScan({"com.global.logic.user.command.infrastructure.persistence.mybatis.mapper"})
public class UserManageSideConfig {

    /**
     * Bean que permite recuperar un datasource
     *
     * @return el datasource
     */
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return  new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2) //.H2
                .addScript("db/h2/sql/schema-hsqldb.sql")
                .addScript("db/h2/sql/data-hsqldb.sql")
        .build();
    }
}
