package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.VinocorneEntity;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;

public class LivingGrowth extends Block {
	public static final IntegerProperty GROWTH_STAGE = BlockStateProperties.AGE_0_5;
	private static final VoxelShape[] AGE_SHAPES = new VoxelShape[] {
			VoxelShapes.create(new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375)),
			VoxelShapes.create(new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.5, 0.8125)),
			VoxelShapes.create(new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 0.875, 0.9375)),
			VoxelShapes.create(new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 1.4375, 0.9375)),
			VoxelShapes.create(new AxisAlignedBB(0.001, 0, 0.001, 0.099, 1.625, 0.099)),
			VoxelShapes.create(new AxisAlignedBB(0.001, 0, 0.001, 0.099, 1.625, 0.099))
	};

	public LivingGrowth() {
		super(BlockUtil.generateBlockProperties(Material.PLANTS, MaterialColor.GREEN, 1, 0, SoundType.PLANT));

		setDefaultState(getDefaultState().with(GROWTH_STAGE, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AGE_SHAPES[state.get(GROWTH_STAGE)];
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		super.tick(state, world, pos, rand);

		int currentAge = state.get(GROWTH_STAGE);

		if (currentAge < 6) {
			if (world.getDifficulty() == Difficulty.PEACEFUL) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());

				ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, world);

				itemEntity.setItem(new ItemStack(AoAItems.PETALS.get()));
				itemEntity.setPosition(pos.getX(), pos.getY() + 0.5d, pos.getZ());
				world.addEntity(itemEntity);
			}
			else if (currentAge == 5) {

				VinocorneEntity vinocorne = new VinocorneEntity(AoAEntities.Mobs.VINOCORNE.get(), world);

				vinocorne.setPosition(pos.getX(), pos.getY(), pos.getZ());
				world.addEntity(vinocorne);
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
			else {
				world.setBlockState(pos, getDefaultState().with(GROWTH_STAGE, currentAge + 1));
				world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 40);
			}
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(GROWTH_STAGE);
	}
}
