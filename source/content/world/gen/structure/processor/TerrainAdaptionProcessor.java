package net.tslat.aoa3.content.world.gen.structure.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.tslat.aoa3.common.registration.worldgen.AoAStructureProcessors;

import java.util.List;

public class TerrainAdaptionProcessor extends StructureProcessor {
    public static final Codec<TerrainAdaptionProcessor> CODEC = RecordCodecBuilder.<TerrainAdaptionProcessor>mapCodec(codec -> codec.group(
            RuleTest.CODEC.fieldOf("pos_test").forGetter(TerrainAdaptionProcessor::posTest)).apply(codec, TerrainAdaptionProcessor::new)).codec();

    private final RuleTest posTest;

    public TerrainAdaptionProcessor(RuleTest posTest) {
        this.posTest = posTest;
    }

    public RuleTest posTest() {
        return this.posTest;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return AoAStructureProcessors.TERRAIN_ADAPTION.get();
    }

    @Override
    public List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor level, BlockPos posOffset, BlockPos blockPos, List<StructureTemplate.StructureBlockInfo> originalBlockData, List<StructureTemplate.StructureBlockInfo> modifiedBlockData, StructurePlaceSettings placementSettings) {
        for (StructureTemplate.StructureBlockInfo info : modifiedBlockData) {
            BlockPos pos = info.pos();

            if (this.posTest.test(info.state(), level.getRandom()) && level.getBlockState(pos.below()).isAir()) {
                final List<BlockPos> toPlacePositions = new ObjectArrayList<>();
                BlockState lastState;

                while ((lastState = level.getBlockState(pos = pos.below())).isAir() && pos.getY() > level.getMinBuildHeight()) {
                    toPlacePositions.add(pos);
                }

                if (pos.getY() == level.getMinBuildHeight())
                    return modifiedBlockData;

                for (BlockPos terrainPos : toPlacePositions) {
                    level.setBlock(terrainPos, lastState, Block.UPDATE_CLIENTS);
                }
            }
        }

        return modifiedBlockData;
    }
}
