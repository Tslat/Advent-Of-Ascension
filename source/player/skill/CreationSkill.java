package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;

public class CreationSkill extends AoASkill.Instance {
	public CreationSkill(PlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.CREATION.get(), plData, jsonData);
	}

	public CreationSkill(CompoundNBT nbtData) {
		super(AoASkills.CREATION.get(), nbtData);
	}
}