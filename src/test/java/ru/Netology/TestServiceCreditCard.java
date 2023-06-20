package ru.Netology;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class TestServiceCreditCard {

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
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));

    }
}
