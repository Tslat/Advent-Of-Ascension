package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class CandylandEvents {
    public static void doPlayerTick(PlayerDataManager plData) {
        EntityPlayer pl = plData.player();

        if (!PlayerUtil.shouldPlayerBeAffected(pl))
            return;

        if (pl.isInWater()) {
            if (!pl.isPotionActive(MobEffects.SLOWNESS))
                plData.sendThrottledChatMessage("message.event.candyland.sticky", TextFormatting.LIGHT_PURPLE);

            pl.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 6, 2, true, false));
        }
    }
}
