package ru.netology.sql.data;


import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker = new Faker(Locale.forLanguageTag("ru"));

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("1", "vasya", "qwerty123", "active");
    }

    public static AuthInfo getValidOtherAuthInfo() {
        return new AuthInfo("1", "petya", "123qwerty", "active");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("1", "vasya", faker.internet().password(), "active");
    }


    public static VerificationInfo generateFakerCode() {
        return new VerificationInfo(Integer.toString(faker.number().randomDigit()));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthInfo {
        private String id;
        private String login;
        private String password;
        private String status;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VerificationInfo {
        private String code;
    }

}


