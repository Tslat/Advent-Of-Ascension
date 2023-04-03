package net.tslat.aoa3.content.entity.projectile.arrow;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.weapon.bow.BaseBow;
import net.tslat.aoa3.content.item.weapon.crossbow.BaseCrossbow;

import javax.annotation.Nullable;
import java.util.List;

public class CustomArrowEntity extends Arrow {
	protected BaseBow bow;
	protected BaseCrossbow crossbow;

	private boolean ignoreExplosions = false;
	private Entity cachedOwner = null;

	public CustomArrowEntity(EntityType<? extends Arrow> type, Level world) {
		super(type, world);
	}

	public CustomArrowEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.ARROW.get(), world);

		setPos(x, y, z);
	}

	public CustomArrowEntity(Level world, BaseBow bow, LivingEntity shooter, double baseDamage) {
		super(AoAProjectiles.ARROW.get(), world);

		setOwner(shooter);
		setBaseDamage(baseDamage);

		this.bow = bow;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	public CustomArrowEntity(Level world, BaseCrossbow crossbow, LivingEntity shooter, double baseDamage) {
		super(AoAProjectiles.ARROW.get(), world);

		setOwner(shooter);
		setBaseDamage(baseDamage);

		this.crossbow = crossbow;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	public static CustomArrowEntity fromArrow(AbstractArrow baseArrow, BaseBow bow, LivingEntity shooter, double baseDamage) {
		CustomArrowEntity arrow = new CustomArrowEntity(AoAProjectiles.ARROW.get(), baseArrow.level);

		arrow.setOwner(shooter);
		arrow.setBaseDamage(baseDamage);
		arrow.setKnockback(baseArrow.getKnockback());
		arrow.setCritArrow(baseArrow.isCritArrow());
		arrow.setSecondsOnFire(baseArrow.getRemainingFireTicks());
		duplicateArrowVelocity(baseArrow, arrow);
		arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());

		if (baseArrow instanceof Arrow baseArrowEntity) {
			arrow.potion = baseArrowEntity.potion;
			arrow.effects = baseArrowEntity.effects;
			arrow.setFixedColor(baseArrowEntity.getColor());
		}

		arrow.bow = bow;

		return arrow;
	}

	public static CustomArrowEntity fromArrow(AbstractArrow baseArrow, BaseCrossbow crossbow, LivingEntity shooter, double baseDamage) {
		CustomArrowEntity arrow = new CustomArrowEntity(AoAProjectiles.ARROW.get(), baseArrow.level);

		arrow.setOwner(shooter);
		arrow.setBaseDamage(baseDamage);
		arrow.setKnockback(baseArrow.getKnockback());
		arrow.setCritArrow(baseArrow.isCritArrow());
		arrow.setSecondsOnFire(baseArrow.getRemainingFireTicks());
		duplicateArrowVelocity(baseArrow, arrow);
		arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());

		if (baseArrow instanceof Arrow baseArrowEntity) {
			arrow.potion = baseArrowEntity.potion;
			arrow.effects = baseArrowEntity.effects;
			arrow.setFixedColor(baseArrowEntity.getColor());
		}

		arrow.crossbow = crossbow;

		return arrow;
	}

	protected static void duplicateArrowVelocity(AbstractArrow source, AbstractArrow target) {
		target.setDeltaMovement(source.getDeltaMovement());

		target.setXRot(source.getXRot());
		target.setYRot(source.getYRot());

		target.xRotO = source.xRotO;
		target.yRotO = source.yRotO;
	}

	@Nullable
	@Override
	public Entity getOwner() {
		if (this.cachedOwner != null && this.cachedOwner.isAlive())
			return this.cachedOwner;

		this.cachedOwner = super.getOwner();

		return this.cachedOwner;
	}

	@Override
	public void tick() {
		if (bow != null) {
			bow.onArrowTick(this, getOwner());
		}
		else if (crossbow != null) {
			crossbow.onArrowTick(this, getOwner());
		}

		super.tick();
	}

	@Override
	public boolean ignoreExplosion() {
		return ignoreExplosions;
	}

	public void setIgnoreExplosions() {
		this.ignoreExplosions = true;
	}

	@Override
	protected void onHit(HitResult rayTrace) {
		if (rayTrace.getType() == HitResult.Type.ENTITY) {
			onHitEntity((EntityHitResult)rayTrace);
		}
		else if (rayTrace.getType() == HitResult.Type.BLOCK) {
			BlockHitResult blockTrace = (BlockHitResult)rayTrace;
			BlockState blockstate = level.getBlockState(blockTrace.getBlockPos());
			lastState = blockstate;
			Vec3 Vec3 = blockTrace.getLocation().subtract(getX(), getY(), getZ());

			if (bow != null) {
				bow.onBlockHit(this, blockTrace, getOwner());
			}
			else if (crossbow != null) {
				crossbow.onBlockHit(this, blockTrace, getOwner());
			}

			setDeltaMovement(Vec3);

			Vec3 Vector3d1 = Vec3.normalize().scale((double)0.05F);

			setPosRaw(getX() - Vector3d1.x, getY() - Vector3d1.y, getZ() - Vector3d1.z);
			playSound(getHitGroundSoundEvent(), 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));

			inGround = true;
			shakeTime = 7;

			setCritArrow(false);
			setPierceLevel((byte)0);
			setSoundEvent(SoundEvents.ARROW_HIT);
			setShotFromCrossbow(false);

			if (piercedAndKilledEntities != null)
				piercedAndKilledEntities.clear();

			if (piercingIgnoreEntityIds != null)
				piercingIgnoreEntityIds.clear();

			blockstate.onProjectileHit(level, blockstate, blockTrace, this);
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult rayTrace) {
		Entity target = rayTrace.getEntity();
		float drawPower = (float)this.getDeltaMovement().length();
		double damage = getBaseDamage();
		boolean critical = isCritArrow();

		if (bow != null) {
			damage = bow.getArrowDamage(this, target, damage, drawPower, critical);
		}
		else if (crossbow != null) {
			damage = crossbow.getArrowDamage(this, target, damage, drawPower, critical);
		}

		damage = Math.max(damage, 0.0D);

		if (getPierceLevel() > 0) {
			if (piercingIgnoreEntityIds == null)
				piercingIgnoreEntityIds = new IntOpenHashSet(5);

			if (piercedAndKilledEntities == null)
				piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);

			if (piercingIgnoreEntityIds.size() >= getPierceLevel() + 1) {
				discard();

				return;
			}

			piercingIgnoreEntityIds.add(target.getId());
		}

		Entity shooter = this.getOwner();
		DamageSource source;

		if (shooter == null) {
			source = damageSources().arrow(this, this);
		}
		else {
			source = damageSources().arrow(this, shooter);

			if (shooter instanceof LivingEntity)
				((LivingEntity)shooter).setLastHurtMob(target);
		}

		boolean isEnderman = target.getType() == EntityType.ENDERMAN;
		int fireTimer = target.getRemainingFireTicks();

		if (isOnFire() && !isEnderman)
			target.setSecondsOnFire(5);

		if (target.hurt(source, (float)damage)) {
			if (isEnderman)
				return;

			if (bow != null) {
				bow.onEntityHit(this, target, shooter, damage, drawPower);
			}
			else if (crossbow != null) {
				crossbow.onEntityHit(this, target, shooter, damage, drawPower);
			}

			if (target instanceof LivingEntity livingTarget) {
				if (!level.isClientSide && getPierceLevel() <= 0)
					livingTarget.setArrowCount(livingTarget.getArrowCount() + 1);

				if (getKnockback() > 0) {
					Vec3 Vec3 = getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale(getKnockback() * 0.6D);

					if (Vec3.lengthSqr() > 0.0D)
						livingTarget.push(Vec3.x, 0.1D, Vec3.z);
				}

				if (!level.isClientSide && shooter instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingTarget, shooter);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)shooter, livingTarget);
				}

				doPostHurtEffects(livingTarget);

				if (livingTarget != shooter && livingTarget instanceof Player && shooter instanceof ServerPlayer)
					((ServerPlayer)shooter).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));

				if (!target.isAlive() && piercedAndKilledEntities != null)
					piercedAndKilledEntities.add(livingTarget);

				if (!level.isClientSide && shooter instanceof ServerPlayer serverPlayer) {
					if (piercedAndKilledEntities != null && shotFromCrossbow()) {
						CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverPlayer, this.piercedAndKilledEntities);
					}
					else if (!target.isAlive() && shotFromCrossbow()) {
						CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverPlayer, List.of(target));
					}
				}
			}

			playSound(getHitGroundSoundEvent(), 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));

			if (getPierceLevel() <= 0)
				discard();
		}
		else {
			target.setRemainingFireTicks(fireTimer);
			setDeltaMovement(getDeltaMovement().scale(-0.1D));

			setYRot(getYRot() + 180f);

			yRotO += 180.0F;

			if (!level.isClientSide && getDeltaMovement().lengthSqr() < 0.0000001) {
				if (pickup == Pickup.ALLOWED)
					spawnAtLocation(getPickupItem(), 0.1F);

				discard();
			}
		}
	}
}
