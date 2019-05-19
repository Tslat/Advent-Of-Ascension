package net.tslat.aoa3.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class StructuresHandler {
	private static final TreeMap<String, AoAStructure> structures = new TreeMap<String, AoAStructure>();
	public static final AoAStructure EMPTY_STRUCTURE = new AoAStructure.EmptyStructure();

	protected static void registerStructure(AoAStructure structure) {
		String name = structure.getName();

		if (name != null && name.length() > 0) {
			structures.put(name, structure);

			if (ConfigurationUtil.MainConfig.doVerboseDebugging)
				AdventOfAscension.getLogger().log(Level.INFO, "Registered structure: " + name);
		}
	}

	public static boolean generateStructure(final String name, final World world, final Random rand, final BlockPos basePos) {
		return generateStructure(getStructure(name), world, rand, basePos);
	}

	public static boolean generateStructure(final AoAStructure structure, final World world, final Random rand, final BlockPos basePos) {
		if (structure == EMPTY_STRUCTURE)
			return false;

		structure.generate(world, rand, basePos);
		return true;
	}

	public static AoAStructure getStructure(String name) {
		AoAStructure structure = structures.get(name);

		if (structure == null) {
			AdventOfAscension.getLogger().log(Level.WARN, "Unable to find registered structure with name: " + name);

			return EMPTY_STRUCTURE;
		}
		else {
			return structure;
		}
	}

	public static int getStructureListPageCount() {
		return (int)Math.ceil(structures.size() / 50d);
	}

	public static String getStructuresList(int pageIndex) {
		StringBuilder builder = new StringBuilder(", ");
		int i = 0;

		for (String name : structures.keySet()) {
			if (i >= (pageIndex - 1) * 50) {
				if (i >= pageIndex * 50)
					return builder.toString().substring(2);

				builder.append(name);
				builder.append(", ");
			}

			i++;
		}

		return builder.toString().substring(2);
	}

	public static List<String> autoCompleteStructureName(String st) {
		List<String> names = new ArrayList<String>();

		for (String name : structures.keySet()) {
			if (name.startsWith(st))
				names.add(name);
		}

		return names;
	}
}
