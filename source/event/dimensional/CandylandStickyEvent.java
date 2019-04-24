package net.nevermine.event.dimensional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;

public class CandylandStickyEvent {
	private static int lastTicksExisted = 0;

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		if (ev.player.capabilities.isCreativeMode)
			return;

		if (ev.phase == TickEvent.Phase.END && !ev.player.worldObj.isRemote && ev.player.dimension == ConfigurationHelper.candyland && ev.player.isInWater()) {
			ev.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, -1, 2));

			if (ev.player.ticksExisted % 120 == 0) {
				if (CandylandStickyEvent.lastTicksExisted != ev.player.ticksExisted) {
					ev.player.addChatMessage(StringUtil.getColourLocale("message.event.candylandSticky", EnumChatFormatting.LIGHT_PURPLE));
				}

				CandylandStickyEvent.lastTicksExisted = ev.player.ticksExisted;
			}
		}
	}
}
