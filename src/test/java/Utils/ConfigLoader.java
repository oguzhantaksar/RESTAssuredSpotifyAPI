package Utils;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader instance;

    private final Properties properties;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }

    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }

    public String getCliendId() {
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }

    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }

    public String getUserId() {
        String prop = properties.getProperty("user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }


}
