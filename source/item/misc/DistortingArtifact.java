package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DistortingArtifact extends SimpleItem {
	public DistortingArtifact() {
		super("DistortingArtifact", "distorting_artifact");

		setMaxDamage(10);
		setMaxStackSize(1);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!world.isRemote && itemSlot < 9 && entity.posY < -3) {
			entity.setPositionAndUpdate(entity.posX, 257, entity.posZ);
			entity.fallDistance = -20;

			if (entity instanceof EntityLivingBase) {
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40, 0, true, false));

				if (entity instanceof EntityPlayer && !((EntityPlayer)entity).isCreative()) {
					stack.damageItem(1, (EntityLivingBase)entity);
					((EntityPlayer)entity).inventoryContainer.detectAndSendChanges();
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		tooltip.add(ItemUtil.getFormattedDescriptionText("item.DistortingArtifact.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.DistortingArtifact.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
