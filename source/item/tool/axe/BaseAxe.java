package net.tslat.aoa3.item.tool.axe;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class BaseAxe extends ItemAxe {
	public BaseAxe(String name, String registryName, ToolMaterial material, float speed) {
		super(material, material.getAttackDamage(), speed);

		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.TOOLS);
	}

	public BaseAxe(String name, String registryName, ToolMaterial material) {
		this(name, registryName, material, -3f);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.MAGIC_REPAIR_DUST), false) || super.getIsRepairable(stack, repairMaterial);
	}
}
