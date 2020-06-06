package net.tslat.aoa3.dimension.lunalus.biomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.WorldGenLuniciaTree;
import net.tslat.aoa3.worldgen.trees.WorldGenLunossoTree;

import java.awt.*;
import java.util.Random;

public class BiomeLunalus extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Lunalus");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0f);
		properties.setRainfall(0f);
		properties.setWaterColor(1644825);
		properties.setRainDisabled();
		properties.setBaseHeight(0.1f);
		properties.setHeightVariation(0.2f);
	}

	public BiomeLunalus() {
		super(properties);
		setRegistryName("aoa3", "lunalus");
		this.topBlock = BlockRegister.getUnmappedBlock("lunasole_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("lunasole_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeLunalusDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID, BiomeDictionary.Type.SPARSE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.PINK.getRGB();
	}

	public class BiomeLunalusDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					for (int y = 36; y <= 60; y++) {
						Block bl = world.getBlockState(pos.setPos(basePos.getX() + x, y - 1, basePos.getZ() + z)).getBlock();

						if (rand.nextInt(10) == 0 && (bl == BlockRegister.LUNASOLE_GRASS || bl == BlockRegister.LUNALYTE_GRASS) && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
							if (rand.nextBoolean()) {
								world.setBlockState(pos.up(), BlockRegister.LUNTAR.getDefaultState());
							}
							else {
								world.setBlockState(pos.up(), BlockRegister.LUNALIP.getDefaultState());
							}
						}
					}
				}
			}

			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					for (int y = 61; y <= 85; y++) {
						Block bl = world.getBlockState(pos.setPos(basePos.getX() + x, y - 1, basePos.getZ() + z)).getBlock();

						if (rand.nextInt(10) == 0 && (bl == BlockRegister.LUNASOLE_GRASS || bl == BlockRegister.LUNALYTE_GRASS) && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
							if (rand.nextBoolean()) {
								world.setBlockState(pos.up(), BlockRegister.LUNTAR.getDefaultState());
							}
							else {
								world.setBlockState(pos.up(), BlockRegister.LUNALIP.getDefaultState());
							}
						}
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			int count = 0;

			LowTrees:
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					for (int y = 36; y <= 60; y++) {
						if (rand.nextInt(25) == 0) {
							Block bl;

							switch (rand.nextInt(4)) {
								case 0:
									if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 1, y - 1, basePos.getZ() + z + 1)).getBlock()) == BlockRegister.LUNALYTE_GRASS || bl == BlockRegister.LUNASOLE_GRASS) {
										StructuresHandler.generateStructure("LunossoTree1", world, rand, pos.add(-1, 1, -1));
										count++;
									}
									break;
								case 1:
									if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == BlockRegister.LUNALYTE_GRASS || bl == BlockRegister.LUNASOLE_GRASS) {
										StructuresHandler.generateStructure("LunossoTree2", world, rand, pos.add(-2, 1, -2));
										count++;
									}
									break;
								case 2:
									if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == BlockRegister.LUNALYTE_GRASS || bl == BlockRegister.LUNASOLE_GRASS) {
										StructuresHandler.generateStructure("LuniciaTree1", world, rand, pos.add(-2, 1, -2));
										count++;
									}
									break;
								case 3:
									if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == BlockRegister.LUNALYTE_GRASS || bl == BlockRegister.LUNASOLE_GRASS) {
										StructuresHandler.generateStructure("LuniciaTree2", world, rand, pos.add(-2, 1, -2));
										count++;
									}
									break;
							}

							if (count == 2)
								break LowTrees;
						}
					}
				}
			}

			count = 0;

			HighTrees:
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					for (int y = 61; y <= 85; y++) {
						if (rand.nextInt(25) == 0) {
							Block bl;

							if (rand.nextBoolean()) {
								if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == BlockRegister.LUNALYTE_GRASS || bl == BlockRegister.LUNASOLE_GRASS) {
									new WorldGenLunossoTree(null).generate(world, rand, pos.up());
									count++;
								}
							}
							else {
								if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == BlockRegister.LUNALYTE_GRASS || bl == BlockRegister.LUNASOLE_GRASS) {
									new WorldGenLuniciaTree(null).generate(world, rand, pos.up());
									count++;
								}
							}

							if (count == 2)
								break HighTrees;
						}
					}
				}
			}
		}
	}
}
