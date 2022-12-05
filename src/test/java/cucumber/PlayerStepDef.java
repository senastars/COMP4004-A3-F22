package cucumber;

import com.example.crazy.Game;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerStepDef {
    Game.Player player = new Game.Player();

    @Given("A player exists")
    public void aPlayerExists(){
        System.out.println("new player");
        player = new Game.Player();
    }

    @Given("a player has a {int}")
    public void aPlayerHasAInitScore(int initScore) {
        player.setScore(initScore);
    }

    @When("they gain {int}")
    public void theyGainPoints(int points) {
        player.setScore(player.getScore()+points);
    }

    @Then("a player score is {int}")
    public void aPlayerScoreIsAfterScore(int afterScore) {
        assertEquals(player.getScore(),afterScore);
    }

    @Given("a player has whatever")
    public void aPlayerHasWhatever() {
    }

    @When("they get dealt {string}")
    public void theyGetDealtCards(String cards) {
        player.setHand(cards);
    }

    @Then("a player hand is {string}")
    public void aPlayerHandIsHand(String hand) {
        assertEquals(hand,player.getHand());
    }

}
