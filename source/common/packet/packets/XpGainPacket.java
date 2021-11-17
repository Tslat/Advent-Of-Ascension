package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.RegistryManager;
import net.tslat.aoa3.client.gui.hud.XpParticlesRenderer;
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
	public void encode(PacketBuffer buffer) {
		buffer.writeResourceLocation(skillId);
		buffer.writeFloat(xp);
		buffer.writeBoolean(levelUp);
	}

	public static XpGainPacket decode(PacketBuffer buffer) {
		return new XpGainPacket(buffer.readResourceLocation(), buffer.readFloat(), buffer.readBoolean());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (AoAConfig.CLIENT.showXpParticles.get()) {
			AoASkill skill = RegistryManager.ACTIVE.getRegistry(AoASkill.class).getValue(skillId);

			if (skill != null)
				XpParticlesRenderer.addXpParticle(skill, xp, levelUp);
		}

		context.get().setPacketHandled(true);
	}
}
