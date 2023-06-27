/*
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MultiStackablePlant extends StackablePlant {
	protected List<Supplier<Block>> stemBlocks = new ArrayList<Supplier<Block>>();

	public MultiStackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
		stemBlocks.add(() -> this);
	}

	public MultiStackablePlant(MaterialColor mapColour, Material... growthMaterials) {
		super(mapColour, growthMaterials);
		stemBlocks.add(() -> this);
	}

	public MultiStackablePlant addStemBlock(Supplier<Block>... blocks) {
		this.stemBlocks.addAll(Arrays.asList(blocks));

		return this;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.below());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.below())) || isStemBlock(targetState.getBlock());
	}

	private boolean isStemBlock(Block block) {
		for (Supplier<Block> sup : stemBlocks) {
			if (sup.get() == block)
				return true;
		}

		return false;
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.above());
		Block block = blockState.getBlock();

		while (isStemBlock(block) || block == hatBlock.get()) {
			world.setBlock(newPos, Blocks.AIR.defaultBlockState(), 35);
			world.levelEvent(player, 2001, newPos, Block.getId(blockState));

			if (!world.isClientSide() && !player.isCreative()) {
				dropResources(state, world, pos, null, player, player.getMainHandItem());
				dropResources(blockState, world, newPos, null, player, player.getMainHandItem());
			}

			blockState = world.getBlockState(newPos = newPos.above());
			block = blockState.getBlock();
		}

		super.playerWillDestroy(world, pos, state, player);
	}
}
*/
