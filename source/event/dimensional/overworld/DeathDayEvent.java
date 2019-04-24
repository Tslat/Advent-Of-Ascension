package net.nevermine.event.dimensional.overworld;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;
import java.util.Random;

public class DeathDayEvent {
	private World world;
	public static boolean Deadly;
	private Random rand;

	public DeathDayEvent() {
		rand = new Random();
	}

	public static boolean isDeadly() {
		return DeathDayEvent.Deadly;
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && (evt.world.getWorldTime() % 24000L > 12400L || evt.world.getWorldTime() % 24000L < 480L)) {
			DeathDayEvent.Deadly = false;
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 480L && rand.nextInt(25) == 12) {
			DeathDayEvent.Deadly = true;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.deathDayStart", EnumChatFormatting.RED));
				evt.world.playSoundAtEntity(p, "nevermine:DeathDay", 3.85f, 1.0f);
			}
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 12400L && DeathDayEvent.Deadly) {
			DeathDayEvent.Deadly = false;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.deathDayEnd", EnumChatFormatting.RED));
			}
		}
	}

	static {
		DeathDayEvent.Deadly = false;
	}
}
