package net.tslat.aoa3.entity.boss.kingbambambam;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityLittleBam extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;
	private boolean isMinion = false;

	public EntityLittleBam(EntityKingBamBamBam kingBamBamBam) {
		this(kingBamBamBam.world);

		setLocationAndAngles(kingBamBamBam.posX, kingBamBamBam.posY, kingBamBamBam.posZ, rand.nextFloat() * 360, 0);

		isMinion = true;
	}

	public EntityLittleBam(World world) {
		super(world, entityWidth, 1f);
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof EntityKingBamBamBam)
			return;

		super.setAttackTarget(target);
	}

	@Override
	public float getEyeHeight() {
		return 0.78125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_BAT_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_BAT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_BAT_HURT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return isMinion	? null : LootSystemRegister.entityLittleBam;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("IsKingBamBamBamMinion"))
			isMinion = true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (isMinion)
			compound.setBoolean("IsKingBamBamBamMinion", true);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		WorldUtil.createExplosion(this, world, 2f);
	}
}
