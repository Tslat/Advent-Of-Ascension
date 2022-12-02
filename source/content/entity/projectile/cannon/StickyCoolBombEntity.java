package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class StickyCoolBombEntity extends BaseBullet implements HardProjectile {
	private BaseGun weapon;
	private LivingEntity shooter;
	private int ticksInGround = 0;

	public StickyCoolBombEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public StickyCoolBombEntity(Level world) {
		super(AoAProjectiles.STICKY_COOL_BOMB.get(), world);
	}

	public StickyCoolBombEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.STICKY_COOL_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.weapon = gun;
		this.shooter = shooter;
	}

	public StickyCoolBombEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.STICKY_COOL_BOMB.get(), world, x, y, z);
	}

	@Override
	protected void onHit(HitResult result) {
		if (result.getType() == HitResult.Type.BLOCK) {
			BlockHitResult rayTraceResult = (BlockHitResult)result;
			BlockState bl = level.getBlockState(rayTraceResult.getBlockPos());
			double posX = rayTraceResult.getBlockPos().getX();
			double posY = rayTraceResult.getBlockPos().getY();
			double posZ = rayTraceResult.getBlockPos().getZ();

			if (!bl.getMaterial().blocksMotion())
				return;

			ticksInGround++;

			setDeltaMovement(0, 0, 0);

			switch (rayTraceResult.getDirection()) {
				case UP -> posY += 1;
				case DOWN -> posY -= 1;
				case SOUTH -> posZ += 0.5d;
				case NORTH -> posZ -= 0.5d;
				case EAST -> posX += 0.5d;
				case WEST -> posX -= 0.5d;
				default -> {}
			}

			setPos(posX, posY, posZ);
		}
		else {
			if (!level.isClientSide()) {
				if (result.getType() == HitResult.Type.ENTITY) {
					Entity shooter = getOwner();

					if (shooter instanceof LivingEntity)
						weapon.doImpactDamage(((EntityHitResult)result).getEntity(), (LivingEntity)shooter, this, result.getLocation(), 1.0f);

					doImpactEffect(result.getLocation());
				}

				discard();
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
				doImpactEffect(position());
				return;
			}

			if (!level.isClientSide)
				unsetRemoved();
		}
	}

	@Override
	public void doImpactEffect(Vec3 impactLocation) {
		WorldUtil.createExplosion(getOwner(), level, this, 2.0f);

		if (!level.isClientSide)
			discard();
	}
}
