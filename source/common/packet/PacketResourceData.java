package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.client.gui.render.ResourcesRenderer;

public class PacketResourceData implements IMessage {
	private float creation;
	private float energy;
	private float rage;
	private float soul;
	private boolean revengeActive;

	public PacketResourceData() {}

	public PacketResourceData(final float creation, final float energy, final float rage, final float soul, final boolean revengeActive) {
		this.creation = creation;
		this.energy = energy;
		this.rage = rage;
		this.soul = soul;
		this.revengeActive = revengeActive;
	}

	public void fromBytes(final ByteBuf buffer) {
		creation = buffer.readFloat();
		energy = buffer.readFloat();
		rage = buffer.readFloat();
		soul = buffer.readFloat();
		revengeActive = buffer.readBoolean();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeFloat(creation);
		buffer.writeFloat(energy);
		buffer.writeFloat(rage);
		buffer.writeFloat(soul);
		buffer.writeBoolean(revengeActive);
	}

	public static class Handler implements IMessageHandler<PacketResourceData, IMessage> {
		public IMessage onMessage(final PacketResourceData msg, final MessageContext ctx) {
			AdventGuiTabPlayer.resourceCreation = msg.creation;
			AdventGuiTabPlayer.resourceEnergy = msg.energy;
			AdventGuiTabPlayer.resourceRage = msg.rage;
			AdventGuiTabPlayer.resourceSoul = msg.soul;
			ResourcesRenderer.revengeActive = msg.revengeActive;

			return null;
		}
	}
}
