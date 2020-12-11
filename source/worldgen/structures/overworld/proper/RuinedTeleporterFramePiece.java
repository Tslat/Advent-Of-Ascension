package net.tslat.aoa3.worldgen.structures.overworld.proper;

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

public class RuinedTeleporterFramePiece extends ScatteredStructurePiece {
	private boolean generated = false;

	public RuinedTeleporterFramePiece(Random rand, int x, int y, int z) {
		super(AoAWorldGen.Structures.RTFrame, rand, x, y, z, 11, 9, 15);
	}

	public RuinedTeleporterFramePiece(TemplateManager templateManager, CompoundNBT nbt) {
		super(AoAWorldGen.Structures.RTFrame, nbt);
	}

	@Override
	public boolean create(IWorld world, ChunkGenerator<?> chunkGen, Random rand, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
		if (!generated) {
			int posX = boundingBox.minX + rand.nextInt(16);
			int posZ = boundingBox.minZ + rand.nextInt(15);
			int posY = rand.nextInt(10) + 10;

			StructuresHandler.generateStructure("RuinedTeleporterFrame", world, rand, new BlockPos(posX, posY, posZ));

			generated = true;

			return true;
		}

		return false;
	}
}
