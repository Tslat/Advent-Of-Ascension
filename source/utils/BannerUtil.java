package net.tslat.aoa3.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.util.EnumHelper;
import net.tslat.aoa3.common.registration.ItemRegister;

public class BannerUtil {
	public static void init() {
		registerBannerTextures();
	}

	private static void registerBannerTextures() {
		registerBannerTexture("compass_rune", "com", ItemRegister.COMPASS_RUNE);
		registerBannerTexture("distortion_rune", "dis", ItemRegister.DISTORTION_RUNE);
		registerBannerTexture("energy_rune", "ene", ItemRegister.ENERGY_RUNE);
		registerBannerTexture("fire_rune", "fir", ItemRegister.FIRE_RUNE);
		registerBannerTexture("kinetic_rune", "kin", ItemRegister.KINETIC_RUNE);
		registerBannerTexture("life_rune", "lif", ItemRegister.LIFE_RUNE);
		registerBannerTexture("lunar_rune", "lun", ItemRegister.LUNAR_RUNE);
		registerBannerTexture("poison_rune", "poi", ItemRegister.POISON_RUNE);
		registerBannerTexture("power_rune", "pow", ItemRegister.POWER_RUNE);
		registerBannerTexture("storm_rune", "sto", ItemRegister.STORM_RUNE);
		registerBannerTexture("strike_rune", "str", ItemRegister.STRIKE_RUNE);
		registerBannerTexture("water_rune", "wat", ItemRegister.WATER_RUNE);
		registerBannerTexture("wind_rune", "win", ItemRegister.WIND_RUNE);
		registerBannerTexture("wither_rune", "wit", ItemRegister.WITHER_RUNE);
	}

	private static void registerBannerTexture(String name, String id, Item patternItem) {
		String fixedName = "aoa3_" + name;

		EnumHelper.addEnum(BannerPattern.class, fixedName.toUpperCase(), new Class[] {String.class, String.class, ItemStack.class}, fixedName, "a_" + id, new ItemStack(patternItem));
	}
}
