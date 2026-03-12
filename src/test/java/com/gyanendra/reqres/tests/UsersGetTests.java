package com.gyanendra.reqres.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyanendra.reqres.core.Endpoints;
import com.gyanendra.reqres.core.JsonSchemaValidatorUtil;
import com.gyanendra.reqres.core.ResponseValidator;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Users GET tests.
 *
 * Author: Gyanendra
 */
@Feature("Users - GET")
public class UsersGetTests extends BaseTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test(description = "List users page=2 and validate schema")
    @Severity(SeverityLevel.CRITICAL)
    @Story("List users - positive")
    public void listUsersPage2ShouldValidateSchema() {
        Response response = apiClient.get(Endpoints.LIST_USERS.getPath(), null, Map.of("page", 2));

        ResponseValidator.verifyStatusCode(response, 200);
        JsonSchemaValidatorUtil.validateResponseSchema(response, "schemas/users-list-schema.json");

        int page = response.jsonPath().getInt("page");
        Assert.assertEquals(page, 2, "Author: Gyanendra - Page mismatch");

        Assert.assertNotNull(mapper, "Author: Gyanendra - Mapper should be initialized");
    }
}
