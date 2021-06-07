package net.tslat.aoa3.entity.projectile.arrow;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.weapon.bow.BaseBow;
import net.tslat.aoa3.item.weapon.crossbow.BaseCrossbow;

import javax.annotation.Nullable;
import java.util.Arrays;

public class CustomArrowEntity extends ArrowEntity {
	protected BaseBow bow;
	protected BaseCrossbow crossbow;

	private boolean ignoreExplosions = false;
	private Entity cachedOwner = null;

	public CustomArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
		super(type, world);
	}

	public CustomArrowEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ARROW.get(), world);

		setPos(x, y, z);
	}

	public CustomArrowEntity(World world, BaseBow bow, LivingEntity shooter, double baseDamage) {
		super(AoAEntities.Projectiles.ARROW.get(), world);

		setOwner(shooter);
		setBaseDamage(baseDamage);

		this.bow = bow;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	public CustomArrowEntity(World world, BaseCrossbow crossbow, LivingEntity shooter, double baseDamage) {
		super(AoAEntities.Projectiles.ARROW.get(), world);

		setOwner(shooter);
		setBaseDamage(baseDamage);

		this.crossbow = crossbow;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	public static CustomArrowEntity fromArrow(AbstractArrowEntity baseArrow, BaseBow bow, LivingEntity shooter, double baseDamage) {
		CustomArrowEntity arrow = new CustomArrowEntity(AoAEntities.Projectiles.ARROW.get(), baseArrow.level);

		arrow.setOwner(shooter);
		arrow.setBaseDamage(baseDamage);
		arrow.setKnockback(baseArrow.knockback);
		arrow.setCritArrow(baseArrow.isCritArrow());
		arrow.setSecondsOnFire(baseArrow.getRemainingFireTicks());
		duplicateArrowVelocity(baseArrow, arrow);
		arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());

		if (baseArrow instanceof ArrowEntity) {
			ArrowEntity baseArrowEntity = (ArrowEntity)baseArrow;

			arrow.potion = baseArrowEntity.potion;
			arrow.effects = baseArrowEntity.effects;
			arrow.setFixedColor(baseArrowEntity.getColor());
		}

		arrow.bow = bow;

		return arrow;
	}

	public static CustomArrowEntity fromArrow(AbstractArrowEntity baseArrow, BaseCrossbow crossbow, LivingEntity shooter, double baseDamage) {
		CustomArrowEntity arrow = new CustomArrowEntity(AoAEntities.Projectiles.ARROW.get(), baseArrow.level);

		arrow.setOwner(shooter);
		arrow.setBaseDamage(baseDamage);
		arrow.setKnockback(baseArrow.knockback);
		arrow.setCritArrow(baseArrow.isCritArrow());
		arrow.setSecondsOnFire(baseArrow.getRemainingFireTicks());
		duplicateArrowVelocity(baseArrow, arrow);
		arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());

		if (baseArrow instanceof ArrowEntity) {
			ArrowEntity baseArrowEntity = (ArrowEntity)baseArrow;

			arrow.potion = baseArrowEntity.potion;
			arrow.effects = baseArrowEntity.effects;
			arrow.setFixedColor(baseArrowEntity.getColor());
		}

		arrow.crossbow = crossbow;

		return arrow;
	}

	protected static void duplicateArrowVelocity(AbstractArrowEntity source, AbstractArrowEntity target) {
		target.setDeltaMovement(source.getDeltaMovement());

		target.xRot = source.xRot;
		target.yRot = source.yRot;
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
	protected void onHit(RayTraceResult rayTrace) {
		if (rayTrace.getType() == RayTraceResult.Type.ENTITY) {
			onHitEntity((EntityRayTraceResult)rayTrace);
		}
		else if (rayTrace.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult blockTrace = (BlockRayTraceResult)rayTrace;
			BlockState blockstate = level.getBlockState(blockTrace.getBlockPos());
			lastState = blockstate;
			Vector3d Vector3d = blockTrace.getLocation().subtract(getX(), getY(), getZ());

			if (bow != null) {
				bow.onBlockHit(this, blockTrace, getOwner());
			}
			else if (crossbow != null) {
				crossbow.onBlockHit(this, blockTrace, getOwner());
			}

			setDeltaMovement(Vector3d);

			Vector3d Vector3d1 = Vector3d.normalize().scale((double)0.05F);

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
	protected void onHitEntity(EntityRayTraceResult rayTrace) {
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
				remove();

				return;
			}

			piercingIgnoreEntityIds.add(target.getId());
		}

		Entity shooter = this.getOwner();
		DamageSource source;

		if (shooter == null) {
			source = DamageSource.arrow(this, this);
		}
		else {
			source = DamageSource.arrow(this, shooter);

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

			if (target instanceof LivingEntity) {
				LivingEntity livingTarget = (LivingEntity)target;

				if (!level.isClientSide && getPierceLevel() <= 0)
					livingTarget.setArrowCount(livingTarget.getArrowCount() + 1);

				if (knockback > 0) {
					Vector3d Vector3d = getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale(knockback * 0.6D);

					if (Vector3d.lengthSqr() > 0.0D)
						livingTarget.push(Vector3d.x, 0.1D, Vector3d.z);
				}

				if (!level.isClientSide && shooter instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingTarget, shooter);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)shooter, livingTarget);
				}

				doPostHurtEffects(livingTarget);

				if (livingTarget != shooter && livingTarget instanceof PlayerEntity && shooter instanceof ServerPlayerEntity)
					((ServerPlayerEntity)shooter).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));

				if (!target.isAlive() && piercedAndKilledEntities != null)
					piercedAndKilledEntities.add(livingTarget);

				if (!level.isClientSide && shooter instanceof ServerPlayerEntity) {
					ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)shooter;

					if (piercedAndKilledEntities != null && shotFromCrossbow()) {
						CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, this.piercedAndKilledEntities);
					}
					else if (!target.isAlive() && shotFromCrossbow()) {
						CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(target));
					}
				}
			}

			playSound(getHitGroundSoundEvent(), 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));

			if (getPierceLevel() <= 0)
				remove();
		}
		else {
			target.setRemainingFireTicks(fireTimer);
			setDeltaMovement(getDeltaMovement().scale(-0.1D));

			yRot += 180.0F;
			yRotO += 180.0F;

			if (!level.isClientSide && getDeltaMovement().lengthSqr() < 0.0000001) {
				if (pickup == PickupStatus.ALLOWED)
					spawnAtLocation(getPickupItem(), 0.1F);

				remove();
			}
		}
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
