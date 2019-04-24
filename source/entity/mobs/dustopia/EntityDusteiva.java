package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityDusteiva extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;
	private int cooldown = 40;

	public EntityDusteiva(World world) {
		super(world, entityWidth, 2.25f);
	}

	@Override
	public float getEyeHeight() {
		return 2.125f;
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
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDusteivaLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDusteivaDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDusteivaHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterDarkDestroyer, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			cooldown--;

			EntityLivingBase target = getAttackTarget();

			if (cooldown <= 0 && target instanceof EntityPlayer) {
				if (EntityUtil.isPlayerLookingAtEntity((EntityPlayer)target, this) && canEntityBeSeen(target)) {
					target.addVelocity(0, 1.5, 0);
					target.velocityChanged = true;
					cooldown = 40;
				}
			}
		}
	}
}
