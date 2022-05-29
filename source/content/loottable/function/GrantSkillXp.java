package net.tslat.aoa3.content.loottable.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class GrantSkillXp extends LootItemConditionalFunction {
	private final AoASkill skill;
	private final float xp;

	protected GrantSkillXp(LootItemCondition[] lootConditions, AoASkill skill, float xp) {
		super(lootConditions);

		this.skill = skill;
		this.xp	= xp;
	}

	@Override
	public LootItemFunctionType getType() {
		return AoALootOperations.LootFunctions.GRANT_SKILL_XP.get();
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		Entity entity = context.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (!(entity instanceof Player))
			entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity instanceof ServerPlayer)
			PlayerUtil.getAdventPlayer((ServerPlayer)entity).getSkill(skill).adjustXp(xp, false, false);

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

	public static class Serializer extends LootItemConditionalFunction.Serializer<GrantSkillXp> {
		@Override
		public void serialize(JsonObject object, GrantSkillXp function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.addProperty("skill", function.skill.getRegistryName().toString());
			object.addProperty("xp", function.xp);
		}

		@Override
		public GrantSkillXp deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditions) {
			return new GrantSkillXp(conditions, AoASkills.getSkill(new ResourceLocation(GsonHelper.getAsString(object, "skill"))), GsonHelper.getAsFloat(object, "xp"));
		}
	}
}
