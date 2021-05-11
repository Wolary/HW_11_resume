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

    @Test
    @Severity(NORMAL)
    @Feature("SberHealh")
    @DisplayName("Проверка поиска и магазина")
    void fillForm() {
        String  site = "https://sberhealth.ru/",
                doctor = "Кардиолог",
                metro = "Курская";

        step("Открываем сайт", () -> {
            open(site);
        });

        step("Проверка что сайт открылся", () -> {
            $(".help-to-get-better__title").shouldHave(text("Помогаем быть здоровыми"));
        });

        step("Проверка QR кода для скачивания приложения", () -> {
            $(".download-mobile-app__qr-code").shouldBe(visible);
        });

        step("Проверка поиска врачей", () -> {
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

//        step("Проверка работы онлайн магазина", () -> {
//            switchTo().window(0);
//            $(".med-service__container").find(byText("Онлайн-аптека")).click();
//            switchTo().window(2);
// почему то на удаленном драйвере не работает сайт eapteka.ru, жаль.
//            $(byAttribute("href", "/goods/zootovary/")).click();
//            $(".filter__widget-inner").$(byText("Зоэтис")).click();
//            $(byText("Бренды: Зоэтис")).shouldBe(visible);
//            $(".cc-item--group").find(byText("Купить")).click();
//            $(byAttribute("href", "/personal/cart/")).click();
//            $(byAttribute("href", "/personal/order/make/")).shouldBe(visible);
//        });

    }

}
