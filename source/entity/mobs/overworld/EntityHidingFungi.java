package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.skills.HunterUtil;

public class EntityHidingFungi extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityHidingFungi(World world) {
		super(world, entityWidth, 1.0f);
	}

	@Override
	public float getEyeHeight() {
		return 0.4f;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	protected void initEntityAI() {}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable(source))
			return false;

		if (!world.isRemote && !isDead && HunterUtil.canAttackTarget(this, source.getTrueSource(), true)) {
			EntityLivingFungi livingFungi = new EntityLivingFungi(world);

			livingFungi.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			world.spawnEntity(livingFungi);
			livingFungi.attackEntityFrom(source, amount);
			world.playSound(null, posX, posY, posZ, SoundsRegister.MOB_LIVING_FUNGI_SPAWN, SoundCategory.HOSTILE, 1.0f, 1.0f);
			setDead();

			return true;
		}

		return false;
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {}

	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	public void addVelocity(double x, double y, double z) {}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}