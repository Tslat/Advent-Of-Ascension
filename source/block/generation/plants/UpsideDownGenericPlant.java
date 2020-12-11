package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class UpsideDownGenericPlant extends GenericPlantBlock {
	private static VoxelShape SHAPE = VoxelShapes.create(new AxisAlignedBB(0.1, 0.1, 0.1, 1, 1, 1));

	public UpsideDownGenericPlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterials) {
		super(material, mapColour, sound, growthMaterials);
	}

	public UpsideDownGenericPlant(MaterialColor mapColour, Material... growthMaterials) {
		super(mapColour, growthMaterials);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.up());

		return (growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube(world, pos.down());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
