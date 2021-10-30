package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form_theme_alfa-on-white");
        form.$("[type=text]").setValue("Татьяна Кислицина");
        form.$("[type=tel]").setValue("+79220000000");
        form.$("[class=checkbox__box]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitRequestWrongName() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form_theme_alfa-on-white");
        form.$("[type=text]").setValue("Tatiana Kislitcina");
        form.$("[type=tel]").setValue("+79220000000");
        form.$("[class=checkbox__box]").click();
        form.$("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestWrongPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form_theme_alfa-on-white");
        form.$("[type=text]").setValue("Татьяна Кислицина");
        form.$("[type=tel]").setValue("89220000000");
        form.$("[class=checkbox__box]").click();
        form.$("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestWrongCheck() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form_theme_alfa-on-white");
        form.$("[type=text]").setValue("Татьяна Кислицина");
        form.$("[type=tel]").setValue("+79220000000");
        form.$("[type=button]").click();
        $(".checkbox_theme_alfa-on-white").shouldHave(Condition.cssClass("input_invalid"));
    }

    @Test
    void shouldSubmitRequestWithoutName() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form_theme_alfa-on-white");
        form.$("[type=tel]").setValue("+79220000000");
        form.$("[class=checkbox__box]").click();
        form.$("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestWithoutPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form.form_theme_alfa-on-white");
        form.$("[type=text]").setValue("Татьяна Кислицина");
        form.$("[class=checkbox__box]").click();
        form.$("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}

