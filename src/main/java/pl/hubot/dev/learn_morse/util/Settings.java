package pl.hubot.dev.learn_morse.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public final class Settings {
    private static final Settings INSTANCE = new Settings();
    private static final Properties PROPERTIES = new Properties();
    private int pauseBeforeKeying;
    private int keyingSpeed;
    private int dotLength;
    private int dashLength;
    private int lengthOfSpaceBetweenCharacters;
    private int lengthOfSpaceBetweenWords;
    private int lengthOfSpaceBetweenCharElements;
    private int frequency;
    private float volume;
    private String pool;
    private int blocksStringsToSend;
    private int blocksLengthOfStrings;
    private int kochCharsToSend;

    public static Settings getInstance()
            throws IOException,
            NoSuchFieldException,
            IllegalAccessException {
        Class<?> aClass = INSTANCE.getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        String filename = "app.properties";
        try (InputStream input = classLoader
                .getResourceAsStream(filename)) {
            if (input == null) {
                throw new FileNotFoundException(
                        "Sorry, unable to find " + filename);
            }

            // load a properties file from class path,
            // inside public static  method
            PROPERTIES.load(input);

            for (String key : PROPERTIES.stringPropertyNames()) {
                String value = PROPERTIES.getProperty(key);
                Field field = aClass.getDeclaredField(key);
                Class<?> fieldType = field.getType();
                if (fieldType.equals(Integer.TYPE)) {
                    field.setInt(INSTANCE,
                            Integer.parseInt(value));
                } else if (fieldType.equals(Float.TYPE)) {
                    field.setFloat(INSTANCE,
                            Float.parseFloat(value));
                } else {
                    field.set(INSTANCE, value);
                }
            }

            return INSTANCE;
        }
    }

    private Settings() {
    }

    public void apply() throws IllegalAccessException {
        Class<?> aClass = INSTANCE.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            if (!field.getName().equals("INSTANCE")
                    && !field.getName().equals("PROPERTIES")) {
                PROPERTIES.setProperty(field.getName(),
                        String.valueOf(field.get(INSTANCE)));
            }
        }
    }

    public int getPauseBeforeKeying() {
        return pauseBeforeKeying;
    }

    public void setPauseBeforeKeying(final int pauseBeforeKeying) {
        this.pauseBeforeKeying = pauseBeforeKeying;
    }

    public int getKeyingSpeed() {
        return keyingSpeed;
    }

    public void setKeyingSpeed(final int keyingSpeed) {
        this.keyingSpeed = keyingSpeed;
    }

    public int getDotLength() {
        return dotLength;
    }

    public void setDotLength(final int dotLength) {
        this.dotLength = dotLength;
    }

    public int getDashLength() {
        return dashLength;
    }

    public void setDashLength(final int dashLength) {
        this.dashLength = dashLength;
    }

    public int getLengthOfSpaceBetweenCharacters() {
        return lengthOfSpaceBetweenCharacters;
    }

    public void setLengthOfSpaceBetweenCharacters(
            final int lengthOfSpaceBetweenCharacters) {
        this.lengthOfSpaceBetweenCharacters
                = lengthOfSpaceBetweenCharacters;
    }

    public int getLengthOfSpaceBetweenWords() {
        return lengthOfSpaceBetweenWords;
    }

    public void setLengthOfSpaceBetweenWords(
            final int lengthOfSpaceBetweenWords) {
        this.lengthOfSpaceBetweenWords = lengthOfSpaceBetweenWords;
    }

    public int getLengthOfSpaceBetweenCharElements() {
        return lengthOfSpaceBetweenCharElements;
    }

    public void setLengthOfSpaceBetweenCharElements(
            final int lengthOfSpaceBetweenCharElements) {
        this.lengthOfSpaceBetweenCharElements
                = lengthOfSpaceBetweenCharElements;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(final int frequency) {
        this.frequency = frequency;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(final float volume) {
        this.volume = volume;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(final String pool) {
        this.pool = pool;
    }

    public int getBlocksStringsToSend() {
        return blocksStringsToSend;
    }

    public void setBlocksStringsToSend(final int blocksStringsToSend) {
        this.blocksStringsToSend = blocksStringsToSend;
    }

    public int getBlocksLengthOfStrings() {
        return blocksLengthOfStrings;
    }

    public void setBlocksLengthOfStrings(final int blocksLengthOfStrings) {
        this.blocksLengthOfStrings = blocksLengthOfStrings;
    }

    public int getKochCharsToSend() {
        return kochCharsToSend;
    }

    public void setKochCharsToSend(final int kochCharsToSend) {
        this.kochCharsToSend = kochCharsToSend;
    }
}
