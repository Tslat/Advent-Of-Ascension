package net.tslat.aoa3.content.world.teleporter;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public record PortalCoordinatesContainer(ResourceKey<Level> fromDim, double x, double y, double z) {
	public BlockPos asBlockPos() {
		return new BlockPos(x, y, z);
	}
}
