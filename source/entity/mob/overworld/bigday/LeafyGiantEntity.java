package net.tslat.aoa3.entity.mob.overworld.bigday;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class LeafyGiantEntity extends AoAMeleeMob {
	public LeafyGiantEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 3f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GIANT_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GIANT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			double resist = 1;
			ModifiableAttributeInstance attrib = ((LivingEntity)target).getAttribute(Attributes.KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getValue();

			((LivingEntity)target).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 50, 0, true, true));
			target.push(getDeltaMovement().x() * 10.5f * resist, 0.5 * resist, getDeltaMovement().z() * 10.5 * resist);
			target.hurtMarked = true;
		}
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SLAM));
	}
}
