package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.gui.misc.XpGainParticle;
import net.tslat.aoa3.client.gui.render.XpParticlesRenderer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class PacketXpGain implements IMessage {
	private int skillId;
	private float xp;

	public PacketXpGain() {}

	public PacketXpGain(final int skill, final float xp) {
		this.skillId = skill;
		this.xp = xp;
	}

	public void fromBytes(final ByteBuf buffer) {
		this.skillId = buffer.readInt();
		this.xp = buffer.readFloat();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(skillId);
		buffer.writeFloat(xp);
	}

	public static class Handler implements IMessageHandler<PacketXpGain, IMessage> {
		public IMessage onMessage(final PacketXpGain msg, final MessageContext ctx) {
			if (ConfigurationUtil.showXpParticles && XpParticlesRenderer.particlesStore.size() < 20)
				XpParticlesRenderer.particlesStore.add(new XpGainParticle(Enums.Skills.getById(msg.skillId), msg.xp));

			return null;
		}
	}
}
