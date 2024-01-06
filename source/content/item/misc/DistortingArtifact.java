package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import org.jetbrains.annotations.Nullable;

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
		if (itemSlot >= 9)
			return;

		if (!world.isClientSide && stack.getDamageValue() < stack.getMaxDamage()) {
			if (entity.getY() <= world.getMinBuildHeight()) {
				entity.teleportTo(entity.getX(), 257, entity.getZ());
				entity.fallDistance = -255;

				if (entity instanceof LivingEntity) {
					EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.BLINDNESS, 40).isAmbient().hideParticles());

					if (entity instanceof Player && PlayerUtil.shouldPlayerBeAffected((Player)entity)) {
						ItemUtil.damageItem(stack, (Player)entity, 1, null);
						((Player)entity).inventoryMenu.broadcastChanges();
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
