package net.tslat.aoa3.dimension.dustopia.biomes;

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

public class BiomeDustopia extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Dustopian Forest");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.25F);
		properties.setRainfall(500);
		properties.setWaterColor(Color.BLACK.getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.25f);
	}

	public BiomeDustopia() {
		super(properties);
		setRegistryName("aoa3", "dustopia");
		this.topBlock = BlockRegister.getUnmappedBlock("dustopia_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("dustopia_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeDustopiaDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLACK.getRGB();
	}

	public class BiomeDustopiaDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 8; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					if (rand.nextBoolean()) {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantDawnBush.getDefaultState());
					}
					else {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantDawnFlower.getDefaultState());
					}
				}
			}

			for (int i = 0; i < 25; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
					world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantDawnGrass.getDefaultState());
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 5; i++) {
				switch (rand.nextInt(3)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("DawnTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("DawnTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("DawnTree3", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (rand.nextInt(5) == 0) {
				switch (rand.nextInt(3)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 2, posZ + 2);

						if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
							StructuresHandler.generateStructure("DawnCage1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ + 5);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 5)) == biome.topBlock)
							StructuresHandler.generateStructure("DawnCage2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 5, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("DawnCage3", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
