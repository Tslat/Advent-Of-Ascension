package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class ArtificeSkill extends AoASkill.Instance {
	public ArtificeSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.ARTIFICE.get(), plData, jsonData);
	}

	public ArtificeSkill(CompoundTag nbtData) {
		super(AoASkills.ARTIFICE.get(), nbtData);
	}
}
