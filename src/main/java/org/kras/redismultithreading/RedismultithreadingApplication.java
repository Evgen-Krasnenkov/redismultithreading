package org.kras.redismultithreading;

import org.kras.redismultithreading.configure.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class RedismultithreadingApplication implements CommandLineRunner {
    @Value("${mock-endpoint.address}")
    private String urlAddress;


    public static void main(String[] args) {
        SpringApplication.run(RedismultithreadingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(urlAddress);
    }
}
