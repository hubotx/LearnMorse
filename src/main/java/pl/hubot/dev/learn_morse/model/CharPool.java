package pl.hubot.dev.learn_morse.model;

import java.util.Arrays;

public class CharPool {
	public static final char[] englishSet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static final char[] polishSet = (Arrays.toString(englishSet) + "ąćęłńóśźż").toCharArray();
	public static final char[] digits = "1234567890".toCharArray();
	public static final char[] alphanumeric = (Arrays.toString(englishSet) + Arrays.toString(digits)).toCharArray();
	public static final char[] polishAlphanumeric = (Arrays.toString(polishSet) + Arrays.toString(digits)).toCharArray();
	public static final char[] symbols = ".,'\"_:;?!-+/()=@".toCharArray();
	public static final char[] fullCharacterSet = (Arrays.toString(polishAlphanumeric) + Arrays.toString(symbols)).toCharArray();
}
