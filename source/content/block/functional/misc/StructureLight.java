package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class StructureLight extends Block {
	public StructureLight() {
		super(new BlockUtil.CompactProperties(new Material(
				MaterialColor.NONE,
				false,
				false,
				false,
				false,
				false,
				true,
				PushReaction.DESTROY),
				MaterialColor.NONE).stats(BlockUtil.UNBREAKABLE_HARDNESS, 0f).isAir().noDrops().noClip().light(state -> state.getValue(BlockStateProperties.LEVEL)).get());

		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.LEVEL, 15));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return context.getEntity() instanceof PlayerEntity && context.getEntity().isCrouching() && ((PlayerEntity)context.getEntity()).isCreative() ? VoxelShapes.block() : VoxelShapes.empty();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, IBlockReader world, BlockPos pos) {
		return VoxelShapes.empty();
	}

	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, @Nullable EntityType<?> entityType) {
		return false;
	}

	@Override
	public boolean isPossibleToRespawnInThis() {
		return true;
	}

	@Override
	public void animateTick(BlockState pState, World world, BlockPos pos, Random rand) {
		if (Minecraft.getInstance().player != null) {
			ClientPlayerEntity player = Minecraft.getInstance().player;

			if (player.getItemInHand(Hand.MAIN_HAND).getItem() == this.asItem())
				world.addParticle(ParticleTypes.END_ROD, pos.getX() +  rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0, 0, 0);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(BlockStateProperties.LEVEL);
	}
}
