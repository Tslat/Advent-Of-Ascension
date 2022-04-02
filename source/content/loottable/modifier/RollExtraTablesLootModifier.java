package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.util.List;

public class RollExtraTablesLootModifier extends LootModifier {
	private final ResourceLocation[] additionalTables;

	public RollExtraTablesLootModifier(LootItemCondition[] conditions, ResourceLocation[] additionalTables) {
		super(conditions);

		this.additionalTables = additionalTables;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		for (ResourceLocation tableLocation : additionalTables) {
			LootTable table = context.getLootTable(tableLocation);
			boolean compatible = true;

			for (LootContextParam<?> param : table.getParamSet().getRequired()) {
				if (!context.hasParam(param)) {
					compatible = false;

					Logging.logMessage(Level.WARN, "Incompatible loot table found for Roll Extra Tables Loot Modifier: " + tableLocation.toString() + ", missing: " + param.getName().toString());

					break;
				}
			}

			if (compatible && table != LootTable.EMPTY)
				table.getRandomItems(context, generatedLoot::add);
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<RollExtraTablesLootModifier> {
		@Override
		public RollExtraTablesLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootConditions) {
			JsonArray tables = GsonHelper.getAsJsonArray(object, "tables");
			ResourceLocation[] tableList = new ResourceLocation[tables.size()];

			for (int i = 0; i < tables.size(); i++) {
				tableList[i] = new ResourceLocation(tables.get(i).getAsString());
			}

			return new RollExtraTablesLootModifier(lootConditions, tableList);
		}

		@Override
		public JsonObject write(RollExtraTablesLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);
			JsonArray tables = new JsonArray();

			for (ResourceLocation table : instance.additionalTables) {
				tables.add(table.toString());
			}

			json.add("tables", tables);
			json.addProperty("type", getRegistryName().toString());

			return json;
		}
	}
}
