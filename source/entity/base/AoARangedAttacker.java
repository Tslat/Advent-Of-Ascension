package net.tslat.aoa3.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;

public interface AoARangedAttacker {
	void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target);

	void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit);

	void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target);
}
