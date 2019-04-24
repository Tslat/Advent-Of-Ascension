package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.render.FXRenders;

public class PacketParticleSpawn implements IMessage {
	private int particleId;
	private double posX;
	private double posY;
	private double posZ;
	private double speedX;
	private double speedY;
	private double speedZ;
	private int args;

	public PacketParticleSpawn() {}

	public PacketParticleSpawn(final int particleId, final double posX, final double posY, final double posZ, final double speedX, final double speedY, final double speedZ, int args) {
		this.particleId = particleId;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedZ = speedZ;
		this.args = args;
	}

	public void fromBytes(final ByteBuf buffer) {
		particleId = buffer.readInt();
		posX = buffer.readDouble();
		posY = buffer.readDouble();
		posZ = buffer.readDouble();
		speedX = buffer.readDouble();
		speedY = buffer.readDouble();
		speedZ = buffer.readDouble();
		args = buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(particleId);
		buffer.writeDouble(posX);
		buffer.writeDouble(posY);
		buffer.writeDouble(posZ);
		buffer.writeDouble(speedX);
		buffer.writeDouble(speedY);
		buffer.writeDouble(speedZ);
		buffer.writeInt(args);
	}

	public static class Handler implements IMessageHandler<PacketParticleSpawn, IMessage> {
		public IMessage onMessage(final PacketParticleSpawn msg, final MessageContext ctx) {
			FXRenders.spawnParticle(msg.particleId, msg.posX, msg.posY, msg.posZ, msg.speedX, msg.speedY, msg.speedZ, msg.args);
			return null;
		}
	}
}