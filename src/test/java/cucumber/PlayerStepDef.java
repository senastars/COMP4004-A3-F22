package cucumber;

import crazyGame.Player;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerStepDef {
    Player player = new Player();

    @Given("A player exists")
    public void aPlayerExists(){
        System.out.println("new player");
        player = new Player();
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
}
