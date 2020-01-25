package net.tslat.aoa3.entity.boss.smash;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntitySmash extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/smash.png");
	public static final float entityWidth = 0.8f;

	public EntitySmash(World world) {
		super(world, entityWidth, 2.6f);

		this.setSlipperyMovement();
		this.setAIMoveSpeed(2.1f);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSmashLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSmashDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSmashHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entitySmash;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 500;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	public float getEyeHeight() {
		return 2.35f;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			final float percentHealth = EntityUtil.getCurrentHealthPercent(this);

			if (percentHealth > 0) {
				double resist = 1;
				IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

				if (attrib != null)
					resist -= attrib.getAttributeValue();

				if (percentHealth <= 20) {
					target.addVelocity(motionX * 5.2 * resist, 3.5 * resist, motionZ * 5.2 * resist);
				}
				else if (percentHealth <= 40) {
					target.addVelocity(motionX * 5.8 * resist, 3 * resist, motionZ * 5.8 * resist);
				}
				else if (percentHealth <= 60) {
					target.addVelocity(motionX * 5.4 * resist, 2.5 * resist, motionZ * 5.4 * resist);
				}
				else if (percentHealth <= 80) {
					target.addVelocity(motionX * 5 * resist, 2 * resist, motionZ * 5 * resist);
				}

				target.velocityChanged = true;
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.smash.kill", killer.getDisplayNameString()), this, 50);
		}
		else if (getBossMusic() != null ) {
			AdventOfAscension.proxy.stopMusic();
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Nonnull
	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicSmash;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
