package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;

public interface AoARangedAttacker {
	void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target);

	void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit);

	void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target);
}
