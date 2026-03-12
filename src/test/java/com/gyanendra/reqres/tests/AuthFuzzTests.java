package com.gyanendra.reqres.tests;

import com.gyanendra.reqres.core.Endpoints;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Fuzz tests for Auth API.
 *
 * Author: Gyanendra
 */
@Feature("Auth - Fuzz")
public class AuthFuzzTests extends BaseTest {
    @Test(description = "Fuzz login API with random email/pass")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login fuzz")
    public void fuzzLogin() {
        String email = FuzzData.randomEmail();
        String password = FuzzData.randomString();

        Map<String, Object> body = Map.of(
                "email", email,
                "password", password
        );

        Response response = apiClient.post(Endpoints.LOGIN.getPath(), null, body);

        int status = response.statusCode();
        Assert.assertTrue(status == 400 || status == 500,
                "Author: Gyanendra - Expected error for fuzz login, got " + status);
    }
}
