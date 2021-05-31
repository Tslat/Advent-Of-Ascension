package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import java.util.Random;

public class AcidBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 2, 16);

	public AcidBlock() {
		super(new BlockUtil.CompactProperties(Material.TOP_SNOW, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.WET_GRASS).noDrops().randomTicks().get());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entity) {
		if (entity instanceof MonsterEntity || (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative())) {
			entity.hurt(new DamageSource("acid"), 4);
			EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 40).level(4));
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!world.isClientSide)
			world.removeBlock(pos, false);
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!state.canSurvive(world, pos))
			world.removeBlock(pos, false);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		return Block.canSupportRigidBlock(world, pos);
	}
}
