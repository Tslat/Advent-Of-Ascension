package net.nevermine.assist;

import net.minecraft.util.*;

public class StringUtil {
	public static IChatComponent getLocale(String key) {
		//return new ChatComponentText(StatCollector.translateToLocal(key));
		return new ChatComponentTranslation(key);
	}

	public static IChatComponent getLocaleWithArguments(String key, String... args) {
		//return new ChatComponentText(StatCollector.translateToLocalFormatted(key, (Object[])args));
		return new ChatComponentTranslation(key, (Object[])args);
	}

	public static IChatComponent getColourLocaleWithArguments(String key, EnumChatFormatting colour, String... args) {
		//return new ChatComponentText(StatCollector.translateToLocalFormatted(key, (Object[])args)).setChatStyle(new ChatStyle().setColor(colour));
		return new ChatComponentTranslation(key, (Object[])args).setChatStyle(new ChatStyle().setColor(colour));
	}

	public static IChatComponent getColourLocale(String key, EnumChatFormatting colour) {
		//return new ChatComponentText(StatCollector.translateToLocal(key)).setChatStyle(new ChatStyle().setColor(colour));
		return new ChatComponentTranslation(key).setChatStyle(new ChatStyle().setColor(colour));
	}

	public static String getColourLocaleString(String key, EnumChatFormatting colour) {
		return colour + StatCollector.translateToLocal(key);
	}

	public static String getLocaleString(String key) {
		return StatCollector.translateToLocal(key);
	}

	public static String getColourLocaleStringWithArguments(String key, EnumChatFormatting colour, String... args) {
		return colour + StatCollector.translateToLocalFormatted(key, (Object[])args);
	}

	public static String getLocaleStringWithArguments(String key, String... args) {
		return StatCollector.translateToLocalFormatted(key, (Object[])args);
	}
}
