package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

import java.util.List;

public class BaseBullet extends EntityThrowable implements HardProjectile {
	private float dmgMulti;
	private int lifespan;
	private int pierceCount;
	private int age;
	private BaseGun weapon;
	private Entity lastPierceTarget;
	private EnumHand hand;

	public BaseBullet(World world) {
		super(world);
		this.age = 0;
	}

	public BaseBullet(EntityLivingBase shooter, BaseGun gun, float dmgMultiplier, int piercingValue, float velocity) {
		super(shooter.world);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = 60;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		this.hand = EnumHand.MAIN_HAND;

		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

		float vectorX = -MathHelper.sin(shooter.rotationYaw * 0.017453292F) * MathHelper.cos(shooter.rotationPitch * 0.017453292F);
		float vectorY = -MathHelper.sin((shooter.rotationPitch) * 0.017453292F);
		float vectorZ = MathHelper.cos(shooter.rotationYaw * 0.017453292F) * MathHelper.cos(shooter.rotationPitch * 0.017453292F);
		shoot((double)vectorX, (double)vectorY, (double)vectorZ, velocity, 0.0f);
	}

	public BaseBullet(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(shooter.world);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

		boolean right = true;

		if (shooter instanceof EntityPlayer) {
			if (hand == EnumHand.MAIN_HAND) {
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

		shoot((double)(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI) + xMod),
				(double)(-MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI) + yMod),
				(double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI) + zMod),
				3.0f,2.0f);

		posX += motionX * 0.5;
		posY += motionY * 0.5;
		posZ += motionZ * 0.5;
	}

	public BaseBullet(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, float dmgMultiplier, int piercingValue) {
		super(shooter.world);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;
		this.thrower = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

		boolean right = true;

		if (shooter instanceof EntityPlayer) {
			if (hand == EnumHand.MAIN_HAND) {
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
				3.0f,2.0f);

		posX += motionX * 0.5;
		posY += motionY * 0.5;
		posZ += motionZ * 0.5;
	}

	public BaseBullet(World world, double x, double y, double z) {
		super(world);
		age = 0;
		lifespan = 60;
		setPosition(x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState bl = world.getBlockState(result.getBlockPos());

			if (!bl.getMaterial().blocksMovement())
				return;

			this.doImpactEffect();
		}
		else {
			if (result.entityHit != lastPierceTarget && !world.isRemote) {
				if (weapon == null) {
					doEntityImpact(result.entityHit);
				}
				else {
					weapon.doImpactDamage(result.entityHit, getThrower(), this, dmgMulti);
				}

				this.doImpactEffect();
				pierceCount--;
			}

			if (pierceCount >= 0) {
				lastPierceTarget = result.entityHit;
				return;
			}
		}

		if (!world.isRemote)
			setDead();
	}

	public void doEntityImpact(Entity target) {}

	@Override
	public void doImpactEffect() {}

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
			if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
				onImpact(raytraceresult);
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;

		float f1 = 0.99F;

		if (isInWater()) {
			for (int j = 0; j < 4; ++j)	{
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * 0.25D, posY - motionY * 0.25D, posZ - motionZ * 0.25D, motionX, motionY, motionZ);
			}

			f1 = 0.8F;
		}

		motionX *= (double)f1;
		motionY *= (double)f1;
		motionZ *= (double)f1;

		motionY -= getGravityVelocity();

		setPosition(posX, posY, posZ);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	public EnumHand getHand() {
		return hand;
	}

	public BaseBullet setMaxAge(final int maxAge) {
		this.lifespan = maxAge;
		return this;
	}

	public BaseBullet setPierceCount(final int pierceCount) {
		this.pierceCount = pierceCount;
		return this;
	}

	public int getAge() {
		return age;
	}
}
