package net.nevermine.event.dimensional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;

public class GardencianOceanEvent {
	private World world;
	private static int lastTicksExisted;

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		world = ev.player.worldObj;
		if (!ev.player.capabilities.isCreativeMode && ev.player.dimension == ConfigurationHelper.gardencia && (ev.player.posY == 63.0 || ev.player.posY == 64.0) && world.getBlock((int)ev.player.posX, 66, (int)ev.player.posZ) == Blocks.water) {
			ev.player.attackEntityFrom(DamageSource.drown, 2.0f);
			if (ev.player.ticksExisted % 40 == 0) {
				if (GardencianOceanEvent.lastTicksExisted != ev.player.ticksExisted) {
					ev.player.addChatMessage(StringUtil.getColourLocale("message.event.gardenciaPressure", EnumChatFormatting.RED));
				}
				GardencianOceanEvent.lastTicksExisted = ev.player.ticksExisted;
			}
		}
	}

	static {
		GardencianOceanEvent.lastTicksExisted = 0;
	}
}
