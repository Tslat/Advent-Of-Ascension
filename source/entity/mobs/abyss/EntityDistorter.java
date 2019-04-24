package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityDistorter extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityDistorter(World world) {
		super(world, entityWidth, 2.125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.675f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
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
		return SoundsRegister.mobDistorterLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDistorterDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDistorterHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensAbyss, 1 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.cannonSuperCannon, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(10 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(11), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (!pl.isSneaking())
				pl.addVelocity(Math.signum(posX - pl.posX) * 0.029, 0, Math.signum(posZ - pl.posZ) * 0.029);
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 2, true, false));
	}
}
