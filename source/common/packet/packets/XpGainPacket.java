package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;
import net.tslat.aoa3.client.gui.hud.XpParticlesRenderer;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.constant.Skills;

import java.util.function.Supplier;

public class XpGainPacket implements AoAPacket {
	private final int skillId;
	private final float xp;
	private final boolean levelUp;

	public XpGainPacket(final int skill, final float xp, boolean isLevelUp) {
		this.skillId = skill;
		this.xp = xp;
		this.levelUp = isLevelUp;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(skillId);
		buffer.writeFloat(xp);
		buffer.writeBoolean(levelUp);
	}

	public static XpGainPacket decode(PacketBuffer buffer) {
		return new XpGainPacket(buffer.readInt(), buffer.readFloat(), buffer.readBoolean());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (AoAConfig.CLIENT.showXpParticles.get()) {
			Skills skill = Skills.getById(skillId);

			if (AdventGuiTabPlayer.getSkillLevel(skill) < 100 || AoAConfig.CLIENT.showVanityLevels.get())
				XpParticlesRenderer.addXpParticle(skill, xp, levelUp);
		}

		context.get().setPacketHandled(true);
	}
}
