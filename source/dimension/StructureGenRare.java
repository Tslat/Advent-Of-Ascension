package net.nevermine.dimension;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.nevermine.structures.abyss.FleshTemple;
import net.nevermine.structures.abyss.IllusionTree;
import net.nevermine.structures.gardencia.*;
import net.nevermine.structures.greckon.GreckonMazeP1;
import net.nevermine.structures.greckon.GreckonMazeP2;
import net.nevermine.structures.haven.HavenTower;
import net.nevermine.structures.haven.RockriderSpawn;
import net.nevermine.structures.iromine.IromineMazeP1;
import net.nevermine.structures.iromine.IromineMazeP2;
import net.nevermine.structures.iromine.IromineMazeP3;
import net.nevermine.structures.iromine.MechTower;
import net.nevermine.structures.lborean.AquaticCastleP1;
import net.nevermine.structures.lborean.AquaticCastleP2;
import net.nevermine.structures.lborean.AquaticCastleP3;
import net.nevermine.structures.lelyetia.*;
import net.nevermine.structures.precasia.*;
import net.nevermine.structures.shyrelands.*;
import net.nevermine.structures.vanilla.AmphibiyteCave;
import net.nevermine.structures.vanilla.NethengeicPit;

import java.util.Random;

public class StructureGenRare {
	public static void shouldGen(final int togen, final World world, final Random random, final int x, final int z) {
		if (togen == 1) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 30 + random.nextInt(45);
			final int Zcoord = z + random.nextInt(16);
			new HavenTower().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 2) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 30 + random.nextInt(45);
			final int Zcoord = z + random.nextInt(16);
			new RockriderSpawn().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 3) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 60 + random.nextInt(5);
			final int Zcoord = z + random.nextInt(16);
			new KaiyuTempleP1().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 4) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 60 + random.nextInt(5);
			final int Zcoord = z + random.nextInt(16);
			new SkeletalArmyArena().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 5) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 2 + random.nextInt(5);
			final int Zcoord = z + random.nextInt(16);
			new PrecasiaDen1().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 6) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 2 + random.nextInt(5);
			final int Zcoord = z + random.nextInt(16);
			new PrecasiaDen2().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 7) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 2 + random.nextInt(5);
			final int Zcoord = z + random.nextInt(16);
			new PrecasiaDen3().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 8) {
			final int Xcoord = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = 63;
			if (world.getBlock(Xcoord + 1, Zcoord2 + 1, Ycoord2 + 1) == Blocks.air) {
				new FleshTemple().generate(world, random, Xcoord, Ycoord2, Zcoord2);
			}
		}
		else if (togen == 9) {
			final int Xcoord = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			final int Ycoord2 = 63;
			if (world.getBlock(Xcoord, Zcoord2 + 1, Ycoord2) == Blocks.air) {
				new IllusionTree().generate(world, random, Xcoord, Ycoord2, Zcoord2);
			}
		}
		else if (togen == 10) {
			final int y = world.getHeightValue(x, z);
			final int Xcoord = x + random.nextInt(16);
			final int Zcoord2 = z + random.nextInt(16);
			new MechTower().generate(world, random, x, y, z);
		}
		else if (togen == 15) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 50 + random.nextInt(20);
			final int Zcoord = z + random.nextInt(16);
			new AmphibiyteCave().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 16) {
			final int Xcoord = x + random.nextInt(16);
			final int Ycoord = 25 + random.nextInt(35);
			final int Zcoord = z + random.nextInt(16);
			new NethengeicPit().generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		else if (togen == 17) {
			final int y = world.getHeightValue(x, z);
			new GreckonMazeP1().generate(world, random, x, y, z);
			new GreckonMazeP2().generate(world, random, x, y, z);
		}
		else if (togen == 18) {
			final int y = world.getHeightValue(x + 11, z + 22);
			new IromineMazeP1().generate(world, random, x, y, z);
			new IromineMazeP2().generate(world, random, x, y, z);
			new IromineMazeP3().generate(world, random, x, y, z);
		}
		else if (togen == 19) {
			final int y = world.getHeightValue(x + 5, z + 5);
			new AquaticCastleP1().generate(world, random, x, y, z);
			new AquaticCastleP2().generate(world, random, x, y, z);
			new AquaticCastleP3().generate(world, random, x, y, z);
		}
		else if (togen == 20) {
			final int y = 63;
			new FloroCastleP1().generate(world, random, x, y, z);
			new FloroCastleP2().generate(world, random, x, y, z);
			new FloroCastleP3().generate(world, random, x, y, z);
			new FloroCastleP4().generate(world, random, x, y, z);
		}
		else if (togen == 21) {
			final int y = world.getHeightValue(x + 4, z + 4);
			new GardenCastle().generate(world, random, x, y - 1, z);
			new GardenCastle2().generate(world, random, x, y - 1, z);
		}
		else if (togen == 22) {
			final int y = 60;
			new LelyetianTowerP1().generate(world, random, x, y - 1, z);
			new LelyetianTowerP2().generate(world, random, x, y - 1, z);
		}
		else if (togen == 23) {
			final int y = 15;
			if (world.getBlock(x, 55, z) != Blocks.air) {
				new ZhinxEnclave().generate(world, random, x, y, z);
			}
		}
		else if (togen == 24) {
			final int y = 36;
			if (world.getBlock(x + 5, 55, z + 5) != Blocks.air) {
				new ParaviteHive().generate(world, random, x, y, z);
			}
		}
		else if (togen == 25) {
			final int y = 31;
			new BoneyDungeon().generate(world, random, x, y, z);
		}
		else if (togen == 26) {
			int y = 31;
			new CraexxeusTowerP1().generate(world, random, x, y, z);
			new CraexxeusTowerP2().generate(world, random, x, y, z);
		}
		else if (togen == 27) {
			int y = 5 + random.nextInt(12);
			new ShyreDungeon1().generate(world, random, x, y, z);
		}
		else if (togen == 28) {
			int y = 5 + random.nextInt(12);
			new ShyreDungeon2().generate(world, random, x, y, z);
		}
		else if (togen == 29) {
			int y = 5 + random.nextInt(12);
			new ShyreDungeon3().generate(world, random, x, y, z);
		}
	}
}
