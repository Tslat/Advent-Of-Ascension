package net.tslat.aoa3.util;

import javax.annotation.Nonnull;
import java.util.regex.Pattern;

public final class StringUtil {
	private static final Pattern FORMATTING_CODE_PATTERN = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

	public static String toTitleCase(@Nonnull String str) {
		str = str.toLowerCase();
		int size = str.length();
		StringBuilder buffer = new StringBuilder(size);
		boolean space = true;

		for (int i = 0; i < size; i++) {
			char ch = str.charAt(i);

			if (Character.isWhitespace(ch) || ch == '_') {
				buffer.append(' ');
				space = true;
			}
			else if (space) {
				buffer.append(Character.toTitleCase(ch));
				space = false;
			}
			else {
				buffer.append(ch);
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
}
