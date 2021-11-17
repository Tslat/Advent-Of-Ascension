package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
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
	public void encode(PacketBuffer buffer) {
		buffer.writeResourceLocation(skill == null ? new ResourceLocation("", "") : skill.getRegistryName());
	}

	public static AddSkillCyclePacket decode(PacketBuffer buffer) {
		return new AddSkillCyclePacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (skill != null)
			context.get().enqueueWork(() -> PlayerUtil.getAdventPlayer(context.get().getSender()).getSkill(skill).addCycle());

		context.get().setPacketHandled(true);
	}
}
