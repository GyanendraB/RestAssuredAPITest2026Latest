package com.gyanendra.reqres.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager - environment based configuration loader.
 *
 * Author: Gyanendra
 */
public final class ConfigManager {
    private static final Properties PROPERTIES = new Properties();

    static {
        String env = System.getProperty("env", "dev");
        String path = "/env/" + env + "/config.properties";
        try (InputStream inputStream = ConfigManager.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new RuntimeException("Config file not found for env: " + env);
            }
            PROPERTIES.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties - Author: Gyanendra", e);
        }
    }

    private ConfigManager() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static long getTimeoutMs() {
        return Long.parseLong(get("timeout.ms"));
    }

    public static boolean isLoggingEnabled() {
        return Boolean.parseBoolean(get("log.all"));
    }
}
