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
		super(Material.REPLACEABLE_PLANT, mapColour, SoundType.GRASS, growthMaterial);
	}

	@Override
	public StackablePlant setStemBlock(Supplier<Block> block) {
		this.stemBlock = block;

		return this;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.above());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.above())) || targetState.getBlock() == stemBlock.get();
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
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
