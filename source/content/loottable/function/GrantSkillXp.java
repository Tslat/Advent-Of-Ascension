package net.tslat.aoa3.content.loottable.function;

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
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class GrantSkillXp extends LootFunction {
	private final AoASkill skill;
	private final float xp;

	protected GrantSkillXp(ILootCondition[] lootConditions, AoASkill skill, float xp) {
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
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity).getSkill(skill).adjustXp(xp, false, false);

		return stack;
	}

	public AoASkill getSkill() {
		return this.skill;
	}

	public float getXp() {
		return this.xp;
	}

	public static Builder<?> builder(AoASkill skill, float xp) {
		return simpleBuilder((conditions) -> new GrantSkillXp(conditions, skill, xp));
	}

	public static class Serializer extends LootFunction.Serializer<GrantSkillXp> {
		@Override
		public void serialize(JsonObject object, GrantSkillXp function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.addProperty("skill", function.skill.getRegistryName().toString());
			object.addProperty("xp", function.xp);
		}

		@Override
		public GrantSkillXp deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditions) {
			return new GrantSkillXp(conditions, AoASkills.getSkill(new ResourceLocation(JSONUtils.getAsString(object, "skill"))), JSONUtils.getAsFloat(object, "xp"));
		}
	}
}
