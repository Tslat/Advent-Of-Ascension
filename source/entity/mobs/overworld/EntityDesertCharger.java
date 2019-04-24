package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityDesertCharger extends AoAMeleeMob {
	public static final float entityWidth = 0.625f;

	public EntityDesertCharger(World world) {
		super(world, entityWidth, 1.5f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.275f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobChargerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobChargerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobChargerHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 150, 0, true, true));

			return true;
		}

		return false;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (source.isFireDamage() || isBurning()) {
			dropItem(ItemRegister.chargerShank, 1 + rand.nextInt(1 + lootingMod));
		}
		else {
			dropItem(ItemRegister.chargerShankRaw, 1 + rand.nextInt(1 + lootingMod));
		}

		if (rand.nextInt(6) == 0)
			dropItem(Items.FEATHER, 3 + rand.nextInt(1 + lootingMod));
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
