package net.tslat.aoa3.common.networking.packets.adventplayer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RegistryUtil;

public record SyncAoAAbilityDataPacket(AoASkill skill, String abilityUniqueId, String data) implements AoAPacket<IPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("sync_aoa_ability_data");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public SyncAoAAbilityDataPacket(AoAAbility.Instance ability, String data) {
		this(ability.getSkill().type(), ability.getUniqueIdentifier(), data);
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(RegistryUtil.getId(this.skill));
		buffer.writeUtf(this.abilityUniqueId);
		buffer.writeUtf(this.data);
	}

	public static SyncAoAAbilityDataPacket decode(FriendlyByteBuf buffer) {
		return new SyncAoAAbilityDataPacket(AoASkills.getSkill(buffer.readResourceLocation()), buffer.readUtf(), buffer.readUtf());
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		context.workHandler().execute(() -> {
			AoASkill.Instance skillInstance = PlayerUtil.getAdventPlayer(context.player().get()).getSkill(this.skill);
			AoAAbility.Instance abilityInstance = skillInstance.getAbilityMap().get(this.abilityUniqueId);

			abilityInstance.receiveInteractionDataFromClient(this.data);
		});
	}
}
