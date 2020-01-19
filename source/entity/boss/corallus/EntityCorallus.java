package net.tslat.aoa3.entity.boss.corallus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
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

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCorallus;
	}

	@Override
	public boolean isNonBoss() {
		return false;
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
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
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

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicCorallus;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
