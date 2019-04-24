package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import org.apache.logging.log4j.Level;

public class PacketSkillData implements IMessage {
	private int skillId;
	private int level;
	private float xp;
	private int data;

	public PacketSkillData() {}

	public PacketSkillData(final int skill, final int lvl, final float xp, final int opt) {
		skillId = skill;
		level = lvl;
		this.xp = xp;
		data = opt;
	}

	public void fromBytes(final ByteBuf buffer) {
		skillId = buffer.readInt();
		level = buffer.readInt();
		xp = buffer.readFloat();
		data = buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(skillId);
		buffer.writeInt(level);
		buffer.writeFloat(xp);
		buffer.writeInt(data);
	}

	public static class Handler implements IMessageHandler<PacketSkillData, IMessage> {
		public IMessage onMessage(final PacketSkillData msg, final MessageContext ctx) {

			Enums.Skills skill = Enums.Skills.getById(msg.skillId);

			if (skill != null) {
				AdventGuiTabPlayer.setSkillData(skill, msg.xp, msg.level, msg.data);
			}
			else {
				AdventOfAscension.getLogger().log(Level.WARN, "Error trying to set skill data in client skills holder, skipping");
			}

			return null;
		}
	}
}
