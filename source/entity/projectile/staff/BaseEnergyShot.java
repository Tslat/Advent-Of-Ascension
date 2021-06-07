package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

import javax.annotation.Nullable;

public abstract class BaseEnergyShot extends ThrowableEntity {
	protected int lifespan;
	private int age;
	protected EnergyProjectileWeapon weapon;

	private Entity cachedOwner = null;

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);

		this.age = 0;
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = 60;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(posX, posY, posZ, 0, 360);
		setDeltaMovement(new Vector3d(motionX, motionY, motionZ));
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, Entity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = 120;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(posX, posY, posZ, 0, 360);

		setDeltaMovement(new Vector3d(random.nextGaussian() / 33 + 0.03D, -velocity, random.nextGaussian() / 33 + 0.03D));
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.yRot, shooter.xRot);

		boolean right = true;

		if (shooter instanceof PlayerEntity) {
			if (weapon.getWeaponHand(shooter) == Hand.MAIN_HAND) {
				if (shooter.getMainArm() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(((double)(-MathHelper.sin(yRot / 180.0F * (float)Math.PI) * MathHelper.cos(xRot / 180.0F * (float)Math.PI)) + xMod),
				((double)(-MathHelper.sin(xRot / 180.0F * (float)Math.PI)) + yMod),
				((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * MathHelper.cos(xRot / 180.0F * (float)Math.PI)) + zMod),
				3.0f,1.0f);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4f)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4f)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4f)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4f)));
		}
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(entityType, shooter.level);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.yRot, shooter.xRot);

		boolean right = shooter.getMainArm() == HandSide.RIGHT;

		if (shooter instanceof PlayerEntity) {
			if (weapon.getWeaponHand(shooter) == Hand.MAIN_HAND) {
				if (shooter.getMainArm() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(-MathHelper.sin(yRot / 180.0F * (float)Math.PI) * MathHelper.cos(xRot / 180.0F * (float)Math.PI),
				-MathHelper.sin(xRot / 180.0F * (float)Math.PI),
				MathHelper.cos(yRot / 180.0F * (float)Math.PI) * MathHelper.cos(xRot / 180.0F * (float)Math.PI),
				3.0f,1.0f);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, World world, double x, double y, double z) {
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
	protected void onHit(RayTraceResult result) {
		if (!level.isClientSide) {
			if (weapon != null && isAlive()) {
				Entity shooter = getOwner();

				if (shooter instanceof LivingEntity) {
					if (result.getType() == RayTraceResult.Type.BLOCK) {
						weapon.doBlockImpact(this, result.getLocation(), (LivingEntity)shooter);
					}
					else if (result.getType() == RayTraceResult.Type.ENTITY) {
						weapon.doEntityImpact(this, ((EntityRayTraceResult)result).getEntity(), (LivingEntity)shooter);
					}
				}
			}

			remove();
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

		Vector3d motion = getDeltaMovement();
		Vector3d position = new Vector3d(getX() - motion.x() * 0.5f, getY() - motion.y() * 0.5f, getZ() - motion.z() * 0.5f);
		Vector3d velocityAdjustedPosition = new Vector3d(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		RayTraceResult raytraceresult = level.clip(new RayTraceContext(position, velocityAdjustedPosition, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));

		if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
			velocityAdjustedPosition = new Vector3d(raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
		}
		else {
			velocityAdjustedPosition = new Vector3d(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		}

		Entity shooter = getOwner();
		EntityRayTraceResult entityTrace = ProjectileHelper.getEntityHitResult(level, this, position, velocityAdjustedPosition, getBoundingBox().expandTowards(motion.x(), motion.y(), motion.z()).inflate(0.5D), entity -> entity.isAlive() && entity.isPickable() && !entity.isSpectator() && entity != shooter);

		if (entityTrace != null)
			raytraceresult = entityTrace;

		if (raytraceresult.getType() != RayTraceResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
			onHit(raytraceresult);

		xOld = getX();
		yOld = getY();
		zOld = getZ();

		super.tick();

		if (!level.isClientSide) {
			if (age > lifespan) {
				remove();
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
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
