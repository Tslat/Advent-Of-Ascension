package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;

public class HaulingSkill extends AoASkill.Instance {
	public HaulingSkill(PlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.HAULING.get(), plData, jsonData);
	}

	public HaulingSkill(CompoundNBT nbtData) {
		super(AoASkills.HAULING.get(), nbtData);
	}
}
