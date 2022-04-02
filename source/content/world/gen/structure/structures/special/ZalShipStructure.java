/*
package net.tslat.aoa3.content.world.gen.structure.structures.special;

import net.minecraft.world.biome.MobSpawnSettings;
import net.minecraft.world.gen.GenerationStep;

import net.tslat.aoa3.content.world.gen.structure.structures.YPosStructure;

import java.util.Arrays;
import java.util.List;

public class ZalShipStructure extends YPosStructure {
	private static final List<MobSpawnSettings.SpawnerData> ZAL_SPAWNS = Arrays.asList(
			new MobSpawnSettings.SpawnerData(AoANpcs.ZAL_CHILD.get(), 3, 1, 3),
			new MobSpawnSettings.SpawnerData(AoANpcs.ZAL_CITIZEN.get(), 5, 1, 1));

	public ZalShipStructure(GenerationStep.Decoration decorationStage, String templatePoolPath) {
		super(decorationStage, templatePoolPath);
	}

	@Override
	public boolean getDefaultRestrictsSpawnsToInside() {
		return true;
	}

	@Override
	public List<MobSpawnSettings.SpawnerData> getDefaultCreatureSpawnList() {
		return ZAL_SPAWNS;
	}
}
*/
