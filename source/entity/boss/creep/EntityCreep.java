package net.tslat.aoa3.entity.boss.creep;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCreepBomb;
import net.tslat.aoa3.entity.projectiles.mob.EntityCreepTube;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCreep extends AoARangedMob implements BossEntity, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/creep.png");
	public static final float entityWidth = 0.7f;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityCreep(World world) {
		super(world, entityWidth, 1.75f);

		this.mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.53125f;
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
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCreepoidLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCreepoidDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCreepoidHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityCreepTube(this, Enums.MobProjectileType.OTHER);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueCreep), 1);

		switch(rand.nextInt(4)) {
			case 0:
				dropItem(WeaponRegister.shotgunDischargeShotgun, 1);
				break;
			case 1:
				dropItem(WeaponRegister.cannonMissileMaker, 1);
				break;
			case 2:
				dropItem(WeaponRegister.staffConcussion, 1);
				break;
			case 3:
				dropItem(WeaponRegister.swordCreepified, 1);
				break;
		}
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return source.isExplosion();
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (rand.nextInt(10) == 0) {
			EntityCreepBomb creepBomb = new EntityCreepBomb(this, Enums.MobProjectileType.OTHER);

			creepBomb.shoot((double)(1 / (rand.nextInt(15) + 1) * (1 - rand.nextInt(3))), 4.300000190734863, (double)(1 / (rand.nextInt(15) + 1) * (1 - rand.nextInt(3))), 1.6f, 12.0f);
			world.spawnEntity(creepBomb);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (projectile instanceof EntityCreepBomb) {
			if (target.attackEntityFrom(DamageSource.causeIndirectDamage(projectile, this), (float)getBaseProjectileDamage() * 2.5f))
				doProjectileImpactEffect(projectile, target);
		}
		else {
			super.doProjectileEntityImpact(projectile, target);
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 1.2f, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 1.2f, false);
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.creep.kill", killer.getDisplayNameString()), this, 50);
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
					bossMusic = new BossMusicSound(SoundsRegister.musicCreep, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
