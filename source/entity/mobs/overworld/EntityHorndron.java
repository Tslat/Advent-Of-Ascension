package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityHorndron extends AoAMeleeMob {
	public static final float entityWidth = 1.7f;

	public EntityHorndron(World world) {
		super(world, entityWidth, 2.2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 14;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobHorndronLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobHorndronDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobHorndronHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		motionX *= 0.800000011920929;
		motionZ *= 0.800000011920929;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
