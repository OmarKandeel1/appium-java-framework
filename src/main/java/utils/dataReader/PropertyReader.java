package utils.dataReader;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader  {

    private static Properties properties;

    private PropertyReader() {
        // prevent instantiation
    }

    private static void load() {
        if (properties == null) {
            properties = new Properties();
            String path = "src/main/java/resources/configs/config.properties";
            try (FileInputStream fis = new FileInputStream(path)) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties at " + path, e);
            }
        }
    }

    public static String get(String key) {
        load();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }
        return value;
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}