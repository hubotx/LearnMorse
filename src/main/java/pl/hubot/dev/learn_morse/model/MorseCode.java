package pl.hubot.dev.learn_morse.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MorseCode {
	public static String encode(String input) {
		StringBuilder encoded = new StringBuilder();
		String lowerCaseInput = input.toLowerCase();
		for (int i = 0; i < lowerCaseInput.length(); i++) {
			char current = lowerCaseInput.charAt(i);
			encoded.append(morseCode.getOrDefault(Character.toString(current), " "));
			encoded.append(" ");
			if (current == ' ') {
				encoded.append("       ");
			} else {
				encoded.append("   ");
			}
		}
		return encoded.toString();
	}

	public static String decode(String input) {
		StringBuilder decoded = new StringBuilder();
		String lowerCaseInput = input.toLowerCase();
		for (String current : lowerCaseInput.split(" ")) {
			if (getKeysByValue(morseCode, current).toArray().length != 0)
				decoded.append(getKeysByValue(morseCode, current).toArray()[0]);
			decoded.append(" ");
		}
		return decoded.toString();
	}

	private static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
		return map.entrySet()
				.stream()
				.filter(entry -> Objects.equals(entry.getValue(), value))
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
	}

	static final Map<String, String> morseCode = new HashMap<String, String>() {{
		put("a", "._");
		put("b", "_...");
		put("c", "_._.");
		put("d", "_..");
		put("e", ".");
		put("f", ".._.");
		put("g", "__.");
		put("h", "....");
		put("i", "..");
		put("j", ".____");
		put("k", "_._");
		put("l", "._..");
		put("m", "__");
		put("n", "_.");
		put("o", "___");
		put("p", ".__.");
		put("q", "__._");
		put("r", "._.");
		put("s", "...");
		put("t", "_");
		put("u", ".._");
		put("v", "..._");
		put("w", ".__");
		put("x", "_.._");
		put("y", "_.__");
		put("z", "__..");
		put("ą", "._._");
		put("ć", "_._..");
		put("ę", ".._..");
		put("é", ".._..");
		put("ch", "____");
		put("ł", "._.._");
		put("ń", "__.__");
		put("ó", "___.");
		put("ś", "...");
		put("ź", "__.._");
		put("ż", "__.._.");
		put("0", "_____");
		put("1", ".____");
		put("2", "..___");
		put("3", "...__");
		put("4", "...._");
		put("5", ".....");
		put("6", "_....");
		put("7", "__...");
		put("8", "___..");
		put("9", "____.");
		put(".", "._._._");
		put(",", "__..__");
		put("'", ".____.");
		put("\"", "._.._.");
		put("_", "..__._");
		put(":", "___...");
		put(";", "_._._.");
		put("?", "..__..");
		put("!", "_._.__");
		put("-", "_...._");
		put("+", "._._.");
		put("/", "_.._.");
		put("(", "_.__.");
		put(")", "_.__._");
		put("=", "_..._");
		put("@", ".__._.");
	}};
}
