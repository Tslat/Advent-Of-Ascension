package net.tslat.aoa3.object.block.generation.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;

public class BidirectionalStackablePlant extends StackablePlant {
	public Supplier<Block> bottomHatBlock = null;

	public BidirectionalStackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
	}

	public BidirectionalStackablePlant(MaterialColor mapColour, Material... growthMaterial) {
		super(Material.REPLACEABLE_PLANT, mapColour, SoundType.GRASS, growthMaterial);
	}

	public BidirectionalStackablePlant(AbstractBlock.Properties properties, Material... growthMaterial) {
		super(properties, growthMaterial);
	}

	public BidirectionalStackablePlant setBottomHatBlock(Supplier<Block> block) {
		this.bottomHatBlock = block;

		return this;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState downState = world.getBlockState(pos.below());
		BlockState upState = world.getBlockState(pos.above());

		return (((growthMaterials.isEmpty() || growthMaterials.contains(downState.getMaterial())) && downState.isSolidRender(world, pos.below())) || downState.getBlock() == stemBlock.get()) ||
				(((growthMaterials.isEmpty() || growthMaterials.contains(upState.getMaterial())) && upState.isSolidRender(world, pos.above())) || upState.getBlock() == stemBlock.get());
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos downPos = pos;
		boolean isUpPlant = false;
		boolean isDownPlant = false;
		BlockState block = world.getBlockState(downPos = downPos.below());

		while (block.getBlock() == stemBlock.get() || (bottomHatBlock != null && block.getBlock() == bottomHatBlock.get())) {
			block = world.getBlockState(downPos = downPos.below());

			if (block.getBlock() == bottomHatBlock)
				break;
		}

		if ((growthMaterials.isEmpty() || growthMaterials.contains(block.getMaterial())) && block.isSolidRender(world, pos.below()))
			isUpPlant = true;

		BlockPos upPos = pos;
		block = world.getBlockState(upPos = upPos.above());

		while (block.getBlock() == stemBlock.get() || (hatBlock != null && block.getBlock() == hatBlock.get())) {
			block = world.getBlockState(upPos = upPos.above());

			if (block.getBlock() == hatBlock.get())
				break;
		}

		if ((growthMaterials.isEmpty() || growthMaterials.contains(block.getMaterial())) && block.isSolidRender(world, pos.above()))
			isDownPlant = true;

		if (!isDownPlant) {
			while (upPos.getY() > pos.getY()) {
				BlockState breakState = world.getBlockState(upPos);
				world.setBlock(upPos, Blocks.AIR.defaultBlockState(), 35);
				world.levelEvent(player, 2001, upPos, Block.getId(breakState));

				if (!world.isClientSide() && !player.isCreative())
					dropResources(breakState, world, upPos, null, player, player.getMainHandItem());

				upPos = upPos.below();
			}
		}

		if (!isUpPlant) {
			while (downPos.getY() < pos.getY()) {
				BlockState breakState = world.getBlockState(downPos);
				world.setBlock(downPos, Blocks.AIR.defaultBlockState(), 35);
				world.levelEvent(player, 2001, downPos, Block.getId(breakState));

				if (!world.isClientSide() && !player.isCreative())
					dropResources(breakState, world, downPos, null, player, player.getMainHandItem());

				downPos = downPos.above();
			}
		}

		if (!world.isClientSide() && !player.isCreative())
			dropResources(state, world, pos, null, player, player.getMainHandItem());

		world.levelEvent(player, 2001, pos, getId(state));

		if (is(BlockTags.GUARDED_BY_PIGLINS))
			PiglinTasks.angerNearbyPiglins(player, false);
	}
}
