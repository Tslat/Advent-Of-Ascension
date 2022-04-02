package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.function.Supplier;

public class ToggleAoAAbilityPacket implements AoAPacket {
	private final AoASkill skill;
	private final String abilityUniqueId;

	public ToggleAoAAbilityPacket(ResourceLocation skillId, String abilityId) {
		this.skill = AoASkills.getSkill(skillId);
		this.abilityUniqueId = abilityId;
	}

	public ToggleAoAAbilityPacket(AoASkill skill, AoAAbility.Instance ability) {
		this.skill = skill;
		this.abilityUniqueId = ability.getUniqueIdentifier();
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(skill == null ? new ResourceLocation("", "") : skill.getRegistryName());
		buffer.writeUtf(abilityUniqueId);
	}

	public static ToggleAoAAbilityPacket decode(FriendlyByteBuf buffer) {
		return new ToggleAoAAbilityPacket(buffer.readResourceLocation(), buffer.readUtf());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (skill != null) {
			context.get().enqueueWork(() -> {
				AoASkill.Instance skillInstance = PlayerUtil.getAdventPlayer(context.get().getSender()).getSkill(skill);
				AoAAbility.Instance abilityInstance = skillInstance.getAbilityMap().get(abilityUniqueId);

				if (abilityInstance.getListenerState() == AoAPlayerEventListener.ListenerState.ACTIVE) {
					abilityInstance.disable(AoAPlayerEventListener.ListenerState.MANUALLY_DISABLED, false);
				}
				else if (abilityInstance.getListenerState() == AoAPlayerEventListener.ListenerState.MANUALLY_DISABLED) {
					abilityInstance.reenable(false);
				}
			});
		}

		context.get().setPacketHandled(true);
	}
}
