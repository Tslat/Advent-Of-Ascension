package net.nevermine.event.dimensional.overworld;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;
import java.util.Random;

public class LunarEvent {
	private World world;
	public static boolean LunarInvasion;
	private Random rand;

	public LunarEvent() {
		rand = new Random();
	}

	public static boolean isLunar() {
		return LunarEvent.LunarInvasion;
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && (evt.world.getWorldTime() % 24000L > 23100L || evt.world.getWorldTime() % 24000L < 12550L)) {
			LunarEvent.LunarInvasion = false;
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 12550L && rand.nextInt(15) == 11) {
			LunarEvent.LunarInvasion = true;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.lunarStart", EnumChatFormatting.LIGHT_PURPLE));
				evt.world.playSoundAtEntity(p, "nevermine:LunarInvasionStart", 3.85f, 1.0f);
			}
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 23100L && LunarEvent.LunarInvasion) {
			LunarEvent.LunarInvasion = false;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.lunarEnd", EnumChatFormatting.LIGHT_PURPLE));
			}
		}
	}

	static {
		LunarEvent.LunarInvasion = false;
	}
}
