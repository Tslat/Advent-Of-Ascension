package net.tslat.aoa3.entity.boss.baroness;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityBaronessShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityBaroness extends AoARangedMob implements BossEntity, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/baroness.png");
	public static final float entityWidth = 0.6875f;
	private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(EntityBaroness.class, DataSerializers.BOOLEAN);
	private int invulnerableTicks = 0;
	private int bombCoolown = 150;

	public EntityBaroness(World world) {
		super(world, entityWidth, 2.75f);

		this.mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
	}

	@Override
	public float getEyeHeight() {
		return 1.71875f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(INVULNERABLE, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2000;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Override
	public boolean getIsInvulnerable() {
		return this.dataManager.get(INVULNERABLE);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return source.isExplosion();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_ARIEL_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_ARIEL_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_ARIEL_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.BARONESS_SHOOT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBaroness;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityBaronessShot(this, Enums.MobProjectileType.MAGIC);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		invulnerableTicks = 40;
		changeStage(true);
	}

	private void changeStage(boolean invulnerable) {
		this.dataManager.set(INVULNERABLE, invulnerable);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
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

		if (isAIDisabled() || isDead)
			return;

		if (invulnerableTicks > 0) {
			invulnerableTicks--;

			if (invulnerableTicks == 0)
				changeStage(false);
		}

		if (bombCoolown > 0) {
			bombCoolown--;

			if (bombCoolown == 0) {
				bombCoolown = 50 + rand.nextInt(50);
				EntityLivingBase target = getAttackTarget();

				if (target != null)
					addVelocity(Math.signum((target.posX - posX) * 2.329), Math.signum((target.posY + 1 - posY) * 0.929), Math.signum(target.posZ - posZ) * 2.329);

				if (!world.isRemote) {
					EntityBaronBomb bomb = new EntityBaronBomb(this);

					world.spawnEntity(bomb);
					world.playSound(null, posX, posY, posZ, SoundsRegister.BARON_BOMB_SPAWN, SoundCategory.HOSTILE, 1.0f, 1.0f);
				}
			}
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			EntityPlayer killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.baroness.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Nonnull
	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.BARONESS_MUSIC;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
