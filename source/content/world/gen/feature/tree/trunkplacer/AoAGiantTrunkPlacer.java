package net.tslat.aoa3.content.world.gen.feature.tree.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.tslat.aoa3.common.registration.worldgen.AoATrees;

import java.util.List;
import java.util.function.BiConsumer;

public class AoAGiantTrunkPlacer extends AoATrunkPlacer {
	public static final Codec<AoAGiantTrunkPlacer> CODEC = RecordCodecBuilder.create(builder ->
			heightValues(builder).apply(builder, AoAGiantTrunkPlacer::new));

	public AoAGiantTrunkPlacer(final IntProvider baseHeight, final IntProvider additionalHeight) {
		super(baseHeight, additionalHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return AoATrees.AOA_GIANT_TRUNK.get();
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos startPos, TreeConfiguration config) {
		BlockPos blockpos = startPos.below();
		BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();

		setDirtAt(level, blockSetter, random, blockpos, config);
		setDirtAt(level, blockSetter, random, blockpos.east(), config);
		setDirtAt(level, blockSetter, random, blockpos.south(), config);
		setDirtAt(level, blockSetter, random, blockpos.south().east(), config);

		for (int yOffset = 0; yOffset < freeTreeHeight; ++yOffset) {
			placeLogIfFreeWithOffset(level, blockSetter, random, placementPos, config, startPos, 0, yOffset, 0);

			if (yOffset < freeTreeHeight - 1) {
				placeLogIfFreeWithOffset(level, blockSetter, random, placementPos, config, startPos, 1, yOffset, 0);
				placeLogIfFreeWithOffset(level, blockSetter, random, placementPos, config, startPos, 1, yOffset, 1);
				placeLogIfFreeWithOffset(level, blockSetter, random, placementPos, config, startPos, 0, yOffset, 1);
			}
		}

		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(freeTreeHeight), 0, true));
	}

	private void placeLogIfFreeWithOffset(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos.MutableBlockPos pos, TreeConfiguration config, BlockPos offsetPos, int offsetX, int offsetY, int offsetZ) {
		placeLogIfFree(level, blockSetter, random, pos.setWithOffset(offsetPos, offsetX, offsetY, offsetZ), config);
	}
}
