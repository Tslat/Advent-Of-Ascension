package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class ShyrelandsEvents {
    public static void doPlayerTick(PlayerEntity pl) {

        if (PlayerUtil.shouldPlayerBeAffected(pl) && pl.tickCount % 80 == 0 && RandomUtil.oneInNChance(3)) {
            if (pl.getY() >= 55 && pl.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, pl.blockPosition()).getY() <= pl.getY()) {
                float gustX = RandomUtil.randomValueUpTo(1f);
                float gustZ = RandomUtil.randomValueUpTo(1f);

                pl.sendMessage(new TranslationTextComponent("message.event.shyrelandsWind"), Util.NIL_UUID);
                pl.playSound(AoASounds.SHYRELANDS_WIND.get(), 1.0f, 1.0f);
                pl.push(gustX, 0.05f, gustZ);
                pl.hurtMarked = true;
            }
        }
    }
}
