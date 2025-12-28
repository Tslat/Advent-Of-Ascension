package net.tslat.aoa3.content.item.tool.artifice;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.ChargeableItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class ExpFlask extends ArtificeItem implements ChargeableItem {
	public ExpFlask() {
		super(new Item.Properties().stacksTo(1).component(AoADataComponents.CHARGE, 0f));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 100;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!world.isClientSide) {
			if (!hasEnoughCharge(stack))
				return InteractionResultHolder.fail(stack);

			player.startUsingItem(hand);
		}

		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
		if (entity instanceof ServerPlayer player) {
			float charge = getCharge(stack);

			if (charge > 0) {
				int xpChange = (int)Math.min(1 + ((int)(player.experienceLevel / 15f)), charge);

				player.giveExperiencePoints(xpChange);
				subtractCharge(stack, xpChange, true);

				ParticleBuilder.forRandomPosInEntity(AoAParticleTypes.ORB.get(), player)
						.colourTint((Mth.sin(entity.tickCount / 2f) + 1) * 0.5f, 1f, (Mth.sin(((float)entity.tickCount) / 2f + 4.1887903f) + 1) * 0.1f, 0.5f)
						.lifespan(RandomUtil.numberBetween(30, 70))
						.scaleMod((float)RandomUtil.valueBetween(0.5f, 1.25f))
						.sendToAllPlayersTrackingEntity(player);

				if (!hasEnoughCharge(stack))
					player.stopUsingItem();
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		int storedValue = (int)getCharge(stack);

		if (storedValue > 0)
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.SPECIAL, 1, Component.literal(NumberUtil.floorAndAppendSuffix(storedValue, true) + (storedValue >= 7 ? " (" + PlayerUtil.getPlayerLevelFromExp(storedValue) + ")" : ""))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
	}
}
