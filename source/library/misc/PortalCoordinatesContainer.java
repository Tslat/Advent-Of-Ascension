package net.tslat.aoa3.library.misc;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortalCoordinatesContainer {
	public final RegistryKey<World> fromDim;
	public final double x;
	public final double y;
	public final double z;

	public PortalCoordinatesContainer(RegistryKey<World> fromDim, double posX, double posY, double posZ) {
		this.fromDim = fromDim;
		this.x = posX;
		this.y = posY;
		this.z = posZ;
	}

	public BlockPos asBlockPos() {
		return new BlockPos(x, y, z);
	}
}
