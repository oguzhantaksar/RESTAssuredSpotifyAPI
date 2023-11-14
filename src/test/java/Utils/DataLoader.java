package Utils;

import java.util.Properties;

public class DataLoader {

    private static DataLoader instance;

    private final Properties properties;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if (instance == null) {
            instance = new DataLoader();
        }
        return instance;
    }

    public String getPlaylistIdGet() {
        String prop = properties.getProperty("playlist_id_get");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }

    public String getPlaylistIdUpdate() {
        String prop = properties.getProperty("playlist_id_update");
        if(prop != null) return prop;
        else throw new RuntimeException("It is not specified");
    }


}
