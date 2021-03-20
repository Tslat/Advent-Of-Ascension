package net.tslat.aoa3.util.skill;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.RandomUtil;

import java.util.List;

public class ForagingUtil {
	public static boolean shouldGetLoot(final int lvl) {
		if (lvl < 20)
			return RandomUtil.oneInNChance(65);

		if (lvl < 40)
			return RandomUtil.oneInNChance(55);

		if (lvl < 60)
			return RandomUtil.oneInNChance(48);

		if (lvl < 80)
			return RandomUtil.oneInNChance(36);

		return RandomUtil.oneInNChance(29);
	}

	public static List<ItemStack> getLoot(ServerPlayerEntity player, Vector3d pos) {
		return player.level.getServer().getLootTables().get(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/foraging")).getRandomItems(new LootContext.Builder((ServerWorld)player.level).withLuck(player.getLuck()).withParameter(LootParameters.THIS_ENTITY, player).withParameter(LootParameters.ORIGIN, pos).create(LootParameterSets.GIFT));
	}
}
