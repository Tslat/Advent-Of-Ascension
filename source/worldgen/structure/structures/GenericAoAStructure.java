package net.tslat.aoa3.worldgen.structure.structures;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class GenericAoAStructure extends AoAStructureBase<NoFeatureConfig> {
	public GenericAoAStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(NoFeatureConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<NoFeatureConfig> getStructureStart() {
		return AoAStructureStart::new;
	}
}
