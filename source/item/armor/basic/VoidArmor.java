package net.nevermine.item.armor.basic;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;

public class VoidArmor extends ItemArmor {
	public VoidArmor(final ItemArmor.ArmorMaterial p_i45325_1_, final int p_i45325_2_, final int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
		if (stack.getItem() == Armorizer.VoidHelmet || stack.getItem() == Armorizer.VoidChestplate || stack.getItem() == Armorizer.VoidBoots) {
			return "nevermine:textures/models/armor/voidarmor_1.png";
		}
		if (stack.getItem() == Armorizer.VoidLeggings) {
			return "nevermine:textures/models/armor/voidarmor_2.png";
		}
		return null;
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
