package net.tslat.aoa3.dimension.gardencia.biomes;

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

public class BiomeGardencia extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Gardencia");

	static {
		properties.setTemperature(0.5f);
		properties.setRainfall(0.7f);
		properties.setWaterColor(1691929);
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.3f);
	}

	public BiomeGardencia() {
		super(properties);
		setRegistryName("aoa3", "gardencia");
		this.topBlock = BlockRegister.getUnmappedBlock("gardencia_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("gardencia_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeGardenciaDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET, BiomeDictionary.Type.HILLS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.GREEN.getRGB();
	}

	public class BiomeGardenciaDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 59; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (posY > 67 && world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
					switch (rand.nextInt(3)) {
						case 0:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantDaileers.getDefaultState());
							break;
						case 1:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantLylips.getDefaultState());
							break;
						case 2:
							StructuresHandler.generateStructure("GardenGrass", world, rand, pos.setPos(posX, posY, posZ));
							break;
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 2; i++) {
				if (rand.nextBoolean())
					continue;

				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (posY > 67) {
					AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

					switch (rand.nextInt(10)) {
						case 0:
							if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 3)) == biome.topBlock)
								structure = StructuresHandler.getStructure("RoseTree");

							break;
						case 1:
							if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ)) == biome.topBlock)
								structure = StructuresHandler.getStructure("BlueStarflower1");

							break;
						case 2:
							if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 5)) == biome.topBlock)
								structure = StructuresHandler.getStructure("BlueStarflower2");

							break;
						case 3:
							if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ)) == biome.topBlock)
								structure = StructuresHandler.getStructure("RoseStarflower1");

							break;
						case 4:
							if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 5)) == biome.topBlock)
								structure = StructuresHandler.getStructure("RoseStarflower2");

							break;
						case 5:
							if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 5)) == biome.topBlock)
								structure = StructuresHandler.getStructure("Sunflower1");

							break;
						case 6:
							if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)) == biome.topBlock)
								structure = StructuresHandler.getStructure("Sunflower2");

							break;
						case 7:
							if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)) == biome.topBlock)
								structure = StructuresHandler.getStructure("BlueTulip");

							break;
						case 8:
							if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)) == biome.topBlock)
								structure = StructuresHandler.getStructure("PurpleTulip");

							break;
						case 9:
							if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)) == biome.topBlock)
								structure = StructuresHandler.getStructure("MagentaTulip");

							break;
					}

					StructuresHandler.generateStructure(structure, world, rand, pos.setPos(posX, posY, posZ));
				}
			}
		}
	}
}
