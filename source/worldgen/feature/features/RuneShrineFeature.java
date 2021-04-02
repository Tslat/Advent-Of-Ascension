package net.tslat.aoa3.worldgen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;

public class RuneShrineFeature extends Feature<BlockStateFeatureConfig> {
	public RuneShrineFeature(Codec<BlockStateFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
		BlockState darkBricks = AoABlocks.DARK_BRICKS.get().defaultBlockState();
		BlockPos.Mutable testPos = new BlockPos.Mutable().set(pos);

		for (int x = 0; x <= 6; x++) {
			for (int z = 0; z <= 6; z++) {
				BlockState state = reader.getBlockState(testPos.setWithOffset(pos, x, 0, z));

				if (state.getBlock() != Blocks.WATER && !state.isAir(reader, testPos))
					return false;
			}
		}

		for (int x = 0; x <= 6; x++) {
			for (int z = 0; z <= 6; z++) {
				reader.setBlock(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z), darkBricks, 2);

				if ((x == 0 || x == 6) && (z == 0 || z == 6)) {
					reader.setBlock(new BlockPos(pos.getX() + x, pos.getY() + 1, pos.getZ() + z), darkBricks, 2);
					reader.setBlock(new BlockPos(pos.getX() + x, pos.getY() + 2, pos.getZ() + z), darkBricks, 2);
					reader.setBlock(new BlockPos(pos.getX() + x, pos.getY() + 3, pos.getZ() + z), config.state, 2);
				}
			}
		}

		reader.setBlock(new BlockPos(pos.getX() + 3, pos.getY() + 1, pos.getZ() + 3), AoABlocks.RUNE_SHRINE.get().defaultBlockState(), 2);

		return true;
	}
}
