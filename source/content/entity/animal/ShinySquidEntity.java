package net.tslat.aoa3.content.entity.animal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.PlayerUtil;

public class ShinySquidEntity extends Squid {
	public ShinySquidEntity(EntityType<? extends ShinySquidEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);

		if (!isRemoved() && dead) {
			Entity killer = source.getEntity();

			if (killer instanceof ServerPlayer player && player.fishing != null && player.fishing.getHookedIn() == this)
				PlayerUtil.givePartialLevelToPlayer(player, AoASkills.HAULING.get(), 0.1f, false);
		}
	}

	public static SpawnPlacements.SpawnPredicate<ShinySquidEntity> spawnRules(EntityType<ShinySquidEntity> entityType) {
		return EntitySpawnConditions.create(entityType).and(GlowSquid::checkGlowSquidSpawnRules).spawnChance(1 / 200f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<ShinySquidEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(15)
				.swimSpeedMod(1.1f)
				.followRange(16);
	}
}
