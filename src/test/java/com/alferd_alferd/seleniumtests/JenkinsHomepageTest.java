package com.alferd_alferd.seleniumtests;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import io.github.bonigarcia.wdm.WebDriverManager;

class JenkinsHomepageTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
    }
/*
    @Test
    void titleTest() {
        driver.get("https://www.jenkins.io/");
        Assertions.assertEquals("Jenkins", driver.getTitle());
    }

    @Test
    void screenshotTest() throws IOException {
        File tgtFile = new File("./src/main/resources/sample.png");
        try {
            tgtFile.delete();
        } finally {}

        driver.get("http://www.unimelb.edu.au");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, tgtFile);
    }
*/
    @Test
    void clickTest() throws IOException {
        File tgtFile = new File("./src/main/resources/sample.png");
        try {
            tgtFile.delete();
        } finally {}

        driver.get("https://www.marvel.com/whichavengerareyou/en_US/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(600));

        Actions builder = new Actions(driver);
        WebElement quizButton = driver.findElement(By.xpath("//body//div[@class='main_container']//div[@id='app_container']//div[@class='container']//div[@class='landing_container content_container']//div[@class='quiz-btn']//a[@class='start_button']"));
        builder.moveToElement(quizButton).build().perform();
        quizButton.click();
        System.out.println("**--- Start quiz ---**");

        for (int question = 1; question < 15; question++) {
            String progressPath = "//body//div[@class='main_container quiz_mode']//div[@id='app_container']//div[@class='container']//div[@class='question_container content_container']//div[@class='progress']//ul[@class='center_inline']//li[@class='progress_" + Integer.toString(question) + " active current_q']";
            boolean isProgressVisible = driver.findElement(By.xpath(progressPath)).isDisplayed();
            String xPathExp = "//body//div[@class='main_container quiz_mode']//div[@id='app_container']//div[@class='container']//div[@class='question_container content_container']//div[@class='text_text']//div[@class='answers_amount answers text amount4']//ul[@class='answers_holder']//li[@class='answer_template answer_widget answer0']//a[@class='answer_button']";
            WebElement answerButton = driver.findElement(By.xpath(xPathExp));
            answerButton.click();
            System.out.println("**--- Question: " + question + " ---**");
        } 

        boolean isCharVisible = driver.findElement(By.xpath("//a[@id='char_main' and @style='opacity: 1;']")).isDisplayed();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, tgtFile);
        System.out.println("**--- Generate test image ---**");

        File origImage = new File("./src/main/resources/expected.png");

        float percentage = 0;
        BufferedImage biOrig = ImageIO.read(origImage); // reads fileA into bufferedImage
        DataBuffer dbOrig = biOrig.getData().getDataBuffer();
        int sizeOrig = dbOrig.getSize();
        BufferedImage biTgt = ImageIO.read(tgtFile); // reads fileA into bufferedImage
        DataBuffer dbTgt = biTgt.getData().getDataBuffer();
        int sizeTgt = dbTgt.getSize();
        int count = 0;
        // compare data-buffer objects //
        if (sizeOrig == sizeTgt) { // checks the size of the both the bufferedImage
    
            for (int i = 0; i < sizeOrig; i++) {
    
                if (dbOrig.getElem(i) == dbTgt.getElem(i)) { // checks bufferedImage array is same in both the image
                    count = count + 1;
                }
            }
            percentage = (count * 100) / sizeOrig; // calculates matching percentage
        } else {
            System.out.println("Both the images are not of same size");
        }
        System.out.println(percentage);
    }
/* 
    @Test
    void clickTest() throws IOException {
        File tgtFile = new File("./src/main/resources/sample.png");
        try {
            tgtFile.delete();
        } finally {}

        driver.get("https://www.marvel.com/whichavengerareyou/en_US/");
        driver.manage().window().maximize();
        
        WebElement quizButton = new WebDriverWait(driver, Duration.ofSeconds(3))
          .until(ExpectedConditions.elementToBeClickable(By.xpath("//body//div[@class='main_container']//div[@id='app_container']//div[@class='container']//div[@class='landing_container content_container']//div[@class='quiz-btn']//a[@class='start_button']")));
        quizButton.click();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Thread.sleep(10000);
        System.out.println("**--- Start quiz ---**");


        for (int question = 1; question < 15; question++) {
            String xPathExp = "//body//div[@class='main_container quiz_mode']//div[@id='app_container']//div[@class='container']//div[@class='question_container content_container']//div[@class='progress']//ul[@class='center_inline']//li[@class='progress_" + Integer.toString(question) + " active current_q']";
            WebElement progressImage = new WebDriverWait(driver, Duration.ofSeconds(3))
              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathExp)));
            WebElement answerButton = new WebDriverWait(driver, Duration.ofSeconds(3))
              .until(ExpectedConditions.elementToBeClickable(By.xpath("//body//div[@class='main_container quiz_mode']//div[@id='app_container']//div[@class='container']//div[@class='question_container content_container']//div[@class='text_text']//div[@class='answers_amount answers text amount4']//ul[@class='answers_holder']//li[@class='answer_template answer_widget answer0']//a[@class='answer_button']")));
            answerButton.click();
            System.out.println("**--- Question: " + Integer.toString(question) + " ---**");
        } 

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, tgtFile);
    }
*/
    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}