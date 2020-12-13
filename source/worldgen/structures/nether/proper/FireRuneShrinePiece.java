package net.tslat.aoa3.worldgen.structures.nether.proper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

import java.util.Random;

public class FireRuneShrinePiece extends ScatteredStructurePiece {
	private boolean generated = false;

	public FireRuneShrinePiece(Random rand, int x, int y, int z) {
		super(AoAWorldGen.Structures.FRShrine, rand, x, y, z, 7, 4, 7);
	}

	public FireRuneShrinePiece(TemplateManager templateManager, CompoundNBT nbt) {
		super(AoAWorldGen.Structures.FRShrine, nbt);
	}

	@Override
	public boolean create(IWorld world, ChunkGenerator<?> chunkGen, Random rand, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
		if (!generated) {
			int posX = boundingBox.minX + rand.nextInt(16);
			int posZ = boundingBox.minZ + rand.nextInt(16);
			int posY = rand.nextInt(50) + 10;

			StructuresHandler.generateStructure("FireRuneShrine", world, rand, new BlockPos(posX, posY, posZ));

			generated = true;

			return true;
		}

		return false;
	}
}
