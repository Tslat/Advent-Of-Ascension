package net.tslat.aoa3.common.networking.packets.adventplayer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RegistryUtil;

public record ToggleAoAAbilityPacket(AoASkill skill, String abilityUniqueId) implements AoAPacket<IPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("toggle_aoa_ability");

	public ToggleAoAAbilityPacket(AoASkill skill, AoAAbility.Instance ability) {
		this(skill, ability.getUniqueIdentifier());
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(RegistryUtil.getId(this.skill));
		buffer.writeUtf(this.abilityUniqueId);
	}

	public static ToggleAoAAbilityPacket decode(FriendlyByteBuf buffer) {
		return new ToggleAoAAbilityPacket(AoARegistries.AOA_SKILLS.getEntry(buffer.readResourceLocation()), buffer.readUtf());
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		context.workHandler().execute(() -> {
			AoASkill.Instance skillInstance = PlayerUtil.getAdventPlayer(context.player().get()).getSkill(skill);
			AoAAbility.Instance abilityInstance = skillInstance.getAbilityMap().get(this.abilityUniqueId);

			if (abilityInstance.getListenerState() == AoAPlayerEventListener.ListenerState.ACTIVE) {
				abilityInstance.disable(AoAPlayerEventListener.ListenerState.MANUALLY_DISABLED, false);
			}
			else if (abilityInstance.getListenerState() == AoAPlayerEventListener.ListenerState.MANUALLY_DISABLED) {
				abilityInstance.reenable(false);
			}
		});
	}
}
