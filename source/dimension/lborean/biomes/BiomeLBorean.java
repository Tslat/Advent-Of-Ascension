package net.tslat.aoa3.dimension.lborean.biomes;

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

public class BiomeLBorean extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("L'Borean Ponds");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.5f);
		properties.setRainfall(500);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.05f);
	}

	public BiomeLBorean() {
		super(properties);
		setRegistryName("aoa3", "lborean");
		this.topBlock = BlockRegister.grassBorean.getDefaultState();
		this.fillerBlock = BlockRegister.dirtBorean.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeLBoreanDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLUE.getRGB();
	}

	public class BiomeLBoreanDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 65; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					switch (rand.nextInt(5)) {
						case 0:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantBurealStocks.getDefaultState());
							break;
						case 1:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantWaterweedsGreen.getDefaultState());
							break;
						case 2:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantWaterweedsWhite.getDefaultState());
							break;
						case 3:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantWaterweedsYellow.getDefaultState());
							break;
						case 4:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantOcealitesBlue.getDefaultState());
							break;
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			posX = basePos.getX() + rand.nextInt(16);
			posZ = basePos.getZ() + rand.nextInt(16);

			switch (rand.nextInt(6)) {
				case 0:
					posY = world.getHeight(posX + 3, posZ + 3);

					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
						StructuresHandler.generateStructure("GreenCoral1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posY = world.getHeight(posX + 1, posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 10)) == biome.topBlock)
						StructuresHandler.generateStructure("GreenCoral2", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posY = world.getHeight(posX + 4, posZ + 4);

					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)) == biome.topBlock)
						StructuresHandler.generateStructure("PinkCoral1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posY = world.getHeight(posX + 1, posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 10, posY - 1, posZ + 1)) == biome.topBlock)
						StructuresHandler.generateStructure("OrangeCoral", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posY = world.getHeight(posX + 4, posZ + 4);

					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)) == biome.topBlock)
						StructuresHandler.generateStructure("WhiteCoral", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 5:
					posY = world.getHeight(posX + 4, posZ + 5);

					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 5)) == biome.topBlock)
						StructuresHandler.generateStructure("YellowCoral", world, rand, pos.setPos(posX, posY, posZ));
					break;
			}
		}
	}
}
