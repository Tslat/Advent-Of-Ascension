package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public abstract class EntityRunicLifeform extends AoAMeleeMob {
	public static final float entityWidth = 0.75f;
	private final EntityRuneTemplar templar;

	public EntityRunicLifeform(EntityRuneTemplar templar) {
		super(templar.world, entityWidth, 0.99f);

		this.templar = templar;
	}

	public EntityRunicLifeform(World world) {
		super(world, entityWidth, 1.375f);

		this.templar = null;
	}

	@Override
	public float getEyeHeight() {
		return 1f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	protected double getBaseArmour() {
		return 1;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			if (templar != null) {
				if (templar.getHealth() > 0 && templar.getHealth() < templar.getMaxHealth())
					templar.heal(0.075f);
			}
			else {
				setDead();
			}
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 150, 2, true, true));
	}
}
