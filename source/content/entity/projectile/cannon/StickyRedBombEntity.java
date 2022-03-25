package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class StickyRedBombEntity extends BaseBullet implements HardProjectile {
	private BaseGun weapon;
	private LivingEntity shooter;
	private int ticksInGround = 0;

	public StickyRedBombEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public StickyRedBombEntity(World world) {
		super(AoAEntities.Projectiles.STICKY_RED_BOMB.get(), world);
	}

	public StickyRedBombEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.STICKY_RED_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.weapon = gun;
		this.shooter = shooter;
	}

	public StickyRedBombEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.STICKY_RED_BOMB.get(), world, x, y, z);
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult rayTraceResult = (BlockRayTraceResult)result;
			BlockState bl = level.getBlockState(rayTraceResult.getBlockPos());
			double posX = rayTraceResult.getBlockPos().getX();
			double posY = rayTraceResult.getBlockPos().getY();
			double posZ = rayTraceResult.getBlockPos().getZ();

			if (!bl.getMaterial().blocksMotion())
				return;

			ticksInGround++;

			setDeltaMovement(0, 0, 0);

			switch(rayTraceResult.getDirection()) {
				case UP:
					posY += 1;
					break;
				case DOWN:
					posY -= 1;
					break;
				case SOUTH:
					posZ += 0.5d;
					break;
				case NORTH:
					posZ -= 0.5d;
					break;
				case EAST:
					posX += 0.5d;
					break;
				case WEST:
					posX -= 0.5d;
					break;
				default:
					break;
			}

			setPos(posX, posY, posZ);
		}
		else {
			if (!level.isClientSide()) {
				if (result.getType() == RayTraceResult.Type.ENTITY) {
					Entity shooter = getOwner();

					if (shooter instanceof LivingEntity)
						weapon.doImpactDamage(((EntityRayTraceResult)result).getEntity(), (LivingEntity)shooter, this, 1.0f);

					doImpactEffect();
				}

				remove();
			}
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (ticksInGround > 0) {
			setDeltaMovement(0, 0, 0);

			ticksInGround++;

			if (ticksInGround >= 80 && !level.isClientSide) {
				doImpactEffect();
				return;
			}

			if (!level.isClientSide)
				removed = false;
		}
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, 2.0f);

		if (!level.isClientSide)
			remove();
	}
}
