package net.tslat.aoa3.entity.mobs.overworld.fullmoon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
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

public class EntityTerrestrial extends AoAMeleeMob {
	public static final float entityWidth = 1.2f;

	public EntityTerrestrial(World world) {
		super(world, entityWidth, 2.357f);
	}

	@Override
	public float getEyeHeight() {
		return 2.25f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTerrestrialLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTerrestrialDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTerrestrialHit;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.realmstoneLunalus, 1);
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 150, 2, true, true));
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.FULL_MOON;
	}
}
