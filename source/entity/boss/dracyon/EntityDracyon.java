package net.tslat.aoa3.entity.boss.dracyon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntitySpectralShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityDracyon extends AoAFlyingMeleeMob implements BossEntity, AoARangedAttacker {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/dracyon.png");
	public static final float entityWidth = 1.4f;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityDracyon(World world) {
		super(world, entityWidth, 1.3125f);

		this.setAIMoveSpeed(3.7f);
	}

	@Override
	public float getEyeHeight() {
		return 1.15625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1700;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDracyonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDracyonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDracyonLiving;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueDracyon), 1);

		switch (rand.nextInt(3)) {
			case 0:
				dropItem(WeaponRegister.gunDraco, 1);
				break;
			case 1:
				dropItem(WeaponRegister.bowBoreic, 1);
				break;
			case 2:
				dropItem(WeaponRegister.staffReef, 1);
				break;
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			if (rand.nextInt(200) == 0 && !world.provider.doesWaterVaporize() && world.getBlockState(getPosition()).getMaterial().isReplaceable())
				world.setBlockState(getPosition(), Blocks.FLOWING_WATER.getDefaultState());

			if (rand.nextInt(70) == 0 && getAttackTarget() != null) {
				EntityLivingBase target = getAttackTarget();
				BaseMobProjectile projectile = new EntitySpectralShot(this, Enums.MobProjectileType.ENERGY);

				double distanceFactorX = target.posX - projectile.posX;
				double distanceFactorY = target.getEntityBoundingBox().minY + (target.height / 3) - projectile.posY;
				double distanceFactorZ = target.posZ - projectile.posZ;
				double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

				world.playSound(null, posX, posY, posZ, SoundsRegister.mobDracyonLiving, SoundCategory.HOSTILE, 1.0f, 1.0f);
				projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getDifficultyId()));
				world.spawnEntity(projectile);
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.dracyon.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketScreenOverlay(40, Enums.ScreenOverlays.SCRATCHES), (EntityPlayerMP)target);
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (EntityUtil.dealBlasterDamage(this, target, projectile, 13.5f, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {

	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 45, 0, true, true));
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
					bossMusic = new BossMusicSound(SoundsRegister.musicDracyon, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
