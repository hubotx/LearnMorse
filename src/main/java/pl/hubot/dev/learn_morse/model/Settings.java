package pl.hubot.dev.learn_morse.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Class that represents basics settings of keyer.
 */
public final class Settings {
    /**
     * Settings class instance.
     */
    private static final Settings INSTANCE = new Settings();

    /**
     * Application properties.
     */
    private static final Properties PROPERTIES = new Properties();

    /**
     * Pause before keying.
     */
    private int pauseBeforeKeying;

    /**
     * Keying speed.
     */
    private int keyingSpeed;

    /**
     * Dot length.
     */
    private int dotLength;

    /**
     * Dash length.
     */
    private int dashLength;

    /**
     * Length of space between characters.
     */
    private int lengthOfSpaceBetweenCharacters;

    /**
     * Length of space between words.
     */
    private int lengthOfSpaceBetweenWords;

    /**
     * Length of space between char elements.
     */
    private int lengthOfSpaceBetweenCharElements;

    /**
     * Frequency.
     */
    private int frequency;

    /**
     * Volume.
     */
    private float volume;

    /**
     * Pool.
     */
    private String pool;

    /**
     * Load properties.
     *
     * @return Settings class instance
     * @throws IOException            IOException
     * @throws NoSuchFieldException   NoSuchFieldException
     * @throws IllegalAccessException IllegalAccessException
     */
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

    /**
     * Constructor.
     */
    private Settings() {
    }

    /**
     * Save settings.
     *
     * @throws IllegalAccessException IllegalAccessException
     */
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

    /**
     * Get pause before keying.
     *
     * @return pause before keying
     */
    public int getPauseBeforeKeying() {
        return pauseBeforeKeying;
    }

    /**
     * Set pause before keying.
     *
     * @param aPauseBeforeKeying pause before keying
     */
    public void setPauseBeforeKeying(final int aPauseBeforeKeying) {
        this.pauseBeforeKeying = aPauseBeforeKeying;
    }

    /**
     * Get keying speed.
     *
     * @return keying speed
     */
    public int getKeyingSpeed() {
        return keyingSpeed;
    }

    /**
     * Set keying speed.
     *
     * @param aKeyingSpeed keying speed
     */
    public void setKeyingSpeed(final int aKeyingSpeed) {
        this.keyingSpeed = aKeyingSpeed;
    }

    /**
     * Get dot length.
     *
     * @return dot length
     */
    public int getDotLength() {
        return dotLength;
    }

    /**
     * Set dot length.
     *
     * @param aDotLength dot length
     */
    public void setDotLength(final int aDotLength) {
        this.dotLength = aDotLength;
    }

    /**
     * Get dash length.
     *
     * @return dash length
     */
    public int getDashLength() {
        return dashLength;
    }

    /**
     * Set dash length.
     *
     * @param aDashLength dash length
     */
    public void setDashLength(final int aDashLength) {
        this.dashLength = aDashLength;
    }

    /**
     * Get length of space between two characters.
     *
     * @return length of space between two characters
     */
    public int getLengthOfSpaceBetweenCharacters() {
        return lengthOfSpaceBetweenCharacters;
    }

    /**
     * Set length of space between two characters.
     *
     * @param aLengthOfSpaceBetweenCharacters
     * length of space between two characters
     */
    public void setLengthOfSpaceBetweenCharacters(
            final int aLengthOfSpaceBetweenCharacters) {
        this.lengthOfSpaceBetweenCharacters
                = aLengthOfSpaceBetweenCharacters;
    }

    /**
     * Get length of space between two words.
     *
     * @return length of space between two words
     */
    public int getLengthOfSpaceBetweenWords() {
        return lengthOfSpaceBetweenWords;
    }

    /**
     * Set length of space between two words.
     *
     * @param aLengthOfSpaceBetweenWords length of space between two words
     */
    public void setLengthOfSpaceBetweenWords(
            final int aLengthOfSpaceBetweenWords) {
        this.lengthOfSpaceBetweenWords = aLengthOfSpaceBetweenWords;
    }

    /**
     * Get length of space between two char elements.
     *
     * @return length of space between two char elements
     */
    public int getLengthOfSpaceBetweenCharElements() {
        return lengthOfSpaceBetweenCharElements;
    }

    /**
     * Set length of space between two char elements.
     *
     * @param aLengthOfSpaceBetweenCharElements
     * length of space between two char elements
     */
    public void setLengthOfSpaceBetweenCharElements(
            final int aLengthOfSpaceBetweenCharElements) {
        this.lengthOfSpaceBetweenCharElements
                = aLengthOfSpaceBetweenCharElements;
    }

    /**
     * Get keyer frequency.
     *
     * @return keyer frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Set keyer frequency.
     *
     * @param aFrequency keyer frequency
     */
    public void setFrequency(final int aFrequency) {
        this.frequency = aFrequency;
    }

    /**
     * Get buzzer volume.
     *
     * @return buzzer volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Set buzzer volume.
     *
     * @param aVolume buzzer volume
     */
    public void setVolume(final float aVolume) {
        this.volume = aVolume;
    }

    /**
     * Get character pool.
     *
     * @return pool
     */
    public String getPool() {
        return pool;
    }

    /**
     * Set character pool.
     *
     * @param aPool character pool
     */
    public void setPool(final String aPool) {
        this.pool = aPool;
    }
}
