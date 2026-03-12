package com.gyanendra.reqres.core;

import io.qameta.allure.Step;
import io.restassured.response.Response;

/**
 * Common reusable assertions.
 *
 * Author: Gyanendra
 */
public final class ResponseValidator {
    private ResponseValidator() {
    }

    @Step("Verify status code = {expected}")
    public static void verifyStatusCode(Response response, int expected) {
        int actual = response.statusCode();
        if (actual != expected) {
            throw new AssertionError("Author: Gyanendra - Status code mismatch. Expected: " + expected + ", Actual: " + actual);
        }
    }

    @Step("Verify field {jsonPath} is not null")
    public static void verifyJsonNotNull(Response response, String jsonPath) {
        Object val = response.jsonPath().get(jsonPath);
        if (val == null) {
            throw new AssertionError("Author: Gyanendra - JSON path null: " + jsonPath);
        }
    }

    @Step("Verify field {jsonPath} equals expected")
    public static void verifyJsonEquals(Response response, String jsonPath, Object expected) {
        Object actual = response.jsonPath().get(jsonPath);
        if (actual == null ? expected != null : !actual.equals(expected)) {
            throw new AssertionError("Author: Gyanendra - JSON mismatch at " + jsonPath + ". Expected: " + expected + ", Actual: " + actual);
        }
    }
}
