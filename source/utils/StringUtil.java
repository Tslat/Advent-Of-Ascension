package net.tslat.aoa3.utils;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;

public class StringUtil {
	public static TextComponentTranslation getLocale(String key) {
		return new TextComponentTranslation(key);
	}

	public static TextComponentTranslation getColourLocale(String key, TextFormatting colour) {
		return (TextComponentTranslation)(new TextComponentTranslation(key).setStyle(new Style().setColor(colour)));
	}

	public static TextComponentTranslation getLocaleWithArguments(String key, String... args) {
		return new TextComponentTranslation(key, (Object[])args);
	}

	public static TextComponentTranslation getColourLocaleWithArguments(String key, TextFormatting colour, String... args) {
		return (TextComponentTranslation)(new TextComponentTranslation(key, (Object[])args).setStyle(new Style().setColor(colour)));
	}

	@SideOnly(Side.CLIENT)
	public static String getLocaleString(String key) {
		return I18n.format(key);
	}

	@SideOnly(Side.CLIENT)
	public static String getLocaleStringWithArguments(String key, String... args) {
		return I18n.format(key, (Object[])args);
	}

	@SideOnly(Side.CLIENT)
	public static String getColourLocaleString(String key, TextFormatting colour) {
		return colour + I18n.format(key);
	}

	@SideOnly(Side.CLIENT)
	public static String getColourLocaleStringWithArguments(String key, TextFormatting colour, String... args) {
		return colour + I18n.format(key, (Object[])args);
	}

	public static void sendMessageWithinRadius(TextComponentTranslation msg, Entity center, int radius) {
		for (EntityPlayer pl : center.world.getEntitiesWithinAABB(EntityPlayer.class, center.getEntityBoundingBox().grow(radius))) {
			pl.sendMessage(msg);
		}
	}

	public static String capitaliseFirstLetter(String string) {
		char firstLetter = Character.toTitleCase(string.charAt(0));

		return firstLetter + string.substring(1).toLowerCase();
	}

	public static String floorAndAppendSuffix(float value, boolean strictDigitCount) {
		String suffix = "";

		if (value >= 10000) {
			if (value < 1000000) {
				suffix = "k";
				value = value / 1000f;
			}
			else if (value < 1000000000) {
				suffix = "m";
				value = value / 1000000f;
			}
			else {
				suffix = "b";
				value = value / 1000000000f;
			}
		}

		if (strictDigitCount && value >= 10)
			value = (int)value;

		return new DecimalFormat(strictDigitCount ? "#.#" : "#.##").format(value) + suffix;
	}

	public static String roundToNthDecimalPlace(float value, int decimals) {
		float val = Math.round(value * (float)Math.pow(10, decimals)) / (float)Math.pow(10, decimals);

		if (((int)val) == val)
			return String.valueOf((int)val);

		return String.valueOf(val);
	}

	public static String toTitleCase(@Nonnull String str) {
		int sz = str.length();
		StringBuilder buffer = new StringBuilder(sz);
		boolean space = true;

		for (int i = 0; i < sz; i++) {
			char ch = str.charAt(i);

			if (Character.isWhitespace(ch) || ch == '_') {
				buffer.append(ch);
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

	public static boolean isInteger(CharSequence chars) {
		if (chars == null || chars.length() == 0)
			return false;

		for (int i = 0; i < chars.length(); i++) {
			if (!Character.isDigit(chars.charAt(i)))
				return false;
		}

		return true;
	}

	public static int toInteger(String string) {
		try {
			return Integer.parseInt(string);
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}

	public static float toFloat(String string) {
		try {
			return Float.parseFloat(string);
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}
}
