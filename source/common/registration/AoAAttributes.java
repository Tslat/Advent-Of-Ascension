package net.tslat.aoa3.common.registration;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.tme.api.util.RandomUtil;

public final class AoAAttributes {
	public static void init() {}

	public static final DeferredHolder<Attribute, Attribute> AGGRO_RANGE = register("aggro_range", "attribute.name.aoa3.aggroRange", 8, 0, Double.MAX_VALUE, false);
	public static final DeferredHolder<Attribute, Attribute> RANGED_ATTACK_DAMAGE = register("ranged_attack_damage", "attribute.name.aoa3.rangedAttackDamage", 0, 0, Double.MAX_VALUE, false);
	public static final DeferredHolder<Attribute, Attribute> CRITICAL_HIT_MULTIPLIER = register("critical_hit_multiplier", "attribute.name.aoa3.criticalHitMultiplier", 1, 0, Double.MAX_VALUE, true);

	public static final AttributeModifier NIGHT_AGGRO_MODIFIER = new AttributeModifier(AdventOfAscension.id("night_time_pacification"), -0.6d, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

	public static void addSpawnVarianceHealthMod(LivingEntity entity, float modifier) {
		final AttributeInstance attribute = entity.getAttribute(Attributes.MAX_HEALTH);

		if (!attribute.hasModifier(Mob.RANDOM_SPAWN_BONUS_ID)) {
			attribute.addPermanentModifier(new AttributeModifier(Mob.RANDOM_SPAWN_BONUS_ID, Math.max(-0.9f, RandomUtil.scaledGaussianValue(AoAConfigs.SERVER.spawnVarianceHealthScale.getAsDouble() * modifier)), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
			entity.setHealth(entity.getMaxHealth());
		}
	}

	public static void addSpawnVarianceSpeedMod(LivingEntity entity, float modifier) {
		final AttributeInstance attribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);

		if (!attribute.hasModifier(Mob.RANDOM_SPAWN_BONUS_ID))
			attribute.addPermanentModifier(new AttributeModifier(Mob.RANDOM_SPAWN_BONUS_ID, Math.max(-0.99f, RandomUtil.scaledGaussianValue(AoAConfigs.SERVER.spawnVarianceSpeedScale.getAsDouble() * modifier)), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


	}

	private static DeferredHolder<Attribute, Attribute> register(String id, String name, double defaultValue, double minValue, double maxValue, boolean syncedWithClient) {
		return AoARegistries.ENTITY_ATTRIBUTES.register(id, () -> new RangedAttribute(name, defaultValue, minValue, maxValue).setSyncable(syncedWithClient));
	}
}