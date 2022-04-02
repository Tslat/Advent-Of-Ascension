package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nonnull;
import java.util.List;

public class PlayerEventListenerLootModifier extends LootModifier {
	private static final LootContextParam<?>[] ENTITY_SOURCE_PARAMS = new LootContextParam<?>[] {LootContextParams.THIS_ENTITY, LootContextParams.DIRECT_KILLER_ENTITY, LootContextParams.KILLER_ENTITY, LootContextParams.LAST_DAMAGE_PLAYER};

	public PlayerEventListenerLootModifier(LootItemCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		for (LootContextParam<?> param : ENTITY_SOURCE_PARAMS) {
			if (context.hasParam(param)) {
				Player pl = PlayerUtil.getPlayerOrOwnerIfApplicable((Entity)context.getParamOrNull(param));

				if (pl instanceof ServerPlayer) {
					AoAPlayerEvents.issueEvent((ServerPlayer)pl, AoAPlayerEventListener.ListenerType.LOOT_MODIFICATION, listener -> listener.handleLootModification(generatedLoot, context));

					return generatedLoot;
				}
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<PlayerEventListenerLootModifier> {
		@Override
		public PlayerEventListenerLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootConditions) {
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
