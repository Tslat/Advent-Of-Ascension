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

public class BidirectionalStackablePlant extends StackablePlant {
	public Supplier<Block> bottomHatBlock = null;

	public BidirectionalStackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
	}

	public BidirectionalStackablePlant(MaterialColor mapColour, Material... growthMaterial) {
		super(Material.TALL_PLANTS, mapColour, SoundType.PLANT, growthMaterial);
	}

	public BidirectionalStackablePlant(Block.Properties properties, Material... growthMaterial) {
		super(properties, growthMaterial);
	}

	public BidirectionalStackablePlant setBottomHatBlock(Supplier<Block> block) {
		this.bottomHatBlock = block;

		return this;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState downState = world.getBlockState(pos.down());
		BlockState upState = world.getBlockState(pos.up());

		return (((growthMaterials.isEmpty() || growthMaterials.contains(downState.getMaterial())) && downState.isOpaqueCube(world, pos.down())) || downState.getBlock() == stemBlock.get()) ||
				(((growthMaterials.isEmpty() || growthMaterials.contains(upState.getMaterial())) && upState.isOpaqueCube(world, pos.up())) || upState.getBlock() == stemBlock.get());
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos downPos = pos;
		boolean isUpPlant = false;
		boolean isDownPlant = false;
		BlockState block = world.getBlockState(downPos = downPos.down());

		while (block.getBlock() == stemBlock.get() || block.getBlock() == bottomHatBlock.get()) {
			block = world.getBlockState(downPos = downPos.down());

			if (block.getBlock() == bottomHatBlock)
				break;
		}

		if ((growthMaterials.isEmpty() || growthMaterials.contains(block.getMaterial())) && block.isOpaqueCube(world, pos.down()))
			isUpPlant = true;

		BlockPos upPos = pos;
		block = world.getBlockState(upPos = upPos.up());

		while (block.getBlock() == stemBlock.get() || block.getBlock() == hatBlock.get()) {
			block = world.getBlockState(upPos = upPos.up());

			if (block.getBlock() == hatBlock.get())
				break;
		}

		if ((growthMaterials.isEmpty() || growthMaterials.contains(block.getMaterial())) && block.isOpaqueCube(world, pos.up()))
			isDownPlant = true;

		if (!isDownPlant) {
			while (upPos.getY() > pos.getY()) {
				BlockState breakState = world.getBlockState(upPos);
				world.setBlockState(upPos, Blocks.AIR.getDefaultState(), 35);
				world.playEvent(player, 2001, upPos, Block.getStateId(breakState));

				if (!world.isRemote() && !player.isCreative())
					spawnDrops(breakState, world, upPos, null, player, player.getHeldItemMainhand());

				upPos = upPos.down();
			}
		}

		if (!isUpPlant) {
			while (downPos.getY() < pos.getY()) {
				BlockState breakState = world.getBlockState(downPos);
				world.setBlockState(downPos, Blocks.AIR.getDefaultState(), 35);
				world.playEvent(player, 2001, downPos, Block.getStateId(breakState));

				if (!world.isRemote() && !player.isCreative())
					spawnDrops(breakState, world, downPos, null, player, player.getHeldItemMainhand());

				downPos = downPos.up();
			}
		}

		if (!world.isRemote() && !player.isCreative())
			spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());

		world.playEvent(player, 2001, pos, getStateId(state));
	}
}
