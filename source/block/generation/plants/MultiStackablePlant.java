package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

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
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.down());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube(world, pos.down())) || isStemBlock(targetState.getBlock());
	}

	private boolean isStemBlock(Block block) {
		for (Supplier<Block> sup : stemBlocks) {
			if (sup.get() == block)
				return true;
		}

		return false;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.up());
		Block block = blockState.getBlock();

		while (isStemBlock(block) || block == hatBlock.get()) {
			world.setBlockState(newPos, Blocks.AIR.getDefaultState(), 35);
			world.playEvent(player, 2001, newPos, Block.getStateId(blockState));

			if (!world.isRemote() && !player.isCreative()) {
				spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
				spawnDrops(blockState, world, newPos, null, player, player.getHeldItemMainhand());
			}

			blockState = world.getBlockState(newPos = newPos.up());
			block = blockState.getBlock();
		}

		super.onBlockHarvested(world, pos, state, player);
	}
}
