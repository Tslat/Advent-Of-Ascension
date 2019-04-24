package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityFacelessRunner extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityFacelessRunner(World world) {
		super(world, entityWidth, 1.315f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(2.1f);
	}

	@Override
	public float getEyeHeight() {
		return 1.25f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 40 && super.getCanSpawnHere();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFacelessRunnerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFacelessRunnerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFacelessRunnerHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneDeeplands, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
