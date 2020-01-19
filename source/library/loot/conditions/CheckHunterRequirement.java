package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;

import java.util.Random;

public class CheckHunterRequirement implements LootCondition {
	public CheckHunterRequirement() {}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		if (!(context.getLootedEntity() instanceof EntityLivingBase))
			return true;

		int hunterReq = HunterUtil.getHunterLevel((EntityLivingBase)context.getLootedEntity());
		EntityPlayer pl;

		if (hunterReq <= 0)
			return true;

		if (context.getKillerPlayer() != null) {
			pl = (EntityPlayer)context.getKillerPlayer();
		}
		else if (context.getKiller() instanceof EntityTameable && ((EntityTameable)context.getKiller()).getOwner() instanceof EntityPlayer) {
			pl = (EntityPlayer)((EntityTameable)context.getKiller()).getOwner();
		}
		else {
			return false;
		}

		return PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, hunterReq);
	}

	public static class Serializer extends LootCondition.Serializer<CheckHunterRequirement> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "check_hunter_requirement"), CheckHunterRequirement.class);
		}

		@Override
		public void serialize(JsonObject json, CheckHunterRequirement value, JsonSerializationContext context) {}

		@Override
		public CheckHunterRequirement deserialize(JsonObject json, JsonDeserializationContext context) {
			return new CheckHunterRequirement();
		}
	}
}
