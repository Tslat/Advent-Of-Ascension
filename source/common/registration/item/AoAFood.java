package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.tslat.effectslib.api.util.EffectBuilder;

public final class AoAFood {
	public static final FoodProperties BUBBLE_BERRIES = food(0, 0).alwaysEat().build();
	public static final FoodProperties CANDY_CANE = food(1, 0.1f).build();
	public static final FoodProperties CANDY_CORN = food(1, 0.22f).build();
	public static final FoodProperties CHILLI = food(0, 0).alwaysEat().build();
	public static final FoodProperties CHUM = food(1, 0.1f).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 60), 1).effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 1).build();
	public static final FoodProperties CHUM_AND_SALAD_BURGER = food(12, 0.9f).effect(() -> new EffectBuilder(MobEffects.CONFUSION, 80).build(), 1f).build();
	public static final FoodProperties CHUM_BURGER = food(9, 0.85f).effect(() -> new EffectBuilder(MobEffects.CONFUSION, 80).build(), 1f).build();
	public static final FoodProperties COOKED_CHARGER_SHANK = food(7, 0.3f).meat().build();
	public static final FoodProperties COOKED_FISH = food(6, 0.85f).effect(() -> new EffectBuilder(MobEffects.CONDUIT_POWER, 600).build(), 1f).build();
	public static final FoodProperties COOKED_GIANT_LIZARD_MEAT = food(8, 0.7f).build();
	public static final FoodProperties COOKED_HALYCON_BEEF = food(7, 0.7f).meat().effect(() -> new EffectBuilder(MobEffects.CONFUSION, 150).build(), 1f).build();
	public static final FoodProperties EYE_CANDY = food(0, 0).alwaysEat().fast().build();
	public static final FoodProperties FIERY_CHOPS = food(5, 0.5f).meat().build();
	public static final FoodProperties FLORACLE_STICKS = food(0, 0).alwaysEat().build();
	public static final FoodProperties FUNGAL_TEA = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.REGENERATION, 75).level(4).build(), 1).effect(() -> new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 60).build(), 1).build();
	public static final FoodProperties GINGERBREAD_COOKIE = food(2, 0.25f).build();
	public static final FoodProperties GINGERBREAD_WING = food(2, 0.2f).build();
	public static final FoodProperties GOLDICAP_PETALS = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.MOVEMENT_SPEED, 40).level(2).build(), 1).effect(() -> new EffectBuilder(MobEffects.JUMP, 40).level(2).build(), 1).build();
	public static final FoodProperties HALYCON_MILK = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.CONFUSION, 100).build(), 1).build();
	public static final FoodProperties HEART_FRUIT = food(15, 0.3f).build();
	public static final FoodProperties HOT_ROD = food(9, 0.9f).meat().build();
	public static final FoodProperties LUNACRIKE = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.JUMP, 40).level(2).build(), 1).build();
	public static final FoodProperties LUNALONS = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.NIGHT_VISION, 40).level(2).build(), 1).build();
	public static final FoodProperties LUNARADE = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.NIGHT_VISION, 40).level(2).build(), 1).effect(() -> new EffectBuilder(MobEffects.JUMP, 40).build(), 1).build();
	public static final FoodProperties LUNA_GLOBE = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.INVISIBILITY, 120).build(), 1).build();
	public static final FoodProperties MAGIC_MARANG = food(5, 0.7f).effect(() -> new EffectBuilder(MobEffects.NIGHT_VISION, 200).build(), 1).effect(() -> new EffectBuilder(MobEffects.GLOWING, 200).build(), 1).build();
	public static final FoodProperties MYSTIC_SHROOMS = food(2, 0.4f).alwaysEat().build();
	public static final FoodProperties NATURAL_TEA = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.REGENERATION, 50).build(), 1).effect(() -> new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 150).level(2).build(), 1).build();
	public static final FoodProperties NATURE_MELON_SLICE = food(1, 0.4f).build();
	public static final FoodProperties PEPPERMINT_CANDY = food(1, 0.2f).build();
	public static final FoodProperties RAW_CHARGER_SHANK = food(2, 0.2f).meat().build();
	public static final FoodProperties RAW_GIANT_LIZARD_MEAT = food(2, 0.4f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
	public static final FoodProperties RAW_HALYCON_BEEF = food(2, 0.3f).meat().effect(() -> new EffectBuilder(MobEffects.CONFUSION, 150).build(), 1).effect(() -> new EffectBuilder(MobEffects.POISON, 40).build(), 1f).build();
	public static final FoodProperties ROSIDONS = food(0, 0).alwaysEat().build();
	public static final FoodProperties SOUR_CANDY = food(1, 0.15f).build();
	public static final FoodProperties SOUR_GUMMY = food(1, 0.3f).build();
	public static final FoodProperties SOUR_POP = food(1, 0.18f).build();
	public static final FoodProperties SPEARMINT_CANDY = food(1, 0.25f).build();
	public static final FoodProperties TEA = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.REGENERATION, 50).level(2).build(), 1).effect(() -> new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 130).build(), 1).build();
	public static final FoodProperties TRILLIAD_LEAVES = food(0, 0).alwaysEat().effect(() -> new EffectBuilder(MobEffects.BLINDNESS, 130).build(), 1).effect(() -> new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 100).level(11).build(), 1).effect(() -> new EffectBuilder(MobEffects.REGENERATION, 100).level(3).build(), 1).effect(() -> new EffectBuilder(MobEffects.POISON, 100).level(8).build(), 1).effect(() -> new EffectBuilder(MobEffects.JUMP, 100).level(129).build(), 1).build();
	public static final FoodProperties YETI_FINGERNAILS = food(0, 0).alwaysEat().build();

	private static FoodProperties.Builder food(int hunger, float saturationMod) {
		return new FoodProperties.Builder().nutrition(hunger).saturationMod(saturationMod);
	}
}
