package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import java.util.List;

public class ForagingUtil {
	public static boolean shouldGetLoot(final int lvl) {
		if (lvl < 20)
			return AdventOfAscension.rand.nextInt(65) == 0;

		if (lvl < 40)
			return AdventOfAscension.rand.nextInt(55) == 0;

		if (lvl < 60)
			return AdventOfAscension.rand.nextInt(48) == 0;

		if (lvl < 80)
			return AdventOfAscension.rand.nextInt(36) == 0;

		return AdventOfAscension.rand.nextInt(29) == 0;
	}

	public static List<ItemStack> getLoot(EntityPlayer player) {
		return player.world.getLootTableManager().getLootTableFromLocation(LootSystemRegister.skillForaging).generateLootForPools(player.getRNG(), (new LootContext.Builder((WorldServer)player.world).withPlayer(player).withLuck(player.getLuck())).build());
	}
}
