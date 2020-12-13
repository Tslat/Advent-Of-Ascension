package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;
import net.tslat.aoa3.client.gui.hud.ResourcesRenderer;

import java.util.function.Supplier;

public class ResourceDataPacket implements AoAPacket {
	private final float creation;
	private final float energy;
	private final float rage;
	private final float soul;
	private final boolean revengeActive;

	public ResourceDataPacket(final float creation, final float energy, final float rage, final float soul, final boolean revengeActive) {
		this.creation = creation;
		this.energy = energy;
		this.rage = rage;
		this.soul = soul;
		this.revengeActive = revengeActive;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeFloat(creation);
		buffer.writeFloat(energy);
		buffer.writeFloat(rage);
		buffer.writeFloat(soul);
		buffer.writeBoolean(revengeActive);
	}

	public static ResourceDataPacket decode(PacketBuffer buffer) {
		return new ResourceDataPacket(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readBoolean());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		AdventGuiTabPlayer.resourceCreation = creation;
		AdventGuiTabPlayer.resourceEnergy = energy;
		AdventGuiTabPlayer.resourceRage = rage;
		AdventGuiTabPlayer.resourceSoul = soul;
		ResourcesRenderer.revengeActive = revengeActive;

		context.get().setPacketHandled(true);
	}
}
