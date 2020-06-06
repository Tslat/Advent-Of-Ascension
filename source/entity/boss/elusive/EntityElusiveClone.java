package net.tslat.aoa3.entity.boss.elusive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityElusiveClone extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;
	private final EntityElusive elusive;

	public EntityElusiveClone(EntityElusive elusive) {
		super(elusive.world, entityWidth, 1.9f);

		setLocationAndAngles(elusive.posX, elusive.posY, elusive.posZ, elusive.rotationYaw, elusive.rotationPitch);
		this.elusive = elusive;
	}

	public EntityElusiveClone(World world) {
		super(world, entityWidth, 1.625f);

		this.elusive = null;
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_ELUSIVE_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_ELUSIVE_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_ELUSIVE_HIT;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 2, true, true));

		addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 40, 2, true, true));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && elusive == null)
			setDead();
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && elusive != null)
			WorldUtil.createExplosion(elusive, world, posX, posY, posZ, 2f);
	}
}
