package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class RosidianBow extends BaseBow {
	public RosidianBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("RosidianBow");
		setRegistryName("aoa3:rosidian_bow");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity holder, int itemSlot, boolean isSelected) {
		if (!world.isRemote && world.getTotalWorldTime() % 200 == 0 && stack.isItemDamaged() && WorldUtil.getLightLevel(world, holder.getPosition(), false, true) > 7)
			stack.setItemDamage(stack.getItemDamage() - 1);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RosidianBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
