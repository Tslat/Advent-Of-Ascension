package net.tslat.aoa3.library.loot.modifiers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.util.List;

public class RollExtraTablesLootModifier extends LootModifier {
	private final ResourceLocation[] additionalTables;

	protected RollExtraTablesLootModifier(ILootCondition[] conditions, ResourceLocation[] additionalTables) {
		super(conditions);

		this.additionalTables = additionalTables;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		for (ResourceLocation tableLocation : additionalTables) {
			LootTable table = context.getLootTable(tableLocation);
			boolean compatible = true;

			for (LootParameter<?> param : table.getParameterSet().getRequiredParameters()) {
				if (!context.has(param)) {
					compatible = false;

					Logging.logMessage(Level.WARN, "Incompatible loot table found for Roll Extra Tables Loot Modifier: " + tableLocation.toString() + ", missing: " + param.getId().toString());

					break;
				}
			}

			if (compatible && table != LootTable.EMPTY_LOOT_TABLE)
				table.generate(context, generatedLoot::add);
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<RollExtraTablesLootModifier> {
		@Override
		public RollExtraTablesLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			JsonArray tables = JSONUtils.getJsonArray(object, "tables");
			ResourceLocation[] tableList = new ResourceLocation[tables.size()];

			for (int i = 0; i < tables.size(); i++) {
				tableList[i] = new ResourceLocation(tables.get(i).getAsString());
			}

			return new RollExtraTablesLootModifier(lootConditions, tableList);
		}
	}
}
