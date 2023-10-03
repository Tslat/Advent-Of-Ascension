package net.tslat.aoa3.content.world.gen.feature.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class BetterBlockBlobFeature extends Feature<BetterBlockBlobFeature.Configuration> {
   public BetterBlockBlobFeature(Codec<BetterBlockBlobFeature.Configuration> codec) {
      super(codec);
   }

   @Override
   public boolean place(FeaturePlaceContext<BetterBlockBlobFeature.Configuration> context) {
      final WorldGenLevel level = context.level();
      final RandomSource random = context.random();
      final Configuration config = context.config();
      final BlockPos.MutableBlockPos pos = context.origin().mutable().move(Direction.UP);

      while (pos.move(Direction.DOWN).getY() > level.getMinBuildHeight() + 3) {
         if (config.shouldPlace(level, pos, random))
            break;
      }

      if (pos.getY() <= level.getMinBuildHeight() + 3)
         return false;

      final int size = config.getSize(random);
      final int halfSize = size / 2;
      final int blockCount = config.getBlockCount(random);

      for(int i = 0; i < blockCount; ++i) {
         final int x = random.nextInt(size);
         final int y = random.nextInt(size);
         final int z = random.nextInt(size);
         final float radius = (x + y + z) / 3f + 0.5F;

         for(BlockPos offsetPos : BlockPos.betweenClosed(pos.offset(-x, -y, -z), pos.offset(x, y, z))) {
            if (offsetPos.distSqr(pos) <= radius * radius)
               level.setBlock(offsetPos, config.states.getState(random, offsetPos), Block.UPDATE_ALL);
         }

         pos.move(-halfSize + random.nextInt(size), -random.nextInt(size), -halfSize + random.nextInt(size));
      }

      return true;
   }
   
   public static class Configuration implements FeatureConfiguration {
      public static final Codec<Configuration> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                      BlockStateProvider.CODEC.fieldOf("states").forGetter(Configuration::states),
                      RuleTest.CODEC.listOf().fieldOf("placing_in").forGetter(Configuration::targetBlockPredicate),
                      IntProvider.CODEC.optionalFieldOf("blocks", ConstantInt.of(3)).forGetter(Configuration::blocks),
                      IntProvider.CODEC.optionalFieldOf("size", ConstantInt.of(2)).forGetter(Configuration::size))
              .apply(builder, Configuration::new));

      private final BlockStateProvider states;
      private final List<RuleTest> placingIn;
      private final IntProvider blocks;
      private final IntProvider size;

      public Configuration(BlockStateProvider stateProvider, List<RuleTest> placingIn, IntProvider blocks, IntProvider size) {
         this.states = stateProvider;
         this.placingIn = placingIn;
         this.blocks = blocks;
         this.size = size;
      }

      public BlockStateProvider states() {
         return this.states;
      }

      public List<RuleTest> targetBlockPredicate() {
         return this.placingIn;
      }

      public IntProvider blocks() {
         return this.blocks;
      }

      public IntProvider size() {
         return this.size;
      }

      public BlockState sample(RandomSource random, BlockPos pos) {
         return this.states.getState(random, pos);
      }

      public boolean shouldPlace(WorldGenLevel level, BlockPos pos, RandomSource random) {
         final BlockState state = level.getBlockState(pos);

         for (RuleTest rule : this.placingIn) {
            if (rule.test(state, random))
               return true;
         }

         return false;
      }

      public int getBlockCount(RandomSource random) {
         return this.blocks.sample(random);
      }

      public int getSize(RandomSource random) {
         return this.size.sample(random);
      }
   }
}