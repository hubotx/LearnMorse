package pl.hubot.dev.learn_morse.model;

public final class CharPool {
    private CharPool() {
    }

    public static final String ENGLISH_SET = "abcdefghijklmnopqrstuvwxyz";
    public static final String POLISH_SET = ENGLISH_SET + "ąćęłńóśźż";
    public static final String DIGITS = "1234567890";
    public static final String ALPHANUMERIC = ENGLISH_SET + DIGITS;
    public static final String POLISH_ALPHANUMERIC = POLISH_SET + DIGITS;
    public static final String SYMBOLS = ".,'\"_:;?!-+/()=@";
    public static final String FULL_CHARACTER_SET
            = POLISH_ALPHANUMERIC + SYMBOLS;
}
