package net.nevermine.item.tool.axe;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

public class Chainsaw extends ItemAxe {
	public Chainsaw(Item.ToolMaterial p_i45327_1_) {
		super(p_i45327_1_);
		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase livingBase1, EntityLivingBase livingBase2) {
		livingBase2.worldObj.playSoundAtEntity(livingBase2, "nevermine:Chainsaw", 1.0F, 1.0F);
		stack.damageItem(1, livingBase2);
		return true;
	}

	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.none;
	}

	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:Chainsaw", 1.0F, 1.0F);
		return false;
	}

	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return true;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
