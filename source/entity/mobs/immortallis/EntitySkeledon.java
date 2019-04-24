package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

import static net.tslat.aoa3.library.Enums.Deities.EREBON;

public class EntitySkeledon extends AoAMeleeMob {
	public static final float entityWidth = 0.625f;
	private int cloakCooldown = 80;

	public EntitySkeledon(World world) {
		super(world, entityWidth, 1.3125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
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
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		cloakCooldown--;

		if (cloakCooldown == 0) {
			cloakCooldown = 80;
			motionX *= 1.5;
			motionZ *= 1.5;

			addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 20, 2, true, true));
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.dimImmortallis) {
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

					if (cap.getTribute(EREBON) < 100)
						cap.addTribute(EREBON, Math.min(4, 100 - cap.getTribute(EREBON)));

					if (cap.getTribute(EREBON) >= 100)
						cap.sendPlayerMessage(StringUtil.getLocale("message.feedback.immortallisProgression.skeletalSpiritsEnd"));
				}
			}
		}
	}
}
