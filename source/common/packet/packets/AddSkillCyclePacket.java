package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.function.Supplier;

public class AddSkillCyclePacket implements AoAPacket {
	private final AoASkill skill;

	public AddSkillCyclePacket(ResourceLocation skillId) {
		this.skill = AoASkills.getSkill(skillId);
	}

	public AddSkillCyclePacket(AoASkill skill) {
		this.skill = skill;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(skill == null ? new ResourceLocation("", "") : AoARegistries.AOA_SKILLS.getId(skill));
	}

	public static AddSkillCyclePacket decode(FriendlyByteBuf buffer) {
		return new AddSkillCyclePacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (skill != null)
			context.get().enqueueWork(() -> PlayerUtil.getAdventPlayer(context.get().getSender()).getSkill(skill).addCycle());

		context.get().setPacketHandled(true);
	}
}
