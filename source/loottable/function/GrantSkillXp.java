package net.tslat.aoa3.loottable.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

public class GrantSkillXp extends LootFunction {
	private final Skills skill;
	private final float xp;

	protected GrantSkillXp(ILootCondition[] lootConditions, Skills skill, float xp) {
		super(lootConditions);

		this.skill = skill;
		this.xp	= xp;
	}

	@Override
	public LootFunctionType getType() {
		return AoALootOperations.LootFunctions.GRANT_SKILL_XP;
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		Entity entity = context.getParamOrNull(LootParameters.KILLER_ENTITY);

		if (!(entity instanceof PlayerEntity))
			entity = context.getParamOrNull(LootParameters.THIS_ENTITY);

		if (entity instanceof ServerPlayerEntity)
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity).stats().addXp(skill, xp, false, false);

		return stack;
	}

	public static Builder<?> builder(Skills skill, float xp) {
		return simpleBuilder((conditions) -> new GrantSkillXp(conditions, skill, xp));
	}

	public static class Serializer extends LootFunction.Serializer<GrantSkillXp> {
		@Override
		public void serialize(JsonObject object, GrantSkillXp function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.addProperty("skill", function.skill.toString().toLowerCase());
			object.addProperty("xp", function.xp);
		}

		@Override
		public GrantSkillXp deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditions) {
			return new GrantSkillXp(conditions, Skills.valueOf(JSONUtils.getAsString(object, "skill").toUpperCase()), JSONUtils.getAsFloat(object, "xp"));
		}
	}
}
