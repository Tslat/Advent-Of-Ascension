package net.tslat.aoa3.dimension.celeve.biomes;

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

public class BiomeCeleve extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Celeve");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.5f);
		properties.setRainfall(0f);
		properties.setRainDisabled();
		properties.setBaseHeight(0.1f);
		properties.setHeightVariation(0.2f);
	}

	public BiomeCeleve() {
		super(properties);
		setRegistryName("aoa3", "celeve");
		this.topBlock = BlockRegister.grassCeleve.getDefaultState();
		this.fillerBlock = BlockRegister.dirtCeleve.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeCeleveDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MAGICAL);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.GREEN.getRGB();
	}

	public class BiomeCeleveDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 60; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					switch (rand.nextInt(5)) {
						case 0:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCeleviansBlue.getDefaultState());
							break;
						case 1:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCeleviansRed.getDefaultState());
							break;
						case 2:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCeleviansWhite.getDefaultState());
							break;
						case 3:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCeleviansPurple.getDefaultState());
							break;
						case 4:
							StructuresHandler.generateStructure("Celebulb", world, rand, pos.setPos(posX, posY, posZ));
							break;
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 2; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX + 2, posZ + 2);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock) {
					switch (rand.nextInt(4)) {
						case 0:
							StructuresHandler.generateStructure("BlueCelevusTree", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 1:
							StructuresHandler.generateStructure("GreenCelevusTree", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 2:
							StructuresHandler.generateStructure("PurpleCelevusTree", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 3:
							StructuresHandler.generateStructure("YellowCelevusTree", world, rand, pos.setPos(posX, posY, posZ));
							break;
					}
				}
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			posX = basePos.getX() + rand.nextInt(16);
			posZ = basePos.getZ() + rand.nextInt(16);
			posY = world.getHeight(posX + 2, posZ + 2);

			if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)) == biome.topBlock)
				StructuresHandler.generateStructure("CelevePole", world, rand, pos.setPos(posX, posY, posZ));
		}
	}
}
