package net.tslat.aoa3.content.entity.mob.mysterium;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class FungbackEntity extends AoAMeleeMob {
	public FungbackEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.71875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_FUNGI_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_FUNGI_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_FUNGI_HURT.get();
	}

	@Override
	public boolean addEffect(EffectInstance effect) {
		if (effect.getEffect() == Effects.POISON)
			return false;

		return super.addEffect(effect);
	}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (!DamageUtil.isEnvironmentalDamage(source)) {
			AreaEffectCloudEntity effectCloud = new AreaEffectCloudEntity(level, getX(), getY(), getZ());

			effectCloud.setDuration(30);
			effectCloud.setRadius(1.5f);
			effectCloud.setOwner(this);
			effectCloud.setWaitTime(0);
			effectCloud.setFixedColor(ColourUtil.RGB(51, 102, 0));
			effectCloud.setPotion(Potions.POISON);
			effectCloud.addEffect(new EffectInstance(Effects.POISON, 40, 2, false, true));
			effectCloud.setRadiusPerTick(-(effectCloud.getRadius() - 0.5f) / (float)effectCloud.getDuration());

			level.addFreshEntity(effectCloud);
		}
	}
}