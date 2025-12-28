package net.tslat.aoa3.util;


import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public final class StringUtil {
	private static final Pattern FORMATTING_CODE_PATTERN = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

	public static String toTitleCase(@NotNull String str) {
		final StringBuilder buffer = new StringBuilder(str.length());
		boolean capitaliseNext = true;

		for (char ch : str.toCharArray()) {
			if (Character.isWhitespace(ch) || ch == '_') {
				buffer.append(' ');
				capitaliseNext = true;
			}
			else if (capitaliseNext) {
				buffer.append(Character.toUpperCase(ch));
				capitaliseNext = false;
			}
			else {
				buffer.append(Character.toLowerCase(ch));
			}
		}

		return buffer.toString();
	}

	public static boolean isSnakeCase(String str) {
		return str != null && !str.isEmpty() && Pattern.matches("[a-z0-9_]", str);
	}

	public static String toSentenceCase(String string) {
		return Character.toTitleCase(string.charAt(0)) + string.substring(1).toLowerCase();
	}

	public static String removeFormatting(String string) {
		return FORMATTING_CODE_PATTERN.matcher(string).replaceAll("");
	}

	public static String lazyPluralise(String text) {
		if (text.endsWith("s") || text.endsWith("y"))
			return text;

		if (text.endsWith("x") || text.endsWith("o"))
			return text + "es";

		return text + "s";
	}
}
