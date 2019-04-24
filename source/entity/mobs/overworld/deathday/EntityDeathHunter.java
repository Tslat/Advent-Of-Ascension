package net.tslat.aoa3.entity.mobs.overworld.deathday;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityDeathHunter extends AoAMeleeMob {
	public static final float entityWidth = 1.5f;

	public EntityDeathHunter(World world) {
		super(world, entityWidth, 3.125f);
	}

	@Override
	public float getEyeHeight() {
		return 2.95f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDeathHunterLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDeathHunterDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDeathHunterHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.realmstoneImmortallis, 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		boolean toss = rand.nextInt(200) == 0;

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(15), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (pl.isSneaking())
				continue;

			if (toss) {
				pl.addVelocity(Math.signum(pl.posX - posX) * 3.049, 0.5, Math.signum(pl.posZ - posZ) * 3.049);
				world.playSound(null, posX, posY, posZ, getAmbientSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else {
				pl.addVelocity(Math.signum(posX - pl.posX) * 0.049, 0.07, Math.signum(posZ - pl.posZ) * 0.049);
				pl.fallDistance = -0.5f;
			}

			pl.velocityChanged = true;
		}
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.DEATH_DAY;
	}
}
