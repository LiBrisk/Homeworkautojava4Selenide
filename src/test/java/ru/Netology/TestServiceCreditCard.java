package ru.Netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;




public class TestServiceCreditCard {

    private WebDriver driver;
    ChromeOptions options;



    @BeforeAll
    static void SetUpAll() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    void clearCache() {
        System.gc();
    }
    @Test
    void ShouldRegistrationHappyTest(){
        open("http://localhost:9999/");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input ").setValue("Краснодар");
        $("[data-test-id='date'] input ").setValue(date);
        $("[data-test-id='name'] input ").setValue("Иванов Иван");
        $("[data-test-id='phone'] input ").setValue("+79998885555");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date));

    }
    @Test
    void ShouldChoiseFirstCity() throws InterruptedException {
        open("http://localhost:9999/");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Красно");
        Thread.sleep(3000);
        $$(".menu-item__control").first().click();
        $("[data-test-id='date'] input ").setValue(date);
        $("[data-test-id='name'] input ").setValue("Иванов Иван");
        $("[data-test-id='phone'] input ").setValue("+79998885555");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date));

    }
    @Test
    void ShouldChoiseFirstCity1() throws InterruptedException {
        open("http://localhost:9999/");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Красно");
        Thread.sleep(3000);
        $$(".menu-item__control").first().click();
        $("[data-test-id='date'] input ").click();
        $(".calendar__layout").shouldBe(visible);
        $(".calendar__layout .calendar__day").click();
        $("[data-test-id='name'] input ").setValue("Иванов Иван");
        $("[data-test-id='phone'] input ").setValue("+79998885555");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date));

    }
}
