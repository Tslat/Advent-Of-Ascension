package net.nevermine.event.dimensional.overworld;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;
import java.util.Random;

public class BigDayEvent {
	private World world;
	public static boolean Large;
	private Random rand;

	public BigDayEvent() {
		rand = new Random();
	}

	public static boolean isBig() {
		return BigDayEvent.Large;
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && (evt.world.getWorldTime() % 24000L > 12100L || evt.world.getWorldTime() % 24000L < 480L)) {
			BigDayEvent.Large = false;
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 480L && rand.nextInt(20) == 10) {
			BigDayEvent.Large = true;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.bigDayStart", EnumChatFormatting.GOLD));
				evt.world.playSoundAtEntity(p, "nevermine:BigDay", 3.85f, 1.0f);
			}
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 12100L && BigDayEvent.Large) {
			BigDayEvent.Large = false;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.bigDayEnd", EnumChatFormatting.GOLD));
			}
		}
	}

	static {
		BigDayEvent.Large = false;
	}
}
