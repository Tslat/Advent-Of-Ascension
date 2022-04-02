package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class UpsideDownStackablePlant extends StackablePlant {
	public UpsideDownStackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
	}

	public UpsideDownStackablePlant(MaterialColor mapColour, Material... growthMaterial) {
		super(Material.REPLACEABLE_PLANT, mapColour, SoundType.GRASS, growthMaterial);
	}

	@Override
	public StackablePlant setStemBlock(Supplier<Block> block) {
		this.stemBlock = block;

		return this;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.above());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.above())) || targetState.getBlock() == stemBlock.get();
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.above());
		Block block = blockState.getBlock();

		while (block == stemBlock.get() || block == hatBlock.get()) {
			world.setBlock(newPos, Blocks.AIR.defaultBlockState(), 35);
			world.levelEvent(player, 2001, newPos, Block.getId(blockState));

			if (!world.isClientSide() && !player.isCreative()) {
				dropResources(state, world, pos, null, player, player.getMainHandItem());
				dropResources(blockState, world, newPos, null, player, player.getMainHandItem());
			}

			blockState = world.getBlockState(newPos = newPos.below());
			block = blockState.getBlock();
		}

		super.playerWillDestroy(world, pos, state, player);
	}
}
