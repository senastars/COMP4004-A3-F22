package com.example.crazy;

import com.example.crazy.resourceRepresentationClasses.Greeting;
import com.example.crazy.resourceRepresentationClasses.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Controller
public class GameServerController {
    int numOfPLayer = 0;
    /*@MessageMapping("/hello") -> Recieve a request from client from client under this route
    @SendTo("/topic/greetings") -> Send it back to client under this title
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }*/

    @MessageMapping("/newPlayer")
    @SendTo("/player/id")
    public String newPlayer(){
        String playerNum = new String(String.valueOf(numOfPLayer));
        numOfPLayer++;
        if(numOfPLayer == 3){

        }
        return playerNum;
    }

}
