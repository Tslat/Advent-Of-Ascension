package net.tslat.aoa3.content.item.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;
import net.tslat.aoa3.common.registration.item.AoAFood;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class HalyconMilk extends Item {
	public HalyconMilk() {
		super(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)
				.craftRemainder(Items.BUCKET)
				.food(AoAFood.HALYCON_MILK));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (!level.isClientSide) {
			EntityUtil.healEntity(entity, 2);
			entity.removeEffectsCuredBy(EffectCures.MILK);


			if (entity instanceof ServerPlayer player) {
				CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
				player.awardStat(Stats.ITEM_USED.get(this));

				for (FoodProperties.PossibleEffect effect : stack.getItem().getFoodProperties(stack, player).effects()) {
					if (level.random.nextFloat() < effect.probability())
						player.addEffect(new MobEffectInstance(effect.effect()));
				}
			}
		}

		if (entity instanceof Player player && !player.getAbilities().instabuild)
			stack.shrink(1);

		return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
