package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RegistryUtil;

public record AddSkillCyclePacket(AoASkill skill) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("add_skill_cycle");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(RegistryUtil.getId(this.skill));
	}

	public static AddSkillCyclePacket decode(FriendlyByteBuf buffer) {
		return new AddSkillCyclePacket(AoARegistries.AOA_SKILLS.getEntry(buffer.readResourceLocation()));
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(() -> PlayerUtil.getAdventPlayer(context.player().get()).getSkill(skill).addCycle());
	}
}
