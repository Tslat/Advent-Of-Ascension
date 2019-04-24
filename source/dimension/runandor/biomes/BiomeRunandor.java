package net.tslat.aoa3.dimension.runandor.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.StructuresHandler;

import java.awt.*;
import java.util.Random;

public class BiomeRunandor extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Runandor");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.5f);
		properties.setRainfall(0f);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.4f);
	}

	public BiomeRunandor() {
		super(properties);
		setRegistryName("aoa3", "runandor");
		this.topBlock = BlockRegister.grassRunic.getDefaultState();
		this.fillerBlock = BlockRegister.stoneRunic.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeRunandorDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MOUNTAIN);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLUE.getRGB();
	}

	public class BiomeRunandorDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 85; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					switch (rand.nextInt(3)) {
						case 0:
							world.setBlockState(pos.up(), BlockRegister.plantRunicBush.getDefaultState());
							break;
						case 1:
							world.setBlockState(pos.up(), BlockRegister.plantRuneBulbs.getDefaultState());
							break;
						case 2:
							world.setBlockState(pos.up(), BlockRegister.plantMagias.getDefaultState());
							break;
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 2; i++) {
				switch (rand.nextInt(6)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("RunicTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 2, posZ + 2);

						if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
							StructuresHandler.generateStructure("RunicTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("RunicTree3", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 2, posZ + 2);

						if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
							StructuresHandler.generateStructure("RunicTree4", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("RunicTree5", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 5:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 4, posZ + 4);

						if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)) == biome.topBlock)
							StructuresHandler.generateStructure("RunicTree6", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
