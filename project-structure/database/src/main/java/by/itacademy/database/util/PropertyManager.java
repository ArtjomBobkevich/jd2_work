package by.itacademy.database.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyManager {

    private static final Properties PROPERTIES = new Properties();


    static {
        loadApplicationProperties();
    }

    @SneakyThrows
    private static void loadApplicationProperties(){
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } finally {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
