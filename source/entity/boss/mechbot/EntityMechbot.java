package net.tslat.aoa3.entity.boss.mechbot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityMechFall;
import net.tslat.aoa3.entity.projectiles.mob.EntityMechShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityMechbot extends AoAMeleeMob implements BossEntity, SpecialPropertyEntity, AoARangedAttacker {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/mechbot.png");
	public static final float entityWidth = 0.9f;
	private int jumpCooldown = 200;
	private int jumpCount = 0;
	private int jumpIntervalTimer = 0;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityMechbot(World world) {
		super(world, entityWidth, 2.4375f);

		this.mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 2.21875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2500;
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
		return SoundsRegister.mobMechyonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMechyonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMechyonHit;
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
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return source.isExplosion();
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueMechbot), 1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getAttackTarget() == null || isDead)
			return;

		EntityLivingBase target = getAttackTarget();

		if (jumpCooldown > 0)
			jumpCooldown--;

		if (jumpCooldown <= 0) {
			if (jumpCount < 4) {
				jumpIntervalTimer--;

				if (jumpIntervalTimer <= 0) {
					motionY = 0.699;
					motionX = (target.posX - posX) * 0.045;
					motionZ = (target.posZ - posZ) * 0.045;

					if (!world.isRemote) {
						world.playSound(null, posX, posY, posZ, SoundsRegister.mobMechbotJump, SoundCategory.HOSTILE, 1.0f, 1.0f);
						world.spawnEntity(new EntityMechFall(this, posX, posY - 0.25, posZ, Enums.MobProjectileType.ENERGY));

						if (getHealth() < 1250) {
							world.spawnEntity(new EntityMechFall(this, posX - 2.25, posY - 0.25, posZ - 2.25, Enums.MobProjectileType.ENERGY));
							world.spawnEntity(new EntityMechFall(this, posX - 2.25, posY - 0.25, posZ + 2.25, Enums.MobProjectileType.ENERGY));
							world.spawnEntity(new EntityMechFall(this, posX + 2.25, posY - 0.25, posZ - 2.25, Enums.MobProjectileType.ENERGY));
							world.spawnEntity(new EntityMechFall(this, posX + 2.25, posY - 0.25, posZ + 2.25, Enums.MobProjectileType.ENERGY));
						}
					}

					jumpCount++;
					jumpIntervalTimer = 15;
				}
			}
			else {
				jumpCooldown = 200;
				jumpCount = 0;
				jumpIntervalTimer = 15;
			}
		}

		if (getHealth() < 1000 ? rand.nextInt(10) == 0 : rand.nextInt(40) == 0) {
			EntityMechShot projectile = new EntityMechShot(this, Enums.MobProjectileType.ENERGY);

			double distanceFactorX = target.posX - this.posX;
			double distanceFactorY = target.getEntityBoundingBox().minY + (double)(target.height / 3.0f) - projectile.posY;
			double distanceFactorZ = target.posZ - this.posZ;
			double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

			world.playSound(null, posX, posY, posZ, SoundsRegister.shotMechbotFire, SoundCategory.HOSTILE, 1.0f, 1.0f);
			projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getDifficultyId()));
			world.spawnEntity(projectile);
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.mechbot.kill", killer.getDisplayNameString()), this, 50);
		}
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
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (EntityUtil.dealBlasterDamage(this, target, projectile, 5f, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2f, false);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 1.0f, false);
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
					bossMusic = new BossMusicSound(SoundsRegister.musicMechbot, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
