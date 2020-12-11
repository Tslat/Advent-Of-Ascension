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

import java.util.function.Supplier;

public class UpsideDownStackablePlant extends StackablePlant {
	public UpsideDownStackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
	}

	public UpsideDownStackablePlant(MaterialColor mapColour, Material... growthMaterial) {
		super(Material.TALL_PLANTS, mapColour, SoundType.PLANT, growthMaterial);
	}

	@Override
	public StackablePlant setStemBlock(Supplier<Block> block) {
		this.stemBlock = block;

		return this;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.up());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube(world, pos.up())) || targetState.getBlock() == stemBlock.get();
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.up());
		Block block = blockState.getBlock();

		while (block == stemBlock.get() || block == hatBlock.get()) {
			world.setBlockState(newPos, Blocks.AIR.getDefaultState(), 35);
			world.playEvent(player, 2001, newPos, Block.getStateId(blockState));

			if (!world.isRemote() && !player.isCreative()) {
				spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
				spawnDrops(blockState, world, newPos, null, player, player.getHeldItemMainhand());
			}

			blockState = world.getBlockState(newPos = newPos.down());
			block = blockState.getBlock();
		}

		super.onBlockHarvested(world, pos, state, player);
	}
}
