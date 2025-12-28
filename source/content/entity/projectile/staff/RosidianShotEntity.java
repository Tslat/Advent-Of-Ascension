package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class RosidianShotEntity extends NonPhysicalWeaponProjectile {
	private boolean hitGround = false;
	private boolean isSpike = false;

	public RosidianShotEntity(EntityType<? extends RosidianShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public RosidianShotEntity(EntityType<? extends RosidianShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public RosidianShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.ROSIDIAN_SHOT.get(), level, context);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide && this.hitGround && this.isSpike && this.tickCount % 5 == 0) {
			double posX = getX() + this.random.nextGaussian() * 3;
			double posZ = getZ() + this.random.nextGaussian() * 3;
			double posY = level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(posX, getY(), posZ)).getY();
			RosidianShotEntity spike = new RosidianShotEntity(level(), WeaponFiringContext.Builder.copyOf(getShotContext()).lifespan(2).build())
					.fromPos(new Vec3(posX, posY + 0.5f, posZ))
					.withVelocity(new Vec3(this.random.nextGaussian() / 33 + 0.03D, 1f, this.random.nextGaussian() / 33 + 0.03D));

			spike.isSpike = true;

			level().addFreshEntity(spike);
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		setDeltaMovement(Vec3.ZERO);

		this.hitGround = true;
	}
}
