package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.example.crazy.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStepDef {
    Game game = new Game();

    @Given("A game is started")
    public void AGameIsStarted(){
        game = new Game();
    }

    @Given("Testing Start of Game")
    public void testingStartOfGame() {
        System.out.println("Testing Start of Game");
    }
    @When("a pile is shuffled to {string}")
    public void aPileIsShuffledToShuffle(String pile) {
        game.shuffle();
        game.setStockPile(pile);
    }

    @And("the pile is shuffled out")
    public void thePileIsShuffledOut() {
        game.sort();
    }

    @Then("the stock pile is {string}")
    public void theStockPileIsStock(String stock) {
        assertEquals(stock,game.getStockPile());
    }

    @And("player {int} hand is {string}")
    public void playerHandIsHand(int playerNum, String hand) {
        int realNumber = playerNum-1;
        assertEquals(hand,game.getPlayers()[realNumber].getHand());
    }
    @And("player {int} hand is currently {string}")
    public void playerHandIsCurrentlyHand(int playerNum, String hand) {
        int realNumber = playerNum-1;
        game.getPlayers()[realNumber].setHand(hand);
    }

    @Given("Last Card")
    public void lastCard() {
        System.out.println("Testing Last Card");
    }


    @And("player {int} score {int}")
    public void playerScoreScore(int playerNum, int score) {
        int realNumber = playerNum-1;
        assertEquals(score,game.getPlayers()[realNumber].getScore());
    }


    @When("player {int} hand is empty")
    public void playerHandIsEmpty(int playerNum) {
        int realNumber = playerNum-1;
        game.getPlayers()[realNumber].setHand(",");
    }

    @Then("we end the game")
    public void weEndTheGame() {
        game.score();
    }


}
