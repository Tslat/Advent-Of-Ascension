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

public class EntityRockFragmentFungal extends BaseBullet implements HardProjectile {
	public EntityRockFragmentFungal(World world) {
		super(world);
	}

	public EntityRockFragmentFungal(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityRockFragmentFungal(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!world.isRemote && WorldUtil.checkGameRule(world, "destructiveWeaponPhysics") && world.isAirBlock(getPosition())) {
			int i = 1;

			while (world.getBlockState(getPosition().down(i)).getMaterial().isReplaceable() && getPosition().getY() - i >= 0) {
				i++;
			}

			if (getPosition().getY() - i <= 0)
				return;

			if (world.provider instanceof AoAWorldProvider && !((AoAWorldProvider)world.provider).canPlaceBlock(thrower instanceof EntityPlayer ? (EntityPlayer)thrower : null, getPosition(), Blocks.COBBLESTONE.getDefaultState()))
				return;

			world.setBlockState(getPosition().down(i - 1), Blocks.COBBLESTONE.getDefaultState());
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}
}
