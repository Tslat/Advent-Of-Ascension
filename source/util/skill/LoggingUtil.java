package net.tslat.aoa3.util.skill;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
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
		return player.level.getServer().getLootTables().get(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/logging")).getRandomItems(new LootContext.Builder((ServerWorld)player.level).withLuck(player.getLuck()).withParameter(LootParameters.THIS_ENTITY, player).withRandom(player.getRandom()).create(LootParameterSets.GIFT));
	}
}
