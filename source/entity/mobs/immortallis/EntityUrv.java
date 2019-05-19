package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

import static net.tslat.aoa3.library.Enums.Deities.EREBON;

public class EntityUrv extends AoAMeleeMob {
	public static final float entityWidth = 0.75f;

	public EntityUrv(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 75;
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
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobAutomatonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobAutomatonHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.mobGolemStep;
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		int modifier = world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(7), entity -> entity instanceof IMob).size();

		addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1, modifier % 4, true, false));

		return super.attackEntityAsMob(target);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.immortallis) {
			Entity attacker = cause.getTrueSource();

			if (attacker instanceof EntityPlayer || attacker instanceof EntityTameable) {
				EntityPlayer pl = null;

				if (attacker instanceof EntityTameable) {
					if (((EntityTameable)attacker).getOwner() instanceof EntityPlayer)
						pl = (EntityPlayer)((EntityTameable)attacker).getOwner();
				}
				else {
					pl = (EntityPlayer)attacker;
				}

				if (pl != null) {
					AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(pl);

					cap.addTribute(EREBON, 4);

					if (cap.getTribute(EREBON) == 200)
						cap.sendPlayerMessage(StringUtil.getLocale("message.feedback.immortallisProgression.evilSpiritsEnd"));
				}
			}
		}
	}
}
