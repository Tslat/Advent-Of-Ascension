package net.tslat.aoa3.entity.mobs.abyss;

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
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityFade extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityFade(EntityFade fade) {
		this(fade.world);
		this.setLocationAndAngles(fade.posX, fade.posY, fade.posZ, fade.rand.nextFloat() * 360, 0);
	}

	public EntityFade(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.734375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
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
		return SoundsRegister.mobFadeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFadeDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFadeHit;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount)) {
			if (!world.isRemote && !EntityUtil.isMagicDamage(source, this, amount)) {
				if (rand.nextInt(Math.round((float)(getHealth() / (amount * 1.2) + 2))) == 0) {
					EntityFade clone = new EntityFade(this);

					world.spawnEntity(clone);
				}
			}

			return true;
		}

		return false;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 3 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 2, true, false));
	}
}
