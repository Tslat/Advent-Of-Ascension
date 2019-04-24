package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class EntityStickyRedBomb extends BaseBullet implements HardProjectile {
	private BaseGun weapon;
	private EntityLivingBase shooter;
	private int ticksInGround = 0;

	public EntityStickyRedBomb(World world) {
		super(world);
	}

	public EntityStickyRedBomb(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.weapon = gun;
		this.shooter = shooter;
	}

	public EntityStickyRedBomb(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState bl = world.getBlockState(result.getBlockPos());

			if (!bl.getMaterial().blocksMovement())
				return;

			ticksInGround++;

			posX = result.getBlockPos().getX();
			posY = result.getBlockPos().getY();
			posZ = result.getBlockPos().getZ();
			motionY = 0;
			motionX = 0;
			motionZ = 0;

			switch(result.sideHit) {
				case UP:
					posY = posY + 1;
					break;
				case DOWN:
					posY = posY - 1;
					break;
				case SOUTH:
					posZ = posZ + 0.5;
					break;
				case NORTH:
					posZ = posZ - 0.5;
					break;
				case EAST:
					posX = posX + 0.5;
					break;
				case WEST:
					posX = posX - 0.5;
					break;
				default:
					break;
			}
		}
		else {
			if (result.entityHit != null && !world.isRemote) {
				weapon.doImpactDamage(result.entityHit, getThrower(), this, 1.0f);
				this.doImpactEffect();
			}

			if (!world.isRemote)
				setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (ticksInGround > 0) {
			motionX = 0;
			motionY = 0;
			motionZ = 0;
			ticksInGround++;

			if (ticksInGround >= 80 && !world.isRemote) {
				doImpactEffect();
				return;
			}

			if (!world.isRemote)
				isDead = false;
		}
	}

	@Override
	public void doImpactEffect() {
		world.createExplosion(shooter, posX, posY, posZ, 2.0f, false);

		if (!world.isRemote)
			setDead();
	}
}
