package net.tslat.aoa3.entity.projectiles.gun;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
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
		shoot(vectorX, vectorY, vectorZ, velocity, 0.0f);
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

		shoot(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI) + xMod, -MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI) + yMod, MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI) + zMod,
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

		shoot(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI), -MathHelper.sin(rotationPitch / 180.0F * (float)Math.PI), MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float)Math.PI),
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
		if (world.isRemote)
			return;

		if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState bl = world.getBlockState(result.getBlockPos());

			if (WorldUtil.checkGameRule(world, "destructiveWeaponPhysics")) {
				float hardness = bl.getBlockHardness(world, result.getBlockPos());

				if (hardness >= 0 && hardness <= 0.3f) {
					if (rand.nextBoolean()) {
						world.destroyBlock(result.getBlockPos(), true);
					}
					else {
						world.setBlockToAir(result.getBlockPos());
					}

					if (rand.nextFloat() > hardness / 1.5f)
						return;
				}
			}

			if (!bl.getMaterial().blocksMovement())
				return;

			this.doImpactEffect();
		}
		else {
			if (result.entityHit != lastPierceTarget) {
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

		Vec3d position = new Vec3d(posX, posY, posZ);
		Vec3d velocityAdjustedPosition = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		RayTraceResult intersectedBlocksTrace = world.rayTraceBlocks(position, velocityAdjustedPosition);
		position = new Vec3d(posX, posY, posZ);

		if (intersectedBlocksTrace != null) {
			velocityAdjustedPosition = new Vec3d(intersectedBlocksTrace.hitVec.x, intersectedBlocksTrace.hitVec.y, intersectedBlocksTrace.hitVec.z);
		}
		else {
			velocityAdjustedPosition = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		}

		Entity impactEntity = null;
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(0.5D));
		double d0 = 0.0D;

		for (int i = 0; i < list.size(); ++i) {
			Entity collidedEntity = list.get(i);

			if (collidedEntity.canBeCollidedWith()) {
				if (collidedEntity != ignoreEntity) {
					RayTraceResult interceptTrace = collidedEntity.getEntityBoundingBox().grow(0.30000001192092896D).calculateIntercept(position, velocityAdjustedPosition);

					if (interceptTrace != null) {
						double d1 = position.squareDistanceTo(interceptTrace.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							impactEntity = collidedEntity;
							d0 = d1;
						}
					}
				}
			}
		}

		if (impactEntity != null)
			intersectedBlocksTrace = new RayTraceResult(impactEntity);

		if (intersectedBlocksTrace != null) {
			if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, intersectedBlocksTrace))
				onImpact(intersectedBlocksTrace);
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;

		float dragValue = 0.99F;

		if (isInWater()) {
			for (int j = 0; j < 4; ++j)	{
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * 0.25D, posY - motionY * 0.25D, posZ - motionZ * 0.25D, motionX, motionY, motionZ);
			}

			dragValue = 0.8F;
		}

		motionX *= dragValue;
		motionY *= dragValue;
		motionZ *= dragValue;

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

	@Nullable
	public BaseGun getWeapon() {
		return weapon;
	}
}
