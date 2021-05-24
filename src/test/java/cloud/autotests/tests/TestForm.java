package cloud.autotests.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;

public class TestForm extends TestBase {

    String site = "https://sberhealth.ru/",
            apteka = "https://www.eapteka.ru/",
            doctor = "Кардиолог",
            metro = "Курская";


    @Test
    @Severity(NORMAL)
    @Feature("SberHealh")
    @DisplayName("Проверяем что сайт открывается")
    void openSite() {

        step("Открываем сайт", () -> {
            open(site);
        });

        step("Проверка что сайт открылся", () ->

        {
            $(".help-to-get-better__title").shouldHave(text("Помогаем быть здоровыми"));
        });
    }

    @Test
    @DisplayName("Проверяем QR коды")
    void qrCode() {

        step("Открываем сайт", () -> {
            open(site);
        });

        step("Проверка QR кода для скачивания приложения", () ->

        {
            $(".download-mobile-app__qr-code").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Поиск врача")
    void docSearch() {
        step("Открываем сайт", () -> {
            open(site);
        });

        step("Проверка поиска врачей", () ->
        {
            $(".med-service").$(byText("Выбрать врача")).click();
            switchTo().window(1);
            $(".doctors-list-page-search-form").shouldHave(text("Запишитесь на приём к врачу онлайн"));
            $(byText("Врач, клиника, болезнь, услуга")).click();
            $(".v-autocomplete").find(byText(doctor)).click();
            $(byText("Метро, город МО")).click();
            $(".v-autocomplete-list").find(byText(metro)).click();
            $(".doctor-list-page").$(byText("Найти")).click();
            $(".doctor__near-metro-title").shouldHave(text("Врачи на м. " + metro));
        });
    }

    @Test
    @DisplayName("Проверка работы консультации ветеринара")
    void openApteka() {
        step("Открываем сайт", () -> {
            open(site);
        });
        step("Открываем раздел ветеринария", () -> {
            $(".med-service").$(byText("Ветеринария")).click();
        });

        step("Проверяем что кнопка заказа доступна", () -> {
            switchTo().window(1);
            $(".p11mj2ls__20210521131759").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверка скрола")
    void checkFind() {
        step("Открываем сайт", () -> {
            open(site);
        });

        step("Проверяем скролл", () -> {
            $(".telemed-gift-banner__title").scrollTo();
        });
    }

}