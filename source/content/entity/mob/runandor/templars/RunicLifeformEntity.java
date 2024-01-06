/*
package net.tslat.aoa3.content.entity.mob.runandor.templars;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;


public abstract class RunicLifeformEntity extends AoAMeleeMob {
	private final RuneTemplarEntity templar;

	public RunicLifeformEntity(EntityType<? extends RunicLifeformEntity> entityType, RuneTemplarEntity templar) {
		super(entityType, templar.level);

		this.templar = templar;
	}

	public RunicLifeformEntity(EntityType<? extends RunicLifeformEntity> entityType, Level world) {
		super(entityType, world);

		this.templar = null;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ANVIL_LAND;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			if (templar != null) {
				if (templar.getHealth() > 0 && templar.getHealth() < templar.getMaxHealth())
					templar.heal(0.075f);
			}
			else {
				remove();
			}
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WEAKNESS, 150).level(3));
	}
}
*/
