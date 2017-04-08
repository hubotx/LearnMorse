package pl.hubot.dev.learn_morse.model;

/**
 * Training character pool.
 */
public final class CharPool {
    /**
     * Constructor.
     */
    private CharPool() {
    }

    /**
     * English set.
     */
    public static final String ENGLISH_SET = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Polish set.
     */
    public static final String POLISH_SET = ENGLISH_SET + "ąćęłńóśźż";

    /**
     * Digits set.
     */
    public static final String DIGITS = "1234567890";

    /**
     * Alphanumeric set.
     */
    public static final String ALPHANUMERIC = ENGLISH_SET + DIGITS;

    /**
     * Polish alphanumeric set.
     */
    public static final String POLISH_ALPHANUMERIC = POLISH_SET + DIGITS;

    /**
     * Symbols set.
     */
    public static final String SYMBOLS = ".,'\"_:;?!-+/()=@";

    /**
     * Full character set.
     */
    public static final String FULL_CHARACTER_SET
            = POLISH_ALPHANUMERIC + SYMBOLS;
}
