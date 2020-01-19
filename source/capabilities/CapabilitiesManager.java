package net.tslat.aoa3.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;

public class CapabilitiesManager {
	private static final ResourceLocation adventPlayer = new ResourceLocation("aoa3", "AdventPlayer");

	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayerMP)
			event.addCapability(adventPlayer, new AdventPlayerProvider((EntityPlayer)event.getObject()));
	}
}
