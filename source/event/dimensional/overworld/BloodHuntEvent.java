package net.nevermine.event.dimensional.overworld;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;
import java.util.Random;

public class BloodHuntEvent {
	private World world;
	public static boolean BloodHunt;
	private Random rand;

	public BloodHuntEvent() {
		rand = new Random();
	}

	public static boolean isHunt() {
		return BloodHuntEvent.BloodHunt;
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && (evt.world.getWorldTime() % 24000L > 23060L || evt.world.getWorldTime() % 24000L < 12600L)) {
			BloodHuntEvent.BloodHunt = false;
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 12600L && rand.nextInt(10) == 5) {
			BloodHuntEvent.BloodHunt = true;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.bloodHuntStart", EnumChatFormatting.RED));
				evt.world.playSoundAtEntity(p, "nevermine:BloodhuntStart", 3.85f, 1.0f);
			}
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 23060L && BloodHuntEvent.BloodHunt) {
			BloodHuntEvent.BloodHunt = false;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.bloodHuntEnd", EnumChatFormatting.RED));
			}
		}
	}

	static {
		BloodHuntEvent.BloodHunt = false;
	}
}
