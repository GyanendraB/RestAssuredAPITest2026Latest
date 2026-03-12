package com.gyanendra.reqres.steps;

import com.gyanendra.reqres.core.JsonSchemaValidatorUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

/**
 * Cucumber step definitions for API.
 *
 * Author: Gyanendra
 */
public class ApiSteps {
    private Response response;

    @Given("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        response = RestAssured.get("https://reqres.in/" + endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatus) {
        Assert.assertEquals(response.statusCode(), expectedStatus, "Author: Gyanendra - Status mismatch");
    }

    @Then("the response should match JSON schema {string}")
    public void theResponseShouldMatchJsonSchema(String schemaPath) {
        JsonSchemaValidatorUtil.validateResponseSchema(response, schemaPath);
    }
}
