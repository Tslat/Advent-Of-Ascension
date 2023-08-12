package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

final class AoAFood {
	public static final FoodProperties RAW_GIANT_LIZARD_MEAT = food(2, 0.4f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
	public static final FoodProperties COOKED_GIANT_LIZARD_MEAT = food(8, 0.7f).build();

	private static FoodProperties.Builder food(int hunger, float saturationMod) {
		return new FoodProperties.Builder().nutrition(hunger).saturationMod(saturationMod);
	}
}
