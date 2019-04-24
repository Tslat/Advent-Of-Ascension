package net.tslat.aoa3.dimension.crystevia;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenCrystevia implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;
	private int curChunkX;
	private int curChunkZ;

	private final Biome biome = DimensionRegister.biomeCrystevia;

	private double[] noiseArray;
	private double[] depthBuffer = new double[256];

	private NoiseGeneratorOctaves minLimitPerlinNoiseGen;
	private NoiseGeneratorOctaves maxLimitPerlinNoiseGen;
	private NoiseGeneratorOctaves mainPerlinNoiseGen;
	private NoiseGeneratorOctaves stoneNoiseGen;
	private NoiseGeneratorOctaves gravelAndSandNoiseGen;
	private NoiseGeneratorOctaves scaleNoiseGen;
	private NoiseGeneratorOctaves depthNoiseGen;

	private double[] pnr;
	private double[] ar;
	private double[] br;
	private double[] noiseData4;
	private double[] dr;

	private static final double depthNoiseScaleX = 200;
	private static final double depthNoiseScaleZ = 200;
	private static final double depthNoiseScaleExponent = 0.5;
	private static final double coordScale = 684.412;
	private static final int mainNoiseScaleX = 80;
	private static final int mainNoiseScaleY = 160;
	private static final int mainNoiseScaleZ = 80;
	private static final double heightScale = 684.412;
	private static final int biomeDepthOffset = 0;
	private static final int biomeScaleOffset = 0;
	private static final double heightStretch = 12;
	private static final double baseSize = 8.5;
	private static final double lowerLimitScale = 512;
	private static final double upperLimitScale = 512;
	private static final float biomeDepthWeight = 1;
	private static final float biomeScaleWeight = 1;

	protected ChunkGenCrystevia(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);

		minLimitPerlinNoiseGen = new NoiseGeneratorOctaves(rand, 16);
		maxLimitPerlinNoiseGen = new NoiseGeneratorOctaves(rand, 16);
		mainPerlinNoiseGen = new NoiseGeneratorOctaves(rand, 8);
		gravelAndSandNoiseGen = new NoiseGeneratorOctaves(rand, 4);
		stoneNoiseGen = new NoiseGeneratorOctaves(rand, 4);
		scaleNoiseGen = new NoiseGeneratorOctaves(rand, 10);
		depthNoiseGen = new NoiseGeneratorOctaves(rand, 16);

		InitNoiseGensEvent.ContextHell context = new InitNoiseGensEvent.ContextHell(minLimitPerlinNoiseGen, maxLimitPerlinNoiseGen, mainPerlinNoiseGen, gravelAndSandNoiseGen, stoneNoiseGen, scaleNoiseGen, depthNoiseGen);
		context = TerrainGen.getModdedNoiseGenerators(world, rand, context);
		minLimitPerlinNoiseGen = context.getLPerlin1();
		maxLimitPerlinNoiseGen = context.getLPerlin2();
		mainPerlinNoiseGen = context.getPerlin();
		gravelAndSandNoiseGen = context.getPerlin2();
		stoneNoiseGen = context.getPerlin3();
		scaleNoiseGen = context.getScale();
		depthNoiseGen = context.getDepth();
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.curChunkX = chunkX;
		this.curChunkZ = chunkZ;
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();

		setBlocksInChunk();
		replaceBiomeBlocks();

		Chunk chunk = new Chunk(world, primer, curChunkX, curChunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private double[] generateNoiseField(double[] buffer, int xRef, int yRef, int zRef, int bufferX, int bufferY, int bufferZ) {
		if (buffer == null) {
			buffer = new double[bufferX * bufferY * bufferZ];
		}

		ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, buffer, xRef, yRef, zRef, bufferX, bufferY, bufferZ);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.getResult() == Event.Result.DENY)
			return event.getNoisefield();

		double d0 = 684.412D;
		double d1 = 2053.236D;
		this.noiseData4 = scaleNoiseGen.generateNoiseOctaves(this.noiseData4, xRef, yRef, zRef, bufferX, 1, bufferZ, 1.0D, 0.0D, 1.0D);
		this.dr = this.depthNoiseGen.generateNoiseOctaves(this.dr, xRef, yRef, zRef, bufferX, 1, bufferZ, 100.0D, 0.0D, 100.0D);
		this.pnr = this.mainPerlinNoiseGen.generateNoiseOctaves(this.pnr, xRef, yRef, zRef, bufferX, bufferY, bufferZ, 8.555150000000001D, 34.2206D, 8.555150000000001D);
		this.ar = this.minLimitPerlinNoiseGen.generateNoiseOctaves(this.ar, xRef, yRef, zRef, bufferX, bufferY, bufferZ, 684.412D, 2053.236D, 684.412D);
		this.br = this.maxLimitPerlinNoiseGen.generateNoiseOctaves(this.br, xRef, yRef, zRef, bufferX, bufferY, bufferZ, 684.412D, 2053.236D, 684.412D);
		int i = 0;
		double[] adouble = new double[bufferY];

		for (int j = 0; j < bufferY; ++j) {
			adouble[j] = Math.cos((double)j * Math.PI * 6.0D / (double)bufferY) * 2.0D;
			double d2 = (double)j;

			if (j > bufferY / 2) {
				d2 = (double)(bufferY - 1 - j);
			}

			if (d2 < 4.0D) {
				d2 = 4.0D - d2;
				adouble[j] -= d2 * d2 * d2 * 10.0D;
			}
		}

		for (int l = 0; l < bufferX; ++l) {
			for (int i1 = 0; i1 < bufferZ; ++i1) {
				double d3 = 0.0D;

				for (int k = 0; k < bufferY; ++k) {
					double d4 = adouble[k];
					double d5 = this.ar[i] / 512.0D;
					double d6 = this.br[i] / 512.0D;
					double d7 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;
					double d8;

					if (d7 < 0.0D) {
						d8 = d5;
					}
					else if (d7 > 1.0D) {
						d8 = d6;
					}
					else {
						d8 = d5 + (d6 - d5) * d7;
					}

					d8 = d8 - d4;

					if (k > bufferY - 4) {
						double d9 = (double)((float)(k - (bufferY - 4)) / 3.0F);
						d8 = d8 * (1.0D - d9) + -10.0D * d9;
					}

					if ((double)k < 0.0D) {
						double d10 = (0.0D - (double)k) / 4.0D;
						d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
						d8 = d8 * (1.0D - d10) + -10.0D * d10;
					}

					buffer[i] = d8;
					++i;
				}
			}
		}

		return buffer;
	}

	private void setBlocksInChunk() {
		int i = 4;
		int j = this.world.getSeaLevel() / 2 + 1;
		int k = 5;
		int l = 17;
		int i1 = 5;
		this.noiseArray = this.generateNoiseField(this.noiseArray, curChunkX * 4, 0, curChunkZ * 4, 5, 17, 5);

		for (int j1 = 0; j1 < 4; ++j1) {
			for (int k1 = 0; k1 < 4; ++k1) {
				for (int l1 = 0; l1 < 16; ++l1) {
					double d1 = this.noiseArray[(j1 * 5 + k1) * 17 + l1];
					double d2 = this.noiseArray[(j1 * 5 + k1 + 1) * 17 + l1];
					double d3 = this.noiseArray[((j1 + 1) * 5 + k1) * 17 + l1];
					double d4 = this.noiseArray[((j1 + 1) * 5 + k1 + 1) * 17 + l1];
					double d5 = (this.noiseArray[(j1 * 5 + k1) * 17 + l1 + 1] - d1) * 0.125D;
					double d6 = (this.noiseArray[(j1 * 5 + k1 + 1) * 17 + l1 + 1] - d2) * 0.125D;
					double d7 = (this.noiseArray[((j1 + 1) * 5 + k1) * 17 + l1 + 1] - d3) * 0.125D;
					double d8 = (this.noiseArray[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 1] - d4) * 0.125D;

					for (int i2 = 0; i2 < 8; ++i2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int j2 = 0; j2 < 4; ++j2) {
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * 0.25D;

							for (int k2 = 0; k2 < 4; ++k2) {
								IBlockState iblockstate = null;

								if (d15 > 0.0D) {
									iblockstate = BlockRegister.stoneCrystevia.getDefaultState();
								}

								int l2 = j2 + j1 * 4;
								int i3 = i2 + l1 * 8;
								int j3 = k2 + k1 * 4;
								primer.setBlockState(l2, i3, j3, iblockstate);
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	private void replaceBiomeBlocks() {
		if (!ForgeEventFactory.onReplaceBiomeBlocks(this, curChunkX, curChunkZ, primer, this.world))
			return;

		int i = this.world.getSeaLevel() + 1;
		double d0 = 0.03125D;
		this.depthBuffer = this.stoneNoiseGen.generateNoiseOctaves(this.depthBuffer, curChunkX * 16, curChunkZ * 16, 0, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);

		for (int j = 0; j < 16; ++j) {
			for (int k = 0; k < 16; ++k) {
				int l = (int)(this.depthBuffer[j + k * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int i1 = -1;
				IBlockState iblockstate = BlockRegister.stoneCrystevia.getDefaultState();
				IBlockState iblockstate1 = BlockRegister.stoneCrystevia.getDefaultState();

				for (int j1 = 127; j1 >= 0; --j1) {
					if (j1 < 125 && j1 > 2) {
						IBlockState iblockstate2 = primer.getBlockState(k, j1, j);

						if (iblockstate2.getMaterial() != Material.AIR) {
							if (iblockstate2.getBlock() == BlockRegister.stoneCrystevia) {
								if (i1 == -1) {
									if (l <= 0) {
										iblockstate = Blocks.AIR.getDefaultState();
										iblockstate1 = BlockRegister.stoneCrystevia.getDefaultState();
									}
									else if (j1 >= i - 4 && j1 <= i + 1) {
										iblockstate = BlockRegister.stoneCrystevia.getDefaultState();
										iblockstate1 = BlockRegister.stoneCrystevia.getDefaultState();
									}

									if (j1 < i && iblockstate.getMaterial() == Material.AIR) {
										iblockstate = Blocks.WATER.getDefaultState();
									}

									i1 = l;

									if (j1 >= i - 1) {
										primer.setBlockState(k, j1, j, iblockstate);
									}
									else {
										primer.setBlockState(k, j1, j, iblockstate1);
									}
								}
								else if (i1 > 0) {
									--i1;
									primer.setBlockState(k, j1, j, iblockstate1);
								}
							}
						}
						else {
							i1 = -1;
						}
					}
					else {
						primer.setBlockState(k, j1, j, BlockRegister.dimensionalFabric.getDefaultState());
					}
				}
			}
		}
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		this.rand.setSeed(world.getSeed());
		long a = this.rand.nextLong() / 2L * 2L + 1L;
		long b = this.rand.nextLong() / 2L * 2L + 1L;
		final int baseX = chunkX * 16 + 1;
		final int baseZ = chunkZ * 16 + 1;
		int x;
		int z;
		int y;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		BlockPos basePos = new BlockPos(baseX, 0, baseZ);
		Biome biome = world.getBiome(basePos);

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());

		if (ConfigurationUtil.spawnChanceCrystalBuilding > 0 && rand.nextInt(ConfigurationUtil.spawnChanceCrystalBuilding) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 20 + rand.nextInt(80);

			switch (rand.nextInt(4)) {
				case 0:
					if (world.getBlockState(pos.setPos(x + 5, y + 3, z + 5)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("CrystalExtensionStation", world, rand, pos.setPos(x, y, z));
					break;
				case 1:
					if (world.getBlockState(pos.setPos(x + 4, y + 3, z + 4)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("CrystalLottoOverlook", world, rand, pos.setPos(x, y, z));
					break;
				case 2:
					if (world.getBlockState(pos.setPos(x + 3, y + 3, z + 3)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("PowerStation", world, rand, pos.setPos(x, y, z));
					break;
				case 3:
					if (world.getBlockState(pos.setPos(x + 4, y + 1, z + 4)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("CrystalTradingPost", world, rand, pos.setPos(x, y, z));
					break;
			}
		}

		if (ConfigurationUtil.spawnChanceCrystalTransferHut > 0 && rand.nextInt(ConfigurationUtil.spawnChanceCrystalTransferHut) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 20 + rand.nextInt(80);

			if (world.getBlockState(pos.setPos(x + 5, y, z + 5)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x + 5, y + 9, z + 5)).getBlock() == Blocks.AIR) {
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.nextInt(6)) {
					case 0:
						structure = StructuresHandler.getStructure("BlueCrystalTransferHut");
						break;
					case 1:
						structure = StructuresHandler.getStructure("GreenCrystalTransferHut");
						break;
					case 2:
						structure = StructuresHandler.getStructure("PurpleCrystalTransferHut");
						break;
					case 3:
						structure = StructuresHandler.getStructure("RedCrystalTransferHut");
						break;
					case 4:
						structure = StructuresHandler.getStructure("WhiteCrystalTransferHut");
						break;
					case 5:
						structure = StructuresHandler.getStructure("YellowCrystalTransferHut");
						break;
				}

				StructuresHandler.generateStructure(structure, world, rand, pos.setPos(x, y, z));
			}
		}

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());
		biome.decorate(world, rand, basePos);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return this.world.getBiome(pos).getSpawnableList(creatureType);
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}
