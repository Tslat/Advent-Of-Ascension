package net.tslat.aoa3.item.food;

import net.minecraft.item.ItemFood;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class BasicFood extends ItemFood {
	public BasicFood(String name, String registryName, int hunger, float saturation, boolean isWolfFood) {
		super(hunger, saturation, isWolfFood);
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.foodTab);
	}

	public BasicFood(String name, String registryName, int hunger, float saturation) {
		this(name, registryName, hunger, saturation, false);
	}
}
