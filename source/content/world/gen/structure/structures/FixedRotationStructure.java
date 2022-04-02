/*
package net.tslat.aoa3.content.world.gen.structure.structures;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FixedRotationStructure extends AoAStructureBase<NoFeatureConfig> {
	public FixedRotationStructure(GenerationStep.Decoration decorationStage, String templatePoolPath) {
		super(NoFeatureConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<NoFeatureConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<NoFeatureConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected boolean shouldAvoidRotating() {
				return true;
			}
		};
	}
}
*/
