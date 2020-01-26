package net.tslat.aoa3.entity.boss.cottoncandor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCottonCandorShot;
import net.tslat.aoa3.entity.projectiles.staff.*;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCottonCandor extends AoAFlyingRangedMob implements SpecialPropertyEntity, BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/cotton_candor.png");
	public static final float entityWidth = 1.5f;
	private static final DataParameter<Byte> STAGE = EntityDataManager.<Byte>createKey(EntityCottonCandor.class, DataSerializers.BYTE);
	private int stageCountdown = 100;

	public EntityCottonCandor(World world) {
		super(world, entityWidth, 2.375f);

		this.mobProperties.add(Enums.MobProperties.SPECIAL_COMBAT_ENTITY);
	}

	@Override
	public float getEyeHeight() {
		return 1.71875f;
	}

	@Override
	public PathNavigate getNavigator() {
		return new PathNavigateFlying(this, world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(STAGE, (byte)0);
	}

	private void changeStage(int stage) {
		dataManager.set(STAGE, (byte)(stage & 0x7));
	}

	public int getStage() {
		return (int)dataManager.get(STAGE);
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
	public double getBaseProjectileDamage() {
		return 35;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCottonCandorLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCottonCandorDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCottonCandorHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCottonCandor;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		if (source.getImmediateSource() instanceof IProjectile) {
			IProjectile projectile = (IProjectile)source.getImmediateSource();

			switch (getStage()) {
				case 0:
					if (projectile instanceof EntityPrimordialShot)
						return false;
					break;
				case 1:
					if (projectile instanceof EntityWaterShot)
						return false;
					break;
				case 2:
					if (projectile instanceof EntityFirestormFall || projectile instanceof EntityFireflyShot || projectile instanceof EntityBaronShot)
						return false;
					break;
				case 3:
					if (projectile instanceof EntityPoisonShot || projectile instanceof EntityNoxiousShot)
						return false;
					break;
				case 4:
					if (projectile instanceof EntityWitherShot)
						return false;
					break;
				default:
					return true;
			}
		}

		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotCottonCandorFire;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityCottonCandorShot(this, Enums.MobProjectileType.MAGIC);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
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

		if (!world.isRemote) {
			stageCountdown--;

			if (stageCountdown == 0) {
				changeStage(rand.nextInt(5));
				stageCountdown = 100;
			}
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 5f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 5f);
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

					if (source instanceof EntityPlayerMP && cause.getImmediateSource() instanceof EntityPrimordialShot) {
						if (killer.getHeldItemMainhand().getItem() == WeaponRegister.staffWind || killer.getHeldItemOffhand().getItem() == WeaponRegister.staffWind)
							ModUtil.completeAdvancement((EntityPlayerMP)killer, "candyland/when_push_comes_to_shove", "wind_staff_kill");
					}
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.cottonCandor.kill", killer.getDisplayNameString()), this, 50);
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
		return SoundsRegister.musicCottonCandor;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
