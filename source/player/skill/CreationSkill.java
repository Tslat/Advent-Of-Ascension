package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class CreationSkill extends AoASkill.Instance {
	public CreationSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.CREATION.get(), plData, jsonData);
	}

	public CreationSkill(CompoundTag nbtData) {
		super(AoASkills.CREATION.get(), nbtData);
	}
}