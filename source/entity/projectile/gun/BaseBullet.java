package net.tslat.aoa3.entity.projectile.gun;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
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

	private Entity cachedOwner = null;

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
		this.age = 0;
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, BaseGun gun, float dmgMultiplier, int piercingValue, float velocity) {
		super(entityType, shooter.level);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = 60;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = Hand.MAIN_HAND;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.yRot, shooter.xRot);

		float vectorX = -MathHelper.sin(yRot * (float)Math.PI / 180f) * MathHelper.cos(xRot * (float)Math.PI / 180.0F);
		float vectorY = -MathHelper.sin(xRot * (float)Math.PI / 180f);
		float vectorZ = MathHelper.cos(yRot * (float)Math.PI / 180f) * MathHelper.cos(xRot * (float)Math.PI / 180f);

		shoot(vectorX, vectorY, vectorZ, velocity, 0.0f);
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(entityType, shooter.level);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.yRot, shooter.xRot);

		boolean right = true;

		if (shooter instanceof PlayerEntity) {
			if (hand == Hand.MAIN_HAND) {
				if (shooter.getMainArm() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HandSide.RIGHT)
					right = false;
			}
		}

		setOwner(shooter);
		shoot(-MathHelper.sin(yRot * (float)Math.PI / 180.0F) * MathHelper.cos(xRot * (float)Math.PI / 180.0F) + xMod, -MathHelper.sin(xRot * (float)Math.PI / 180.0F) + yMod, MathHelper.cos(yRot * (float)Math.PI / 180.0F) * MathHelper.cos(xRot * (float)Math.PI / 180.0F) + zMod,
				3.0f,2.0f);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, float dmgMultiplier, int piercingValue) {
		super(entityType, shooter.level);
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.yRot, shooter.xRot);

		boolean right = shooter.getMainArm() == HandSide.RIGHT;

		if (shooter instanceof PlayerEntity) {
			if (hand == Hand.MAIN_HAND) {
				if (shooter.getMainArm() == HandSide.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HandSide.RIGHT)
					right = false;
			}
		}

		shoot(-MathHelper.sin(yRot * (float)Math.PI / 180.0F) * MathHelper.cos(xRot * (float)Math.PI / 180.0F), -MathHelper.sin(xRot * (float)Math.PI / 180.0F), MathHelper.cos(yRot * (float)Math.PI / 180.0F) * MathHelper.cos(xRot * (float)Math.PI / 180.0F),
				3f,0);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4F)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(MathHelper.cos(yRot / 180.0F * (float)Math.PI) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(MathHelper.sin(yRot / 180.0F * (float)Math.PI) * 0.4F)));
		}
	}

	public BaseBullet(EntityType<? extends ThrowableEntity> entityType, World world, double x, double y, double z) {
		super(entityType, world);

		age = 0;
		lifespan = 60;

		setPos(x, y, z);
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
	public void shoot(double directionX, double directionY, double directionZ, float velocity, float inaccuracy) {
		Vector3d motionVec = (new Vector3d(directionX, directionY, directionZ)).normalize().add(random.nextGaussian() * 0.0075F * inaccuracy, random.nextGaussian() * 0.0075F * inaccuracy, random.nextGaussian() * (double)0.0075F * inaccuracy).scale(velocity);

		setDeltaMovement(motionVec);

		float lat = MathHelper.sqrt(getHorizontalDistanceSqr(motionVec));
		yRot = (float)(MathHelper.atan2(motionVec.x, motionVec.z) * (double)(180F / (float)Math.PI));
		xRot = (float)(MathHelper.atan2(motionVec.y, lat) * (double)(180F / (float)Math.PI));
		yRotO = yRot;
		xRotO = xRot;
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult rayTrace) {
		BlockPos resultPos = rayTrace.getBlockPos();
		BlockState bl = level.getBlockState(resultPos);

		bl.onProjectileHit(level, bl, rayTrace, this);

		if (WorldUtil.checkGameRule(level, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS) && WorldUtil.canBreakBlock(level, resultPos, getOwner(), weapon == null ? null : new ItemStack(weapon))) {
			float hardness = bl.getDestroySpeed(level, resultPos);

			if (hardness >= 0 && hardness <= 0.3f) {
				if (random.nextBoolean()) {
					level.destroyBlock(resultPos, true);
				}
				else {
					level.setBlockAndUpdate(resultPos, Blocks.AIR.defaultBlockState());
				}

				if (random.nextFloat() > hardness / 1.5f)
					return;
			}
		}

		if (!bl.getMaterial().blocksMotion())
			return;

		this.doImpactEffect();
		this.remove();
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult rayTrace) {
		Entity entityResult = rayTrace.getEntity();

		if (entityResult != lastPierceTarget) {
			if (weapon == null) {
				doEntityImpact(entityResult);
			}
			else if (getOwner() instanceof LivingEntity) {
				weapon.doImpactDamage(entityResult, (LivingEntity)getOwner(), this, dmgMulti);
			}

			this.doImpactEffect();
			pierceCount--;
		}

		if (pierceCount >= 0) {
			lastPierceTarget = entityResult;

			return;
		}

		this.remove();
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (level.isClientSide || result.getType() == RayTraceResult.Type.MISS || !isAlive())
			return;

		if (result.getType() == RayTraceResult.Type.BLOCK) {
			onHitBlock((BlockRayTraceResult)result);
		}
		else {
			onHitEntity((EntityRayTraceResult)result);
		}
	}

	public void doEntityImpact(Entity target) {}

	@Override
	public void doImpactEffect() {}

	@Override
	protected void defineSynchedData() {}

	@Override
	public void tick() {
		if (!isAlive())
			return;

		Vector3d motion = getDeltaMovement();
		Vector3d position;
		AxisAlignedBB boundingBox;

		if (tickCount == 1 && !(weapon instanceof BaseThrownWeapon)) {
			float mod = weapon instanceof BaseSniper ? 0.05f : 0.5f;

			position = new Vector3d(getX() - motion.x() * mod, getY() - motion.y() * mod, getZ() - motion.z() * mod);
			boundingBox = new AxisAlignedBB(position.x(), position.y(), position.z(), getX(), getY(), getZ());
		}
		else {
			boundingBox = getBoundingBox();
			position = new Vector3d(getX(), getY(), getZ());
		}

		Vector3d velocityAdjustedPosition = new Vector3d(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		RayTraceResult intersectedBlocksTrace = level.clip(new RayTraceContext(position, velocityAdjustedPosition, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));

		if (intersectedBlocksTrace.getType() != RayTraceResult.Type.MISS) {
			velocityAdjustedPosition = new Vector3d(intersectedBlocksTrace.getLocation().x, intersectedBlocksTrace.getLocation().y, intersectedBlocksTrace.getLocation().z);
		}
		else {
			velocityAdjustedPosition = new Vector3d(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		}

		Entity shooter = getOwner();
		EntityRayTraceResult entityTrace = ProjectileHelper.getEntityHitResult(level, this, position, velocityAdjustedPosition, boundingBox.expandTowards(motion.x(), motion.y(), motion.z()).inflate(0.5d), entity -> entity.isAlive() && entity.isPickable() && !entity.isSpectator() && entity != shooter);

		if (entityTrace != null)
			intersectedBlocksTrace = entityTrace;

		if (intersectedBlocksTrace.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, intersectedBlocksTrace))
			onHit(intersectedBlocksTrace);

		if (!isAlive())
			return;

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

	@Override
	public boolean ignoreExplosion() {
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
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
