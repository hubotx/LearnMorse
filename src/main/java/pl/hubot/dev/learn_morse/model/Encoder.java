package pl.hubot.dev.learn_morse.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class Encoder {
    public final String encode(final String input)
            throws IOException,
            NoSuchFieldException {
        StringBuilder encoded = new StringBuilder();
        String lowerCaseInput = input.toLowerCase();
        for (int i = 0; i < lowerCaseInput.length(); i++) {
            char current = lowerCaseInput.charAt(i);
            encoded.append(getMorseCode().getOrDefault(
                    Character.toString(current), " "));
            encoded.append("   ");
        }
        return encoded.toString();
    }

    public final String decode(final String input)
            throws IOException,
            NoSuchFieldException {
        StringBuilder decoded = new StringBuilder();
        String lowerCaseInput = input.toLowerCase();
        for (String current : lowerCaseInput.split(" ")) {
            String key = getKeyByValue(getMorseCode(), current);
            if (key != null) {
                decoded.append(key);
            }
            decoded.append(" ");
        }
        return decoded.toString();
    }

    final Map<String, String> getMorseCode()
            throws IOException,
            NoSuchFieldException {
        Class<?> aClass = Encoder.class;
        ClassLoader classLoader = aClass.getClassLoader();
        String filename = "morse_code.properties";
        try (InputStream input = classLoader
                .getResourceAsStream(filename)) {
            if (input == null) {
                throw new FileNotFoundException(
                        "Sorry, unable to find "
                                + filename);
            }

            Properties properties = new Properties();
            properties.load(input);

            Map<String, String> translations = new HashMap<>();
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                translations.put(key, value);
            }

            return translations;
        }
    }

    private <T, E> T getKeyByValue(final Map<T, E> map, final E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
