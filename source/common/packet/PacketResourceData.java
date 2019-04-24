package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;

public class PacketResourceData implements IMessage {
	private float creation;
	private float energy;
	private float rage;
	private float soul;

	public PacketResourceData() {}

	public PacketResourceData(final float creation, final float energy, final float rage, final float soul) {
		this.creation = creation;
		this.energy = energy;
		this.rage = rage;
		this.soul = soul;
	}

	public void fromBytes(final ByteBuf buffer) {
		creation = buffer.readFloat();
		energy = buffer.readFloat();
		rage = buffer.readFloat();
		soul = buffer.readFloat();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeFloat(creation);
		buffer.writeFloat(energy);
		buffer.writeFloat(rage);
		buffer.writeFloat(soul);
	}

	public static class Handler implements IMessageHandler<PacketResourceData, IMessage> {
		public IMessage onMessage(final PacketResourceData msg, final MessageContext ctx) {
			AdventGuiTabPlayer.resourceCreation = msg.creation;
			AdventGuiTabPlayer.resourceEnergy = msg.energy;
			AdventGuiTabPlayer.resourceRage = msg.rage;
			AdventGuiTabPlayer.resourceSoul = msg.soul;

			return null;
		}
	}
}
