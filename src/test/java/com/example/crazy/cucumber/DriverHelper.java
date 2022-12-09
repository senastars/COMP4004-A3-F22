package com.example.crazy.cucumber;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper {

    private static DriverHelper helperHelper;
    private static WebDriver driver0;
    private static WebDriver driver1;
    private static WebDriver driver2;
    private static WebDriver driver3;
    private static WebDriverWait wait;
    public final static int TIMEOUT = 10;

    private DriverHelper() {
        WebDriverManager.chromedriver().setup();
        driver0 = new ChromeDriver();
        wait = new WebDriverWait(driver0, Duration.ofSeconds(TIMEOUT));
        driver0.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver0.manage().window().maximize();

        driver1 = new ChromeDriver();
        wait = new WebDriverWait(driver0, Duration.ofSeconds(TIMEOUT));
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver1.manage().window().maximize();

        driver2 = new ChromeDriver();
        wait = new WebDriverWait(driver2, Duration.ofSeconds(TIMEOUT));
        driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver2.manage().window().maximize();

        driver3 = new ChromeDriver();
        wait = new WebDriverWait(driver3, Duration.ofSeconds(TIMEOUT));
        driver3.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver3.manage().window().maximize();
    }

    public static void openPage(int player, String url) {
        if(player == 0){
            driver0.navigate().to(url);
        }
        else if(player == 1){
            driver1.navigate().to(url);
        }
        else if(player == 2){
            driver2.navigate().to(url);
        }
        else{
            driver3.navigate().to(url);
        }


    }

    public static WebDriver getDriver(int player) {
        if(player == 0){
            return driver0;
        }
        else if(player == 1){
            return driver1;
        }
        else if(player == 2){
            return driver2;
        }
        else{
            return driver3;
        }
    }

    public static void setUpDriver() {
        if (helperHelper == null) {
            helperHelper = new DriverHelper();
        }
    }

    public static void tearDown() {
        if (driver0 != null) {
            driver0.close();
            driver0.quit();
        }
        if (driver1 != null) {
            driver1.close();
            driver1.quit();
        }
        if (driver2 != null) {
            driver2.close();
            driver2.quit();
        }
        if (driver3 != null) {
            driver3.close();
            driver3.quit();
        }
        helperHelper = null;
    }
}
