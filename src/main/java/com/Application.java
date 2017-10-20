package com;

import com.services.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

/**
 * Created by lijuan on 12/10/17.
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        try {
            org.h2.tools.Server.createTcpServer("-tcpAllowOthers").start();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        SpringApplication.run(Application.class, args);
        Server server = new Server();

    }
}
