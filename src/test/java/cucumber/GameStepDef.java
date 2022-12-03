package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GameStepDef {
    @Given("a pile is shuffled to {string}")
    public void aPileIsShuffledToShuffle(String pile) {
    }

    @When("game starts")
    public void gameStarts() {
    }

    @Then("the stock pile is {string}")
    public void theStockPileIsStock(String stock) {
    }

    @And("player {int} hand is {string}")
    public void playerHandIsHand(int playerNum, String hand) {
    }

    @Given("a player {int} has {int} card left in thier hand {string}")
    public void aPlayerHasCardLeftInThierHandHand(int playerNum, int cardsLeft, String hand) {
    }

    @When("that player {int} plays that card")
    public void thatPlayerPlaysThatCardThenTheirHandWillBeEmpty(int playerNum) {
    }

    @Then("that player {int} scores {int}")
    public void thatPlayerScores(int arg0, int arg1) {
    }

    @And("player {int} score {int}")
    public void playerScoreScore(int arg0, int arg1) {
    }
}
