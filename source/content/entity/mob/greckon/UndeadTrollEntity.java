package net.tslat.aoa3.content.entity.mob.greckon;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.AquaballEntity;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.BloodballEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;


public class UndeadTrollEntity extends AoARangedMob<UndeadTrollEntity> {
	private static final EntityDataAccessor<Integer> SHOT_TYPE = SynchedEntityData.defineId(UndeadTrollEntity.class, EntityDataSerializers.INT);

	public UndeadTrollEntity(EntityType<? extends UndeadTrollEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		getEntityData().define(SHOT_TYPE, 0);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.59375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GOBLIN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GOBLIN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GOBLIN_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_UNDEAD_TROLL_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		if (getEntityData().get(SHOT_TYPE) == 0) {
			return new BloodballEntity(this, BaseMobProjectile.Type.MAGIC);
		}
		else {
			return new AquaballEntity(this, BaseMobProjectile.Type.MAGIC);
		}
	}

	@Override
	public void swing(InteractionHand hand, boolean updateSelf) {
		super.swing(hand, updateSelf);

		if (!level().isClientSide())
			getEntityData().set(SHOT_TYPE, rand().nextBoolean() ? 0 : 1);
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 20;
	}

	@Override
	protected int getPreAttackTime() {
		return 10;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkIdleController(this),
				AoAAnimations.dynamicAttackController(this, state -> getEntityData().get(SHOT_TYPE) == 0 ? DefaultAnimations.ATTACK_SHOOT : AoAAnimations.ATTACK_SHOOT_ALTERNATE));
	}
}
