package com.example.crazy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


//@ComponentScan("com.example.crazy.GameServerController")
@SpringBootApplication
@ComponentScan("com.example.crazy")
public class MessagingStompWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingStompWebsocketApplication.class, args);
    }
}