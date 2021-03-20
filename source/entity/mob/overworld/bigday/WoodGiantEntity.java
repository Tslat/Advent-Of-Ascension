package net.tslat.aoa3.entity.mob.overworld.bigday;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

public class WoodGiantEntity extends AoAMeleeMob {
	public WoodGiantEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 5.7f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GIANT_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GIANT_HURT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 50).isAmbient());

			double resist = 1;
			ModifiableAttributeInstance attrib = ((LivingEntity)target).getAttribute(Attributes.KNOCKBACK_RESISTANCE);
			Vector3d motion = getDeltaMovement();

			if (attrib != null)
				resist -= attrib.getValue();

			target.push(motion.x() * 21 * resist, motion.y() * 1.6 * resist, motion.z() * 21 * resist);
			target.hurtMarked = true;
		}
	}
}
