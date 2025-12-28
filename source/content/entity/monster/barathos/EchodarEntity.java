package net.tslat.aoa3.content.entity.monster.barathos;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

public class EchodarEntity extends AoAFlyingMeleeMob<EchodarEntity> {
	private static final RawAnimation ATTACHED_IDLE_ANIM = RawAnimation.begin().thenPlay("misc.idle.attached");
	private static final RawAnimation ATTACH_ANIM = RawAnimation.begin().thenPlay("misc.attach");
	private static final RawAnimation DETACH_ANIM = RawAnimation.begin().thenPlay("misc.detach");

	public EchodarEntity(EntityType<? extends EchodarEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public BrainActivityGroup<? extends EchodarEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new SetRandomFlyingTarget<>()
						.verticalWeight(entity -> rand().oneInNChance(10) ? -1 : 0)
						.setRadius(4, 4));
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	public static SpawnPlacements.SpawnPredicate<EchodarEntity> spawnRules(EntityType<EchodarEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).noLowerThanY(AoADimensions.BARATHOS, 65).difficultyBasedSpawnChance(0.05f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<EchodarEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(19)
				.meleeStrength(5)
				.moveSpeed(0.33)
				.flyingSpeed(0.27f)
				.aggroRange(32)
				.armour(1)
				.followRange(64);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Movement", 0, state -> {
			if (onGround() || (isNoAi() && !getBlockStateOn().isFaceSturdy(level(), getOnPos(), Direction.UP)))
				return PlayState.STOP;

			return state.setAndContinue(DefaultAnimations.FLY);
		}).triggerableAnim("Attach", ATTACH_ANIM).triggerableAnim("Detach", DETACH_ANIM));
	}
}
