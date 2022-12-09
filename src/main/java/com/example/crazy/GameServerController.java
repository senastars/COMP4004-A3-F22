package com.example.crazy;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Controller
@Component("GameServerController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GameServerController {
    int numOfPLayer = 0;
    @Autowired
    Game game;

    @PostConstruct
    public void init(){
        numOfPLayer = 0;
    }

    public Game getGame() {
        return game;
    }
    /*@MessageMapping("/hello") -> Recieve a request from client from client under this route
    @SendTo("/topic/greetings") -> Send it back to client under this title
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }*/

    @MessageMapping("/newPlayer")
    @SendTo("/player/id")
    public String newPlayer(){
        String res = "";
        if(game.numPlayer < 4){
            game.addPlayer();
            String playerNum = new String(String.valueOf(game.numPlayer-1));
            //String playerNum = new String(String.valueOf(numOfPLayer));
            //numOfPLayer++;

            res = res + playerNum +",";
            if(game.numPlayer == 4){
                res = res + "b" +",";
            }
        }
        else {
            res = "-1";
        }

        return res;
    }

    @MessageMapping("/startGame")
    @SendTo("/player/startGame")
    public String startGame(){
        this.game = new Game();
        //game.getStockPile();
        //also draw put leave for now
        String res = this.game.getStockPile();

        return res;
    }

    @MessageMapping("/getHand")
    @SendTo("/player/receiveHand")
    public String sendHand(){
        //game.getPlayerTurn();
        System.out.println("HEREE ");
        String res = "";
        res= res + game.getPlayerTurn() + game.getPlayers()[game.playerTurn].getHand();
        game.nextPlayer(true);


        return res;
    }
    @MessageMapping("/playTurn")
    @SendTo("/player/receiveTurn")
    public String sendTurn(){
        //game.getPlayerTurn();
        String res = "";
        res= res + game.getPlayerTurn();

        return res;
    }
    @MessageMapping("/updateGame")
    @SendTo("/player/receiveTurn")
    public String updateGame(String req){
        //game.getPlayerTurn();
        String res = "";
        res= res + game.getPlayerTurn();

        return res;
    }



}
