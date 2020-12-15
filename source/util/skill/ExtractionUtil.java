package net.tslat.aoa3.util.skill;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
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

    public static List<ItemStack> getLoot(ServerPlayerEntity player, BlockPos pos) {
        return player.world.getServer().getLootTableManager().getLootTableFromLocation(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/extraction")).generate(new LootContext.Builder((ServerWorld)player.world).withLuck(player.getLuck()).withParameter(LootParameters.POSITION, pos).withParameter(LootParameters.THIS_ENTITY, player).build(LootParameterSets.GIFT));
    }
}
