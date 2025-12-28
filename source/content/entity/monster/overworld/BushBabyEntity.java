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

public class BushBabyEntity extends AoAMeleeMob<BushBabyEntity> {
	public BushBabyEntity(EntityType<? extends BushBabyEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.LEAFY_THUD.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 13;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	public static SpawnPlacements.SpawnPredicate<BushBabyEntity> spawnRules(EntityType<BushBabyEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).noLowerThanY(AoADimensions.OVERWORLD, 65);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<BushBabyEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(10)
				.moveSpeed(0.34)
				.meleeStrength(4)
				.followRange(10)
				.aggroRange(8);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0));
	}
}
