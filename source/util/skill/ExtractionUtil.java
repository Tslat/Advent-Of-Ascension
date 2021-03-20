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

public class ExtractionUtil {
    public static boolean shouldGetLoot(int lvl) {
        return RandomUtil.randomNumberUpTo(100) >= Math.pow(((102 - lvl) / 6), 1.34);
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

    public static List<ItemStack> getLoot(ServerPlayerEntity player, Vector3d pos) {
        return player.level.getServer().getLootTables().get(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/extraction")).getRandomItems(new LootContext.Builder((ServerWorld)player.level).withLuck(player.getLuck()).withParameter(LootParameters.ORIGIN, pos).withParameter(LootParameters.THIS_ENTITY, player).create(LootParameterSets.GIFT));
    }
}
