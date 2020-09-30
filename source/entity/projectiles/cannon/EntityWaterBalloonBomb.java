package net.tslat.aoa3.entity.projectiles.cannon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.dimension.AoAWorldProvider;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.WorldUtil;

public class EntityWaterBalloonBomb extends BaseBullet implements HardProjectile {
	EntityLivingBase shooter;

	public EntityWaterBalloonBomb(World world) {
		super(world);
	}

	public EntityWaterBalloonBomb(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
		this.shooter = shooter;
	}

	public EntityWaterBalloonBomb(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.1f;
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(thrower, world, this, 1.5f);

		if (!world.isRemote && WorldUtil.checkGameRule(world, "destructiveWeaponPhysics") && world.isAirBlock(getPosition()) && !world.provider.doesWaterVaporize()) {
			if (world.provider instanceof AoAWorldProvider && !((AoAWorldProvider)world.provider).canPlaceBlock(thrower instanceof EntityPlayer ? (EntityPlayer)thrower : null, getPosition(), Blocks.FLOWING_WATER.getDefaultState()))
				return;

			int i = 1;

			while (world.getBlockState(getPosition().down(i)).getMaterial().isReplaceable() && getPosition().getY() - i >= 0) {
				i++;
			}

			if (getPosition().getY() - i <= 0)
				return;

			world.setBlockState(getPosition().down(i - 1), Blocks.FLOWING_WATER.getDefaultState());
		}
	}
}
