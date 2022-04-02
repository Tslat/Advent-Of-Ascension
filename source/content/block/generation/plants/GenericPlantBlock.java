package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericPlantBlock extends Block implements IForgeShearable, IPlantable {
	private static final VoxelShape SHAPE = Shapes.create(new AABB(0.1, 0, 0.1, 0.9, 0.9, 0.9));
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
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.below());

		return (growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.below());
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(state, worldIn, pos, type);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, Level world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);

		if (state.getBlock() != this)
			return defaultBlockState();

		return state;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		if (!world.isClientSide()) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

			drops.add(new ItemStack(Item.byBlock(this)));

			return drops;
		}

		return new ArrayList<ItemStack>();
	}
}
