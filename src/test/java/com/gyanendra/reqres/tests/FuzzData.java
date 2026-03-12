package com.gyanendra.reqres.tests;

import com.github.javafaker.Faker;

/**
 * Fuzz data generator.
 *
 * Author: Gyanendra
 */
public final class FuzzData {
    private static final Faker FAKER = new Faker();

    private FuzzData() {
    }

    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String randomString() {
        return FAKER.lorem().characters(30);
    }

    public static int randomId() {
        return FAKER.number().numberBetween(1000, 99999);
    }
}
