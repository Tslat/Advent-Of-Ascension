package net.tslat.aoa3.content.world.gen.feature.tree.trunkplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.tslat.aoa3.common.registration.worldgen.AoATrees;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class BaobabTrunkPlacer extends AoATrunkPlacer {
	public static final Codec<BaobabTrunkPlacer> CODEC = RecordCodecBuilder.create(builder ->
			heightValues(builder).apply(builder, BaobabTrunkPlacer::new));

	public BaobabTrunkPlacer(final IntProvider baseHeight, final IntProvider additionalHeight) {
		super(baseHeight, additionalHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return AoATrees.BAOBAB_TRUNK.get();
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
		final List<FoliagePlacer.FoliageAttachment> foliageAnchors = new ObjectArrayList<>();
		final BlockPos soilPos = pos.below();
		final int trunkX = pos.getX();
		final int trunkY = pos.getY();
		final int trunkZ = pos.getZ();
		final int maxTrunkHeight = freeTreeHeight - random.nextInt(2, 4);
		int xOffset = trunkX;
		int zOffset = trunkZ;
		BlockPos.MutableBlockPos setterPos = new BlockPos.MutableBlockPos();

		setDirtAt(level, blockSetter, random, soilPos, config);
		setDirtAt(level, blockSetter, random, soilPos.east(), config);
		setDirtAt(level, blockSetter, random, soilPos.south(), config);
		setDirtAt(level, blockSetter, random, soilPos.south().east(), config);

		for(int i = 0; i < maxTrunkHeight; ++i) {
			setterPos.set(xOffset, trunkY + i, zOffset);

			if (TreeFeature.isAirOrLeaves(level, setterPos)) {
				placeLog(level, blockSetter, random, setterPos, config);
				placeLog(level, blockSetter, random, setterPos.east(), config);
				placeLog(level, blockSetter, random, setterPos.south(), config);
				placeLog(level, blockSetter, random, setterPos.east().south(), config);
			}
		}

		List<Direction> branchDirections = ObjectArrayList.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
		int extraBranches = random.nextInt(2, 4);

		for (int i = 0; i < extraBranches; i++) {
			branchDirections.add(Direction.Plane.HORIZONTAL.getRandomDirection(random));
		}

		for (Direction branchDirection : branchDirections) {
			xOffset = trunkX;
			zOffset = trunkZ;

			if (branchDirection != Direction.NORTH && random.nextBoolean())
				zOffset += branchDirection.getStepZ();

			if (branchDirection != Direction.WEST && random.nextBoolean())
				xOffset += branchDirection.getStepX();

			int branchStartHeight = maxTrunkHeight - random.nextInt(2);
			int branchLength = 1 + (freeTreeHeight / 5) + random.nextInt(3);
			OptionalInt foliageAnchorOffset = OptionalInt.empty();

			for(int j = branchStartHeight; j < freeTreeHeight && branchLength > 0; --branchLength) {
				if (j >= 1) {
					int branchHeightOffset = pos.getY() + j;
					xOffset += branchDirection.getStepX();
					zOffset += branchDirection.getStepZ();

					if (placeLog(level, blockSetter, random, setterPos.set(xOffset, branchHeightOffset, zOffset), config))
						foliageAnchorOffset = OptionalInt.of(branchHeightOffset + 1);
				}

				++j;
			}

			if (foliageAnchorOffset.isPresent())
				foliageAnchors.add(new FoliagePlacer.FoliageAttachment(new BlockPos(xOffset, foliageAnchorOffset.getAsInt(), zOffset), 0, false));
		}

		return foliageAnchors;
	}
}
