/*
package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
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

public class BidirectionalStackablePlant extends StackablePlant {
	public Supplier<Block> bottomHatBlock = null;

	public BidirectionalStackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(material, mapColour, sound, growthMaterial);
	}

	public BidirectionalStackablePlant(MaterialColor mapColour, Material... growthMaterial) {
		super(Material.REPLACEABLE_PLANT, mapColour, SoundType.GRASS, growthMaterial);
	}

	public BidirectionalStackablePlant(Block.Properties properties, Material... growthMaterial) {
		super(properties, growthMaterial);
	}

	public BidirectionalStackablePlant setBottomHatBlock(Supplier<Block> block) {
		this.bottomHatBlock = block;

		return this;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockState downState = world.getBlockState(pos.below());
		BlockState upState = world.getBlockState(pos.above());

		return (((growthMaterials.isEmpty() || growthMaterials.contains(downState.getMaterial())) && downState.isSolidRender(world, pos.below())) || downState.getBlock() == stemBlock.get()) ||
				(((growthMaterials.isEmpty() || growthMaterials.contains(upState.getMaterial())) && upState.isSolidRender(world, pos.above())) || upState.getBlock() == stemBlock.get());
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
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

		if (builtInRegistryHolder().is(BlockTags.GUARDED_BY_PIGLINS))
			PiglinAi.angerNearbyPiglins(player, false);
	}
}
*/
