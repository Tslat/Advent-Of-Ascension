package net.tslat.aoa3.block.decoration.misc;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tslat.aoa3.block.decoration.BasicDecorationBlock;

public class CompressedBlock extends BasicDecorationBlock {
	private final boolean suitableForBeacons;

	public CompressedBlock(String name, String registryName, boolean notForBeacons) {
		super(name, registryName, Material.IRON, 5.0f, 10.0f);
		this.suitableForBeacons = !notForBeacons;
	}

	public CompressedBlock(String name, String registryName) {
		this(name, registryName, false);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return suitableForBeacons;
	}
}
