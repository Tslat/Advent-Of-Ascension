package net.tslat.aoa3.dimension.candyland.biomes;

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

import java.awt.*;
import java.util.Random;

public class BiomeCandyland extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Candyland");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.4f);
		properties.setRainfall(100);
		properties.setWaterColor(10040115);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.1f);
	}

	public BiomeCandyland() {
		super(properties);
		setRegistryName("aoa3", "candyland");
		this.topBlock = BlockRegister.getUnmappedBlock("candyland_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("candyland_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeCandylandDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.YELLOW.getRGB();
	}

	public class BiomeCandylandDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 10; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
					switch (rand.nextInt(5)) {
						case 0:
							StructuresHandler.generateStructure("CandyTube", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 1:
							StructuresHandler.generateStructure("CandyStack", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 2:
							StructuresHandler.generateStructure("GreenPeppermintStack", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 3:
							StructuresHandler.generateStructure("RedPeppermintStack", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 4:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCandycane.getDefaultState());
							break;
					}
				}
			}

			for (int i = 0; i < 100; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock && world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
					if (rand.nextBoolean()) {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCandyGrass.getDefaultState());
					}
					else {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCandyGrassBlue.getDefaultState());
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			switch (rand.nextInt(12)) {
				case 0:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 1, posZ + 6);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 6)) == biome.topBlock)
						StructuresHandler.generateStructure("CandyCane1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 4, posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 1)) == biome.topBlock)
						StructuresHandler.generateStructure("CandyCane2", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 1, posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
						StructuresHandler.generateStructure("CandyCane3", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 1, posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
						StructuresHandler.generateStructure("CandyCane4", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 2);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
						StructuresHandler.generateStructure("CottonCandyTree1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 5:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 2);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
						StructuresHandler.generateStructure("CottonCandyTree2", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 6:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 2);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
						StructuresHandler.generateStructure("AquaCottonCandyTree1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 7:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 2);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
						StructuresHandler.generateStructure("AquaCottonCandyTree2", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 8:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 2);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
						StructuresHandler.generateStructure("Lollypop1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 9:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 2);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
						StructuresHandler.generateStructure("Lollypop2", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 10:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 4, posZ);

					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ)) == biome.topBlock)
						StructuresHandler.generateStructure("SwirlPop1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 11:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX, posZ + 4);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 4)) == biome.topBlock)
						StructuresHandler.generateStructure("SwirlPop2", world, rand, pos.setPos(posX, posY, posZ));
					break;
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (rand.nextInt(3) == 0) {
				switch (rand.nextInt(6)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ + 1);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("ChocolateBar1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure("ChocolateBar2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ + 1);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("WhiteChocolateBar1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure("WhiteChocolateBar2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ + 1);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("DarkChocolateBar1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 5:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure("DarkChocolateBar2", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
