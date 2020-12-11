package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class WaterBalloonBombEntity extends BaseBullet implements HardProjectile {
	LivingEntity shooter;

	public WaterBalloonBombEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public WaterBalloonBombEntity(World world) {
		super(AoAEntities.Projectiles.WATER_BALLOON_BOMB.get(), world);
	}

	public WaterBalloonBombEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.WATER_BALLOON_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public WaterBalloonBombEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.WATER_BALLOON_BOMB.get(), world, x, y, z);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.1f;
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(owner, world, this, 1.5f);

		if (!world.isRemote && WorldUtil.checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS) && world.isAirBlock(getPosition()) && !world.getDimension().doesWaterVaporize()) {
			if (!WorldUtil.canModifyBlock(world, getPosition(), owner instanceof PlayerEntity ? owner : null))
				return;

			int i = 1;

			while (world.getBlockState(getPosition().down(i)).getMaterial().isReplaceable() && getPosition().getY() - i >= 0) {
				i++;
			}

			if (getPosition().getY() - i <= 0)
				return;

			world.setBlockState(getPosition().down(i - 1), Blocks.WATER.getDefaultState().with(FlowingFluidBlock.LEVEL, 7));
		}
	}
}
