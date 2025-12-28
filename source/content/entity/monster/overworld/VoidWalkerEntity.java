package net.tslat.aoa3.content.entity.monster.overworld;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class VoidWalkerEntity extends AoAMeleeMob<VoidWalkerEntity> {
	public VoidWalkerEntity(EntityType<? extends VoidWalkerEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_VOID_WALKER_HURT.get();
	}

	@Override
	protected int getPreAttackTime() {
		return 5;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	public static SpawnPlacements.SpawnPredicate<VoidWalkerEntity> spawnRules(EntityType<VoidWalkerEntity> entityType) {
		return EntitySpawnConditions.createMonster(entityType).noHigherThanY(AoADimensions.OVERWORLD, 0);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<VoidWalkerEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(30)
				.moveSpeed(0.3)
				.meleeStrength(4)
				.followRange(14)
				.aggroRange(10);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0));
	}
}
