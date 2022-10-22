package net.tslat.aoa3.content.entity.mob.mysterium;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class FungbackEntity extends AoAMeleeMob<FungbackEntity> {
	public FungbackEntity(EntityType<? extends FungbackEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
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
	public boolean addEffect(MobEffectInstance effect, @Nullable Entity source) {
		if (effect.getEffect() == MobEffects.POISON)
			return false;

		return super.addEffect(effect);
	}

	@Override
	protected void onHurt(DamageSource source, float amount) {
		if (!DamageUtil.isEnvironmentalDamage(source)) {
			AreaEffectCloud effectCloud = new AreaEffectCloud(level, getX(), getY(), getZ());

			effectCloud.setDuration(30);
			effectCloud.setRadius(1.5f);
			effectCloud.setOwner(this);
			effectCloud.setWaitTime(0);
			effectCloud.setFixedColor(ColourUtil.RGB(51, 102, 0));
			effectCloud.setPotion(Potions.POISON);
			effectCloud.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 2, false, true));
			effectCloud.setRadiusPerTick(-(effectCloud.getRadius() - 0.5f) / (float)effectCloud.getDuration());

			level.addFreshEntity(effectCloud);
		}
	}
}