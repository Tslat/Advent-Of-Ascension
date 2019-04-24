package net.tslat.aoa3.entity.boss.tyrosaur;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityTyrosaur extends AoAMeleeMob implements BossEntity, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/tyrosaur.png");
	public static final float entityWidth = 0.8f;
	private int stompCooldown = 100;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityTyrosaur(World world) {
		super(world, entityWidth, 1.3125f);

		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
		this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 0.96875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 4000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTyrosaurLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTyrosaurDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTyrosaurHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.mobTyrosaurStep;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isRangedDamage(source, this, 1);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueTyrosaur), 1);
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		if (stompCooldown > 0)
			stompCooldown--;

		if (stompCooldown > 70) {
			if (getAttackTarget() != null && rand.nextInt(150) == 0) {
				if (!world.isRemote)
					world.playSound(null, posX, posY, posZ, SoundsRegister.mobTyrosaurCharge, SoundCategory.HOSTILE, 1.0f, 1.0f);

				addVelocity(Math.signum(getAttackTarget().posX - posX) * 1.029, (getAttackTarget().posY - posY) * 0.0429, Math.signum(getAttackTarget().posZ - posZ) * 1.029);
			}
		}
		else if (stompCooldown == 40) {
			if (!world.isRemote)
				world.playSound(null, posX, posY, posZ, SoundsRegister.mobTyrosaurReadyStomp, SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
		else if (stompCooldown == 0) {
			stompCooldown = 100;

			if (!world.isRemote)
				world.playSound(null, posX, posY, posZ, SoundsRegister.mobTyrosaurStomp, SoundCategory.HOSTILE, 1.0f, 1.0f);

			for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(35), PredicateUtil.IS_VULNERABLE_PLAYER)) {
				if (pl.onGround && !world.isRemote) {
					if (EntityUtil.dealAoeDamage(null, this, pl, 10, false))
						pl.sendMessage(StringUtil.getColourLocale("message.mob.tyrosaur.stomp", TextFormatting.DARK_GREEN));
				}
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.tyrosaur.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
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
					bossMusic = new BossMusicSound(SoundsRegister.musicTyrosaur, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
