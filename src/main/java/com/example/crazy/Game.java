package com.example.crazy;

/*
Controls:
    Players
    Player turns
    Updates players score
    draw pile
    discard pile
    start of game procedures
    end of game procedures
    DOES NOT KNOW THE PLAYERS HAND
 */

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

@Component("Game")
public class Game {
//@Autowired
    Player[] players;
    int numPlayer;

    String intDeck = "1D,2D,3D,4D,5D,6D,7D,8D,9D,10D,JD,QD,KD,1H,2H,3H,4H,5H,6H,7H,8H,9H,10H,JH,QH,KH,1C,2C,3C,4C,5C,6C,7C,8C,9C,10C,JC,QC,KC,1S,2S,3S,4S,5S,6S,7S,8S,9S,10S,JS,QS,KS,";
    String stockPile;
    int playerTurn;
    String direction;

    String discard;
    public Game(){
    }

    @PostConstruct
    public void init(){
        //players = new Player()[];
        players = new Player[0];
        playerTurn = 0;
        numPlayer =0;
        direction = "up";
        discard = "";
        //Add stuff like round- turn number and playdirection and gameover
    }

    public void start(){
        this.shuffle();
        this.sort();
        this.getTop();
    }

    public int addPlayer(){
        if(numPlayer<4){
            numPlayer++;
            players = new Player[numPlayer];
            for (int i = 0; i < numPlayer; i++) {
                players[i] = new Player();
            }
        }
        else{
            return -1;
        }
        return numPlayer;
    }

    public String getTop() {
        String[] tempDeck = this.stockPile.split(",");
        String top = tempDeck[0];

        //Shifting stockPile
        System.arraycopy(tempDeck,1,tempDeck,0,tempDeck.length-1);
        String[] copy = new String[tempDeck.length-1];
        System.arraycopy(tempDeck, 0, copy, 0, copy.length);
        tempDeck = new String[copy.length];
        System.arraycopy(copy, 0, tempDeck, 0, copy.length);

        this.stockPile = "";
        for (int i = 0; i < tempDeck.length; i++) {
            this.stockPile = this.stockPile + tempDeck[i] + ",";
        }

        discard = top;
        return top;
    }

    public String getStockPile() {
        return stockPile;
    }

    public String setStockPile(String pile){
        this.stockPile = pile;
        return this.stockPile;
    }

    public Player[] getPlayers(){
        return players;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public void nextPlayer(){
        System.out.println("NEXT PERSON");
        if(direction == "up") {
            playerTurn++;
            if (playerTurn > 3) {
                playerTurn = 0;
            }
        }
        else {
            playerTurn--;
            if (playerTurn <0){
                playerTurn =3;
            }
        }
    }

    public void shuffle(){
        this.stockPile = "";
        String[] cards = intDeck.split(",");
        Random rand = new Random();
        String[] shuffledDeck = new String[52];
        int n;
        for (int i=0; i<52; i++){
            n = rand.nextInt(52-i);
            shuffledDeck[i] = cards[n];
            this.stockPile = this.stockPile + shuffledDeck[i] + ",";
            System.arraycopy(cards,n+1,cards,n,cards.length-n-1);
        }
    }

    public void sort(){
        String[] tempDeck = this.stockPile.split(",");
        String[] hands = new String[4];
        hands[0] = "";
        hands[1] = "";
        hands[2] = "";
        hands[3] = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                String temp = tempDeck[0] + ",";
                hands[j] = hands[j].concat(temp);
                System.arraycopy(tempDeck,1,tempDeck,0,tempDeck.length-1);
                String[] copy = new String[tempDeck.length-1];
                System.arraycopy(tempDeck, 0, copy, 0, copy.length);
                tempDeck = new String[copy.length];
                System.arraycopy(copy, 0, tempDeck, 0, copy.length);
            }
        }

        this.stockPile = "";
        for (int i = 0; i < tempDeck.length; i++) {
            this.stockPile = this.stockPile + tempDeck[i] + ",";
        }
        players[0].setHand(hands[0]);
        players[1].setHand(hands[1]);
        players[2].setHand(hands[2]);
        players[3].setHand(hands[3]);
        System.out.println("Done Sorting");
    }

    public void score(){
        int temp;
        String[] tempHand;
        for (int i = 0; i < 4; i++) {
            temp =0;
            tempHand = players[i].getHand().split(",");

            for (int j = 0; j < tempHand.length; j++) {
                String num = tempHand[j];
                if(num.charAt(0) == '8'){
                    temp+=50;
                }
                else if (num.charAt(0) == 'J' || num.charAt(0) == 'Q' || num.charAt(0)== 'K'){
                    temp+=10;
                }
                else if(num.charAt(0) == '1' && num.charAt(1) == '0'){
                    temp+=10;
                }
                else{
                    temp+= Integer.parseInt(String.valueOf(num.charAt(0)));
                }

            }
            players[i].addScore(temp);

        }
    }

    public String playCard(String card) {
        //playerTurn;
        String[] currHand = players[playerTurn].getHand().split(",");
        StringBuilder temp = new StringBuilder();
        String discard = "";
        for (int i = 0; i < currHand.length; i++) {
            if(Objects.equals(currHand[i], card)){
                discard = currHand[i];
            }
            else{
                temp.append(currHand[i]).append(",");
            }
        }

        if(discard.charAt(0) == '1'){
            if(direction == "up"){
                direction = "down";
            }
            else{
                direction = "up";
            }
        }

        this.discard = discard;
        System.out.println("&&&&&&GAME DIRECTON" + direction);

        return players[playerTurn].setHand(String.valueOf(temp));
    }

    public String getDirection() {
        return this.direction;
    }

    /*
        Player stores
            Their score
            And their hand
         */
    public static class Player {
        private int score;
        private String hand;


        public Player() {
            this.score = 0;
            this.hand = ",";
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void addScore(int score){
            this.score+=score;
        }

        public String getHand() {
            return hand;
        }
        public String setHand(String hand){
            this.hand = hand;
            return this.hand;
        }
    }
}
