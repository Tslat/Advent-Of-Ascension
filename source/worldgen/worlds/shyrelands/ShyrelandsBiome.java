package net.tslat.aoa3.worldgen.worlds.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.AoAStructure;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.ShyreTreeGenerator;

public class ShyrelandsBiome extends AoABiome {
	public ShyrelandsBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(AoABlocks.BRIGHT_GRASS.get().getDefaultState(), AoABlocks.BRIGHT_ROCK.get().getDefaultState(), AoABlocks.BRIGHT_ROCK.get().getDefaultState()))
				.temperature(2)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 255))
				.waterFogColor(NumberUtil.RGB(0, 0, 255))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 255);
	}

	@Override
	public void generateStructuredChunk(WorldGenRegion world, SharedSeedRandom rand, IChunk chunk, int startX, int startZ) {
		BlockState whiteBricks = AoABlocks.WHITE_SHYRE_BRICKS.get().getDefaultState();
		BlockState yellowBricks = AoABlocks.YELLOW_SHYRE_BRICKS.get().getDefaultState();
		BlockState glass = AoABlocks.SHYRE_GLASS.get().getDefaultState();
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);

		setAllBlocksInRegion(chunk, 0, 0, 0, 15, 29, 15, AoABlocks.BRIGHT_ROCK.get().getDefaultState());
		setAllBlocksInRegion(chunk, 1, 30, 1, 14, 30, 14, AoABlocks.BRIGHT_GRASS.get().getDefaultState());

		setAllBlocksInRegion(chunk, 0, 30, 0, 15, 30, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 30, 15, 15, 30, 15, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 30, 1, 0, 30, 14, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 30, 1, 15, 30, 14, whiteBricks);

		setAllBlocksInRegion(chunk, 0, 46, 0, 0, 54, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 46, 15, 0, 54, 15, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 46, 0, 15, 54, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 46, 15, 15, 54, 15, whiteBricks);

		setAllBlocksInRegion(chunk, 0, 55, 0, 15, 55, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 55, 15, 15, 55, 15, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 55, 1, 0, 55, 14, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 55, 1, 15, 55, 14, whiteBricks);

		setAllBlocksInRegion(chunk, 1, 46, 0, 14, 54, 0, glass);
		setAllBlocksInRegion(chunk, 1, 46, 15, 14, 54, 15, glass);
		setAllBlocksInRegion(chunk, 0, 46, 1, 0, 54, 14, glass);
		setAllBlocksInRegion(chunk, 15, 46, 1, 15, 54, 14, glass);

		setAllBlocksInRegion(chunk, 0, 45, 0, 15, 45, 0, yellowBricks);
		setAllBlocksInRegion(chunk, 0, 45, 15, 15, 45, 15, yellowBricks);
		setAllBlocksInRegion(chunk, 0, 45, 1, 0, 45, 14, yellowBricks);
		setAllBlocksInRegion(chunk, 15, 45, 1, 15, 45, 14, yellowBricks);

		setAllBlocksInRegion(chunk, 0, 31, 0, 3, 44, 0, yellowBricks);
		setAllBlocksInRegion(chunk, 12, 31, 0, 14, 44, 0, yellowBricks);
		setAllBlocksInRegion(chunk, 0, 31, 15, 3, 44, 15, yellowBricks);
		setAllBlocksInRegion(chunk, 12, 31, 15, 15, 44, 15, yellowBricks);
		setAllBlocksInRegion(chunk, 0, 31, 0, 0, 44, 3, yellowBricks);
		setAllBlocksInRegion(chunk, 0, 31, 12, 0, 44, 14, yellowBricks);
		setAllBlocksInRegion(chunk, 15, 31, 0, 15, 44, 3, yellowBricks);
		setAllBlocksInRegion(chunk, 15, 31, 12, 15, 44, 14, yellowBricks);

		setAllBlocksInRegion(chunk, 4, 31, 0, 4, 44, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 11, 31, 0, 11, 44, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 4, 31, 15, 4, 44, 15, whiteBricks);
		setAllBlocksInRegion(chunk, 11, 31, 15, 11, 44, 15, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 31, 4, 0, 44, 4, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 31, 11, 0, 44, 11, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 31, 4, 15, 44, 4, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 31, 11, 15, 44, 11, whiteBricks);
		setAllBlocksInRegion(chunk, 5, 44, 0, 10, 44, 0, whiteBricks);
		setAllBlocksInRegion(chunk, 5, 44, 15, 10, 44, 15, whiteBricks);
		setAllBlocksInRegion(chunk, 0, 44, 5, 0, 44, 10, whiteBricks);
		setAllBlocksInRegion(chunk, 15, 44, 5, 15, 44, 10, whiteBricks);

		buildRandomDoor(Direction.NORTH, chunk, random);
		buildRandomDoor(Direction.SOUTH, chunk, random);
		buildRandomDoor(Direction.EAST, chunk, random);
		buildRandomDoor(Direction.WEST, chunk, random);

		setAllBlocksInRegion(chunk, 0, 0, 0, 15, 1, 15, AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState());
	}

	private void setAllBlocksInRegion(IChunk chunk, final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ, final BlockState block) {
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		int x;
		int y;
		int z;

		for (x = lowerX; x <= upperX; x++) {
			for (y = lowerY; y <= upperY; y++) {
				for (z = lowerZ; z <= upperZ; z++) {
					chunk.setBlockState(mutablePos.setPos(x, y, z), block, false);
				}
			}
		}
	}

	private void buildRandomDoor(Direction side, IChunk chunk, RandomUtil.EasyRandom rand) {
		BlockPos.Mutable pos = new BlockPos.Mutable();
		BlockState whiteBricks = AoABlocks.WHITE_SHYRE_BRICKS.get().getDefaultState();
		BlockState glass = AoABlocks.SHYRE_GLASS.get().getDefaultState();
		int x;
		int z;
		
		switch (side) {
			case NORTH:
			case SOUTH:
				z = 0;

				if (side == Direction.SOUTH)
					z = 15;

				switch (rand.randomNumberUpTo(5)) {
					case 0:
						chunk.setBlockState(pos.setPos(5, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(7, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(10, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(7, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(10, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 41, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(7, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 41, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(6, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 40, z), whiteBricks, false);
						break;
					case 1:
						chunk.setBlockState(pos.setPos(5, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(7, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(10, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(6, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(7, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(5, 41, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 41, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(6, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 40, z), whiteBricks, false);
						break;
					case 2:
						chunk.setBlockState(pos.setPos(5, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(7, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(8, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(9, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(10, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(7, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 41, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 41, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(6, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 40, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 40, z), whiteBricks, false);
						break;
					case 3:
						chunk.setBlockState(pos.setPos(5, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(6, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 41, z), whiteBricks, false);
						break;
					case 4:
						chunk.setBlockState(pos.setPos(5, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(7, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(8, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(9, 43, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 43, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(6, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(7, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(8, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 42, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 42, z), glass, false);
						chunk.setBlockState(pos.setPos(5, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(6, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(9, 41, z), whiteBricks, false);
						chunk.setBlockState(pos.setPos(10, 41, z), whiteBricks, false);
						break;
				}
				break;
			case WEST:
			case EAST:
				x = 0;
				if (side == Direction.EAST)
					x = 15;

				switch (rand.randomNumberUpTo(5)) {
					case 0:
						chunk.setBlockState(pos.setPos(x, 43, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 6), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 9), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 6), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 9), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 40, 5), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 10), whiteBricks, false);
						break;
					case 1:
						chunk.setBlockState(pos.setPos(x, 43, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 6), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 9), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 5), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 10), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 40, 5), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 10), whiteBricks, false);
						break;
					case 2:
						chunk.setBlockState(pos.setPos(x, 43, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 6), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 7), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 8), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 9), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 40, 5), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 40, 10), whiteBricks, false);
						break;
					case 3:
						chunk.setBlockState(pos.setPos(x, 43, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 5), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 10), whiteBricks, false);
						break;
					case 4:
						chunk.setBlockState(pos.setPos(x, 43, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 7), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 8), glass, false);
						chunk.setBlockState(pos.setPos(x, 43, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 43, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 5), glass, false);
						chunk.setBlockState(pos.setPos(x, 42, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 7), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 8), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 42, 10), glass, false);
						chunk.setBlockState(pos.setPos(x, 41, 5), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 6), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 9), whiteBricks, false);
						chunk.setBlockState(pos.setPos(x, 41, 10), whiteBricks, false);
						break;
				}
				break;
		}
	}

	@Override
	public void decorate(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> chunkGenerator, IWorld world, long seed, SharedSeedRandom rand, BlockPos pos) {
		super.decorate(stage, chunkGenerator, world, seed, rand, pos);

		Biome biome = world.getBiomeManager().getBiome(pos);
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);

		if (stage == GenerationStage.Decoration.VEGETAL_DECORATION) {
			doPlantGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doTreeGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.UNDERGROUND_ORES) {
			doOreGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			doMiscGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doOreGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block fillerBlock = surfaceBuilder.config.getUnder().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < rand.randomNumberBetween(AoAConfig.SERVER.ORES.shyregemMinOresPerChunk.get(), AoAConfig.SERVER.ORES.shyregemMaxOresPerChunk.get()); i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = rand.randomNumberBetween(2, 27);

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == fillerBlock)
				world.setBlockState(pos, AoABlocks.SHYREGEM_ORE.get().getDefaultState(), 2);
		}

		for (int i = 0; i < rand.randomNumberBetween(AoAConfig.SERVER.ORES.shyrestoneMinOresPerChunk.get(), AoAConfig.SERVER.ORES.shyrestoneMaxOresPerChunk.get()); i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = rand.randomNumberBetween(2, 27);

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == fillerBlock)
				world.setBlockState(pos, AoABlocks.SHYRESTONE_ORE.get().getDefaultState(), 2);
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 9; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(14);
			posZ = basePos.getZ() + rand.randomNumberUpTo(14);
			posY = 54;

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
				world.setBlockState(pos.up(), AoABlocks.SHYRE_GLASS.get().getDefaultState(), 2);
				StructuresHandler.generateStructure("InvertedShyreStock", world, rand.source(), pos);
			}
		}

		for (int i = 0; i < 64; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(14);
			posZ = basePos.getZ() + rand.randomNumberUpTo(14);
			posY = 31;

			if (world.isAirBlock(pos.setPos(posX, posY, posZ)) && world.getBlockState(pos.down()).getBlock() == surfaceBlock) {
				switch (rand.randomNumberUpTo(5)) {
					case 0:
						if (rand.oneInNChance(3))
							StructuresHandler.generateStructure("ShyreStock", world, rand.source(), pos);
						break;
					case 1:
						world.setBlockState(pos, AoABlocks.SHYRE_WEED.get().getDefaultState(), 2);
						break;
					case 2:
						world.setBlockState(pos, AoABlocks.ARCBULB.get().getDefaultState(), 2);
						break;
					case 3:
						world.setBlockState(pos, AoABlocks.ARCFLOWER.get().getDefaultState(), 2);
						break;
					case 4:
						world.setBlockState(pos, AoABlocks.HORIZON_DAISIES.get().getDefaultState(), 2);
						break;
				}
			}
		}

		if (rand.oneInNChance(40)) {
			posX = basePos.getX() + rand.randomNumberUpTo(14);
			posZ = basePos.getZ() + rand.randomNumberUpTo(14);

			if (world.isAirBlock(pos.setPos(posX, 31, posZ)) && world.getBlockState(pos.down()).getBlock() == surfaceBlock)
				world.setBlockState(pos, AoABlocks.TRILLIAD_BLOOM.get().getDefaultState(), 2);
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < rand.randomNumberUpTo(8); i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(12);
			posZ = basePos.getZ() + rand.randomNumberUpTo(12);
			posY = 31;

			if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock && world.getBlockState(pos.setPos(posX + 1, posY, posZ + 1)).getMaterial().isReplaceable())
				new ShyreTreeGenerator(null, null, rand.source()).generate(world, rand.source(), pos);
		}
	}

	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int baseX = basePos.getX();
		int baseY = basePos.getY();
		int baseZ = basePos.getZ();
		BlockState shyreCloud = AoABlocks.SHYRE_CLOUD.get().getDefaultState();
		BlockState glass = AoABlocks.SHYRE_GLASS.get().getDefaultState();

		for (int x = 0; x <= 15; x++) {
			for (int z = 0; z <= 15; z++) {
				if (rand.oneInNChance(3))
					world.setBlockState(pos.setPos(baseX + x, 78, baseZ + z), shyreCloud, 2);
			}
		}

		for (int x = 0; x <= 15; x++) {
			for (int y = 31; y <= 45; y++) {
				pos.setPos(baseX + x, baseY + y, baseZ);

				if (rand.oneInNChance(12) && world.getBlockState(pos).getBlock() == AoABlocks.YELLOW_SHYRE_BRICKS.get())
					world.setBlockState(pos, glass, 2);

				pos.setPos(baseX + x, baseY + y, baseZ + 15);

				if (rand.oneInNChance(12) && world.getBlockState(pos).getBlock() == AoABlocks.YELLOW_SHYRE_BRICKS.get())
					world.setBlockState(pos, glass, 2);
			}
		}

		for (int z = 0; z <= 15; z++) {
			for (int y = 31; y <= 45; y++) {
				pos.setPos(baseX, baseY + y, baseZ + z);

				if (rand.oneInNChance(12) && world.getBlockState(pos).getBlock() == AoABlocks.YELLOW_SHYRE_BRICKS.get())
					world.setBlockState(pos, glass, 2);

				pos.setPos(baseX + 15, baseY + y, baseZ + z);

				if (rand.oneInNChance(12) && world.getBlockState(pos).getBlock() == AoABlocks.YELLOW_SHYRE_BRICKS.get())
					world.setBlockState(pos, glass, 2);
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.craexxeusTowerSpawnChance.get())) {
			x = basePos.getX() + 4;
			z = basePos.getZ() + 4;
			y = 31;

			StructuresHandler.generateStructure("CraexxeusTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.whitewashingStationSpawnChance.get())) {
			StructuresHandler.generateStructure("WhitewashingStation", world, rand.source(), pos.setPos(basePos.getX() + 1, 31, basePos.getZ() + 1));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.shyreDecorationSpawnChance.get())) {
			AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

			switch (rand.randomNumberUpTo(7)) {
				case 0:
					structure = StructuresHandler.getStructure("DivinePlatform");
					break;
				case 1:
					structure = StructuresHandler.getStructure("ArcWizardCheckpoint");
					break;
				case 2:
					structure = StructuresHandler.getStructure("SoulscorneAmbush");
					break;
				case 3:
					structure = StructuresHandler.getStructure("StrangeShrine");
					break;
				case 4:
					structure = StructuresHandler.getStructure("ShyreDecoration1");
					break;
				case 5:
					structure = StructuresHandler.getStructure("ShyreDecoration2");
					break;
				case 6:
					structure = StructuresHandler.getStructure("ShyreDecoration3");
					break;
			}

			StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(basePos.getX() + 1, 31, basePos.getZ() + 1));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.shyreDungeonSpawnChance.get())) {
			switch (rand.randomNumberUpTo(3)) {
				case 0:
					x = basePos.getX() + 1 + rand.randomNumberUpTo(10);
					z = basePos.getZ() + 1 + rand.randomNumberUpTo(4);
					y = rand.randomNumberBetween(2, 17);

					StructuresHandler.generateStructure("LightwalkerDungeon", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 1:
					x = basePos.getX() + 1 + rand.randomNumberUpTo(15);
					z = basePos.getZ() + 1 + rand.randomNumberUpTo(15);
					y = rand.randomNumberBetween(2, 17);

					StructuresHandler.generateStructure("LuxocronDungeon", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 2:
					x = basePos.getX() + 1;
					z = basePos.getZ() + 1 + rand.randomNumberUpTo(15);
					y = rand.randomNumberBetween(2, 17);

					StructuresHandler.generateStructure("ShyreTrollDungeon", world, rand.source(), pos.setPos(x, y, z));
					break;
			}
		}
	}
}
