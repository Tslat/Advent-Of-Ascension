package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.neoforged.neoforge.common.IPlantable;
import net.neoforged.neoforge.common.IShearable;
import net.neoforged.neoforge.common.PlantType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class StackablePlant extends Block implements IShearable, IPlantable {
	protected Supplier<? extends StackablePlant> hatBlock;
	protected Supplier<? extends StackablePlant> stemBlock;
// TODO fix this to use blockstates =/
	public StackablePlant(Block.Properties properties) {
		super(properties);

		stemBlock = () -> this;
		hatBlock = () -> this;
	}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.above());
		Block block = blockState.getBlock();

		while (block == this.stemBlock.get() || block == this.hatBlock.get()) {
			world.setBlock(newPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
			world.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK, newPos, Block.getId(blockState));

			if (!world.isClientSide() && !player.isCreative()) {
				dropResources(state, world, pos, null, player, player.getMainHandItem());
				dropResources(blockState, world, newPos, null, player, player.getMainHandItem());
			}

			blockState = world.getBlockState(newPos = newPos.above());
			block = blockState.getBlock();
		}

		return super.playerWillDestroy(world, pos, state, player);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState targetState = level.getBlockState(pos.below());

		return targetState.getBlock() == this.stemBlock.get() || targetState.canSustainPlant(level, pos.below(), Direction.UP, this);
	}

	@Override
	public PlantType getPlantType(BlockGetter level, BlockPos pos) {
		return PlantType.CAVE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return false;
	}

	@Override
	public boolean isShearable(@NotNull ItemStack item, Level world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(state, worldIn, pos, type);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);

		if (state.getBlock() != this)
			return defaultBlockState();

		return state;
	}

	@NotNull
	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		drops.add(new ItemStack(Item.byBlock(this)));

		return drops;
	}

	// TODO Look at random offset? RE: DoublePlantBlock
}
