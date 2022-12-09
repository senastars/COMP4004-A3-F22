package com.example.crazy.cucumber;

import com.example.crazy.GameServerController;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.Duration;

import static io.netty.util.ResourceLeakDetector.isEnabled;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
//import static org.openqa.selenium.support.ui.ExpectedConditions.isEnabled;

//@CucumberContextConfiguration
//@SpringBootTest(classes = TestConfig.class)
//@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = CucumberSpringConfiguration.class)

public class WebStepDef {
    //Game game;
    //int final test = 1;
    @Autowired
    GameServerController gameServerController;


    @Before
    public static void setUp() {
        DriverHelper.setUpDriver();
    }
    @After
    public static void tearDown(){DriverHelper.tearDown();}

    @And("game is reset")
    public void gameIsReset() {
        gameServerController.init();
        gameServerController.getGame().init();
    }
    //-------------BACKGROUND ----
    @Given("Open the Chrome tab")
    public void openTheChromeTab() {
        DriverHelper.openPage(0,"http://localhost:8080/");
        PageFactory.initElements(DriverHelper.getDriver(0), this);

        DriverHelper.openPage(1,"http://localhost:8080/");
        PageFactory.initElements(DriverHelper.getDriver(1), this);

        DriverHelper.openPage(2,"http://localhost:8080/");
        PageFactory.initElements(DriverHelper.getDriver(2), this);

        DriverHelper.openPage(3,"http://localhost:8080/");
        PageFactory.initElements(DriverHelper.getDriver(3), this);
        for (int i =0; i<4; i++){
            new WebDriverWait(DriverHelper.getDriver(i), Duration.ofSeconds(3)).until(visibilityOf(DriverHelper.getDriver(i).findElement(By.id("connect"))));
        }
        System.out.println("All Connected");
    }

    @And("all users are connected")
    public void allUsersAreConnected() {
        DriverHelper.getDriver(0).findElement(By.id("connect")).click();
        DriverHelper.getDriver(1).findElement(By.id("connect")).click();
        DriverHelper.getDriver(2).findElement(By.id("connect")).click();
        DriverHelper.getDriver(3).findElement(By.id("connect")).click();

        for (int i =0; i<4; i++){
            new WebDriverWait(DriverHelper.getDriver(i), Duration.ofSeconds(5)).until(elementToBeClickable(DriverHelper.getDriver(i).findElement(By.id("startGame"))));
        }
        System.out.println("IT WORKS");
    }


    @Given("The game has started")
    public void TheGameHasStarted(){
        System.out.println("New Game");
        DriverHelper.getDriver(0).findElement(By.id("startGame")).click();
        //this.gameServerController =
        System.out.print("-----GAME "+ gameServerController.getGame().getStockPile());
    }
    @And("Player {int} hand is {string}")
    public void playerHandIs(int playernum, String hand) {
        gameServerController.getGame().getPlayers()[playernum].setHand(hand);
    }

    @When("Player {int} plays {string}")
    public void playerPlays(int playernum, String card) {
        gameServerController.getGame().playCard(card);

    }

    @Then("The next player is {int}")
    public void theNextPlayerIs(int playernum) {
        int next = gameServerController.getGame().getPlayerTurn();
        assertEquals(playernum,next);

    }


    @And("interface shows that we are going {string}")
    public void interfaceShowsThatWeAreGoing(String direction) {
        String gameDir = gameServerController.getGame().getDirection();
        for (int i = 0; i < 4; i++) {
            //assertEquals(direction, DriverHelper.getDriver(i).textToBe(By.id("direction")));
            //Drive
            //assertTrue(DriverHelper.getDriver(i).textToBe(By.id("direction"), direction));
            assertEquals(direction,DriverHelper.getDriver(i).findElement(By.id("direction")).getText());
        }
    }
}
