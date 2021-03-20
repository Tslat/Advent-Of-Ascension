package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public abstract class RunicLifeformEntity extends AoAMeleeMob {
	public static final float entityWidth = 0.75f;
	public static final float entityHeight = 0.99f;
	private final RuneTemplarEntity templar;

	public RunicLifeformEntity(EntityType<? extends MonsterEntity> entityType, RuneTemplarEntity templar) {
		super(entityType, templar.level);

		this.templar = templar;
	}

	public RunicLifeformEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		this.templar = null;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WEAKNESS, 150).level(3));
	}
}
