package net.tslat.aoa3.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.item.LootModifyingItem;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

public class LootModifyingItemLootModifier extends LootModifier {
	public LootModifyingItemLootModifier(ILootCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		ItemStack tool = ItemStack.EMPTY;

		if (context.hasParam(LootParameters.TOOL)) {
			tool = context.getParamOrNull(LootParameters.TOOL);

			if (tool == null)
				return generatedLoot;
		}
		else if (context.hasParam(LootParameters.KILLER_ENTITY)) {
			Entity entity = context.getParamOrNull(LootParameters.KILLER_ENTITY);

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
		public LootModifyingItemLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
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
