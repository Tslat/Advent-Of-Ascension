package net.nevermine.event.dimensional.overworld;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;
import java.util.Random;

public class SoulScurryEvent {
	private World world;
	public static boolean SoulScurry;
	private Random rand;

	public SoulScurryEvent() {
		rand = new Random();
	}

	public static boolean isScurry() {
		return SoulScurryEvent.SoulScurry;
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && (evt.world.getWorldTime() % 24000L > 23100L || evt.world.getWorldTime() % 24000L < 12550L)) {
			SoulScurryEvent.SoulScurry = false;
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 12550L && rand.nextInt(12) == 5) {
			SoulScurryEvent.SoulScurry = true;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.soulScurryStart", EnumChatFormatting.AQUA));
				evt.world.playSoundAtEntity(p, "nevermine:SoulScurryStart", 3.85f, 1.0f);
			}
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 23100L && SoulScurryEvent.SoulScurry) {
			SoulScurryEvent.SoulScurry = false;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.soulScurryEnd", EnumChatFormatting.AQUA));
			}
		}
	}

	static {
		SoulScurryEvent.SoulScurry = false;
	}
}
