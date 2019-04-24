package net.tslat.aoa3.entity.boss.smash;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
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

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

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
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueSmash), 1);

		switch (rand.nextInt(4)) {
			case 0:
				dropItem(WeaponRegister.archergunTrolls, 1);
				break;
			case 1:
				dropItem(WeaponRegister.swordTrollBasherAxe, 1);
				break;
			case 2:
				dropItem(WeaponRegister.shotgunBlueBarrel, 1);
				break;
			case 3:
				dropItem(WeaponRegister.cannonBoomCannon, 1);
				break;
		}

		switch (rand.nextInt(3)) {
			case 0:
				dropItem(Items.DIAMOND, 10 + rand.nextInt(5 + lootingMod));
				break;
			case 1:
				dropItem(Items.COAL, 30 + rand.nextInt(15 + lootingMod));
				break;
			case 2:
				dropItem(Items.LEATHER, 5 + rand.nextInt(5 + lootingMod));
				break;
		}
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

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void checkMusicStatus() {
		SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();

		if (!this.isDead && getHealth() > 0) {
			if (BossMusicSound.isAvailable()) {
				if (bossMusic == null)
					bossMusic = new BossMusicSound(SoundsRegister.musicSmash, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
