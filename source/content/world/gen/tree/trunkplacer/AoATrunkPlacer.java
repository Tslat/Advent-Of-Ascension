package net.tslat.aoa3.content.world.gen.tree.trunkplacer;

import com.mojang.datafixers.Products;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.function.BiConsumer;

public abstract class AoATrunkPlacer extends TrunkPlacer {
	protected final IntProvider baseHeight;
	protected final IntProvider additionalHeight;

	public AoATrunkPlacer(final IntProvider baseHeight, final IntProvider additionalHeight) {
		super(0, 0, 0);

		this.baseHeight = baseHeight;
		this.additionalHeight = additionalHeight;
	}

	protected static <P extends AoATrunkPlacer> Products.P2<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider> heightValues(RecordCodecBuilder.Instance<P> builder) {
		return builder.group(
				IntProvider.CODEC.fieldOf("base_height").forGetter(instance -> instance.baseHeight),
				IntProvider.CODEC.fieldOf("additional_height").forGetter(instance -> instance.additionalHeight));
	}

	@Override
	public abstract List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos startPos, TreeConfiguration config);

	@Override
	public int getTreeHeight(RandomSource random) {
		return this.baseHeight.sample(random) + this.additionalHeight.sample(random);
	}
}
