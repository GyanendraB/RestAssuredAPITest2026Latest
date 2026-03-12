package com.gyanendra.reqres.core;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

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
        Assert.assertEquals(response.statusCode(), expected, "Author: Gyanendra - Status code mismatch");
    }

    @Step("Verify field {jsonPath} is not null")
    public static void verifyJsonNotNull(Response response, String jsonPath) {
        Object val = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(val, "Author: Gyanendra - JSON path null: " + jsonPath);
    }

    @Step("Verify field {jsonPath} equals expected")
    public static void verifyJsonEquals(Response response, String jsonPath, Object expected) {
        Object actual = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actual, expected, "Author: Gyanendra - JSON mismatch at " + jsonPath);
    }
}
