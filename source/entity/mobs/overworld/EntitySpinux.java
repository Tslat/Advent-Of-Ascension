package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntitySpinux extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntitySpinux(World world) {
		super(world, entityWidth, 2f);
	}

	public EntitySpinux(World world, double posX, double posY, double posZ) {
		this(world);
		setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360, 1.0f);
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
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
	protected float getWaterSlowDown() {
		return 1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSpinuxLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSpinuxDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSpinuxHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && block.getBlock() == Blocks.WATER || WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
