package net.tslat.aoa3.content.world.gen.feature.tree.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.tslat.aoa3.common.registration.worldgen.AoATrees;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OpteryxNestTreeDecorator extends TreeDecorator {
    public static final Codec<OpteryxNestTreeDecorator> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.floatRange(0, 1).fieldOf("probability").forGetter(instance -> instance.probability),
            ConfiguredFeature.CODEC.fieldOf("nest_feature").forGetter(instance -> instance.nestFeature)
    ).apply(builder, OpteryxNestTreeDecorator::new));

    private final float probability;
    private final Holder<ConfiguredFeature<?, ?>> nestFeature;

    public OpteryxNestTreeDecorator(float probability, Holder<ConfiguredFeature<?, ?>> nestFeature) {
        this.probability = probability;
        this.nestFeature = nestFeature;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return AoATrees.OPTERYX_NEST_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        final RandomSource random = context.random();

        if (random.nextFloat() >= this.probability)
            return;

        ServerChunkCache chunkSource = null;
        WorldGenLevel genLevel = null;

        if (context.level() instanceof WorldGenRegion worldGenRegion) {
            chunkSource = worldGenRegion.getLevel().getChunkSource();
            genLevel = worldGenRegion;
        }
        else if (context.level() instanceof ServerLevel serverLevel) {
            chunkSource = serverLevel.getChunkSource();
            genLevel = serverLevel;
        }

        if (chunkSource == null)
            return;

        final WorldGenLevel level = genLevel;
        final ConfiguredFeature nest = this.nestFeature.value();
        final BlockPos trunkBase = context.logs().get(0);
        final List<BlockPos> coreTrunkColumn = new ObjectArrayList<>();
        final BlockPos trunkTop = context.logs().stream()
                .filter(pos -> pos.getX() == trunkBase.getX() && pos.getZ() == trunkBase.getZ())
                .peek(coreTrunkColumn::add)
                .max(Comparator.comparingInt(BlockPos::getY)).get();

        if (coreTrunkColumn.size() <= 16)
            return;

        final int nestAnchorIndex = random.nextInt(Mth.floor(coreTrunkColumn.size() * 0.6f), coreTrunkColumn.size() - 6);
        final BlockPos nestAnchorPos = coreTrunkColumn.get(nestAnchorIndex);
        final Map<BlockPos, BlockState> trunkSectionSnapshot = new Object2ObjectOpenHashMap<>();

        context.logs().stream()
                .filter(pos -> pos.getY() >= nestAnchorPos.getY() && pos.getY() <= Math.min(nestAnchorPos.getY() + 10, trunkTop.getY()))
                .filter(pos -> (pos.getX() == nestAnchorPos.getX() + 1 || pos.getZ() == nestAnchorPos.getZ() - 1) || (pos.getX() == nestAnchorPos.getX() + 1 && pos.getZ() == nestAnchorPos.getZ() - 1))
                .forEach(pos -> trunkSectionSnapshot.put(pos, level.getBlockState(pos)));

        nest.feature().place(nest.config(), level, chunkSource.getGenerator(), context.random(), nestAnchorPos.offset(-4, 0, -3));

        for (Map.Entry<BlockPos, BlockState> trunkBlock : trunkSectionSnapshot.entrySet()) {
            if (level.getBlockState(trunkBlock.getKey()) != trunkBlock.getValue())
                level.setBlock(trunkBlock.getKey(), trunkBlock.getValue(), Block.UPDATE_CLIENTS);
        }
    }
}
