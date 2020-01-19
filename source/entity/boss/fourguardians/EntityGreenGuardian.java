package net.tslat.aoa3.entity.boss.fourguardians;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityGreenGuardianShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityGreenGuardian extends AoARangedMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/green_guardian.png");
	public static final float entityWidth = 1.5f;

	private EntityBlueGuardian blueGuardian;
	private EntityRedGuardian redGuardian;
	private EntityYellowGuardian yellowGuardian;

	public EntityGreenGuardian(World world) {
		super(world, entityWidth, 2.625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.0d, 35, 60, 32));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 750;
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
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGuardianDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGuardianHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotGuardianFire;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		if (checkGuardian(blueGuardian) && checkGuardian(redGuardian) && checkGuardian(yellowGuardian))
			return LootSystemRegister.entityGreenGuardian;

		return null;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	public void setRedGuardian(EntityRedGuardian redGuardian) {
		this.redGuardian = redGuardian;
	}

	public void setBlueGuardian(EntityBlueGuardian blueGuardian) {
		this.blueGuardian = blueGuardian;
	}

	public void setYellowGuardian(EntityYellowGuardian yellowGuardian) {
		this.yellowGuardian = yellowGuardian;
	}

	private boolean checkGuardian(EntityLivingBase guardian) {
		return guardian == null || guardian.isDead || guardian.getHealth() == 0;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote)
			setDead();
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (target instanceof AoARangedMob && target instanceof BossEntity)
			return;

		super.doProjectileEntityImpact(projectile, target);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityGreenGuardianShot(this, Enums.MobProjectileType.ENERGY);
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicFourGuardians;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
