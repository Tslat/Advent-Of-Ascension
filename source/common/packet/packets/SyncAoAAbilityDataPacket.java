package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.function.Supplier;

public class SyncAoAAbilityDataPacket implements AoAPacket {
	private final ResourceLocation skillId;
	private final String abilityUniqueId;
	private final String data;

	private SyncAoAAbilityDataPacket(ResourceLocation skillId, String abilityUniqueId, String data) {
		this.skillId = skillId;
		this.abilityUniqueId = abilityUniqueId;
		this.data = data;
	}

	public SyncAoAAbilityDataPacket(AoAAbility.Instance ability, String data) {
		this.skillId = ability.getSkill().type().getRegistryName();
		this.abilityUniqueId = ability.getUniqueIdentifier();
		this.data = data;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(skillId);
		buffer.writeUtf(abilityUniqueId);
		buffer.writeUtf(data);
	}

	public static SyncAoAAbilityDataPacket decode(FriendlyByteBuf buffer) {
		return new SyncAoAAbilityDataPacket(buffer.readResourceLocation(), buffer.readUtf(), buffer.readUtf());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			AoASkill.Instance skillInstance = PlayerUtil.getAdventPlayer(context.get().getSender()).getSkill(AoASkills.getSkill(skillId));
			AoAAbility.Instance abilityInstance = skillInstance.getAbilityMap().get(abilityUniqueId);

			abilityInstance.receiveInteractionDataFromClient(data);
		});

		context.get().setPacketHandled(true);
	}
}
