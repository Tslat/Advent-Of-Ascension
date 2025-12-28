package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;

public class DistortingArtifact extends Item {
	public DistortingArtifact() {
		super(new Item.Properties().durability(10));
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (itemSlot >= 9 && (!(entity instanceof LivingEntity user) || user.getItemInHand(InteractionHand.OFF_HAND) != stack))
			return;

		if (!world.isClientSide && stack.getDamageValue() < stack.getMaxDamage()) {
			if (entity.getY() <= world.getMinBuildHeight()) {
				entity.teleportTo(entity.getX(), 257, entity.getZ());
				entity.fallDistance = -255;

				if (entity instanceof LivingEntity) {
					EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.BLINDNESS, 40).isAmbient().hideParticles());

					if (entity instanceof ServerPlayer pl)
						ItemUtil.damageItemForUser(pl.serverLevel(), stack, 1, pl, item -> {
							EquipmentSlot slot = isSelected ? EquipmentSlot.MAINHAND : pl.getItemBySlot(EquipmentSlot.OFFHAND) == stack ? EquipmentSlot.OFFHAND : null;

							if (slot != null)
								pl.onEquippedItemBroken(item, slot);

							EventHooks.onPlayerDestroyItem(pl, stack, slot != null && slot.isArmor() ? null : slot == EquipmentSlot.MAINHAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
						});
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
