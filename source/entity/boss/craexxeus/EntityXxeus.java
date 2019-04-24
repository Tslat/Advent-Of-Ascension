package net.tslat.aoa3.entity.boss.craexxeus;

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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.ToolRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityXxeus extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/xxeus.png");
	public static final float entityWidth = 1f;
	private int jumpCooldown = 60;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityXxeus(EntityCraexxeus craexxeus) {
		this(craexxeus.world);

		setLocationAndAngles(craexxeus.posX, craexxeus.posY + 1, craexxeus.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityXxeus(World world) {
		super(world, entityWidth, 3.125f);

		this.setSlipperyMovement();
		this.setAIMoveSpeed(2.1f);
	}

	@Override
	public float getEyeHeight() {
		return 2.55f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobXxeusLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobXxeusDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobXxeusHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueXxeus), 1);

		switch (rand.nextInt(4)) {
			case 0:
				dropItem(WeaponRegister.staffUltimatum, 1);
				break;
			case 1:
				dropItem(ToolRegister.chainsaw, 1);
				break;
			case 2:
				dropItem(WeaponRegister.blasterParalyzer, 1);
				break;
			case 3:
				dropItem(WeaponRegister.greatbladeGodsGreatblade, 1);
				break;
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getAttackTarget() != null) {
			if (jumpCooldown > 0) {
				jumpCooldown--;
			}
			else {
				jumpCooldown = 60;
				EntityLivingBase target = getAttackTarget();
				motionX = (target.posX - posX) * 0.165;
				motionY = target.posY > posY ? 0.85 : 0.449;
				motionZ = (target.posZ - posZ) * 0.165;

				world.playSound(null, posX, posY, posZ, SoundsRegister.mobXxeusDash, SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && !isAIDisabled()) {
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.xxeus.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

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
					bossMusic = new BossMusicSound(SoundsRegister.musicXxeus, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
