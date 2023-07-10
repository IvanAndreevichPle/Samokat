package ru.praktikum_services.qa_scooter.parametrized;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.praktikum_services.qa_scooter.pages.MainPage;
import ru.praktikum_services.qa_scooter.pages.OrderScooter;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class OrderTest {
    private static WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String telephone;
    private final String date;
    private final String comment;
    private final By by;
    // Кнопка "Заказать"
    private static final By topOrderButton = By.className("Button_Button__ra12g");
    private static final By middleOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button");

    private static final Logger logger = LogManager.getLogger(QuestionTest.class);


    public OrderTest(By by, String name, String surname, String address, String telephone, String date, String comment) {
        this.by = by;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.date = date;
        this.comment = comment;
    }

    @Before
    public void setUp() {
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

        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters (name = "Тест кнопки заказать {index}")
    public static Object[][] getRegistrationOnOrderPage() {
        return new Object[][]{
                {topOrderButton, "Иван", "Иванов", "г.Москва ул. Дворовая д.51", "+79062878999", "01.09.2023", "Самокат - пушка"},
                {middleOrderButton, "Прохор", "Лапин", "г.Москва ул. Нагатина д.101", "+79812151421", "01.10.2023", "Самока- улитка"},
        };
    }

    @Test
    public void registrationOnOrderPageTest() {
        MainPage mainPage = new MainPage(driver);
        logger.info("Шаг 1: Нажимаем кнопку 'Заказать'");
        mainPage.clickOrderButton(by);

        OrderScooter orderScooter = new OrderScooter(driver);
        logger.info("Шаг 2: Заполнить поле 'Имя'");
        orderScooter.setName(name);
        logger.info("Шаг 3: Заполнить поле 'Фамилия'");
        orderScooter.setSurname(surname);
        logger.info("Шаг 4: Заполнить поле 'Адрес'");
        orderScooter.setAddress(address);
        logger.info("Шаг 5: Заполнить поле 'Метро'");
        orderScooter.setSubwayStation();
        logger.info("Шаг 6: Заполнить поле 'Телефон'");
        orderScooter.setTelephone(telephone);
        logger.info("Шаг 7: Нажать кнопку 'Далее'");
        orderScooter.clickNextButton();

        logger.info("Шаг 8: Заполнить поле 'Дата'");
        orderScooter.setDate(date);
        logger.info("Шаг 9: Заполнить поле 'Период'");
        orderScooter.setRentalPeriod();
        logger.info("Шаг 10: Выбрать чек-бокс 'Цвет'");
        orderScooter.setColorGrey();
        logger.info("Шаг 11: Заполнить поле 'Комментарий'");
        orderScooter.setComment(comment);
        logger.info("Шаг 12: Нажать кнопку 'Заказать'");
        orderScooter.clickOrderButton();
        logger.info("Шаг 13: Нажать кнопку 'Да'");
        orderScooter.clickYesButton();
        logger.info("Шаг 14: Проверить появивишийся текст об оформлении заказа");
        String textPopUp = orderScooter.receivePopUpWithSuccessfulOrder();
        MatcherAssert.assertThat("Нет поп-ап окна с сообщением об успешном оформлении заказа", textPopUp, startsWith("Заказ оформлен"));
    }
}
