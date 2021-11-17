package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;

public class ExtractionSkill extends AoASkill.Instance {
	public ExtractionSkill(PlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.EXTRACTION.get(), plData, jsonData);
	}

	public ExtractionSkill(CompoundNBT nbtData) {
		super(AoASkills.EXTRACTION.get(), nbtData);
	}
}
