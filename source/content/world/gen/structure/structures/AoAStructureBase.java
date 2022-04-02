/*
package net.tslat.aoa3.content.world.gen.structure.structures;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfiguration;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.tslat.aoa3.advent.AdventOfAscension;

public abstract class AoAStructureBase<T extends FeatureConfiguration> extends Structure<T> {
	private final GenerationStep.Decoration decorationStage;
	private final ResourceLocation templatePoolPath;

	public AoAStructureBase(Codec<T> codec, GenerationStep.Decoration decorationStage, String templatePoolPath) {
		super(codec);

		this.decorationStage = decorationStage;
		this.templatePoolPath = new ResourceLocation(AdventOfAscension.MOD_ID, templatePoolPath);
	}

	@Override
	public IStartFactory<T> getStartFactory() {
		return getStructureStart();
	}

	protected abstract AoAStartFactory<T> getStructureStart();

	@Override
	public GenerationStep.Decoration step() {
		return this.decorationStage;
	}

	public ResourceLocation getTemplatePoolPath() {
		return this.templatePoolPath;
	}

	public interface AoAStartFactory<C extends FeatureConfiguration> extends Structure.IStartFactory<C> {
		@Override
		default StructureStart<C> create(Structure<C> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
			return create((AoAStructureBase<C>)structure, chunkX, chunkZ, boundingBox, references, seed);
		}

		AoAStructureStart<C> create(AoAStructureBase<C> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed);
	}
}
*/
