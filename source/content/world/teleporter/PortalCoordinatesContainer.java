package net.tslat.aoa3.content.world.teleporter;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class PortalCoordinatesContainer {
	public final ResourceKey<Level> fromDim;
	public final double x;
	public final double y;
	public final double z;

	public PortalCoordinatesContainer(ResourceKey<Level> fromDim, double posX, double posY, double posZ) {
		this.fromDim = fromDim;
		this.x = posX;
		this.y = posY;
		this.z = posZ;
	}

	public BlockPos asBlockPos() {
		return new BlockPos(x, y, z);
	}
}
