package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class BaseBullet extends ThrowableEntity implements HardProjectile {
	private float dmgMulti;
	private int lifespan;
	private int pierceCount;
	private int age;
	private BaseGun weapon;
	private Entity lastPierceTarget;
	private Hand hand;

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
		this.age = 0;
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, BaseGun gun, float dmgMultiplier, int piercingValue, float velocity) {
		super(entityType, shooter.world);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = 60;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.owner = shooter;
		this.ignoreEntity = shooter;
		this.hand = Hand.MAIN_HAND;

		setLocationAndAngles(shooter.getPosX(), shooter.getPosY() + shooter.getEyeHeight(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);

		float vectorX = -MathHelper.sin(rotationYaw * (float)Math.PI / 180f) * MathHelper.cos(rotationPitch * (float)Math.PI / 180.0F);
		float vectorY = -MathHelper.sin(rotationPitch * (float)Math.PI / 180f);
		float vectorZ = MathHelper.cos(rotationYaw * (float)Math.PI / 180f) * MathHelper.cos(rotationPitch * (float)Math.PI / 180f);

		shoot(vectorX, vectorY, vectorZ, velocity, 0.0f);
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(entityType, shooter.world);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;
		this.owner = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.getPosX(), shooter.getPosY() + shooter.getEyeHeight(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);

		boolean right = true;

		if (shooter instanceof PlayerEntity) {
			if (hand == Hand.MAIN_HAND) {
				if (shooter.getPrimaryHand() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getPrimaryHand() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(-MathHelper.sin(rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(rotationPitch * (float)Math.PI / 180.0F) + xMod, -MathHelper.sin(rotationPitch * (float)Math.PI / 180.0F) + yMod, MathHelper.cos(rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(rotationPitch * (float)Math.PI / 180.0F) + zMod,
				3.0f,2.0f);

		if (right) {
			setPosition(getMotion().getX() * 0.5f + getPosX() - ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() + ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPosition(getMotion().getX() * 0.5f + getPosX() + ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() - ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue) {
		super(entityType, shooter.world);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;
		this.owner = shooter;
		this.ignoreEntity = shooter;
		setLocationAndAngles(shooter.getPosX(), shooter.getPosY() + shooter.getEyeHeight(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);

		boolean right = shooter.getPrimaryHand() == HandSide.RIGHT;

		if (shooter instanceof PlayerEntity) {
			if (hand == Hand.MAIN_HAND) {
				if (shooter.getPrimaryHand() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getPrimaryHand() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(-MathHelper.sin(rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(rotationPitch * (float)Math.PI / 180.0F), -MathHelper.sin(rotationPitch * (float)Math.PI / 180.0F), MathHelper.cos(rotationYaw * (float)Math.PI / 180.0F) * MathHelper.cos(rotationPitch * (float)Math.PI / 180.0F),
				3f,0);

		if (right) {
			setPosition(getMotion().getX() * 0.5f + getPosX() - ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() + ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPosition(getMotion().getX() * 0.5f + getPosX() + ((double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)), getMotion().getY() * 0.5f + getPosY() - 0.3D, getMotion().getZ() * 0.5f + getPosZ() - ((double)(MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, World world, double x, double y, double z) {
		super(entityType, world);

		age = 0;
		lifespan = 60;

		setPosition(x, y, z);
	}

	@Override
	public void shoot(double directionX, double directionY, double directionZ, float velocity, float inaccuracy) {
		Vec3d motionVec = (new Vec3d(directionX, directionY, directionZ)).normalize().add(rand.nextGaussian() * 0.0075F * inaccuracy, rand.nextGaussian() * 0.0075F * inaccuracy, rand.nextGaussian() * (double)0.0075F * inaccuracy).scale(velocity);

		setMotion(motionVec);

		float lat = MathHelper.sqrt(horizontalMag(motionVec));
		rotationYaw = (float)(MathHelper.atan2(motionVec.x, motionVec.z) * (double)(180F / (float)Math.PI));
		rotationPitch = (float)(MathHelper.atan2(motionVec.y, lat) * (double)(180F / (float)Math.PI));
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (world.isRemote || result.getType() == RayTraceResult.Type.MISS || !isAlive())
			return;

		if (result.getType() == RayTraceResult.Type.BLOCK) {
			BlockPos resultPos = ((BlockRayTraceResult)result).getPos();
			BlockState bl = world.getBlockState(resultPos);

			if (WorldUtil.checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS)) {
				float hardness = bl.getBlockHardness(world, resultPos);

				if (hardness >= 0 && hardness <= 0.3f) {
					if (rand.nextBoolean()) {
						world.destroyBlock(resultPos, true);
					}
					else {
						world.setBlockState(resultPos, Blocks.AIR.getDefaultState());
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
			Entity entityResult = ((EntityRayTraceResult)result).getEntity();

			if (entityResult != lastPierceTarget) {
				if (weapon == null) {
					doEntityImpact(entityResult);
				}
				else {
					weapon.doImpactDamage(entityResult, getThrower(), this, dmgMulti);
				}

				this.doImpactEffect();
				pierceCount--;
			}

			if (pierceCount >= 0) {
				lastPierceTarget = entityResult;
				return;
			}
		}

		remove();
	}

	public void doEntityImpact(Entity target) {}

	@Override
	public void doImpactEffect() {}

	@Override
	protected void registerData() {}

	@Override
	public void tick() {
		if (!isAlive())
			return;

		Vec3d motion = getMotion();
		Vec3d position;
		AxisAlignedBB boundingBox;

		if (ticksExisted == 1 && !(weapon instanceof BaseThrownWeapon)) {
			float mod = weapon instanceof BaseSniper ? 0.1f : 0.5f;

			position = new Vec3d(getPosX() - motion.getX() * mod, getPosY() - motion.getY() * mod, getPosZ() - motion.getZ() * mod);
			boundingBox = new AxisAlignedBB(position.getX(), position.getY(), position.getZ(), getPosX(), getPosY(), getPosZ());
		}
		else {
			boundingBox = getBoundingBox();
			position = new Vec3d(getPosX(), getPosY(), getPosZ());
		}

		Vec3d velocityAdjustedPosition = new Vec3d(getPosX() + motion.getX(), getPosY() + motion.getY(), getPosZ() + motion.getZ());
		RayTraceResult intersectedBlocksTrace = world.rayTraceBlocks(new RayTraceContext(position, velocityAdjustedPosition, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));

		if (intersectedBlocksTrace.getType() != RayTraceResult.Type.MISS) {
			velocityAdjustedPosition = new Vec3d(intersectedBlocksTrace.getHitVec().x, intersectedBlocksTrace.getHitVec().y, intersectedBlocksTrace.getHitVec().z);
		}
		else {
			velocityAdjustedPosition = new Vec3d(getPosX() + motion.getX(), getPosY() + motion.getY(), getPosZ() + motion.getZ());
		}

		EntityRayTraceResult entityTrace = ProjectileHelper.rayTraceEntities(world, this, position, velocityAdjustedPosition, boundingBox.expand(motion.getX(), motion.getY(), motion.getZ()).grow(0.5d), entity -> entity.isAlive() && entity.canBeCollidedWith() && !entity.isSpectator() && entity != ignoreEntity);

		if (entityTrace != null)
			intersectedBlocksTrace = entityTrace;

		if (intersectedBlocksTrace.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, intersectedBlocksTrace))
			onImpact(intersectedBlocksTrace);

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

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	public Hand getHand() {
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

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
