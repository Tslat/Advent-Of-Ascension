package net.tslat.aoa3.dimension.iromine.biomes;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.awt.*;
import java.util.Random;

public class BiomeIromine extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Iromine");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.4f);
		properties.setRainfall(100);
		properties.setWaterColor(new Color(255, 249, 173).getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.5f);
	}

	public BiomeIromine() {
		super(properties);
		setRegistryName("aoa3", "iromine");
		this.topBlock = BlockRegister.getUnmappedBlock("iromine_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("iromine_stone").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeIromineDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return 0;
	}

	public class BiomeIromineDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.lyon.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreLyon.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.lyon.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.lyon.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stoneIromine)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(20) + 45, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 40; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == topBlock) {
					switch (rand.nextInt(3)) {
						case 0:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantIroGrass.getDefaultState());
							break;
						case 1:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantLurchians.getDefaultState());
							break;
						case 2:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantIrotops.getDefaultState());
							break;
					}
				}

			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 3; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);

				switch (rand.nextInt(4)) {
					case 0:
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("IrodustTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("IrodustTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posY = world.getHeight(posX + 4, posZ + 4);

						if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)) == biome.topBlock)
							StructuresHandler.generateStructure("IrogoldTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("IrogoldTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (rand.nextInt(50) == 0) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX + 3, posZ + 3) + 15 + rand.nextInt(5);

				if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("IroDoubler", world, rand, pos.setPos(posX, posY, posZ));
					}
					else {
						StructuresHandler.generateStructure("IroFloater", world, rand, pos.setPos(posX, posY, posZ));
					}
				}
			}
		}
	}
}
