package net.tslat.aoa3.entity.boss.voxxulon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityVoxxulonMeteor;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityVoxxulon extends AoAMeleeMob implements BossEntity, SpecialPropertyEntity, AoARangedAttacker {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/voxxulon.png");
	public static final float entityWidth = 2f;

	public EntityVoxxulon(World world) {
		super(world, entityWidth, 2.375f);

		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.8125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_VOXXULON_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_VOXXULON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_VOXXULON_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityVoxxulon;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isGunDamage(source);
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

		if (!world.isRemote && getAttackTarget() != null && rand.nextInt(200) == 0 && getAttackTarget().getDistance(this) < 60)
			setPositionAndUpdate(getAttackTarget().posX, getAttackTarget().posY, getAttackTarget().posZ);

		if (rand.nextInt(200) == 0) {
			for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(40), PredicateUtil.IS_VULNERABLE_PLAYER)) {
				pl.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 4, true, true));
			}
		}

		if (rand.nextInt(10) == 0) {
			EntityPlayer pl = world.getClosestPlayer(posX, posY, posZ, 30, false);

			if (pl != null && !pl.capabilities.isCreativeMode)
				world.spawnEntity(new EntityVoxxulonMeteor(this, pl, Enums.MobProjectileType.MAGIC));
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.voxxulon.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.VOXXULON_MUSIC;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		EntityUtil.dealBlasterDamage(this, target, projectile, 25, false);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 1f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 1f);
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
