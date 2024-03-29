package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;

public class AlchemySkill extends AoASkill.Instance {
	public AlchemySkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.ALCHEMY.get(), plData, jsonData);
	}

	public AlchemySkill(CompoundTag nbtData) {
		super(AoASkills.ALCHEMY.get(), nbtData);
	}
}
