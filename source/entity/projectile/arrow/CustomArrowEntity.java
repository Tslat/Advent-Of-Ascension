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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.weapon.bow.BaseBow;
import net.tslat.aoa3.item.weapon.crossbow.BaseCrossbow;

import java.util.Arrays;

public class CustomArrowEntity extends ArrowEntity {
	protected BaseBow bow;
	protected BaseCrossbow crossbow;

	private boolean ignoreExplosions = false;

	public CustomArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
		super(type, world);
	}

	public CustomArrowEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ARROW.get(), world);

		setPosition(x, y, z);
	}

	public CustomArrowEntity(World world, BaseBow bow, LivingEntity shooter, double baseDamage) {
		super(AoAEntities.Projectiles.ARROW.get(), world);

		setShooter(shooter);
		setDamage(baseDamage);

		this.bow = bow;

		setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1f, shooter.getPosZ());
	}

	public CustomArrowEntity(World world, BaseCrossbow crossbow, LivingEntity shooter, double baseDamage) {
		super(AoAEntities.Projectiles.ARROW.get(), world);

		setShooter(shooter);
		setDamage(baseDamage);

		this.crossbow = crossbow;

		setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1f, shooter.getPosZ());
	}

	public static CustomArrowEntity fromArrow(AbstractArrowEntity baseArrow, BaseBow bow, LivingEntity shooter, double baseDamage) {
		CustomArrowEntity arrow = new CustomArrowEntity(AoAEntities.Projectiles.ARROW.get(), baseArrow.world);

		arrow.setShooter(shooter);
		arrow.setDamage(baseDamage);
		arrow.setKnockbackStrength(baseArrow.knockbackStrength);
		arrow.setIsCritical(baseArrow.getIsCritical());
		arrow.setFire(baseArrow.getFireTimer());
		duplicateArrowVelocity(baseArrow, arrow);
		arrow.setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1f, shooter.getPosZ());

		if (baseArrow instanceof ArrowEntity) {
			ArrowEntity baseArrowEntity = (ArrowEntity)baseArrow;

			arrow.potion = baseArrowEntity.potion;
			arrow.customPotionEffects = baseArrowEntity.customPotionEffects;
			arrow.setFixedColor(baseArrowEntity.getColor());
		}

		arrow.bow = bow;

		return arrow;
	}

	public static CustomArrowEntity fromArrow(AbstractArrowEntity baseArrow, BaseCrossbow crossbow, LivingEntity shooter, double baseDamage) {
		CustomArrowEntity arrow = new CustomArrowEntity(AoAEntities.Projectiles.ARROW.get(), baseArrow.world);

		arrow.setShooter(shooter);
		arrow.setDamage(baseDamage);
		arrow.setKnockbackStrength(baseArrow.knockbackStrength);
		arrow.setIsCritical(baseArrow.getIsCritical());
		arrow.setFire(baseArrow.getFireTimer());
		duplicateArrowVelocity(baseArrow, arrow);
		arrow.setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1f, shooter.getPosZ());

		if (baseArrow instanceof ArrowEntity) {
			ArrowEntity baseArrowEntity = (ArrowEntity)baseArrow;

			arrow.potion = baseArrowEntity.potion;
			arrow.customPotionEffects = baseArrowEntity.customPotionEffects;
			arrow.setFixedColor(baseArrowEntity.getColor());
		}

		arrow.crossbow = crossbow;

		return arrow;
	}

	protected static void duplicateArrowVelocity(AbstractArrowEntity source, AbstractArrowEntity target) {
		target.setMotion(source.getMotion());

		target.rotationPitch = source.rotationPitch;
		target.rotationYaw = source.rotationYaw;
		target.prevRotationPitch = source.prevRotationPitch;
		target.prevRotationYaw = source.prevRotationYaw;
	}

	@Override
	public void tick() {
		if (bow != null) {
			bow.onArrowTick(this, getShooter());
		}
		else if (crossbow != null) {
			crossbow.onArrowTick(this, getShooter());
		}

		super.tick();
	}

	@Override
	public boolean isImmuneToExplosions() {
		return ignoreExplosions;
	}

	public void setIgnoreExplosions() {
		this.ignoreExplosions = true;
	}

	@Override
	protected void onHit(RayTraceResult rayTrace) {
		if (rayTrace.getType() == RayTraceResult.Type.ENTITY) {
			onEntityHit((EntityRayTraceResult)rayTrace);
		}
		else if (rayTrace.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult blockTrace = (BlockRayTraceResult)rayTrace;
			BlockState blockstate = world.getBlockState(blockTrace.getPos());
			inBlockState = blockstate;
			Vec3d vec3d = blockTrace.getHitVec().subtract(getPosX(), getPosY(), getPosZ());

			if (bow != null) {
				bow.onBlockHit(this, blockTrace, getShooter());
			}
			else if (crossbow != null) {
				crossbow.onBlockHit(this, blockTrace, getShooter());
			}

			setMotion(vec3d);

			Vec3d vec3d1 = vec3d.normalize().scale((double)0.05F);

			setRawPosition(getPosX() - vec3d1.x, getPosY() - vec3d1.y, getPosZ() - vec3d1.z);
			playSound(getHitGroundSound(), 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));

			inGround = true;
			arrowShake = 7;

			setIsCritical(false);
			setPierceLevel((byte)0);
			setHitSound(SoundEvents.ENTITY_ARROW_HIT);
			setShotFromCrossbow(false);

			if (hitEntities != null)
				hitEntities.clear();

			if (piercedEntities != null)
				piercedEntities.clear();

			blockstate.onProjectileCollision(world, blockstate, blockTrace, this);
		}
	}

	@Override
	protected void onEntityHit(EntityRayTraceResult rayTrace) {
		Entity target = rayTrace.getEntity();
		float drawPower = (float)this.getMotion().length();
		double damage = getDamage();
		boolean critical = getIsCritical();

		if (bow != null) {
			damage = bow.getArrowDamage(this, target, damage, drawPower, critical);
		}
		else if (crossbow != null) {
			damage = crossbow.getArrowDamage(this, target, damage, drawPower, critical);
		}

		damage = Math.max(damage, 0.0D);

		if (getPierceLevel() > 0) {
			if (piercedEntities == null)
				piercedEntities = new IntOpenHashSet(5);

			if (hitEntities == null)
				hitEntities = Lists.newArrayListWithCapacity(5);

			if (piercedEntities.size() >= getPierceLevel() + 1) {
				remove();

				return;
			}

			piercedEntities.add(target.getEntityId());
		}

		Entity shooter = this.getShooter();
		DamageSource source;

		if (shooter == null) {
			source = DamageSource.causeArrowDamage(this, this);
		}
		else {
			source = DamageSource.causeArrowDamage(this, shooter);

			if (shooter instanceof LivingEntity)
				((LivingEntity)shooter).setLastAttackedEntity(target);
		}

		boolean isEnderman = target.getType() == EntityType.ENDERMAN;
		int fireTimer = target.getFireTimer();

		if (isBurning() && !isEnderman)
			target.setFire(5);

		if (target.attackEntityFrom(source, (float)damage)) {
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

				if (!world.isRemote && getPierceLevel() <= 0)
					livingTarget.setArrowCountInEntity(livingTarget.getArrowCountInEntity() + 1);

				if (knockbackStrength > 0) {
					Vec3d vec3d = getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale(knockbackStrength * 0.6D);

					if (vec3d.lengthSquared() > 0.0D)
						livingTarget.addVelocity(vec3d.x, 0.1D, vec3d.z);
				}

				if (!world.isRemote && shooter instanceof LivingEntity) {
					EnchantmentHelper.applyThornEnchantments(livingTarget, shooter);
					EnchantmentHelper.applyArthropodEnchantments((LivingEntity)shooter, livingTarget);
				}

				arrowHit(livingTarget);

				if (livingTarget != shooter && livingTarget instanceof PlayerEntity && shooter instanceof ServerPlayerEntity)
					((ServerPlayerEntity)shooter).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F));

				if (!target.isAlive() && hitEntities != null)
					hitEntities.add(livingTarget);

				if (!world.isRemote && shooter instanceof ServerPlayerEntity) {
					ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)shooter;

					if (hitEntities != null && getShotFromCrossbow()) {
						CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, this.hitEntities, this.hitEntities.size());
					}
					else if (!target.isAlive() && getShotFromCrossbow()) {
						CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(target), 0);
					}
				}
			}

			playSound(getHitGroundSound(), 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));

			if (getPierceLevel() <= 0)
				remove();
		}
		else {
			target.setFireTimer(fireTimer);
			setMotion(getMotion().scale(-0.1D));

			rotationYaw += 180.0F;
			prevRotationYaw += 180.0F;
			ticksInAir = 0;

			if (!world.isRemote && getMotion().lengthSquared() < 0.0000001) {
				if (pickupStatus == PickupStatus.ALLOWED)
					entityDropItem(getArrowStack(), 0.1F);

				remove();
			}
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
