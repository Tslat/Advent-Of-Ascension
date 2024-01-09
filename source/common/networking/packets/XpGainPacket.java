package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.hud.XpParticlesRenderer;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;

public record XpGainPacket(ResourceLocation skill, float xp, boolean levelUp) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("xp_gain");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(this.skill);
		buffer.writeFloat(this.xp);
		buffer.writeBoolean(this.levelUp);
	}

	public static XpGainPacket decode(FriendlyByteBuf buffer) {
		return new XpGainPacket(buffer.readResourceLocation(), buffer.readFloat(), buffer.readBoolean());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		if (AoAConfigs.CLIENT.showXpParticles.get()) {
			AoASkill skill = AoASkills.getSkill(this.skill);

			if (skill != null)
				XpParticlesRenderer.addXpParticle(skill, this.xp, this.levelUp);
		}
	}
}
