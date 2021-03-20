package net.tslat.aoa3.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public abstract class AdvancementUtil {
	public static Advancement getAdvancement(ResourceLocation id) {
		return ServerLifecycleHooks.getCurrentServer().getAdvancements().getAdvancement(id);
	}

	public static boolean completeAdvancement(ServerPlayerEntity player, ResourceLocation id, String criterion) {
		Advancement adv = getAdvancement(id);

		if (adv != null)
			return player.getAdvancements().award(adv, criterion);

		return false;
	}

	public static boolean isAdvancementCompleted(ServerPlayerEntity player, ResourceLocation id) {
		Advancement adv = getAdvancement(id);

		if (adv != null)
			return player.getAdvancements().getOrStartProgress(adv).isDone();

		return false;
	}
}
