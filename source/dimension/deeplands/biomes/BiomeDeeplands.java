package net.tslat.aoa3.dimension.deeplands.biomes;

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

public class BiomeDeeplands extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Deeplands");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.0f);
		properties.setRainfall(500f);
		properties.setRainDisabled();
		properties.setBaseHeight(0.0f);
		properties.setHeightVariation(0.05f);
	}

	public BiomeDeeplands() {
		super(properties);
		setRegistryName("aoa3", "deeplands");
		this.topBlock = BlockRegister.stoneDeeplands.getDefaultState();
		this.fillerBlock = BlockRegister.stoneDeeplands.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeDeeplandsDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.SPARSE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.GREEN.getRGB();
	}

	public class BiomeDeeplandsDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
				for (int i = 0; i < ConfigurationUtil.OreConfig.chargedRunium.veinsPerChunk; i++) {
					new WorldGenMinable(BlockRegister.oreChargedRunium.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.chargedRunium.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.chargedRunium.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stoneDeeplands)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(45) + 70, rand.nextInt(16)));
				}

			for (int i = 0; i < 4; i++) {
				new WorldGenMinable(BlockRegister.deepCase.getDefaultState(), 5, BlockMatcher.forBlock(BlockRegister.stoneDeeplands))
						.generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(45) + 70, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 30; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 20;

				if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
					if (rand.nextBoolean()) {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantDeepGrass.getDefaultState());
					}
					else {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantDeepBlooms.getDefaultState());
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (rand.nextInt(3) == 0) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 20;

				if (rand.nextBoolean()) {
					if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("Deepshroom1", world, rand, pos.setPos(posX, posY, posZ));
				}
				else {
					if (world.getBlockState(pos.setPos(posX + 4, posY, posZ + 4)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("Deepshroom2", world, rand, pos.setPos(posX, posY, posZ));
				}
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			posX = basePos.getX() + rand.nextInt(16);
			posZ = basePos.getZ() + rand.nextInt(16);
			posY = 19;

			world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.lightDeepCrystal.getDefaultState());

			for (int i = 0; i < 25; i++) {
				switch (rand.nextInt(3)) {
					case 0:
						new WorldGenMinable(BlockRegister.deeplandsTrapExplosion.getDefaultState(), 20, BlockMatcher.forBlock(BlockRegister.stoneDeeplands)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(45) + 70, rand.nextInt(16)));
						break;
					case 1:
						new WorldGenMinable(BlockRegister.deeplandsTrapLava.getDefaultState(), 20, BlockMatcher.forBlock(BlockRegister.stoneDeeplands)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(45) + 70, rand.nextInt(16)));
						break;
					case 2:
						new WorldGenMinable(BlockRegister.deeplandsTrapNipper.getDefaultState(), 20, BlockMatcher.forBlock(BlockRegister.stoneDeeplands)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(45) + 70, rand.nextInt(16)));
						break;
				}
			}

			if (rand.nextInt(25) == 0) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 20 - rand.nextInt(2);


				if (world.getBlockState(pos.setPos(posX, 20, posZ)).getBlock() == Blocks.AIR) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("DeepSpire1", world, rand, pos.setPos(posX, posY, posZ));
					}
					else {
						StructuresHandler.generateStructure("DeepSpire2", world, rand, pos.setPos(posX, posY, posZ));
					}
				}
			}

			if (rand.nextInt(6) == 0) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 20;

				switch (rand.nextInt(3)) {
					case 0:
						if (world.getBlockState(pos.setPos(posX + 6, posY, posZ + 6)).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("FossilisedRibs1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						if (world.getBlockState(pos.setPos(posX + 6, posY, posZ + 6)).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("FossilisedRibs2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						if (world.getBlockState(pos.setPos(posX + 7, posY, posZ + 7)).getBlock() == Blocks.AIR)
							StructuresHandler.generateStructure("BoneCircle", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
