package net.tslat.aoa3.entity.projectiles.arrow;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.EntityHollyArrowShot;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

import javax.annotation.Nullable;
import java.util.List;

public class EntityHollyArrow extends EntityArrow implements HardProjectile {
	private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, Entity::canBeCollidedWith);
	private static final DataParameter<Byte> CRITICAL = EntityDataManager.<Byte>createKey(EntityHollyArrow.class, DataSerializers.BYTE);
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	private int inData;
	private boolean hasHitGround;
	public boolean inGround;
	public int timeInGround;
	public EntityArrow.PickupStatus pickupStatus = PickupStatus.DISALLOWED;
	private int arrowShake;
	private Entity shooter;
	private int ticksInGround;
	private int ticksInAir;
	private double baseDamage = 10;
	private int knockbackStrength;
	private BaseBow bow;

	public EntityHollyArrow(World world) {
		super(world);
	}

	public EntityHollyArrow(World world, double x, double y, double z) {
		super(world);
		setPosition(x, y, z);
	}

	public EntityHollyArrow(World world, EntityHollyArrowShot archergunShot) {
		super(world);
		this.shooter = archergunShot.getThrower();
		this.shootingEntity = this.shooter;
		this.bow = null;
		this.baseDamage = 0;
		this.knockbackStrength = 0;
		setSize(0.5f, 0.5f);
		setPositionAndRotation(archergunShot.posX, archergunShot.posY, archergunShot.posZ, archergunShot.rotationYaw, archergunShot.rotationPitch);

		if (shooter instanceof EntityPlayer)
			pickupStatus = PickupStatus.ALLOWED;
	}

	public EntityHollyArrow(World world, BaseBow bow, EntityLivingBase shooter, double damage) {
		super(world);
		this.shooter = shooter;
		this.shootingEntity = shooter;
		this.bow = bow;
		this.baseDamage = damage;
		setSize(0.5f, 0.5f);
		setPosition(shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);

		if (shooter instanceof EntityPlayer)
			pickupStatus = PickupStatus.ALLOWED;
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(CRITICAL, (byte)0);
	}

	@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
		motionX += shooter.motionX;
		motionZ += shooter.motionZ;

		if (!shooter.onGround)
			motionY += shooter.motionY;
	}

	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x = x / (double)f;
		y = y / (double)f;
		z = z / (double)f;
		x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
		x = x * (double)velocity;
		y = y * (double)velocity;
		z = z * (double)velocity;
		motionX = x;
		motionY = y;
		motionZ = z;
		float f1 = MathHelper.sqrt(x * x + z * z);
		rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
		rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
		prevRotationYaw = rotationYaw;
		prevRotationPitch = rotationPitch;
		ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void setVelocity(double x, double y, double z) {
		motionX = x;
		motionY = y;
		motionZ = z;

		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			rotationPitch = (float)(MathHelper.atan2(y, (double)f) * (180D / Math.PI));
			rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
			prevRotationPitch = rotationPitch;
			prevRotationYaw = rotationYaw;
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate() {
		if (isDead)
			return;

		if (!world.isRemote)
			setFlag(6, isGlowing());

		super.onEntityUpdate();

		if (bow != null && !hasHitGround)
			bow.doArrowTick(this, shooter);

		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float)(MathHelper.atan2(motionX, motionZ) * (180D / Math.PI));
			rotationPitch = (float)(MathHelper.atan2(motionY, (double)f) * (180D / Math.PI));
			prevRotationYaw = rotationYaw;
			prevRotationPitch = rotationPitch;
		}

		BlockPos blockpos = new BlockPos(xTile, yTile, zTile);
		IBlockState iblockstate = world.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		if (iblockstate.getMaterial() != Material.AIR) {
			AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(world, blockpos);

			if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(posX, posY, posZ))) {
				inGround = true;
				hasHitGround = true;
			}
		}

		if (arrowShake > 0)
			--arrowShake;

		if (inGround) {
			int j = block.getMetaFromState(iblockstate);

			if ((block != inTile || j != inData) && !world.collidesWithAnyBlock(getEntityBoundingBox().grow(0.05D))) {
				inGround = false;
				motionX *= (double)(rand.nextFloat() * 0.2F);
				motionY *= (double)(rand.nextFloat() * 0.2F);
				motionZ *= (double)(rand.nextFloat() * 0.2F);
				ticksInGround = 0;
				ticksInAir = 0;
			}
			else {
				++ticksInGround;

				if (ticksInGround >= 1200)
					setDead();
			}

			++timeInGround;
		}
		else {
			timeInGround = 0;
			++ticksInAir;
			Vec3d vec3d1 = new Vec3d(posX, posY, posZ);
			Vec3d vec3d = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
			RayTraceResult raytraceresult = world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
			vec3d1 = new Vec3d(posX, posY, posZ);
			vec3d = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

			if (raytraceresult != null)
				vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);

			Entity entity = findEntityOnPath(vec3d1, vec3d);

			if (entity != null)
				raytraceresult = new RayTraceResult(entity);

			if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)	{
				EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

				if (shooter instanceof EntityPlayer && !((EntityPlayer)shooter).canAttackPlayer(entityplayer))
					raytraceresult = null;
			}

			if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
				onHit(raytraceresult);

			if (getIsCritical() && !hideCriticalParticles()) {
				for (int k = 0; k < 4; ++k)	{
					world.spawnParticle(EnumParticleTypes.CRIT, posX + motionX * (double)k / 4.0D, posY + motionY * (double)k / 4.0D, posZ + motionZ * (double)k / 4.0D, -motionX, -motionY + 0.2D, -motionZ);
				}
			}

			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			float f4 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float)(MathHelper.atan2(motionX, motionZ) * (180D / Math.PI));

			for (rotationPitch = (float)(MathHelper.atan2(motionY, (double)f4) * (180D / Math.PI)); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F) {;}

			while (rotationPitch - prevRotationPitch >= 180.0F) {
				prevRotationPitch += 360.0F;
			}

			while (rotationYaw - prevRotationYaw < -180.0F)	{
				prevRotationYaw -= 360.0F;
			}

			while (rotationYaw - prevRotationYaw >= 180.0F)	{
				prevRotationYaw += 360.0F;
			}

			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
			float f1 = 0.99F;

			if (isInWater()) {
				for (int i = 0; i < 4; ++i)	{
					world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * 0.25D, posY - motionY * 0.25D, posZ - motionZ * 0.25D, motionX, motionY, motionZ);
				}

				f1 = 0.6F;
			}

			if (isWet())
				extinguish();

			motionX *= (double)f1;
			motionY *= (double)f1;
			motionZ *= (double)f1;
			motionY -= getGravityVelocity();

			setPosition(posX, posY, posZ);
			doBlockCollisions();
		}
	}

	@Override
	protected void onHit(RayTraceResult rayTrace) {
		Entity target = rayTrace.entityHit;

		if (target != null) {
			if (world.isRemote && target instanceof EntityPlayer && ticksInAir <= 3)
				return;

			float vectoredVelocity = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
			float damage = (float)(baseDamage / 1.5f) / (3f / vectoredVelocity);

			if (getIsCritical())
				damage += damage / 4f + rand.nextFloat() * (damage / 1.5f);

			DamageSource damageSource;

			if (shooter == null) {
				damageSource = DamageSource.causeArrowDamage(this, this);
			}
			else {
				damageSource = DamageSource.causeArrowDamage(this, shooter);
			}

			if (bow != null)
				damage = bow.getArrowDamage(this, target, damage);

			if (target.attackEntityFrom(damageSource, damage)) {
				if (isBurning() && !(target instanceof EntityEnderman))
					target.setFire(5);

				if (target instanceof EntityLivingBase)	{
					EntityLivingBase entitylivingbase = (EntityLivingBase)target;

					if (!world.isRemote)
						entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);

					if (knockbackStrength > 0)	{
						float motionVector = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);

						if (motionVector > 0.0F)
							entitylivingbase.addVelocity(motionX * (double)knockbackStrength / 2d * 0.6000000238418579D / (double)motionVector, 0.1D, motionZ * (double)knockbackStrength / 2d * 0.6000000238418579D / (double)motionVector);
					}

					if (shooter instanceof EntityLivingBase) {
						EnchantmentHelper.applyThornEnchantments(entitylivingbase, shooter);
						EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)shooter, entitylivingbase);
					}

					arrowHit(entitylivingbase);

					if (shooter != null && entitylivingbase != shooter && entitylivingbase instanceof EntityPlayer && shooter instanceof EntityPlayerMP)
						((EntityPlayerMP)shooter).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
				}

				playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));

				if (bow != null && !hasHitGround)
					bow.doImpactEffect(this, target, shooter, damage);

				if (!(target instanceof EntityEnderman))
					setDead();
			}
			else {
				motionX *= -0.10000000149011612D;
				motionY *= -0.10000000149011612D;
				motionZ *= -0.10000000149011612D;
				rotationYaw += 180.0F;
				prevRotationYaw += 180.0F;
				ticksInAir = 0;

				if (!world.isRemote && motionX * motionX + motionY * motionY + motionZ * motionZ < 0.0010000000474974513D) {
					if (pickupStatus == EntityArrow.PickupStatus.ALLOWED)
						entityDropItem(getArrowStack(), 0.1F);

					setDead();
				}
			}
		}
		else {
			BlockPos blockpos = rayTrace.getBlockPos();
			xTile = blockpos.getX();
			yTile = blockpos.getY();
			zTile = blockpos.getZ();
			IBlockState iblockstate = world.getBlockState(blockpos);
			inTile = iblockstate.getBlock();
			inData = inTile.getMetaFromState(iblockstate);
			motionX = (double)((float)(rayTrace.hitVec.x - posX));
			motionY = (double)((float)(rayTrace.hitVec.y - posY));
			motionZ = (double)((float)(rayTrace.hitVec.z - posZ));
			float f2 = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
			posX -= motionX / (double)f2 * 0.05000000074505806D;
			posY -= motionY / (double)f2 * 0.05000000074505806D;
			posZ -= motionZ / (double)f2 * 0.05000000074505806D;
			playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
			inGround = true;
			arrowShake = 7;
			setIsCritical(false);

			if (iblockstate.getMaterial() != Material.AIR)
				inTile.onEntityCollision(world, blockpos, iblockstate, this);

			if (!world.isRemote && bow != null && !hasHitGround)
				bow.doBlockImpact(this, iblockstate, shooter);

			hasHitGround = true;
		}
	}
	
	@Override
	public void move(MoverType type, double x, double y, double z) {
		super.move(type, x, y, z);

		if (inGround) {
			xTile = MathHelper.floor(posX);
			yTile = MathHelper.floor(posY);
			zTile = MathHelper.floor(posZ);
		}
	}

	public float getGravityVelocity() {
		return 0.05000000074505806F;
	}

	public boolean hideCriticalParticles() {
		return false;
	}

	@Override
	protected void arrowHit(EntityLivingBase target) {}

	@Nullable
	@Override
	protected Entity findEntityOnPath(Vec3d start, Vec3d end) {
		Entity entity = null;
		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), ARROW_TARGETS);
		double d0 = 0.0D;

		for (int i = 0; i < list.size(); ++i) {
			Entity entity1 = list.get(i);

			if (entity1 != this.shooter || this.ticksInAir >= 5)	{
				AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
				RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

				if (raytraceresult != null)	{
					double d1 = start.squareDistanceTo(raytraceresult.hitVec);

					if (d1 < d0 || d0 == 0.0D) {
						entity = entity1;
						d0 = d1;
					}
				}
			}
		}

		return entity;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!world.isRemote && inGround && arrowShake <= 0) {
			boolean flag = pickupStatus == EntityArrow.PickupStatus.ALLOWED || pickupStatus == EntityArrow.PickupStatus.CREATIVE_ONLY && entityIn.capabilities.isCreativeMode;

			if (pickupStatus == EntityArrow.PickupStatus.ALLOWED && !entityIn.inventory.addItemStackToInventory(getArrowStack()))
				flag = false;

			if (flag) {
				entityIn.onItemPickup(this, 1);
				setDead();
			}
		}
	}

	public double getDamage() {
		return baseDamage;
	}

	public void setDamage(double damage) {
		baseDamage = damage;
	}

	public void setIsCritical(boolean critical) {
		byte b0 = dataManager.get(CRITICAL);

		if (critical) {
			dataManager.set(CRITICAL, (byte)(b0 | 1));
		}
		else {
			dataManager.set(CRITICAL, (byte)(b0 & -2));
		}
	}

	@Override
	public boolean getIsCritical() {
		return (dataManager.get(CRITICAL) & 1) != 0;
	}

	@Override
	public void setEnchantmentEffectsFromEntity(EntityLivingBase shooter, float mod) {
		int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.POWER, shooter);
		int j = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.PUNCH, shooter);
		setDamage((double)(mod * 2.0F) + rand.nextGaussian() * 0.25D + (double)((float)world.getDifficulty().getId() * 0.11F));

		if (i > 0)
			setDamage(getDamage() + (double)i * 0.5D + 0.5D);

		if (j > 0)
			setKnockbackStrength(j);

		if (EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, shooter) > 0)
			setFire(100);
	}

	@Override
	public void setKnockbackStrength(int knockback) {
		this.knockbackStrength = knockback;
	}

	public int getKnockbackStrength() {
		return this.knockbackStrength;
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ItemRegister.hollyArrow);
	}

	public Entity getShooter() {
		return shooter;
	}

	@Override
	public void doImpactEffect() {}

	public BaseBow getShootingBow() {
		return bow;
	}
}
