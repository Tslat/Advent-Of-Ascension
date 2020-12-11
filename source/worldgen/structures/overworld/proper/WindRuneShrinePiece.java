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

public class WindRuneShrinePiece extends ScatteredStructurePiece {
	private boolean generated = false;

	public WindRuneShrinePiece(Random rand, int x, int y, int z) {
		super(AoAWorldGen.Structures.WRShrine, rand, x, y, z, 7, 4, 7);
	}

	public WindRuneShrinePiece(TemplateManager templateManager, CompoundNBT nbt) {
		super(AoAWorldGen.Structures.WRShrine, nbt);
	}

	@Override
	public boolean create(IWorld world, ChunkGenerator<?> chunkGen, Random rand, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
		if (!generated) {
			int posX = boundingBox.minX + rand.nextInt(16);
			int posZ = boundingBox.minZ + rand.nextInt(16);
			int posY = chunkGen.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			StructuresHandler.generateStructure("WindRuneShrine", world, rand, new BlockPos(posX, Math.min(posY + 30, 240), posZ));

			generated = true;

			return true;
		}

		return false;
	}
}
