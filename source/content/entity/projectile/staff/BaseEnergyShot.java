package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

import javax.annotation.Nullable;

public abstract class BaseEnergyShot extends ThrowableProjectile {
	protected int lifespan;
	private int age;
	protected EnergyProjectileWeapon weapon;

	private Entity cachedOwner = null;

	public BaseEnergyShot(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);

		this.age = 0;
	}

	public BaseEnergyShot(EntityType<? extends ThrowableProjectile> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = 60;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(posX, posY, posZ, 0, 360);
		setDeltaMovement(new Vec3(motionX, motionY, motionZ));
	}

	public BaseEnergyShot(EntityType<? extends ThrowableProjectile> entityType, Entity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = 120;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(posX, posY, posZ, 0, 360);

		setDeltaMovement(new Vec3(random.nextGaussian() / 33 + 0.03D, -velocity, random.nextGaussian() / 33 + 0.03D));
	}

	public BaseEnergyShot(EntityType<? extends ThrowableProjectile> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());

		boolean right = true;

		if (shooter instanceof Player) {
			if (weapon.getWeaponHand(shooter) == InteractionHand.MAIN_HAND) {
				if (shooter.getMainArm() == HumanoidArm.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HumanoidArm.RIGHT)
					right = false;
			}
		}

		shoot(((double)(-Mth.sin(getYRot() / 180.0F * (float)Math.PI) * Mth.cos(getXRot() / 180.0F * (float)Math.PI)) + xMod),
				((double)(-Mth.sin(getXRot() / 180.0F * (float)Math.PI)) + yMod),
				((double)(Mth.cos(getYRot() / 180.0F * (float)Math.PI) * Mth.cos(getXRot() / 180.0F * (float)Math.PI)) + zMod),
				3.0f,1.0f);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(Mth.cos(getYRot() / 180.0F * (float)Math.PI) * 0.4f)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(Mth.sin(getYRot() / 180.0F * (float)Math.PI) * 0.4f)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(Mth.cos(getYRot() / 180.0F * (float)Math.PI) * 0.4f)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(Mth.sin(getYRot() / 180.0F * (float)Math.PI) * 0.4f)));
		}
	}

	public BaseEnergyShot(EntityType<? extends ThrowableProjectile> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());

		boolean right = shooter.getMainArm() == HumanoidArm.RIGHT;

		if (shooter instanceof Player) {
			if (weapon.getWeaponHand(shooter) == InteractionHand.MAIN_HAND) {
				if (shooter.getMainArm() == HumanoidArm.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HumanoidArm.RIGHT)
					right = false;
			}
		}

		shoot(-Mth.sin(getYRot() / 180.0F * (float)Math.PI) * Mth.cos(getXRot() / 180.0F * (float)Math.PI),
				-Mth.sin(getXRot() / 180.0F * (float)Math.PI),
				Mth.cos(getYRot() / 180.0F * (float)Math.PI) * Mth.cos(getXRot() / 180.0F * (float)Math.PI),
				3.0f,1.0f);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(Mth.cos(getYRot() / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(Mth.sin(getYRot() / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(Mth.cos(getYRot() / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(Mth.sin(getYRot() / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseEnergyShot(EntityType<? extends ThrowableProjectile> entityType, Level world, double x, double y, double z) {
		super(entityType, world);
		this.age = 0;
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
	protected void onHit(HitResult result) {
		if (!level.isClientSide) {
			if (weapon != null && isAlive()) {
				Entity shooter = getOwner();

				if (shooter instanceof LivingEntity) {
					if (result.getType() == HitResult.Type.BLOCK) {
						weapon.doBlockImpact(this, result.getLocation(), (LivingEntity)shooter);
					}
					else if (result.getType() == HitResult.Type.ENTITY) {
						weapon.doEntityImpact(this, ((EntityHitResult)result).getEntity(), (LivingEntity)shooter);
					}
				}
			}

			discard();
		}
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	public void tick() {
		if (!isAlive())
			return;

		Vec3 motion = getDeltaMovement();
		Vec3 position = new Vec3(getX() - motion.x() * 0.5f, getY() - motion.y() * 0.5f, getZ() - motion.z() * 0.5f);
		Vec3 velocityAdjustedPosition = new Vec3(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		HitResult collisionTrace = level.clip(new ClipContext(position, velocityAdjustedPosition, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, null));

		if (collisionTrace.getType() != HitResult.Type.MISS) {
			velocityAdjustedPosition = new Vec3(collisionTrace.getLocation().x, collisionTrace.getLocation().y, collisionTrace.getLocation().z);
		}
		else {
			velocityAdjustedPosition = new Vec3(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		}

		Entity shooter = getOwner();
		EntityHitResult entityTrace = ProjectileUtil.getEntityHitResult(level, this, position, velocityAdjustedPosition, getBoundingBox().expandTowards(motion.x(), motion.y(), motion.z()).inflate(0.5D), entity -> entity.isAlive() && entity.isPickable() && !entity.isSpectator() && entity != shooter);

		if (entityTrace != null)
			collisionTrace = entityTrace;

		if (collisionTrace.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, collisionTrace))
			onHit(collisionTrace);

		xOld = getX();
		yOld = getY();
		zOld = getZ();

		super.tick();

		if (!level.isClientSide) {
			if (age > lifespan) {
				discard();
			}
			else {
				age++;
			}
		}
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	public int getAge() {
		return age;
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
