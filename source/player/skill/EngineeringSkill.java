package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class EngineeringSkill extends AoASkill.Instance {
	public EngineeringSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.ENGINEERING.get(), plData, jsonData);
	}

	public EngineeringSkill(CompoundTag nbtData) {
		super(AoASkills.ENGINEERING.get(), nbtData);
	}
}
