package net.tslat.aoa3.content.world.gen.tree.foliageplacer;

import com.mojang.serialization.Codec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.tslat.aoa3.common.registration.worldgen.AoATrees;

public class NoFoliagePlacer extends FoliagePlacer {
	public static final Codec<NoFoliagePlacer> CODEC = Codec.unit(new NoFoliagePlacer());

	public NoFoliagePlacer() {
		super(ConstantInt.of(0), ConstantInt.of(0));
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return AoATrees.NO_FOLIAGE_FOLIAGE.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int foliageOffset) {}

	@Override
	public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocationSigned(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
		return true;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
		return true;
	}
}
