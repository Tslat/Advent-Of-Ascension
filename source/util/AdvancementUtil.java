package net.tslat.aoa3.util;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public final class AdvancementUtil {
	public static AdvancementHolder getAdvancement(ResourceLocation id) {
		return ServerLifecycleHooks.getCurrentServer().getAdvancements().get(id);
	}

	public static boolean completeAdvancement(ServerPlayer player, ResourceLocation id, String criterion) {
		AdvancementHolder adv = getAdvancement(id);

		if (adv != null)
			return player.getAdvancements().award(adv, criterion);

		return false;
	}

	public static boolean isAdvancementCompleted(ServerPlayer player, ResourceLocation id) {
		AdvancementHolder adv = getAdvancement(id);

		if (adv != null)
			return player.getAdvancements().getOrStartProgress(adv).isDone();

		return false;
	}
}
