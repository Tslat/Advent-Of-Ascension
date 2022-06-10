package net.tslat.aoa3.content.loottable.function;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoALootOperations;
import org.apache.logging.log4j.Level;

import java.util.*;

public class EnchantSpecific extends LootItemConditionalFunction {
	private final HashMap<Enchantment, Integer> enchants;

	protected EnchantSpecific(LootItemCondition[] lootConditions, List<EnchantmentInstance> enchantments) {
		super(lootConditions);

		this.enchants = new HashMap<Enchantment, Integer>(enchantments.size());

		for (EnchantmentInstance ench : enchantments) {
			this.enchants.put(ench.enchantment, ench.level);
		}
	}

	@Override
	public LootItemFunctionType getType() {
		return AoALootOperations.LootFunctions.ENCHANT_SPECIFIC.get();
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		EnchantmentHelper.setEnchantments(enchants, stack);

		return stack;
	}

	public Map<Enchantment, Integer> getEnchantments() {
		return ImmutableMap.copyOf(enchants);
	}

	public static Builder<?> builder(EnchantmentInstance... enchantments) {
		return simpleBuilder((conditions) -> new EnchantSpecific(conditions, Arrays.asList(enchantments)));
	}

	public static class Serializer extends LootItemConditionalFunction.Serializer<EnchantSpecific> {
		@Override
		public void serialize(JsonObject object, EnchantSpecific function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			JsonArray enchantments = new JsonArray();

			for (Map.Entry<Enchantment, Integer> enchantEntry : function.enchants.entrySet()) {
				JsonObject enchantObject = new JsonObject();

				enchantObject.addProperty("id", ForgeRegistries.ENCHANTMENTS.getKey(enchantEntry.getKey()).toString());
				enchantObject.addProperty("level", enchantEntry.getValue());
				enchantments.add(enchantObject);
			}

			object.add("enchantments", enchantments);
		}

		@Override
		public EnchantSpecific deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditions) {
			List<EnchantmentInstance> enchants = new ArrayList<EnchantmentInstance>();

			if (object.has("enchantments")) {
				JsonElement enchantmentsObject = object.get("enchantments");

				if (enchantmentsObject.isJsonArray()) {
					for (JsonElement element : enchantmentsObject.getAsJsonArray()) {
						if (element.isJsonObject()) {
							JsonObject enchantObject = element.getAsJsonObject();

							if (!enchantObject.has("id") || !enchantObject.has("level"))
								continue;

							Enchantment enchant = null;
							int level = 1;

							try {
								JsonElement idElement = enchantObject.get("id");

								if (idElement.isJsonPrimitive())
									enchant = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(idElement.getAsJsonPrimitive().getAsString()));

								JsonElement levelElement = enchantObject.get("level");

								if (levelElement.isJsonPrimitive())
									level = levelElement.getAsJsonPrimitive().getAsInt();

								enchants.add(new EnchantmentInstance(enchant, level));
							}
							catch (Exception ex) {
								Logging.logMessage(Level.ERROR, "Invalid data type for enchant in EnchantSpecific deserialization, skipping entry");
							}
						}
					}
				}
			}

			return new EnchantSpecific(conditions, enchants);
		}
	}
}
