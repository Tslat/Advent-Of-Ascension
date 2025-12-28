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
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;


public class SasquatchEntity extends AoAMeleeMob<SasquatchEntity> {
	public SasquatchEntity(EntityType<? extends SasquatchEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_YETI_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_YETI_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_YETI_HURT.get();
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 13;
	}

	public static SpawnPlacements.SpawnPredicate<SasquatchEntity> spawnRules(EntityType<SasquatchEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).noLowerThanY(AoADimensions.OVERWORLD, 55);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<SasquatchEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(25)
				.moveSpeed(0.2875)
				.meleeStrength(5)
				.knockbackResist(0.1)
				.followRange(12)
				.aggroRange(12);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING).transitionLength(0));
	}
}
