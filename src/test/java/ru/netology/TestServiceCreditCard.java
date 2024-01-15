package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TestServiceCreditCard {

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldRegistrationHappyTest() {
        $("[data-test-id='city'] input ").setValue("Краснодар");
        $("[data-test-id=‘date’] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(generateDate(7, "dd.MM.yyyy"));
        $("[data-test-id='name'] input ").setValue("Иванов Иван");
        $("[data-test-id='phone'] input ").setValue("+79998885555");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(7, "dd.MM.yyyy")));
    }

    @Test
    void shouldChoiseFirstCity() {
        $("[data-test-id='city'] input").setValue("Красно");
        $$(".menu-item__control").first().click();
        $("[data-test-id=‘date’] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input ").setValue(generateDate(7, "dd.MM.yyyy"));
        $("[data-test-id='name'] input ").setValue("Иванов Иван");
        $("[data-test-id='phone'] input ").setValue("+79998885555");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(7, "dd.MM.yyyy")));
    }

    @Test
    void shouldChoiseFirstCity1() {
        $("[data-test-id='city'] input").setValue("Красно");
        $$(".menu-item__control").first().click();
        $("[data-test-id='date'] input ").click();
        $(".calendar__layout").shouldBe(visible);
        $(".calendar__layout .calendar__day").click();
        $("[data-test-id='name'] input ").setValue("Иванов Иван");
        $("[data-test-id='phone'] input ").setValue("+79998885555");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(7, "dd.MM.yyyy")));
    }
}
