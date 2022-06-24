package net.tslat.aoa3.content.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

import java.util.Optional;

import static net.tslat.aoa3.content.world.gen.structure.AoAStructure.Settings.aoaSettings;

public class SinglePlacementStructure extends AoAStructure {
	public static final Codec<SinglePlacementStructure> CODEC = RecordCodecBuilder.<SinglePlacementStructure>mapCodec(codec -> codec.group(
			aoaSettings(),
			BlockPos.CODEC.fieldOf("position").forGetter(SinglePlacementStructure::pos)
	).apply(codec, SinglePlacementStructure::new)).codec();

	private final BlockPos pos;

	public SinglePlacementStructure(Settings settings, BlockPos pos) {
		super(settings);

		this.pos = pos;
	}

	public BlockPos pos() {
		return this.pos;
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(GenerationContext genContext) {
		Pools.forceBootstrap();

		return JigsawPlacement.addPieces(genContext, this.settings.startPool(), this.settings.startJigsawName(), this.settings.maxPieces(), this.pos, false, this.settings.startHeightmap(), 128);
	}
}
