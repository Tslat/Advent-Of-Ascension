package net.tslat.aoa3.entity.mobs.overworld.lunarinvasion;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityIrkling extends AoAMeleeMob {
	public static final float entityWidth = 0.8f;
	private int jumpTimer = 80;

	public EntityIrkling(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.32f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 135;
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
		return SoundsRegister.mobIrklingLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobIrklingDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobIrklingHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		fallDistance = -0.5f;

		if (jumpTimer > 0)
			--jumpTimer;

		if (jumpTimer == 0) {
			jumpTimer = 80;
			motionY = 0.800000011920929;

			if (getAttackTarget() != null) {
				motionX = (getAttackTarget().posZ - posZ) * 0.0949999988079071;
				motionZ = (getAttackTarget().posZ - posZ) * 0.0949999988079071;
			}
		}
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.orbulon, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.LUNAR_INVASION;
	}
}
