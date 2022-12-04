package crazyGame;

/*
Player stores
    Their score
    And their hand
 */
public class Player {
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
