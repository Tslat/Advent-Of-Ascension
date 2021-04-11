package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DistortingArtifact extends Item {
	public DistortingArtifact() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).durability(10));
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (itemSlot >= 9)
			return;

		if (!world.isClientSide) {
			if (entity.getY() <= 50) {
				entity.teleportTo(entity.getX(), 257, entity.getZ());
				entity.fallDistance = -255;

				if (entity instanceof LivingEntity) {
					EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 40).isAmbient().hideParticles());

					if (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative()) {
						ItemUtil.damageItem(stack, (PlayerEntity)entity, 1, null);
						((PlayerEntity)entity).inventoryMenu.broadcastChanges();
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
