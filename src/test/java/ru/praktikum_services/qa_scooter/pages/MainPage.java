package ru.praktikum_services.qa_scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    // Заголовок "Вопросы о важном"
    private final By frequentlyAskedQuestionsHeader = By.xpath("//div[@class='Home_FourPart__1uthg']//div[@class='Home_SubHeader__zwi_E']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToFAQ() {
        WebElement faqHeader = driver.findElement(frequentlyAskedQuestionsHeader);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", faqHeader);
    }

    public void openAccordionQuestion(String question) {
        driver.findElement(By.xpath("//div[@class='accordion__heading']//div[text()='" + question + "']")).click();
    }

    public String getAnswer(String answer) {
        final Wait<WebDriver> wait = new FluentWait<>(driver).withMessage("Элемент не найден").withTimeout(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='accordion__panel']//p[text()='" + answer + "']"))));
        return driver.findElement(By.xpath("//div[@class='accordion__panel']//p[text()='" + answer + "']")).getText();
    }

    public void clickOrderButton(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(by).click();
    }
}
