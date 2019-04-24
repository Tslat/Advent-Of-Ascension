package net.nevermine.dimension;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.lunalus.*;
import net.nevermine.structures.mysterium.*;
import net.nevermine.structures.runandor.*;
import net.nevermine.structures.vanilla.AncientTeleporter;
import net.nevermine.structures.vanilla.MysticPortalPlatform;
import net.nevermine.structures.vanilla.RuneShrinePlatform;

import java.util.Random;

public class WorldGen implements IWorldGenerator {
	private int id;

	public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
		id = world.provider.dimensionId;
		if (id == -1) {
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == 0) {
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == 1) {
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.precasia) {
			generatePrecasia(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.abyss) {
			generateAbyss(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.haven) {
			generateHaven(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.iromine) {
			generateIromine(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.lunalus) {
			generateLunalus(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.gardencia) {
			generateGardencia(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.mysterium) {
			generateMysterium(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.greckon) {
			generateGreckon(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.lborean) {
			generateBorean(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.runandor) {
			generateRunandor(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.lelyetia) {
			generateLelyetia(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.crystevia) {
			generateCrystevia(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.creeponia) {
			generateCreeponia(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (id == ConfigurationHelper.shyrelands) {
			generateShyrelands(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateEnd(final World world, final Random random, final int x, final int z) {
	}

	private void generateShyrelands(World world, Random random, int x, int z) {
		if (world.provider.dimensionId == ConfigurationHelper.shyrelands) {
			if (random.nextInt(600) == 535) {
				int Xcoord = x + random.nextInt(16);
				int Zcoord = z + random.nextInt(16);
				StructureGenRare.shouldGen(26, world, random, Xcoord, Zcoord);
			}

			if (random.nextInt(250) == 121) {
				int Xcoord = x + random.nextInt(16);
				int Zcoord = z + random.nextInt(16);
				int pick = random.nextInt(3);
				if (pick == 1) {
					StructureGenRare.shouldGen(27, world, random, Xcoord, Zcoord);
				}
				else if (pick == 2) {
					StructureGenRare.shouldGen(28, world, random, Xcoord, Zcoord);
				}
				else {
					StructureGenRare.shouldGen(29, world, random, Xcoord, Zcoord);
				}
			}
		}
	}

	private void generateGardencia(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.gardencia) {
			if (random.nextInt(1700) == 656) {
				final int y = 63;
				if (world.getBlock(x, y, z) == Blocks.air) {
					StructureGenRare.shouldGen(20, world, random, x, z);
				}
			}
			if (random.nextInt(1650) == 1142) {
				final int y = world.getHeightValue(x + 4, z + 4);
				if (world.getBlock(x + 4, y - 1, z + 4) == Blockizer.GrassGardencia) {
					StructureGenRare.shouldGen(21, world, random, x, z);
				}
			}
		}
	}

	private void generateRunandor(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.runandor) {
			for (int i = 0; i < 3; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Zcoord = z + random.nextInt(16);
				final int Ycoord = world.getHeightValue(Xcoord + 2, Zcoord + 2);
				if (world.getBlock(Xcoord, Ycoord - 1, Zcoord) == Blockizer.GrassRunic) {
					final int pick = random.nextInt(6);
					if (pick == 1) {
						new RunicTree1().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
					}
					else if (pick == 2) {
						new RunicTree2().generate(world, random, Xcoord - 1, Ycoord - 1, Zcoord - 1);
					}
					else if (pick == 3) {
						new RunicTree3().generate(world, random, Xcoord - 2, Ycoord - 2, Zcoord - 2);
					}
					else if (pick == 4) {
						new LuxonTree1().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
					}
					else if (pick == 5) {
						new LuxonTree2().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
					}
					else {
						new LuxonTree3().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
					}
				}
			}
			if (random.nextInt(300) == 250) {
				final int pick2 = random.nextInt(4);
				final int Xcoord = x + random.nextInt(16);
				final int Zcoord = z + random.nextInt(16);
				if (pick2 == 1) {
					new RunicRoomBlue().generate(world, random, Xcoord, 3, Zcoord);
				}
				else if (pick2 == 2) {
					new RunicRoomGreen().generate(world, random, Xcoord, 3, Zcoord);
				}
				else if (pick2 == 3) {
					new RunicRoomYellow().generate(world, random, Xcoord, 3, Zcoord);
				}
				else {
					new RunicRoomRed().generate(world, random, Xcoord, 3, Zcoord);
				}
			}
			else if (random.nextInt(500) == 301) {
				final int Xcoord2 = x + random.nextInt(16);
				final int Zcoord2 = z + random.nextInt(16);
				final int Ycoord2 = world.getHeightValue(Xcoord2 + 4, Zcoord2 + 4);
				new SpectralCage().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
			}
			else if (random.nextInt(800) == 221) {
				final int Xcoord2 = x + random.nextInt(16);
				final int Zcoord2 = z + random.nextInt(16);
				final int Ycoord2 = world.getHeightValue(Xcoord2 + 3, Zcoord2 + 3);
				new ClunkheadArena().generate(world, random, Xcoord2, Ycoord2, Zcoord2);
			}
			else if (random.nextInt(650) == 405) {
				final int Xcoord2 = x + random.nextInt(16);
				final int Zcoord2 = z + random.nextInt(16);
				final int Ycoord2 = world.getHeightValue(Xcoord2 + 2, Zcoord2 + 2);
				new RunicTower().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
			}
			else if (random.nextInt(315) == 190) {
				final int Xcoord2 = x + random.nextInt(16);
				final int Zcoord2 = z + random.nextInt(16);
				final int Ycoord2 = world.getHeightValue(Xcoord2 + 3, Zcoord2 + 3);
				new RuneRandomizationStation().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
			}
		}
	}

	private void generateLelyetia(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.lelyetia) {
			final int pick = random.nextInt(760);
			if (pick == 330) {
				StructureGenRare.shouldGen(22, world, random, x, z);
			}
			else if (pick == 427 || pick == 702) {
				StructureGenRare.shouldGen(23, world, random, x, z);
			}
			else if (pick == 315 || pick == 206) {
				StructureGenRare.shouldGen(24, world, random, x, z);
			}
			else if (pick == 170 || pick == 555) {
				StructureGenRare.shouldGen(25, world, random, x, z);
			}
		}
	}

	private void generateBorean(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.lborean && random.nextInt(1700) == 656) {
			final int y = world.getHeightValue(x + 5, z + 5);
			if (world.getBlock(x + 5, y - 1, z + 5) == Blockizer.GrassBorean || world.getBlock(x + 5, y - 1, z + 5) == Blocks.water) {
				StructureGenRare.shouldGen(19, world, random, x, z);
			}
		}
	}

	private void generateAbyss(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.abyss) {
			if (random.nextInt(600) == 335) {
				StructureGenRare.shouldGen(9, world, random, x, z);
			}
			if (random.nextInt(520) == 325) {
				StructureGenRare.shouldGen(8, world, random, x, z);
			}
		}
	}

	private void generateIromine(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.iromine && random.nextInt(1300) == 656) {
			final int y = world.getHeightValue(x + 11, z + 22);
			if (world.getBlock(x + 11, y - 1, z + 22) == Blockizer.GrassIromine) {
				if (random.nextBoolean()) {
					StructureGenRare.shouldGen(18, world, random, x, z);
				}
				else {
					StructureGenRare.shouldGen(10, world, random, x, z);
				}
			}
		}
	}

	private void generateGreckon(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.greckon && random.nextInt(700) == 343) {
			final int y = world.getHeightValue(x, z);
			if (world.getBlock(x, y - 1, z) == Blockizer.GrassGreckon) {
				StructureGenRare.shouldGen(17, world, random, x, z);
			}
		}
	}

	private void generateHaven(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.haven) {
			final int randomNum = random.nextInt(35) + 1;
			if (randomNum == 7) {
				StructureGenRare.shouldGen(1, world, random, x, z);
			}
			if (randomNum == 9 || randomNum == 14) {
				StructureGenRare.shouldGen(2, world, random, x, z);
			}
		}
	}

	private void generateLunalus(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.lunalus) {
			int y = random.nextInt(4) + 23;
			if (random.nextInt(700) == 355) {
				new LunarAtom().generate(world, random, x, y - 15, z);
				new LunarLottoPlatform().generate(world, random, x + 25, y - 15, z + 15);
				new LunarFoodMarket().generate(world, random, x - 25, y - 15, z - 14);
				new LunarHerbalHouse().generate(world, random, x - 45, y - 15, z - 9);
				new LunarSpellbindHouse().generate(world, random, x - 13, y - 15 + 26, z + 8);
				new LunarLunaradeStand().generate(world, random, x - 32, y - 15 + 26, z + 8);
				new LunarBank().generate(world, random, x - 60, y - 15 + 5, z - 2);
				new LunarFountain().generate(world, random, x - 30, y - 15, z);
			}
			y = random.nextInt(3) + 15;
			if (random.nextInt(900) == 462) {
				new LunarMazeP1().generate(world, random, x, y, z);
				new LunarMazeP2().generate(world, random, x, y, z);
			}
			else if (random.nextInt(850) == 777) {
				new LunarPrison().generate(world, random, x, y, z);
			}
			else if (random.nextInt(905) == 896) {
				new LunarCreationPlatform().generate(world, random, x, y, z);
			}
			else if (random.nextInt(804) == 335) {
				new LunarGarden().generate(world, random, x, y, z);
			}
			else if (random.nextInt(600) == 445) {
				new RuneShrinePlatform(6).generate(world, random, x, y, z);
			}
			else if (random.nextInt(900) == 225) {
				new ZargPlanet().generate(world, random, x, y, z);
			}
			else if (random.nextInt(905) == 313) {
				new SpaceOutpost().generate(world, random, x, y, z);
			}
		}
	}

	private void generatePrecasia(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.precasia) {
			final int randomNumrare = random.nextInt(1000) + 1;
			final int randomNumcommon = random.nextInt(600) + 1;
			if (randomNumrare == 135) {
				StructureGenRare.shouldGen(3, world, random, x, z);
			}
			if (randomNumrare == 242) {
				StructureGenRare.shouldGen(4, world, random, x, z);
			}
			if (randomNumcommon == 34) {
				StructureGenRare.shouldGen(5, world, random, x, z);
			}
			if (randomNumcommon == 23) {
				StructureGenRare.shouldGen(6, world, random, x, z);
			}
			if (randomNumcommon == 11) {
				StructureGenRare.shouldGen(7, world, random, x, z);
			}

			if (random.nextInt(800) == 301) {
				int Xcoord = x + random.nextInt(16);
				int Zcoord = z + random.nextInt(16);
				int Ycoord = world.getHeightValue(Xcoord + 6, Zcoord + 6);
				new net.nevermine.structures.precasia.TyrosaurAltar().generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(40);
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreSkullFragment, 4, Blockizer.StonePrecasianLow).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(40);
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreFootFragment, 4, Blockizer.StonePrecasianLow).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(40);
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreLegFragment, 4, Blockizer.StonePrecasianLow).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(40);
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreChestboneFragment, 4, Blockizer.StonePrecasianLow).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		}
	}

	private void generateMysterium(final World world, final Random random, final int x, final int z) {
		for (int i = 0; i < 3; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Zcoord = z + random.nextInt(16);
			final int Ycoord = world.getHeightValue(Xcoord + 4, Zcoord + 4);
			final int pick = random.nextInt(6);
			if (pick == 1) {
				new MysticMushroom1().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
			}
			else if (pick == 2) {
				new MysticMushroom2().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
			}
			else if (pick == 3) {
				new MysticMushroom3().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
			}
			else if (pick == 4) {
				new MysticMushroom4().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
			}
			else if (pick == 5) {
				new MysticMushroom5().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
			}
			else {
				new MysticMushroom6().generate(world, random, Xcoord, Ycoord - 1, Zcoord);
			}
		}
		for (int i = 0; i < 3; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Zcoord = z + random.nextInt(16);
			final int Ycoord = world.getHeightValue(Xcoord, Zcoord);
			new MiniMushroom().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for (int i = 0; i < 2; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Zcoord = z + random.nextInt(16);
			final int Ycoord = 2 + random.nextInt(20);
			new WorldGenMinable(Blockizer.oreMystite, 7, Blockizer.StoneMysterium).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		if (random.nextInt(500) == 226) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 3, Zcoord2 + 3);
			new RuneShrinePlatform(9).generate(world, random, Xcoord2, Ycoord2 + 15, Zcoord2);
		}
		if (random.nextInt(500) == 301) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 4, Zcoord2 + 4);
			new LottoMysteriumStructure().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
		}
		else if (random.nextInt(800) == 221) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 3, Zcoord2 + 3);
			new GiantMushroom().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
		}
		else if (random.nextInt(650) == 405) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 2, Zcoord2 + 2);
			new MysticBuilding().generate(world, random, Xcoord2, Ycoord2 + 16, Zcoord2);
		}
		else if (random.nextInt(450) == 413) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 2, Zcoord2 + 2);
			new MushroomHive().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
		}
		else if (random.nextInt(315) == 190) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 3, Zcoord2 + 3);
			new RunicArena().generate(world, random, Xcoord2, Ycoord2 + 11, Zcoord2);
		}
		else if (random.nextInt(850) == 203) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 6, Zcoord2 + 6);
			new HauntedCastleP1().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
		}
		else if (random.nextInt(416) == 87) {
			final int Xcoord2 = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = world.getHeightValue(Xcoord2 + 5, Zcoord2 + 3);
			new MysticPortalPlatform().generate(world, random, Xcoord2, Ycoord2 - 1, Zcoord2);
		}
	}

	private void generateSurface(final World world, final Random random, final int x, final int z) {
		final BiomeGenBase b = world.getBiomeGenForCoords(x, z);
		if (b.biomeName.equals("Ocean")) {
			final int randomNum = random.nextInt(6) + 1;
			if (randomNum == 3) {
				StructureGenRare.shouldGen(15, world, random, x, z);
			}
		}
		for (int i = 0; i < 2; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(40);
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreAmethyst, 7).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for (int i = 0; i < 1; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(30);
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreRosite, 4).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for (int i = 0; i < 3; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(30);
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreLimonite, 20).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for (int i = 0; i < 3; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(20);
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreRunium, 14).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for (int i = 0; i < 1; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(20);
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreJade, 4).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		for (int i = 0; i < 1; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(10);
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreSapphire, 4).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		if (random.nextInt(100) == 1) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 10 + random.nextInt(10);
			final int Zcoord = z + random.nextInt(16);
			if (world.getBlock(Xcoord, Ycoord, Zcoord) != Blocks.air)
				new AncientTeleporter().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}

	private void generateCreeponia(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.creeponia) {
			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(12) + 3;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreOrnamyte, 4, Blockizer.PrimedStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(15) + 22;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreGemenyte, 4, Blockizer.UnstableStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 2; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(15) + 22;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreJewelyte, 4, Blockizer.UnstableStone).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		}
	}

	private void generateCrystevia(final World world, final Random random, final int x, final int z) {
		if (world.provider.dimensionId == ConfigurationHelper.crystevia) {
			for (int i = 0; i < 4; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(100) + 10;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreCrystalBlue, 14, Blockizer.CrysteviaRock).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 4; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(100) + 10;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreCrystalRed, 14, Blockizer.CrysteviaRock).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 4; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(100) + 10;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreCrystalGreen, 14, Blockizer.CrysteviaRock).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 4; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(100) + 10;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreCrystalYellow, 14, Blockizer.CrysteviaRock).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 4; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(100) + 10;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreCrystalPurple, 14, Blockizer.CrysteviaRock).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			for (int i = 0; i < 4; ++i) {
				final int Xcoord = x + random.nextInt(16);
				final int Ycoord = random.nextInt(100) + 10;
				final int Zcoord = z + random.nextInt(16);
				new WorldGenMinable(Blockizer.oreCrystalWhite, 14, Blockizer.CrysteviaRock).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		}
	}

	private void generateNether(final World world, final Random random, int x, int z) {
		final int randomNum = random.nextInt(50) + 1;
		if (randomNum == 13) {
			StructureGenRare.shouldGen(16, world, random, x, z);
		}
		for (int i = 0; i < 2; ++i) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = random.nextInt(39) + 4;
			final int Zcoord = z + random.nextInt(16);
			new WorldGenMinable(Blockizer.oreEmberstone, 7, Blocks.netherrack).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		if (random.nextInt(125) == 26) {
			x += random.nextInt(16);
			z += random.nextInt(16);
			final int y = 35 + random.nextInt(15);
			if (world.getBlock(x, y, z) == Blocks.air) {
				new RuneShrinePlatform(1).generate(world, random, x, y, z);
			}
		}
	}
}
