package com.gyanendra.reqres.core;

import com.gyanendra.reqres.config.ConfigManager;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.Map;
import org.hamcrest.Matchers;

/**
 * ApiClient - common HTTP client using Rest Assured.
 *
 * Author: Gyanendra
 */
public class ApiClient {
    private final RequestSpecification baseRequest;
    private final ResponseSpecification baseResponse;

    public ApiClient() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();

        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .addFilter(new AllureRestAssured())
                .setAccept("application/json")
                .setContentType("application/json");

        if (ConfigManager.isLoggingEnabled()) {
            specBuilder.addFilter((req, res, ctx) -> {
                req.log().all();
                Response response = ctx.next(req, res);
                response.then().log().all();
                return response;
            });
        }

        baseRequest = specBuilder.build();

        baseResponse = new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(ConfigManager.getTimeoutMs()))
                .build();
    }

    private RequestSpecification given() {
        return RestAssured.given().spec(baseRequest);
    }

    @Step("GET {endpoint}")
    public Response get(String endpoint, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return given()
                .pathParams(pathParams == null ? Map.of() : pathParams)
                .queryParams(queryParams == null ? Map.of() : queryParams)
                .when()
                .get(endpoint)
                .then()
                .spec(baseResponse)
                .extract()
                .response();
    }

    @Step("POST {endpoint}")
    public Response post(String endpoint, Map<String, ?> pathParams, Object body) {
        return given()
                .pathParams(pathParams == null ? Map.of() : pathParams)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(baseResponse)
                .extract()
                .response();
    }

    @Step("PUT {endpoint}")
    public Response put(String endpoint, Map<String, ?> pathParams, Object body) {
        return given()
                .pathParams(pathParams == null ? Map.of() : pathParams)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(baseResponse)
                .extract()
                .response();
    }

    @Step("PATCH {endpoint}")
    public Response patch(String endpoint, Map<String, ?> pathParams, Object body) {
        return given()
                .pathParams(pathParams == null ? Map.of() : pathParams)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .spec(baseResponse)
                .extract()
                .response();
    }

    @Step("DELETE {endpoint}")
    public Response delete(String endpoint, Map<String, ?> pathParams) {
        return given()
                .pathParams(pathParams == null ? Map.of() : pathParams)
                .when()
                .delete(endpoint)
                .then()
                .spec(baseResponse)
                .extract()
                .response();
    }

    @Step("HEAD {endpoint}")
    public Response head(String endpoint, Map<String, ?> pathParams) {
        return executeNoBody(Method.HEAD, endpoint, pathParams);
    }

    @Step("OPTIONS {endpoint}")
    public Response options(String endpoint, Map<String, ?> pathParams) {
        return executeNoBody(Method.OPTIONS, endpoint, pathParams);
    }

    private Response executeNoBody(Method method, String endpoint, Map<String, ?> pathParams) {
        return given()
                .pathParams(pathParams == null ? Map.of() : pathParams)
                .request(method, endpoint)
                .then()
                .spec(baseResponse)
                .extract()
                .response();
    }
}
