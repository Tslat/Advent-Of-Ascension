package net.tslat.aoa3.entity.boss.corallus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.projectiles.mob.EntityCorallusShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EntityCorallus extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/corallus.png");
	public static final float entityWidth = 0.75f;
	private static final DataParameter<Boolean> ENRAGED = EntityDataManager.<Boolean>createKey(EntityCorallus.class, DataSerializers.BOOLEAN);

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	private int shotCooldown = 7;
	private int shootStageTimer = 0;
	private int jumpCooldown = 320;
	private int rageStateCooldown = 200;

	public EntityCorallus(World world) {
		super(world, entityWidth, 2.875f);
	}

	@Override
	public float getEyeHeight() {
		return 2.72f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ENRAGED, false);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(52);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1800;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3286;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCorallusLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCorallusDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCorallusHit;
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
		dropItem(Item.getItemFromBlock(BlockRegister.statueCorallus), 1);

		if (rand.nextBoolean())
			dropItem(WeaponRegister.staffCoral, 1);

		switch (rand.nextInt(3)) {
			case 0:
				dropItem(ArmourRegister.OceanusHelmet, 1);
				break;
			case 1:
				dropItem(ArmourRegister.SealordHelmet, 1);
				break;
			case 2:
				dropItem(ArmourRegister.AchelosHelmet, 1);
				break;
		}
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.slabCorby, 5 + rand.nextInt(5 + lootingMod));
		dropItem(ItemRegister.realmstoneBorean, 1);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	private void setEnraged(boolean enraged) {
		this.dataManager.set(ENRAGED, enraged);
	}

	public boolean isEnraged() {
		return this.dataManager.get(ENRAGED);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (jumpCooldown == 0) {
			motionY = 1.6;
			fallDistance = -10;
			jumpCooldown = 320;
			shootStageTimer = 60;

			world.playSound(null, posX, posY, posZ, SoundsRegister.mobCorallusTaunt, SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
		else {
			jumpCooldown--;
		}

		if (shootStageTimer > 0) {
			if (shotCooldown == 0 && !world.isRemote) {
				List<EntityPlayer> targets = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(40), PredicateUtil.IS_VULNERABLE_PLAYER);

				for (EntityPlayer target : targets) {
					EntityCorallusShot shot = new EntityCorallusShot(this, target, 12);

					shot.setLocationAndAngles(posX, posY + getEyeHeight(), posZ, rand.nextFloat() * 360, 0);
					world.spawnEntity(shot);
				}

				shotCooldown = 10 + rand.nextInt(15);
			}
			else {
				shotCooldown--;
			}

			shootStageTimer--;
		}

		rageStateCooldown--;

		if (rageStateCooldown <= 0) {
			if (isEnraged()) {
				if (!world.isRemote)
					setEnraged(false);

				rageStateCooldown = 200;
			}
			else {
				if (!world.isRemote)
					setEnraged(true);

				rageStateCooldown = 80;
				addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 80, 3, true, true));
			}
		}

		if (isInWater()) {
			if (motionX > -1.2000000476837158 && motionX < 1.2000000476837158)
				motionX *= 1.2000000476837158;

			if (motionZ > -1.2000000476837158 && motionZ < 1.2000000476837158)
				motionZ *= 1.2000000476837158;
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			target.motionY = -1.799;

			if (!isInWater()) {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 15, true, true));
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 3, true, true));
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.corallus.kill", killer.getDisplayNameString()), this, 50);
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
					bossMusic = new BossMusicSound(SoundsRegister.musicCorallus, this);

				Minecraft.getMinecraft().getMusicTicker();
				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
