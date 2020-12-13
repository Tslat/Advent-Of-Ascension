package net.tslat.aoa3.worldgen.structures.overworld.proper;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class RuinedTeleporterFrameStructure extends ScatteredStructure<NoFeatureConfig> {
	public RuinedTeleporterFrameStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
		super(configFactory);
	}

	@Override
	protected int getSeedModifier() {
		return 456768723;
	}

	@Override
	public String getStructureName() {
		return "Ruined_Teleporter_Frame";
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}

	public static class Start extends StructureStart {
		public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox boundingBox, int refCount, long seed) {
			super(structure, chunkPosX, chunkPosZ, boundingBox, refCount, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
			RuinedTeleporterFramePiece piece = new RuinedTeleporterFramePiece(rand, chunkX * 16, 1, chunkZ * 16);

			components.add(piece);
			recalculateStructureSize();
		}
	}
}
