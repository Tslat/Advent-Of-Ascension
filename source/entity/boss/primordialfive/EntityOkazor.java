package net.tslat.aoa3.entity.boss.primordialfive;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityOkazor extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/okazor.png");
	public static final float entityWidth = 0.7f;
	private int healCountdown = 0;
	private int healCounter = 0;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityOkazor(EntityRaxxan raxxan) {
		this(raxxan.world);

		setLocationAndAngles(raxxan.posX, raxxan.posY, raxxan.posZ, raxxan.rotationYaw, raxxan.rotationPitch);
	}

	public EntityOkazor(World world) {
		super(world, entityWidth, 2.375f);
	}

	@Override
	public float getEyeHeight() {
		return 2.009375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1200;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 50;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobPrimordialLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobPrimordialDeath;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueOkazor), 1);

		switch (rand.nextInt(3)) {
			case 0:
				dropItem(WeaponRegister.bowPrimordial, 1);
				break;
			case 1:
				dropItem(WeaponRegister.staffPrimordial, 1);
				break;
			case 2:
				dropItem(WeaponRegister.greatbladePrimordial, 1);
				break;
		}

		switch (rand.nextInt(4)) {
			case 0:
				dropItem(ArmourRegister.PrimordialHelmet, 1);
				break;
			case 1:
				dropItem(ArmourRegister.PrimordialBody, 1);
				break;
			case 2:
				dropItem(ArmourRegister.PrimordialLegs, 1);
				break;
			case 3:
				dropItem(ArmourRegister.PrimordialBoots, 1);
				break;
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			if (healCountdown > 0) {
				healCountdown--;
			}
			else {
				healCounter = 0;
			}

			if (getAttackTarget() != null && rand.nextInt(125) == 0)
				setPositionAndUpdate(getAttackTarget().posX, getAttackTarget().posY, getAttackTarget().posZ);
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote) {
			healCounter++;
			healCountdown = 80;

			if (healCounter == 3)
				heal(getMaxHealth());
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.primordialFive.kill", killer.getDisplayNameString()), this, 50);
		}
	}

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
					bossMusic = new BossMusicSound(SoundsRegister.musicPrimordialFive, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
