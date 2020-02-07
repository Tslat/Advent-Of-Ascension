package net.tslat.aoa3.entity.boss.rockrider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityRockRider extends AoAMeleeMob implements BossEntity, SpecialPropertyEntity {
	private static final DataParameter<Boolean> ALTERNATE_FORM = EntityDataManager.<Boolean>createKey(EntityRockRider.class, DataSerializers.BOOLEAN);
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/rock_rider.png");
	public static final float entityWidth = 1.3f;
	private boolean alternateForm = false;
	private int formCooldown = 300;

	public EntityRockRider(World world) {
		super(world, entityWidth, 3.375f);

		this.mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(0.8f);
	}

	@Override
	public float getEyeHeight() {
		return 3.0625f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(ALTERNATE_FORM, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1500;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRockRiderDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRockRiderHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRockRider;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	private void changeForm(boolean alternate) {
		if (alternate) {
			mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
			mobProperties.remove(Enums.MobProperties.MELEE_IMMUNE);
			formCooldown = 300;

			if (!world.isRemote)
				this.dataManager.set(ALTERNATE_FORM, true);
		}
		else {
			mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
			mobProperties.remove(Enums.MobProperties.GUN_IMMUNE);
			formCooldown = 300;

			if (!world.isRemote)
				this.dataManager.set(ALTERNATE_FORM, false);
		}
	}

	public boolean isAlternateForm() {
		return this.dataManager.get(ALTERNATE_FORM);
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

		if (formCooldown > 0) {
			formCooldown--;
		}
		else {
			if (alternateForm) {
				alternateForm = false;

				changeForm(false);

				if (!world.isRemote)
					world.playSound(null, posX, posY, posZ, SoundsRegister.mobRockRiderSwitch, SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else {
				alternateForm = true;

				changeForm(true);

				if (!world.isRemote)
					world.playSound(null, posX, posY, posZ, SoundsRegister.mobRockRiderSwitch, SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
		}
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return alternateForm ? EntityUtil.isGunDamage(source) : EntityUtil.isMeleeDamage(source);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			if (alternateForm) {
				double resist = 1;
				IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

				if (attrib != null)
					resist -= attrib.getAttributeValue();

				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 150, 3, true, true));
				target.addVelocity(motionX * 5 * resist, motionY * resist, motionZ * 5 * resist);
				target.velocityChanged = true;
			}
			else {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 150, 3, true, true));
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.rockrider.kill", killer.getDisplayNameString()), this, 50);
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

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicRockRider;
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
}
