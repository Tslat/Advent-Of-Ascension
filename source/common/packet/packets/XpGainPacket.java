package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.client.gui.hud.XpParticlesRenderer;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.function.Supplier;

public class XpGainPacket implements AoAPacket {
	private final ResourceLocation skillId;
	private final float xp;
	private final boolean levelUp;

	public XpGainPacket(final ResourceLocation skillId, final float xp, boolean isLevelUp) {
		this.skillId = skillId;
		this.xp = xp;
		this.levelUp = isLevelUp;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(skillId);
		buffer.writeFloat(xp);
		buffer.writeBoolean(levelUp);
	}

	public static XpGainPacket decode(FriendlyByteBuf buffer) {
		return new XpGainPacket(buffer.readResourceLocation(), buffer.readFloat(), buffer.readBoolean());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (AoAConfig.CLIENT.showXpParticles.get()) {
			AoASkill skill = AoASkills.getSkill(skillId);

			if (skill != null)
				XpParticlesRenderer.addXpParticle(skill, xp, levelUp);
		}

		context.get().setPacketHandled(true);
	}
}
