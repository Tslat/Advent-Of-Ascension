package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import java.util.List;

public class ExtractionUtil {
    public static boolean shouldGetLoot(int lvl) {
        return AdventOfAscension.rand.nextInt(100) >= Math.pow(((102 - lvl) / 6), 1.34);
    }

    public static int getXpDenominator(final int level) {
        if (level < 3)
            return 5;

        if (level < 9)
            return 10;

        if (level < 17)
            return 20;

        if (level < 26)
            return 35;

        if (level < 41)
            return 60;

        if (level < 61)
            return 90;

        if (level < 76)
            return 150;

        if (level < 99)
            return 300;

        return 500;
    }

    public static List<ItemStack> getLoot(EntityPlayer player) {
        return player.world.getLootTableManager().getLootTableFromLocation(LootSystemRegister.skillExtraction).generateLootForPools(player.getRNG(), (new LootContext.Builder((WorldServer)player.world).withPlayer(player).withLuck(player.getLuck())).build());
    }
}
