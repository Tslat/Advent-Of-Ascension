package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class FaunamancySkill extends AoASkill.Instance {
	public FaunamancySkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.FAUNAMANCY.get(), plData, jsonData);
	}

	public FaunamancySkill(CompoundNBT nbtData) {
		super(AoASkills.FAUNAMANCY.get(), nbtData);
	}
}
