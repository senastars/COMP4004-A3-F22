package com.example.crazy;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
        //this.game = new Game();
        //game.init();
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
        //game.init();
        //game.getStockPile();
        game.start();
        //also draw put leave for now
        String res = game.getStockPile()+"."+game.discard;
        //this.game.shuffle();
        //this.game.sort();
        System.out.println("In startGame: "+ game.getStockPile() +" Current Hand " + game.players[game.getPlayerTurn()].getHand());

        return res;
    }

    @MessageMapping("/getHand")
    @SendTo("/player/receiveHand")
    public String sendHand(){
        //game.getPlayerTurn();
        System.out.println("HEREE in sendHand");
        StringBuilder res = new StringBuilder();
        res.append(game.getPlayerTurn());
        res.append(",");
        res.append(this.game.players[this.game.getPlayerTurn()].getHand());
        //System.out.println("sendhand res "+ res + " "+ this.game.players[this.game.getPlayerTurn()].getHand() + this.game.stockPile + " "+ this.game.getPlayerTurn());
        //game.nextPlayer(true);


        return String.valueOf(res);
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

    @MessageMapping("/playCard")
    @SendTo("/player/receiveCard")
    public String playingCard(String req){
        String res= "";
        res = res + game.getPlayerTurn() +",";
        String dir="0";
        System.out.println("------REQUEST " + req);

        String temp = game.playCard(req);
        game.nextPlayer();

        if(game.direction == "up"){
            dir= "1";
        }
        //0,3,0,1S:10C,1D,QS,3S,
        res = res + game.getPlayerTurn() +","+dir +","+"." +req+ ":" + temp;
        return res;
    }


}
