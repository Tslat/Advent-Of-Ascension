package net.tslat.aoa3.util.skill;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.RandomUtil;

import java.util.List;

public class LoggingUtil {
	public static boolean shouldGetLoot(final int lvl) {
		if (lvl < 20)
			return RandomUtil.oneInNChance(25);

		if (lvl < 40)
			return RandomUtil.oneInNChance(20);

		if (lvl < 60)
			return RandomUtil.oneInNChance(16);

		if (lvl < 80)
			return RandomUtil.oneInNChance(12);

		return RandomUtil.oneInNChance(9);
	}

	public static List<ItemStack> getLoot(ServerPlayerEntity player) {
		return player.world.getServer().getLootTableManager().getLootTableFromLocation(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/logging_loot")).generate(new LootContext.Builder((ServerWorld)player.world).withLuck(player.getLuck()).withParameter(LootParameters.THIS_ENTITY, player).withRandom(player.getRNG()).build(LootParameterSets.GENERIC));
	}
}
