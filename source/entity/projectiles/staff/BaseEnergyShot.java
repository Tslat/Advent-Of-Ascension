package net.tslat.aoa3.entity.projectiles.staff;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;

import java.util.List;

public class BaseEnergyShot extends EntityThrowable {
	private int lifespan;
	private int age;
	private EnergyProjectileWeapon weapon;

	public BaseEnergyShot(World world) {
		super(world);
		this.age = 0;
	}

	public BaseEnergyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(shooter.world);
		this.age = 0;
		this.lifespan = 60;
		this.weapon = weapon;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(posX, posY, posZ, 0, 360);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	public BaseEnergyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(shooter.world);
		this.age = 0;
		this.lifespan = 120;
		this.weapon = weapon;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(posX, posY, posZ, 0, 360);

		this.motionX = rand.nextGaussian() / 33 + 0.03D;
		this.motionY = -velocity;
		this.motionZ = rand.nextGaussian() / 33 + 0.03D;
	}

	public BaseEnergyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge, float xMod, float yMod, float zMod) {
		super(shooter.world);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

		boolean right = true;

		if (shooter instanceof EntityPlayer) {
			if (weapon.getWeaponHand() == EnumHand.MAIN_HAND) {
				if (shooter.getPrimaryHand() == EnumHandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getPrimaryHand() == EnumHandSide.RIGHT)
					right = false;
			}
		}

		if (right) {
			setPosition(posX - ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), posY - 0.2D, posZ - ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPosition(posX + ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), posY - 0.2D, posZ + ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}

		shoot(((double)(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI)) + xMod),
				((double)(-MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI)) + yMod),
				((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI)) + zMod),
				3.0f,1.0f);

		posX += motionX * 0.05;
		posY += motionY * 0.05;
		posZ += motionZ * 0.05;
	}

	public BaseEnergyShot(EntityLivingBase shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(shooter.world);
		this.age = 0;
		this.lifespan = maxAge;
		this.weapon = weapon;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

		boolean right = true;

		if (shooter instanceof EntityPlayer) {
			if (weapon.getWeaponHand() == EnumHand.MAIN_HAND) {
				if (shooter.getPrimaryHand() == EnumHandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getPrimaryHand() == EnumHandSide.RIGHT)
					right = false;
			}
		}

		if (right) {
			setPosition(posX - ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), posY - 0.2D, posZ - ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPosition(posX + ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), posY - 0.2D, posZ + ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}

		shoot((double)(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI)),
				(double)(-MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI)),
				(double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI)),
				3.0f,1.0f);

		posX += motionX * 0.5;
		posY += motionY * 0.5;
		posZ += motionZ * 0.5;
	}

	public BaseEnergyShot(World world, double x, double y, double z) {
		super(world);
		this.age = 0;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote && result.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState bl = world.getBlockState(result.getBlockPos());

			weapon.doBlockImpact(this, result.getBlockPos(), this.thrower);
		}
		else {
			if (!world.isRemote && result.entityHit != null)
				weapon.doEntityImpact(this, result.entityHit, thrower);
		}

		if (!world.isRemote)
			setDead();
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	public void onUpdate() {
		if (isDead)
			return;

		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;

		super.onEntityUpdate();

		if (!world.isRemote) {
			if (inGround || age > lifespan) {
				setDead();
				return;
			}
			else {
				age++;
			}
		}

		Vec3d vec3d = new Vec3d(posX, posY, posZ);
		Vec3d vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		RayTraceResult raytraceresult = world.rayTraceBlocks(vec3d, vec3d1);
		vec3d = new Vec3d(posX, posY, posZ);

		if (raytraceresult != null) {
			vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
		}
		else {
			vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		}

		Entity entity = null;
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(0.5D));
		double d0 = 0.0D;

		for (int i = 0; i < list.size(); ++i) {
			Entity entity1 = list.get(i);

			if (entity1.canBeCollidedWith()) {
				if (entity1 != ignoreEntity) {
					AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
					RayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

					if (raytraceresult1 != null) {
						double d1 = vec3d.squareDistanceTo(raytraceresult1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}
		}

		if (entity != null)
			raytraceresult = new RayTraceResult(entity);

		if (raytraceresult != null) {
			if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
				onImpact(raytraceresult);
			}
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		motionX *= 0.99d;
		motionY *= 0.99d;
		motionZ *= 0.99d;

		motionY -= getGravityVelocity();

		setPosition(posX, posY, posZ);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	public int getAge() {
		return age;
	}
}
