package net.tslat.aoa3.content.item.food;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HalyconMilk extends Item {
	public HalyconMilk() {
		super(new Item.Properties().tab(AoACreativeModeTabs.FOOD).craftRemainder(Items.BUCKET).stacksTo(1)
				.craftRemainder(Items.BUCKET)
				.food(new FoodProperties.Builder()
						.nutrition(0)
						.saturationMod(0)
						.alwaysEat()
						.effect(new EffectBuilder(MobEffects.CONFUSION, 100).build(), 1)
						.build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide) {
			EntityUtil.healEntity(entity, 2);
			entity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));


			if (entity instanceof ServerPlayer player) {
				CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
				player.awardStat(Stats.ITEM_USED.get(this));

				for(Pair<MobEffectInstance, Float> pair : stack.getItem().getFoodProperties(stack, player).getEffects()) {
					if (pair.getFirst() != null && world.random.nextFloat() < pair.getSecond())
						player.addEffect(new MobEffectInstance(pair.getFirst()));
				}
			}
		}

		if (entity instanceof Player player && !player.getAbilities().instabuild)
			stack.shrink(1);

		return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
