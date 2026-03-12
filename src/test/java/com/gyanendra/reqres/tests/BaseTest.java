package com.gyanendra.reqres.tests;

import com.gyanendra.reqres.core.ApiClient;
import io.qameta.allure.Epic;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * BaseTest - common setup/teardown.
 *
 * Author: Gyanendra
 */
@Epic("ReqRes API Automation - Gyanendra")
public abstract class BaseTest {
    protected ApiClient apiClient;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        apiClient = new ApiClient();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        apiClient = null;
    }
}
