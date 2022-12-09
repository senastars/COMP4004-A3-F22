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

import java.time.Duration;

import static io.netty.util.ResourceLeakDetector.isEnabled;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
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

    @When("Player {int} plays {string}")
    public void playerPlays(int playernum, String card) {
        System.out.println("test");

    }

    @Then("The next player is {int}")
    public void theNextPlayerIs(int arg0) {
        System.out.println("test");
    }

    @And("interface switches direction")
    public void interfaceSwitchesDirection() {
        System.out.println("test");
    }


}
