package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class ImbuingSkill extends AoASkill.Instance {
	public ImbuingSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.IMBUING.get(), plData, jsonData);
	}

	public ImbuingSkill(CompoundTag nbtData) {
		super(AoASkills.IMBUING.get(), nbtData);
	}
}
