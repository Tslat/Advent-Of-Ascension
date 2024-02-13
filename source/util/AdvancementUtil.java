package net.tslat.aoa3.util;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class AdvancementUtil {
	public static AdvancementHolder getAdvancement(ServerLevel level, ResourceLocation id) {
		return level.getServer().getAdvancements().get(id);
	}

	public static boolean completeAdvancement(ServerPlayer player, ResourceLocation id, String criterion) {
		AdvancementHolder adv = getAdvancement(player.serverLevel(), id);

		if (adv != null)
			return player.getAdvancements().award(adv, criterion);

		return false;
	}

	public static boolean isAdvancementCompleted(ServerPlayer player, ResourceLocation id) {
		AdvancementHolder adv = getAdvancement(player.serverLevel(), id);

		if (adv != null)
			return player.getAdvancements().getOrStartProgress(adv).isDone();

		return false;
	}
}
