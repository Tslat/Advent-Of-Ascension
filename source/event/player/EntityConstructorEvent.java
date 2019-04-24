package net.nevermine.event.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.nevermine.container.PlayerContainer;
import net.nevermine.resource.creation.creationHelper;
import net.nevermine.resource.energy.energyHelper;
import net.nevermine.resource.rage.rageHelper;
import net.nevermine.resource.soulpower.soulPowerHelper;

public class EntityConstructorEvent {
	@SubscribeEvent
	public void onEntityConstructing(final EntityEvent.EntityConstructing evt) {
		if (evt.entity instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)evt.entity;

			if (energyHelper.getProperties(pl) == null)
				energyHelper.addProperties(pl);

			if (soulPowerHelper.getProperties(pl) == null)
				soulPowerHelper.addProperties(pl);

			if (creationHelper.getProperties(pl) == null)
				creationHelper.addProperties(pl);

			if (rageHelper.getProperties(pl) == null)
				rageHelper.addProperties(pl);

			if (PlayerContainer.getProperties(pl) == null)
				PlayerContainer.addProperties(pl);
		}
	}
}
