package net.nevermine.assist;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.nevermine.common.nevermine;
import net.nevermine.event.player.HealthMessage;
import net.nevermine.event.recoil.RecoilMessage;
import net.nevermine.gui.GuiHandler;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.resource.revenge.revengeMessage;
import net.nevermine.resource.tribute.TributeMessage;
import net.nevermine.skill.SkillMessage;
import net.nevermine.skill.expedition.reverseExpeditionMessage;

public class AddPackets {
	public static SimpleNetworkWrapper network;

	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(nevermine.instance, new GuiHandler());
		(AddPackets.network = NetworkRegistry.INSTANCE.newSimpleChannel("NeverminePackets")).registerMessage(MobHitPacket.Handler.class, MobHitPacket.class, 0, Side.CLIENT);
		AddPackets.network.registerMessage(TributeMessage.Handler.class, TributeMessage.class, 1, Side.CLIENT);
		AddPackets.network.registerMessage(SkillMessage.Handler.class, SkillMessage.class, 2, Side.CLIENT);
		AddPackets.network.registerMessage(RecoilMessage.Handler.class, RecoilMessage.class, 3, Side.CLIENT);
		AddPackets.network.registerMessage(revengeMessage.Handler.class, revengeMessage.class, 4, Side.CLIENT);
		AddPackets.network.registerMessage(reverseExpeditionMessage.Handler.class, reverseExpeditionMessage.class, 5, Side.SERVER);
		AddPackets.network.registerMessage(HealthMessage.Handler.class, HealthMessage.class, 6, Side.CLIENT);
	}
}
