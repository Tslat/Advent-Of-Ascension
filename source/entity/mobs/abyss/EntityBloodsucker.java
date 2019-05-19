package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
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
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nullable;

public class EntityBloodsucker extends AoAMeleeMob {
	public static final float entityWidth = 1.3f;

	public EntityBloodsucker(World world) {
		super(world, entityWidth, 1.0f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 130;
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
		return SoundsRegister.mobBloodsuckerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBloodsuckerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBloodsuckerHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.ancientCavern) {
			if (rand.nextInt(40 - lootingMod) == 0)
				dropItem(ItemRegister.realmstoneAncientCavern, 1);

			if (rand.nextInt(100 - lootingMod) == 0)
				dropItem(WeaponRegister.blasterBloodDrainer, 1);

			if (rand.nextInt(100 - lootingMod) == 0)
				dropItem(WeaponRegister.gunBloodIron, 1);

			if (rand.nextBoolean())
				dropItem(ItemRegister.tokensAbyss, 1 + rand.nextInt(1 + lootingMod));

			if (rand.nextInt(150 - lootingMod) == 0)
				dropItem(ItemRegister.upgradeKitAbyssal, 1);
		}
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(10 + lootingMod));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 2, true, false));
			heal((float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() * 2);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.ancientCavern) {
			Entity source = cause.getTrueSource();
			EntityPlayer killer = null;

			if (source != null) {
				if (source instanceof EntityPlayer) {
					killer = (EntityPlayer)source;
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null)
				PlayerUtil.addTributeToPlayer(killer, Enums.Deities.EREBON, 8);
		}
	}
}
