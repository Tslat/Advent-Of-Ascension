package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;
import net.tslat.aoa3.util.constant.Skills;
import org.apache.logging.log4j.Level;

import java.util.function.Supplier;

public class SkillDataPacket implements AoAPacket {
	private final int skillId;
	private final int level;
	private final float xp;
	private final int data;

	public SkillDataPacket(final int skill, final int lvl, final float xp, Integer opt) {
		skillId = skill;
		level = lvl;
		this.xp = xp;

		if (opt == null)
			opt = 0;

		data = opt;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(skillId);
		buffer.writeInt(level);
		buffer.writeFloat(xp);
		buffer.writeInt(data);
	}

	public static SkillDataPacket decode(PacketBuffer buffer) {
		return new SkillDataPacket(buffer.readInt(), buffer.readInt(), buffer.readFloat(), buffer.readInt());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		Skills skill = Skills.getById(skillId);

		if (skill != null) {
			AdventGuiTabPlayer.setSkillData(skill, xp, level, data);
		}
		else {
			Logging.logMessage(Level.WARN, "Error trying to set skill data in client skills holder, skipping");
		}

		context.get().setPacketHandled(true);
	}
}
