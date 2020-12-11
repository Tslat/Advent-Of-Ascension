package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class StickyCoolBombEntity extends BaseBullet implements HardProjectile {
	private BaseGun weapon;
	private LivingEntity shooter;
	private int ticksInGround = 0;

	public StickyCoolBombEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public StickyCoolBombEntity(World world) {
		super(AoAEntities.Projectiles.STICKY_COOL_BOMB.get(), world);
	}

	public StickyCoolBombEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.STICKY_COOL_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.weapon = gun;
		this.shooter = shooter;
	}

	public StickyCoolBombEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.STICKY_COOL_BOMB.get(), world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult rayTraceResult = (BlockRayTraceResult)result;
			BlockState bl = world.getBlockState(rayTraceResult.getPos());
			double posX = rayTraceResult.getPos().getX();
			double posY = rayTraceResult.getPos().getY();
			double posZ = rayTraceResult.getPos().getZ();

			if (!bl.getMaterial().blocksMovement())
				return;

			ticksInGround++;

			setMotion(0, 0, 0);

			switch(rayTraceResult.getFace()) {
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

			setPosition(posX, posY, posZ);
		}
		else {
			if (!world.isRemote()) {
				if (result.getType() == RayTraceResult.Type.ENTITY) {
					weapon.doImpactDamage(((EntityRayTraceResult)result).getEntity(), getThrower(), this, 1.0f);
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
			setMotion(0, 0, 0);
			ticksInGround++;

			if (ticksInGround >= 80 && !world.isRemote) {
				doImpactEffect();
				return;
			}

			if (!world.isRemote)
				removed = false;
		}
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(owner, world, this, 2.0f);

		if (!world.isRemote)
			remove();
	}
}
