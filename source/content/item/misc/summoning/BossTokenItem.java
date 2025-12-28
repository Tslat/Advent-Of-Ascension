package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.util.AttributeUtil;
import org.jetbrains.annotations.Nullable;


public interface BossTokenItem {
	@Nullable
	Entity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack, int playerCount);

	@Nullable
	EntityType<? extends Entity> getEntityType(ItemStack stack);

	@FunctionalInterface
	interface SpawningFunction {
		void spawn(ServerLevel level, Vec3 position, ItemStack stack, int playerCount);
	}

	default void applyMultiplayerHealthBoost(@Nullable LivingEntity boss, int playerCount) {
		if (boss == null || playerCount < 2)
			return;

		double configHealthBoost = AoAConfigs.SERVER.multiplayerBossHealthBonus.getAsDouble();

		if (configHealthBoost > 0) {
			AttributeUtil.applyPermanentModifier(boss, Attributes.MAX_HEALTH, getPerPlayerHealthBuff(playerCount, configHealthBoost));
			boss.setHealth(boss.getMaxHealth());
		}
	}

	default AttributeModifier getPerPlayerHealthBuff(int playerCount, double configValue) {
		return new AttributeModifier(AdventOfAscension.id("per_player_boss_health_buff"), configValue / 100d * Math.max(1, playerCount - 1), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}
}
