package net.tslat.aoa3.library;

import net.minecraft.util.math.BlockPos;

public class PortalCoordinatesContainer {
	public final int fromDim;
	public final double x;
	public final double y;
	public final double z;

	public PortalCoordinatesContainer(int fromDim, double posX, double posY, double posZ) {
		this.fromDim = fromDim;
		this.x = posX;
		this.y = posY;
		this.z = posZ;
	}

	public BlockPos asBlockPos() {
		return new BlockPos(x, y, z);
	}
}
