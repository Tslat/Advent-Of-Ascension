package net.nevermine.event.dimensional.overworld;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;

import java.util.List;
import java.util.Random;

public class CreepDayEvent {
	private World world;
	public static boolean Creep;
	private Random rand;

	public CreepDayEvent() {
		rand = new Random();
	}

	public static boolean isCreep() {
		return CreepDayEvent.Creep;
	}

	@SubscribeEvent
	public void tickWorld(final TickEvent.WorldTickEvent evt) {
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && (evt.world.getWorldTime() % 24000L > 12000L || evt.world.getWorldTime() % 24000L < 480L)) {
			CreepDayEvent.Creep = false;
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 480L && rand.nextInt(20) == 13) {
			CreepDayEvent.Creep = true;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.creepDayStart", EnumChatFormatting.GREEN));
				evt.world.playSoundAtEntity(p, "nevermine:CreepDay", 3.85f, 1.0f);
			}
		}
		if (!evt.world.isRemote && evt.phase == TickEvent.Phase.END && evt.world.provider.dimensionId == 0 && evt.world.getWorldTime() % 24000L == 12000L && CreepDayEvent.Creep) {
			CreepDayEvent.Creep = false;
			for (final EntityPlayer p : (List<EntityPlayer>)evt.world.playerEntities) {
				p.addChatMessage(StringUtil.getColourLocale("message.event.creepDayEnd", EnumChatFormatting.GREEN));
			}
		}
	}

	static {
		CreepDayEvent.Creep = false;
	}
}
