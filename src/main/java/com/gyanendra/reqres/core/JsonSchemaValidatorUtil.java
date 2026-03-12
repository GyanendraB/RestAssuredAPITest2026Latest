package com.gyanendra.reqres.core;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * JSON Schema validation utility.
 *
 * Author: Gyanendra
 */
public final class JsonSchemaValidatorUtil {
    private JsonSchemaValidatorUtil() {
    }

    @Step("Validate JSON schema: {schemaPath}")
    public static void validateResponseSchema(Response response, String schemaPath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
