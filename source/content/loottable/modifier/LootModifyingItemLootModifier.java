package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.content.item.LootModifyingItem;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

public class LootModifyingItemLootModifier extends LootModifier {
	public LootModifyingItemLootModifier(LootItemCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		ItemStack tool = ItemStack.EMPTY;

		if (context.hasParam(LootContextParams.TOOL)) {
			tool = context.getParamOrNull(LootContextParams.TOOL);

			if (tool == null)
				return generatedLoot;
		}
		else if (context.hasParam(LootContextParams.KILLER_ENTITY)) {
			Entity entity = context.getParamOrNull(LootContextParams.KILLER_ENTITY);

			if (entity != null) {
				Iterator<ItemStack> heldItems = entity.getHandSlots().iterator();

				if (heldItems.hasNext())
					tool = heldItems.next();
			}
		}

		if (tool.getItem() instanceof LootModifyingItem)
			((LootModifyingItem)tool.getItem()).doLootModification(generatedLoot, context);

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<LootModifyingItemLootModifier> {
		@Override
		public LootModifyingItemLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootConditions) {
			return new LootModifyingItemLootModifier(lootConditions);
		}

		@Override
		public JsonObject write(LootModifyingItemLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);

			json.addProperty("type", getRegistryName().toString());

			return json;
		}
	}
}
