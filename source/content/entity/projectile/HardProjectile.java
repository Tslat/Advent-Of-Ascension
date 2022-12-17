package net.tslat.aoa3.content.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public interface HardProjectile {
	void doBlockImpact(Vec3 impactLocation, Direction face, BlockPos blockPos);
}
