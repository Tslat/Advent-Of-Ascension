package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
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
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (context instanceof EntityCollisionContext entityContext && entityContext.getEntity() instanceof Player player) {
			if (player.isCrouching() && player.isCreative())
				return Shapes.block();
		}

		return Shapes.empty();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
		return Shapes.empty();
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public boolean isValidSpawn(BlockState state, BlockGetter world, BlockPos pos, SpawnPlacements.Type type, @Nullable EntityType<?> entityType) {
		return false;
	}

	@Override
	public boolean isPossibleToRespawnInThis() {
		return true;
	}

	@Override
	public void animateTick(BlockState pState, Level world, BlockPos pos, Random rand) {
		if (Minecraft.getInstance().player != null) {
			LocalPlayer player = Minecraft.getInstance().player;

			if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == this.asItem())
				world.addParticle(ParticleTypes.END_ROD, pos.getX() +  rand.nextFloat(), pos.getY() + rand.nextFloat(), pos.getZ() + rand.nextFloat(), 0, 0, 0);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(BlockStateProperties.LEVEL);
	}
}
