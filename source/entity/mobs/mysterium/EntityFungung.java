package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityFungung extends AoAMeleeMob {
	public static final float entityWidth = 0.625f;

	public EntityFungung(World world) {
		super(world, entityWidth, 2.1875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.53125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
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
		return SoundsRegister.mobFungiLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFungiDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFungiHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 20;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.seedsMysticShroom, 1);
		dropItem(Item.getItemFromBlock(BlockRegister.bannerFungal), 1);
		dropItem(ItemRegister.shroomStone, 1);
		dropItem(ItemRegister.realmstoneMysterium, 1);

		if (rand.nextBoolean())
			dropItem(WeaponRegister.greatbladeShroomic, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinSilver, 1 + rand.nextInt(4 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1, true, true));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(11), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (!pl.isSneaking())
				pl.addVelocity(Math.signum(posX - pl.posX) * 0.029, 0, Math.signum(posZ - pl.posZ) * 0.029);
		}
	}
}
