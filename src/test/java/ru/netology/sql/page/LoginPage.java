package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.HOME;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login'] span.button__text");
    private SelenideElement errorMessageOfLogin = $("[data-test-id='error-notification'] div.notification__content");

    private SelenideElement popUp = $("[data-test-id='popup-notification'] div.notification__content");
    public VerificationPage validAuthInfo(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidAuthInfo(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();

    }

    public void invalidThreefoldPassword(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        passwordField.sendKeys(Keys.SHIFT, HOME, Keys.BACK_SPACE);
        passwordField.setValue(info.getPassword());
        loginButton.click();
        passwordField.sendKeys(Keys.SHIFT, HOME, Keys.BACK_SPACE);
        passwordField.setValue(info.getPassword());
        loginButton.click();

    }

    public void findErrorMessage(String expectedText) {
        errorMessageOfLogin.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

    public void findPopUp(String expectedText) {
        popUp.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

}
