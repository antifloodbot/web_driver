package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE:
                properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
                break;
            case PRODUCTION:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalArgumentException("Unsupported environment: " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String property = properties.getProperty("baseUrl");
        if (property != null) return property;
        else throw new RuntimeException("baseUrl is not specified in the stg_config.properties file");
    }

    public String getUsername() {
        String property = properties.getProperty("username");
        if (property != null) return property;
        else throw new RuntimeException("username is not specified in the stg_config.properties file");
    }

    public String getPassword() {
        String property = properties.getProperty("password");
        if (property != null) return property;
        else throw new RuntimeException("password is not specified in the stg_config.properties file");
    }
}
