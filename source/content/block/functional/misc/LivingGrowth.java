package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;

public class LivingGrowth extends Block {
	public static final IntegerProperty GROWTH_STAGE = BlockStateProperties.AGE_5;
	private static final VoxelShape[] AGE_SHAPES = new VoxelShape[] {
			Shapes.create(new AABB(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375)),
			Shapes.create(new AABB(0.1875, 0, 0.1875, 0.8125, 0.5, 0.8125)),
			Shapes.create(new AABB(0.0625, 0, 0.0625, 0.9375, 0.875, 0.9375)),
			Shapes.create(new AABB(0.0625, 0, 0.0625, 0.9375, 1.4375, 0.9375)),
			Shapes.create(new AABB(0.001, 0, 0.001, 0.99, 1.625, 0.99)),
			Shapes.create(new AABB(0.001, 0, 0.001, 0.99, 1.625, 0.99))
	};

	public LivingGrowth() {
		super(new BlockUtil.CompactProperties(Material.PLANT, MaterialColor.COLOR_GREEN).stats(1f, 0f).get());

		registerDefaultState(defaultBlockState().setValue(GROWTH_STAGE, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AGE_SHAPES[state.getValue(GROWTH_STAGE)];
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		super.tick(state, world, pos, rand);

		int currentAge = state.getValue(GROWTH_STAGE);

		if (currentAge < 6) {
			if (world.getDifficulty() == Difficulty.PEACEFUL) {
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

				ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, world);

				itemEntity.setItem(new ItemStack(AoAItems.PETALS.get()));
				itemEntity.setPos(pos.getX(), pos.getY() + 0.5d, pos.getZ());
				world.addFreshEntity(itemEntity);
			}
			else if (currentAge == 5) {

				/*VinocorneEntity vinocorne = new VinocorneEntity(AoAMobs.VINOCORNE.get(), world);

				vinocorne.setPos(pos.getX(), pos.getY(), pos.getZ());
				world.addFreshEntity(vinocorne);
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());*/
			}
			else {
				world.setBlockAndUpdate(pos, defaultBlockState().setValue(GROWTH_STAGE, currentAge + 1));
				world.scheduleTick(pos, state.getBlock(), 40);
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(GROWTH_STAGE);
	}
}
