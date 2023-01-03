package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement codeButton = $("[data-test-id='action-verify'] span.button__text");
    private SelenideElement errorMessageOfVerification = $("[data-test-id='error-notification'] div.notification__content");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationInfo verificationInfo) {
        codeField.setValue(verificationInfo.getCode());
        codeButton.click();
        return new DashboardPage();
    }

    public void invalidVerify(DataHelper.VerificationInfo verificationInfo) {
        codeField.setValue(verificationInfo.getCode());
        codeButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessageOfVerification.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }
}
