package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericPlantBlock extends Block implements IForgeShearable, IPlantable {
	private static final VoxelShape SHAPE = VoxelShapes.create(new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.9, 0.9));
	protected final List<Material> growthMaterials;

	public GenericPlantBlock(Material material, MaterialColor mapColour, SoundType sound, int lightLevel, Material... growthMaterials) {
		super(new BlockUtil.CompactProperties(material, mapColour).stats(0, 0).sound(sound).light(lightLevel).noClip().get());

		this.growthMaterials = Arrays.asList(growthMaterials);
	}

	public GenericPlantBlock(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterials) {
		this(material, mapColour, sound, 0, growthMaterials);
	}

	public GenericPlantBlock(Material material, MaterialColor mapColour, Material... growthMaterials) {
		this(material, mapColour, SoundType.GRASS, growthMaterials);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.below());

		return (growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.below());
	}

	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !this.hasCollision || super.isPathfindable(state, worldIn, pos, type);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, World world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);

		if (state.getBlock() != this)
			return defaultBlockState();

		return state;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
		if (!world.isClientSide()) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

			drops.add(new ItemStack(Item.byBlock(this)));

			return drops;
		}

		return new ArrayList<ItemStack>();
	}
}
