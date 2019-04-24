package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BidirectionalPlantStackable extends PlantStackable {
	public PlantStackable bottomHatBlock = null;

	public BidirectionalPlantStackable(String name, String registryName, Material... growthMaterial) {
		super(name, registryName, growthMaterial);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState downState = world.getBlockState(pos.down());
		IBlockState upState = world.getBlockState(pos.up());

		return (((growthMaterials.isEmpty() || growthMaterials.contains(downState.getMaterial())) && downState.isOpaqueCube()) || downState.getBlock() == stemBlock) ||
				(((growthMaterials.isEmpty() || growthMaterials.contains(upState.getMaterial())) && upState.isOpaqueCube()) || upState.getBlock() == stemBlock);
	}



	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		BlockPos downPos = pos;
		boolean isUpPlant = false;
		boolean isDownPlant = false;
		IBlockState block = world.getBlockState(downPos = downPos.down());

		while (block.getBlock() == stemBlock || block.getBlock() == bottomHatBlock) {
			block = world.getBlockState(downPos = downPos.down());

			if (block.getBlock() == bottomHatBlock)
				break;
		}

		if ((growthMaterials.isEmpty() || growthMaterials.contains(block.getMaterial())) && block.isOpaqueCube())
			isUpPlant = true;

		BlockPos upPos = pos;
		block = world.getBlockState(upPos = upPos.up());

		while (block.getBlock() == stemBlock || block.getBlock() == hatBlock) {
			block = world.getBlockState(upPos = upPos.up());

			if (block.getBlock() == hatBlock)
				break;
		}

		if ((growthMaterials.isEmpty() || growthMaterials.contains(block.getMaterial())) && block.isOpaqueCube())
			isDownPlant = true;

		if (!isDownPlant) {
			while (upPos.getY() > pos.getY()) {
				world.setBlockToAir(upPos);

				upPos = upPos.down();
			}
		}

		if (!isUpPlant) {
			while (downPos.getY() < pos.getY()) {
				world.setBlockToAir(downPos);

				downPos = downPos.up();
			}
		}
	}
}
