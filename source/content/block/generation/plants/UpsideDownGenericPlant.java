package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UpsideDownGenericPlant extends GenericPlantBlock {
	private static final VoxelShape SHAPE = Shapes.create(new AABB(0.1, 0.1, 0.1, 1, 1, 1));

	public UpsideDownGenericPlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterials) {
		super(material, mapColour, sound, growthMaterials);
	}

	public UpsideDownGenericPlant(Material material, MaterialColor mapColour, Material... growthMaterials) {
		super(material, mapColour, growthMaterials);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.above());

		return (growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.below());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
