package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTests {

    @Test
    void shouldCheckValidData() {
        String city = "Москва";
        String planningDate = generateDate(5);
        String fullName = "Иванов Сергей Петрович";
        String number = "+79996665544";
        open("http://localhost:9999/");

        $("[data-test-id = 'city'] input").sendKeys(city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = 'date'] input").sendKeys(planningDate);
        $("[data-test-id = 'name'] input").sendKeys(fullName);
        $("[data-test-id = 'phone'] input").sendKeys(number);
        $("[data-test-id = 'agreement'] .checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id = 'notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
