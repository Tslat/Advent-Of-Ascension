package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameter;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nonnull;
import java.util.List;

public class PlayerEventListenerLootModifier extends LootModifier {
	private static final LootParameter<?>[] ENTITY_SOURCE_PARAMS = new LootParameter<?>[] {LootParameters.THIS_ENTITY, LootParameters.DIRECT_KILLER_ENTITY, LootParameters.KILLER_ENTITY, LootParameters.LAST_DAMAGE_PLAYER};

	public PlayerEventListenerLootModifier(ILootCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		for (LootParameter<?> param : ENTITY_SOURCE_PARAMS) {
			if (context.hasParam(param)) {
				PlayerEntity pl = PlayerUtil.getPlayerOrOwnerIfApplicable((Entity)context.getParamOrNull(param));

				if (pl instanceof ServerPlayerEntity) {
					AoAPlayerEvents.issueEvent((ServerPlayerEntity)pl, AoAPlayerEventListener.ListenerType.LOOT_MODIFICATION, listener -> listener.handleLootModification(generatedLoot, context));

					return generatedLoot;
				}
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<PlayerEventListenerLootModifier> {
		@Override
		public PlayerEventListenerLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			return new PlayerEventListenerLootModifier(lootConditions);
		}

		@Override
		public JsonObject write(PlayerEventListenerLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);

			json.addProperty("type", getRegistryName().toString());

			return json;
		}
	}
}
