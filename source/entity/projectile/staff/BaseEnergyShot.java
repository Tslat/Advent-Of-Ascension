package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public abstract class BaseEnergyShot extends ThrowableEntity {
	protected int lifespan;
	private int age;
	protected EnergyProjectileWeapon weapon;

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);

		this.age = 0;
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(entityType, shooter.world);
		this.age = 0;
		this.lifespan = 60;
		this.weapon = weapon;
		this.owner = shooter;
		this.ignoreEntity = shooter;

		setLocationAndAngles(posX, posY, posZ, 0, 360);
		setMotion(new Vec3d(motionX, motionY, motionZ));
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(entityType, shooter.world);
		this.age = 0;
		this.lifespan = 120;
		this.weapon = weapon;
		this.owner = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(posX, posY, posZ, 0, 360);

		setMotion(new Vec3d(rand.nextGaussian() / 33 + 0.03D, -velocity, rand.nextGaussian() / 33 + 0.03D));
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(entityType, shooter.world);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;
		this.owner = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.getPosX(), shooter.getPosY() + shooter.getEyeHeight(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);

		boolean right = true;

		if (shooter instanceof PlayerEntity) {
			if (weapon.getWeaponHand(shooter) == Hand.MAIN_HAND) {
				if (shooter.getPrimaryHand() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getPrimaryHand() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(((double)(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI)) + xMod),
				((double)(-MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI)) + yMod),
				((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI)) + zMod),
				3.0f,1.0f);

		if (right) {
			setPosition(getMotion().getX() * 0.5f + getPosX() - ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4f)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() + ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4f)));
		}
		else {
			setPosition(getMotion().getX() * 0.5f + getPosX() + ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4f)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() - ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4f)));
		}
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(entityType, shooter.world);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;
		this.owner = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.getPosX(), shooter.getPosY() + shooter.getEyeHeight(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);

		boolean right = shooter.getPrimaryHand() == HandSide.RIGHT;

		if (shooter instanceof PlayerEntity) {
			if (weapon.getWeaponHand(shooter) == Hand.MAIN_HAND) {
				if (shooter.getPrimaryHand() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getPrimaryHand() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI),
				-MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI),
				MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI),
				3.0f,1.0f);

		if (right) {
			setPosition(getMotion().getX() * 0.5f + getPosX() - ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() + ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPosition(getMotion().getX() * 0.5f + getPosX() + ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() - ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseEnergyShot(EntityType<? extends ThrowableEntity> entityType, World world, double x, double y, double z) {
		super(entityType, world);
		this.age = 0;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (weapon != null && isAlive()) {
				if (result.getType() == RayTraceResult.Type.BLOCK) {
					weapon.doBlockImpact(this, ((BlockRayTraceResult)result).getPos(), owner);
				}
				else if (result.getType() == RayTraceResult.Type.ENTITY) {
					weapon.doEntityImpact(this, ((EntityRayTraceResult)result).getEntity(), owner);
				}
			}

			remove();
		}
	}

	@Override
	protected void registerData() {}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	public void tick() {
		if (!isAlive())
			return;

		Vec3d motion = getMotion();
		Vec3d position = new Vec3d(getPosX() - motion.getX() * 0.5f, getPosY() - motion.getY() * 0.5f, getPosZ() - motion.getZ() * 0.5f);
		Vec3d velocityAdjustedPosition = new Vec3d(getPosX() + motion.getX(), getPosY() + motion.getY(), getPosZ() + motion.getZ());
		RayTraceResult raytraceresult = world.rayTraceBlocks(new RayTraceContext(position, velocityAdjustedPosition, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));

		if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
			velocityAdjustedPosition = new Vec3d(raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
		}
		else {
			velocityAdjustedPosition = new Vec3d(getPosX() + motion.getX(), getPosY() + motion.getY(), getPosZ() + motion.getZ());
		}

		EntityRayTraceResult entityTrace = ProjectileHelper.rayTraceEntities(world, this, position, velocityAdjustedPosition, getBoundingBox().expand(motion.getX(), motion.getY(), motion.getZ()).grow(0.5D), entity -> entity.isAlive() && entity.canBeCollidedWith() && !entity.isSpectator() && entity != ignoreEntity);

		if (entityTrace != null)
			raytraceresult = entityTrace;

		if (raytraceresult.getType() != RayTraceResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
			onImpact(raytraceresult);

		lastTickPosX = getPosX();
		lastTickPosY = getPosY();
		lastTickPosZ = getPosZ();

		super.tick();

		if (!world.isRemote) {
			if (inGround || age > lifespan) {
				remove();
			}
			else {
				age++;
			}
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	public int getAge() {
		return age;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
