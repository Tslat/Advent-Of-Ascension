package net.tslat.aoa3.dimension.voxponds.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;

import java.awt.*;
import java.util.Random;

public class BiomeVoxPonds extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Vox Ponds");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0f);
		properties.setRainfall(0);
		properties.setWaterColor(new Color(103, 147, 0).getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0.0f);
		properties.setHeightVariation(0.1f);
	}

	public BiomeVoxPonds() {
		super(properties);
		setRegistryName("aoa3", "vox_ponds");
		this.topBlock = BlockRegister.getUnmappedBlock("toxic_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("toxic_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeVoxPondsDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.GREEN.getRGB();
	}

	public class BiomeVoxPondsDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					if (rand.nextBoolean() && world.getBlockState(pos.setPos(basePos.getX() + x, 38, basePos.getZ() + z)) == biome.topBlock)
						world.setBlockState(pos.up(), BlockRegister.plantDeadGrass.getDefaultState());
				}
			}

			for (int i = 0; i < 3; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 39;

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
					world.setBlockState(pos.up(), BlockRegister.shroomVox.getDefaultState());
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < rand.nextInt(3); i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 39;

				switch (rand.nextInt(3)) {
					case 0:
						if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTree1", world, rand, pos.setPos(posX, posY, posZ));

						break;
					case 1:
						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTree2", world, rand, pos.setPos(posX, posY, posZ));

						break;
					case 2:
						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTree3", world, rand, pos.setPos(posX, posY, posZ));

						break;
				}
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 12; i++) {
				switch (rand.nextInt(5))  {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = 39;

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTentacle1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = 39;

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTentacle2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = 39;

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTentacle3", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = 39;

						if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 1)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTentacle4", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = 39;

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("ToxicTentacle5", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}

			for (int i = 0; i < 6; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 39;

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("VoxBranch1", world, rand, pos.up());
					}
					else {
						StructuresHandler.generateStructure("VoxBranch2", world, rand, pos.up());
					}
				}
			}

			for (int i = 0; i < 8; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 38;

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == BlockRegister.toxicWaste)
					StructuresHandler.generateStructure("MiniTentacles", world, rand, pos.up());
			}

			if (rand.nextInt(16) == 0) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 16;

				if (rand.nextBoolean()) {
					StructuresHandler.generateStructure("DegradedSupportBeam1", world, rand, pos.setPos(posX, posY, posZ));
				}
				else {
					StructuresHandler.generateStructure("DegradedSupportBeam2", world, rand, pos.setPos(posX, posY, posZ));
				}
			}

			for (int i = 0; i < rand.nextInt(6) + 2; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 28;
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.nextInt(4)) {
					case 0:
						structure = StructuresHandler.getStructure("ToxicStem1");
						break;
					case 1:
						structure = StructuresHandler.getStructure("ToxicStem2");
						break;
					case 2:
						structure = StructuresHandler.getStructure("ToxicStem3");
						break;
					case 3:
						structure = StructuresHandler.getStructure("ToxicStem4");
						break;
				}

				StructuresHandler.generateStructure(structure, world, rand, pos.setPos(posX, posY, posZ));
			}
		}
	}
}
