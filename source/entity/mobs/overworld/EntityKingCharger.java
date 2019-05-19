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

public class EntityKingCharger extends AoAMeleeMob {
	public static final float entityWidth = 1.25f;

	public EntityKingCharger(World world) {
		super(world, entityWidth, 3.25f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 2.5625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 300;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
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
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.coinGold, 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
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
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 45, 4, true, true));
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
