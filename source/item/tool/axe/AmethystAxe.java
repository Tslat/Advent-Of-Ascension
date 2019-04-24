package net.tslat.aoa3.item.tool.axe;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class AmethystAxe extends ItemAxe {
	public AmethystAxe(ToolMaterial material) {
		super(material, material.getAttackDamage(), -3.0f);
		setUnlocalizedName("AmethystAxe");
		setRegistryName("aoa3:amethyst_axe");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}
}
