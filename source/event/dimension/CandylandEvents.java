package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.StringUtil;

public class CandylandEvents {
    public static void doPlayerTick(AdventPlayerCapability cap) {
        EntityPlayer pl = cap.getPlayer();

        if (pl.world.isRemote || pl.capabilities.isCreativeMode || pl.isSpectator())
            return;

        if (pl.isInWater()) {
            if (!pl.isPotionActive(MobEffects.SLOWNESS))
                cap.sendPlayerMessage(StringUtil.getColourLocale("message.event.candyland.sticky", TextFormatting.LIGHT_PURPLE));

            pl.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 6, 2, true, false));
        }
    }
}
