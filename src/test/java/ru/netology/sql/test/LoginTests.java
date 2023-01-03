package ru.netology.sql.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;
import ru.netology.sql.page.LoginPage;

import static ru.netology.sql.data.SQLHelper.clearDataBase;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {

    @AfterAll
    static void tearDown() {
        clearDataBase();
    }

    @Test
    void shouldLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());

    }

    @Test
    void shouldFailWithIncorrectLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getRandomUser().getLogin(), DataHelper.getAuthInfoWithTestData().getPassword());
        loginPage.validLogin(authInfo);
        loginPage.getError();
    }

    @Test
    void shouldFailWithIncorrectPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authInfo);
        loginPage.getError();
    }

    @Test
    void shouldBlockWhenPasswordFailedThreeTimes() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authInfo);
        loginPage.getError();
        loginPage.cleanField();
        var authInfo1 = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authInfo1);
        loginPage.getError();
        loginPage.cleanField();
        var authInfo2 = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authInfo2);
        loginPage.getBlockError();
    }

    @Test
    void shouldFailWithIncorrectVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = DataHelper.getRandomVerificationCode().getCode();
        verificationPage.validVerify(verificationCode);
        verificationPage.getError();
    }

}