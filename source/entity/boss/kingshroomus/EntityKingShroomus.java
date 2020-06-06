package net.tslat.aoa3.entity.boss.kingshroomus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityWitherBall;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityKingShroomus extends AoARangedMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/king_shroomus.png");
	public static final float entityWidth = 0.875f;
	private int healingCounter = 0;

	public EntityKingShroomus(World world) {
		super(world, entityWidth, 3.25f);
	}

	@Override
	public float getEyeHeight() {
		return 2.40625f;
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
	public double getBaseProjectileDamage() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_FUNGI_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_KING_SHROOMUS_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_FUNGI_HIT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.ENTITY_GENERIC_HEAVY_STEP;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.WIZARD_BLAST_SHOOT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityKingShroomus;
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
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (healingCounter == 0 && rand.nextInt(200) == 0) {
			if (!world.isRemote)
				world.playSound(null, posX, posY, posZ, SoundsRegister.MOB_KING_SHROOMUS_HEAL, SoundCategory.HOSTILE, 1.0f, 1.0f);

			healingCounter = 80;
		}

		if (healingCounter > 0) {
			healingCounter--;

			heal(2.0f);
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityLivingBase) {
			switch (rand.nextInt(4)) {
				case 0:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 2, true, true));
					break;
				case 1:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 80, 4, true, true));
					break;
				case 2:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 140, 100, true, true));
					break;
				case 3:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 2, true, true));
					break;
			}
		}
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityWitherBall(this, Enums.MobProjectileType.MAGIC);
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.KING_SHROOMUS_MUSIC;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.kingShroomus.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}
}
