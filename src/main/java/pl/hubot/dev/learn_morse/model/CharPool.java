package pl.hubot.dev.learn_morse.model;

import java.util.Arrays;

public class CharPool {
	public static final char[] englishSet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static final char[] polishSet = (String.valueOf(englishSet) + "ąćęłńóśźż").toCharArray();
	public static final char[] digits = "1234567890".toCharArray();
	public static final char[] alphanumeric = (String.valueOf(englishSet) + String.valueOf(digits)).toCharArray();
	public static final char[] polishAlphanumeric = (String.valueOf(polishSet) + String.valueOf(digits)).toCharArray();
	public static final char[] symbols = ".,'\"_:;?!-+/()=@".toCharArray();
	public static final char[] fullCharacterSet = (String.valueOf(polishAlphanumeric) + String.valueOf(symbols)).toCharArray();
}
