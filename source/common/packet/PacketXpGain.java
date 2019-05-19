package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.gui.render.XpParticlesRenderer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class PacketXpGain implements IMessage {
	private int skillId;
	private float xp;
	private boolean levelUp;

	public PacketXpGain() {}

	public PacketXpGain(final int skill, final float xp, boolean isLevelUp) {
		this.skillId = skill;
		this.xp = xp;
		this.levelUp = isLevelUp;
	}

	public void fromBytes(final ByteBuf buffer) {
		this.skillId = buffer.readInt();
		this.xp = buffer.readFloat();
		this.levelUp = buffer.readBoolean();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(skillId);
		buffer.writeFloat(xp);
		buffer.writeBoolean(levelUp);
	}

	public static class Handler implements IMessageHandler<PacketXpGain, IMessage> {
		public IMessage onMessage(final PacketXpGain msg, final MessageContext ctx) {
			if (ConfigurationUtil.MainConfig.showXpParticles)
				XpParticlesRenderer.addXpParticle(Enums.Skills.getById(msg.skillId), msg.xp, msg.levelUp);

			return null;
		}
	}
}
