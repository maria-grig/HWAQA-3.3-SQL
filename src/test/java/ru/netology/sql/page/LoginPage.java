package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.sql.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void cleanField() {
        login.doubleClick().sendKeys(Keys.BACK_SPACE);
        password.doubleClick().sendKeys(Keys.BACK_SPACE);

    }

    public void getError() {
        error.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void getBlockError() {
        error.shouldHave(text("Введён неверный пароль 3 раза. Вход заблокирован."))
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }


}
