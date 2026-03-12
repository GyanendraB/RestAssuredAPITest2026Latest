package com.gyanendra.reqres.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Extent report manager.
 *
 * Author: Gyanendra
 */
public final class ExtentManager {
    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
