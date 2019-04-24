package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.StringUtil;

public class ShyrelandsEvents {
    public static void doPlayerTick(AdventPlayerCapability cap) {
        EntityPlayer pl = cap.getPlayer();

        if (pl.capabilities.isCreativeMode || pl.world.isRemote)
            return;

        if (pl.ticksExisted % 80 == 0 && AdventOfAscension.rand.nextInt(3) == 0) {
            if (pl.posY >= 55 && pl.world.getHeight((int)pl.posX, (int)pl.posZ) <= pl.posY) {
                float gustX = AdventOfAscension.rand.nextFloat();
                float gustZ = AdventOfAscension.rand.nextFloat();

                cap.sendPlayerMessage(StringUtil.getLocale("message.event.shyrelandsWind"));
                pl.world.playSound(pl.posX, pl.posY, pl.posZ, SoundsRegister.shyrelandsWind, SoundCategory.AMBIENT, 1.0f, 1.0f, false);
                pl.addVelocity(gustX, 0.05f, gustZ);
                pl.velocityChanged = true;
            }
        }
    }
}
