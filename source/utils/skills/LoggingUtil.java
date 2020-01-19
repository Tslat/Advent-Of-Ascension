package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import java.util.List;

public class LoggingUtil {
	public static boolean shouldGetLoot(final int lvl) {
		if (lvl < 20)
			return AdventOfAscension.rand.nextInt(25) == 0;

		if (lvl < 40)
			return AdventOfAscension.rand.nextInt(20) == 0;

		if (lvl < 60)
			return AdventOfAscension.rand.nextInt(16) == 0;

		if (lvl < 80)
			return AdventOfAscension.rand.nextInt(12) == 0;

		return AdventOfAscension.rand.nextInt(9) == 0;
	}

	public static List<ItemStack> getLoot(EntityPlayer player) {
		return player.world.getLootTableManager().getLootTableFromLocation(LootSystemRegister.skillLogging).generateLootForPools(player.getRNG(), (new LootContext.Builder((WorldServer)player.world).withPlayer(player).withLuck(player.getLuck())).build());
	}
}
