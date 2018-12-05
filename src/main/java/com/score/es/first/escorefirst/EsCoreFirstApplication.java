package com.score.es.first.escorefirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsCoreFirstApplication {

    public static void main(String[] args) {
        /**
         * availableProcessors is already set to [4], rejecting [4]
         * 解决netty冲突
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(EsCoreFirstApplication.class, args);
    }
}
