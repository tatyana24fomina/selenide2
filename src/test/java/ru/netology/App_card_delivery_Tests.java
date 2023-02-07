package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class App_card_delivery_Tests {

    @Test
    void shouldCheckValidData() throws InterruptedException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 3);
        String city = "Москва";
        String date = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "." + String.valueOf(calendar.get(Calendar.MONTH)) + "." + String.valueOf(Calendar.YEAR);
        String fullName = "Иванов Сергей Петрович";
        String number = "+79996665544";
        open("http://localhost:9999/");

        $("[data-test-id = 'city'] input").sendKeys(city);
        $("[data-test-id = 'date'] > span > span > span > span > input").sendKeys(date);
        $("[data-test-id = 'name'] > span > span > input").sendKeys(fullName);
        $("[data-test-id = 'phone'] > span > span > input").sendKeys(number);
        $("[data-test-id = 'agreement'] > span").click();
        $$("button").find(exactText("Забронировать")).click();

        Thread.sleep(15000);
        $(withText("Успешно")).shouldBe(Condition.visible);
    }
}
