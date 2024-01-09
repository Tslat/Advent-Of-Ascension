package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class AirGap extends Block {
	public AirGap(BlockBehaviour.Properties properties) {
		super(properties);
	}
	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
		return Shapes.empty();
	}

	@Override
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return true;
	}

	@Override
	public boolean canBeReplaced(BlockState state, Fluid fluid) {
		return true;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
		return ItemStack.EMPTY;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}
}
