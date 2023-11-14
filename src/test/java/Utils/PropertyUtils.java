package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

        public static Properties propertyLoader(String filePath) {
            Properties properties = new Properties();

            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return properties;
        }
}
