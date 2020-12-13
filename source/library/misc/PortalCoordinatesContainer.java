package net.tslat.aoa3.library.misc;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class PortalCoordinatesContainer {
	public final DimensionType fromDim;
	public final double x;
	public final double y;
	public final double z;

	public PortalCoordinatesContainer(DimensionType fromDim, double posX, double posY, double posZ) {
		this.fromDim = fromDim;
		this.x = posX;
		this.y = posY;
		this.z = posZ;
	}

	public BlockPos asBlockPos() {
		return new BlockPos(x, y, z);
	}
}
