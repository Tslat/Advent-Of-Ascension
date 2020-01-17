package net.tslat.aoa3.structure.immortallis;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.apache.logging.log4j.Level;

import java.util.Random;

public class ImmortallisDungeon extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		boolean result;

		AdventOfAscension.logMessage(Level.INFO, "Is this about to cause some 'cascading worldgen' warnings in the console? Yes.");
		AdventOfAscension.logMessage(Level.INFO, "Is it anything to worry about? No.");
		AdventOfAscension.logMessage(Level.INFO, "It only generates once, don't worry.");

		result = new ImmortallisRoom1().generate(world, rand, pos);
		result = result && new ImmortallisRoom2().generate(world, rand, pos);
		result = result && new ImmortallisRoom3().generate(world, rand, pos);
		result = result && new ImmortallisRoom4().generate(world, rand, pos);
		result = result && new ImmortallisRoom5().generate(world, rand, pos);
		result = result && new ImmortallisRoom6().generate(world, rand, pos);
		result = result && new ImmortallisRoom7().generate(world, rand, pos);
		result = result && new ImmortallisRoom8().generate(world, rand, pos);
		result = result && new ImmortallisRoom9().generate(world, rand, pos);

		return result;
	}
}
