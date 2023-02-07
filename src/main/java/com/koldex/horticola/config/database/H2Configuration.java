package com.koldex.horticola.config.database;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile(value = "local")
public class H2Configuration {
    @Bean(initMethod = "start", destroyMethod = "stop", name = "H2 Server")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
