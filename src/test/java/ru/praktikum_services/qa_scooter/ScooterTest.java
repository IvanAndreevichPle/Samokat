package ru.praktikum_services.qa_scooter;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ScooterTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        String browser = System.getProperty("browser");
        DriverManagerType driverType;

        if (browser != null && browser.equalsIgnoreCase("firefox")) {
            driverType = DriverManagerType.FIREFOX;
        } else if (browser != null && browser.equalsIgnoreCase("ie")) {
            driverType = DriverManagerType.IEXPLORER;
        } else {
            driverType = DriverManagerType.CHROME;
        }

        WebDriverManager.getInstance(driverType).setup();
        driver = WebDriverManager.getInstance(driverType).create();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testScooterWebsite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }
}