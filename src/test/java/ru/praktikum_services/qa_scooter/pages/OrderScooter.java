package ru.praktikum_services.qa_scooter.pages;

import org.openqa.selenium.*;

public class OrderScooter {
    private final WebDriver driver;

    public OrderScooter(WebDriver driver) {
        this.driver = driver;
    }

    // Поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро"
    private final By subwayStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    // Станция метро (1..237)
    private final By subwayStation = By.xpath(".//div[@class='select-search__select']/ul/li/button[@value='105']");
    // Поле "Телефон"
    private final By telephoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    // Кнопка "Да"
    private final By yesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//div[@class='Order_Buttons__1xGrp']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Поле "Когда привезти самокат"
    private final By dateForScooterField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле "Срок аренды"
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Поле "Комментарий"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать" (внизу экрана)
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // Поп-ап с уведомлением об успешном оформлении
    private final By PopUpWithSuccessfulOrder = By.className("Order_ModalHeader__3FDaJ");
    public static final By topOrderButton = By.className("Button_Button__ra12g");
    public static final By middleOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button");

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setSubwayStation() {
        driver.findElement(subwayStationField).click();
        WebElement element = driver.findElement(subwayStation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(subwayStation).click();
    }

    public void setTelephone(String telephone) {
        driver.findElement(telephoneField).sendKeys(telephone);
    }

    public void clickNextButton() {
        WebElement element = driver.findElement(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(nextButton).click();
    }

    public void clickYesButton() {
        WebElement element = driver.findElement(yesButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(yesButton).click();
    }

    public void setDate(String date) {
        driver.findElement(dateForScooterField).sendKeys(date);
        driver.findElement(dateForScooterField).sendKeys(Keys.ENTER);
    }

    public void setRentalPeriod(String period) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']")).click();
    }

    public void setColour(String colour) {
        driver.findElement(By.xpath("//label[@class='Checkbox_Label__3wxSf'][text() = '" + colour + "']")).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public String receivePopUpWithSuccessfulOrder() {
        return driver.findElement(PopUpWithSuccessfulOrder).getText();
    }
}
