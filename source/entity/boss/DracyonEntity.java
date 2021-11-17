package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.SpectralShotEntity;
import net.tslat.aoa3.scheduling.async.DracyonCleanupTask;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public class DracyonEntity extends AoAFlyingMeleeMob implements AoARangedAttacker {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public DracyonEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);

		this.setSpeed(3.7f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.15625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DRACYON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DRACYON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DRACYON_AMBIENT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			if (random.nextInt(200) == 0 && !level.dimensionType().ultraWarm() && level.getBlockState(blockPosition()).getMaterial().isReplaceable()) {
				level.setBlockAndUpdate(blockPosition(), Blocks.WATER.defaultBlockState());
				new DracyonCleanupTask(level, blockPosition()).schedule(5, TimeUnit.SECONDS);
			}

			if (random.nextInt(70) == 0 && getTarget() != null) {
				LivingEntity target = getTarget();
				BaseMobProjectile projectile = new SpectralShotEntity(this, BaseMobProjectile.Type.MAGIC);

				double distanceFactorX = target.getX() - projectile.getX();
				double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
				double distanceFactorZ = target.getZ() - projectile.getZ();
				double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

				level.playSound(null, blockPosition(), AoASounds.ENTITY_DRACYON_AMBIENT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
				projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level.getDifficulty().getId()));
				level.addFreshEntity(projectile);
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide && !isNoAi()) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.DRACYON.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof ServerPlayerEntity)
			AoAPackets.messagePlayer((ServerPlayerEntity)target, new ScreenOverlayPacket(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/scratches.png"), 40));
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (DamageUtil.dealBlasterDamage(this, target, projectile, 13.5f, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 45));
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.DRACYON_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.DRACYON_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
