package net.tslat.aoa3.content.world.gen.tree.trunkplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
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

public class AoAGiantJungleTrunkPlacer extends AoAGiantTrunkPlacer {
	public static final Codec<AoAGiantJungleTrunkPlacer> CODEC = RecordCodecBuilder.create(builder ->
			heightValues(builder).apply(builder, AoAGiantJungleTrunkPlacer::new));

	public AoAGiantJungleTrunkPlacer(final IntProvider baseHeight, final IntProvider additionalHeight) {
		super(baseHeight, additionalHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return AoATrees.AOA_GIANT_JUNGLE_TRUNK.get();
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos startPos, TreeConfiguration config) {
		final List<FoliagePlacer.FoliageAttachment> foliagePoints = new ObjectArrayList<>(super.placeTrunk(level, blockSetter, random, freeTreeHeight, startPos, config));

		for (int yOffset = freeTreeHeight - 2 - random.nextInt(4); yOffset > freeTreeHeight / 2; yOffset -= 2 + random.nextInt(4)) {
			float angle = random.nextFloat() * Mth.TWO_PI;
			int xOffset = 0;
			int zOffset = 0;

			for (int step = 0; step < 5; ++step) {
				xOffset = (int)(1.5f + Mth.cos(angle) * (float)step);
				zOffset = (int)(1.5f + Mth.sin(angle) * (float)step);

				placeLog(level, blockSetter, random, startPos.offset(xOffset, yOffset - 3 + step / 2, zOffset), config);
			}

			foliagePoints.add(new FoliagePlacer.FoliageAttachment(startPos.offset(xOffset, yOffset, zOffset), -2, false));
		}

		return foliagePoints;
	}
}
