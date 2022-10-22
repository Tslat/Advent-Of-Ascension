package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;

import javax.annotation.Nullable;

public interface AoARangedAttacker extends RangedAttackMob {
	void doRangedAttackEntity(@Nullable BaseMobProjectile projectile, Entity target);

	void doRangedAttackBlock(@Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit);
}
