package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class MagicMendingSolution extends Item {
	public MagicMendingSolution() {
		setTranslationKey("MagicMendingSolution");
		setRegistryName("aoa3:magic_mending_solution");
		setCreativeTab(CreativeTabsRegister.miscTab);
		setMaxStackSize(1);
		setContainerItem(ItemRegister.metalTub);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		NBTTagCompound tag = stack.getTagCompound();

		if (!tag.hasKey("coolingTime"))
			tag.setInteger("coolingTime", 600);

		int coolingTime = tag.getInteger("coolingTime");

		if (coolingTime <= 0) {
			if (!world.isRemote) {
				stack.shrink(1);

				if (entity instanceof EntityPlayer)
					ItemUtil.givePlayerMultipleItems((EntityPlayer)entity, new ItemStack(ItemRegister.metalTub), new ItemStack(ItemRegister.magicMendingCompound));
			}
		}
		else {
			tag.setInteger("coolingTime", coolingTime - 1);
			stack.setTagCompound(tag);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.MagicMendingSolution.desc.1"));
	}
}
