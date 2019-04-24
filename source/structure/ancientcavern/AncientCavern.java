package net.tslat.aoa3.structure.ancientcavern;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.apache.logging.log4j.Level;

import java.util.Random;

public class AncientCavern extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		boolean result;

		AdventOfAscension.getLogger().log(Level.INFO, "Is this about to cause some 'cascading worldgen' warnings in the console? Yes.");
		AdventOfAscension.getLogger().log(Level.INFO, "Is it anything to worry about? No.");
		AdventOfAscension.getLogger().log(Level.INFO, "It only generates once, don't worry.");

		result = new AncientCavernRoom1().generate(world, rand, pos);
		result = result && new AncientCavernRoom2().generate(world, rand, pos);
		result = result && new AncientCavernRoom3().generate(world, rand, pos);
		result = result && new AncientCavernRoom4().generate(world, rand, pos);
		result = result && new AncientCavernRoom5().generate(world, rand, pos);
		result = result && new AncientCavernRoom6().generate(world, rand, pos);
		result = result && new AncientCavernConiferonRoom().generate(world, rand, pos);
		result = result && new AncientCavernHoronRoom().generate(world, rand, pos);
		result = result && new AncientCavernGoldorthRoom().generate(world, rand, pos);
		result = result && new AncientCavernPenumbraRoom().generate(world, rand, pos);

		return result;
	}
}
