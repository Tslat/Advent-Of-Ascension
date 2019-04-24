package net.tslat.aoa3.entity.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;

public interface AoARangedAttacker {
	void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target);

	void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit);

	void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target);
}
