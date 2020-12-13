package net.tslat.aoa3.worldgen.structures.overworld.proper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

import java.util.Random;

public class AmphibiyteCovePiece extends ScatteredStructurePiece {
	private boolean generated = false;

	public AmphibiyteCovePiece(Random rand, int x, int y, int z) {
		super(AoAWorldGen.Structures.AMCOVE, rand, x, y, z, 17, 7, 18);
	}

	public AmphibiyteCovePiece(TemplateManager templateManager, CompoundNBT nbt) {
		super(AoAWorldGen.Structures.AMCOVE, nbt);
	}

	@Override
	public boolean create(IWorld world, ChunkGenerator<?> chunkGen, Random rand, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
		if (!generated) {
			int posX = boundingBox.minX + rand.nextInt(13);
			int posZ = boundingBox.minZ + rand.nextInt(12);
			int posY = chunkGen.getHeight(posX + 8, posZ + 8, Heightmap.Type.WORLD_SURFACE_WG);

			StructuresHandler.generateStructure("AmphibiyteCove", world, rand, new BlockPos(posX, posY - 8, posZ));

			generated = true;

			return true;
		}

		return false;
	}
}
