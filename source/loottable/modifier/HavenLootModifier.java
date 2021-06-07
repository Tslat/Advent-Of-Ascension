package net.tslat.aoa3.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IServerWorld;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nonnull;
import java.util.List;

public class HavenLootModifier extends LootModifier {
	public static final ResourceLocation HAVEN_LOOT_TABLE = new ResourceLocation(AdventOfAscension.MOD_ID, "worlds/haven_passive");

	public HavenLootModifier(ILootCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (WorldUtil.isWorld((IServerWorld)context.getLevel(), AoADimensions.HAVEN.key) && context.hasParam(LootParameters.THIS_ENTITY) && context.hasParam(LootParameters.ORIGIN) && !context.hasParam(LootParameters.KILLER_ENTITY) && !context.hasParam(LootParameters.DIRECT_KILLER_ENTITY)) {
			if (!context.hasParam(LootParameters.BLOCK_STATE) || context.getRandom().nextInt(10) == 0) {
				LootContext newContext = new LootContext.Builder(context.getLevel())
						.withParameter(LootParameters.THIS_ENTITY, context.getParamOrNull(LootParameters.THIS_ENTITY))
						.withParameter(LootParameters.ORIGIN, context.getParamOrNull(LootParameters.ORIGIN))
						.create(LootParameterSets.GIFT);

				context.getLootTable(HAVEN_LOOT_TABLE).getRandomItems(newContext, generatedLoot::add);
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<HavenLootModifier> {
		@Override
		public HavenLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			return new HavenLootModifier(lootConditions);
		}

		@Override
		public JsonObject write(HavenLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);

			return json;
		}
	}
}
