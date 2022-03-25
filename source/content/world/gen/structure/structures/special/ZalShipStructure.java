package net.tslat.aoa3.content.world.gen.structure.structures.special;

import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.world.gen.structure.structures.YPosStructure;

import java.util.Arrays;
import java.util.List;

public class ZalShipStructure extends YPosStructure {
	private static final List<MobSpawnInfo.Spawners> ZAL_SPAWNS = Arrays.asList(
			new MobSpawnInfo.Spawners(AoAEntities.NPCs.ZAL_CHILD.get(), 3, 1, 3),
			new MobSpawnInfo.Spawners(AoAEntities.NPCs.ZAL_CITIZEN.get(), 5, 1, 1));

	public ZalShipStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(decorationStage, templatePoolPath);
	}

	@Override
	public boolean getDefaultRestrictsSpawnsToInside() {
		return true;
	}

	@Override
	public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
		return ZAL_SPAWNS;
	}
}
