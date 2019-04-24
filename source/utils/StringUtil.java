package net.tslat.aoa3.utils;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class StringUtil {
	public static ITextComponent getLocale(String key) {
		return new TextComponentTranslation(key);
	}

	public static ITextComponent getColourLocale(String key, TextFormatting colour) {
		return new TextComponentTranslation(key).setStyle(new Style().setColor(colour));
	}

	public static ITextComponent getLocaleWithArguments(String key, String... args) {
		return new TextComponentTranslation(key, (Object[])args);
	}

	public static ITextComponent getColourLocaleWithArguments(String key, TextFormatting colour, String... args) {
		return new TextComponentTranslation(key, (Object[])args).setStyle(new Style().setColor(colour));
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

	public static void sendMessageWithinRadius(ITextComponent msg, Entity center, int radius) {
		for (EntityPlayer pl : center.world.getEntitiesWithinAABB(EntityPlayer.class, center.getEntityBoundingBox().grow(50))) {
			pl.sendMessage(msg);
		}
	}

	public static String toProperCasing(String string) {
		char firstLetter = Character.toTitleCase(string.charAt(0));

		return firstLetter + string.substring(1).toLowerCase();
	}

	public static String numberToSuffixFormat(float value) {
		if (value < 1000) {
			return Math.round(value) + "";
		}
		else if (value < 1000000) {
			return Math.round(value / 1000f) + "k";
		}
		else {
			return Math.round(value / 1000000f) + "m";
		}
	}

	public static String capitaliseAllWords(@Nonnull String str) {
		int sz = str.length();
		StringBuilder buffer = new StringBuilder(sz);
		boolean space = true;

		for (int i = 0; i < sz; i++) {
			char ch = str.charAt(i);

			if (Character.isWhitespace(ch)) {
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
}
