package ru.praktikum_services.qa_scooter.parametrized;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum_services.qa_scooter.pages.MainPage;

@RunWith(Parameterized.class)
public class QuestionTest {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(QuestionTest.class);

    public QuestionTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

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

        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    private final String question;
    private final String answer;

    @Parameterized.Parameters(name = "Вопрос: {0}")
    public static Object[][] getQuestions() {
        return new Object[][]{
                {
                        "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                },
                {
                        "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                },
                {
                        "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                },
                {
                        "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                },
                {
                        "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                },
                {
                        "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                },
                {
                        "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                },
                {
                        "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                },
        };
    }

    @Test
    public void FAQListTest() {
        MainPage mainPage = new MainPage(driver);
        logger.info("Шаг 1: Пеходим к 'Вопросы о важном'");
        mainPage.goToFAQ();
        logger.info("Шаг 2: Нажимаем на вопрос: " + question);
        mainPage.openAccordionQuestion(question);
        logger.info("Шаг 3: Получаем ответ и записываем его в переменную reply");
        String reply = mainPage.getAnswer(answer);
        logger.info("Шаг 4: Сравниваем ожидаемые и фактические результаты");
        Assert.assertEquals(answer, reply);
    }

}

