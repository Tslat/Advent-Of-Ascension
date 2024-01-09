package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;

public record ToastPopupPacket(ToastPopupType type, Object subject, Object value) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("toast_trigger");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public ToastPopupPacket(final AoASkill skill, final int levelReq) {
		this(ToastPopupType.SKILL_REQUIREMENT, levelReq, AoARegistries.AOA_SKILLS.getId(skill));
	}

	public ToastPopupPacket(final AoAResource resource, final float amount) {
		this(ToastPopupType.RESOURCE_REQUIREMENT, amount, AoARegistries.AOA_RESOURCES.getId(resource));
	}

	public ToastPopupPacket(final AoASkill skill, final AoAAbility ability) {
		this(ToastPopupType.ABILITY_UNLOCK, AoARegistries.AOA_ABILITIES.getId(ability), AoARegistries.AOA_SKILLS.getId(skill));
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeUtf(this.type.toString());

		switch (this.type) {
			case SKILL_REQUIREMENT:
				buffer.writeUtf(this.subject.toString());
				buffer.writeInt((Integer)this.value);
				break;
			case RESOURCE_REQUIREMENT:
				buffer.writeUtf(this.subject.toString());
				buffer.writeFloat((Float)this.value);
				break;
			case ABILITY_UNLOCK:
				buffer.writeUtf(this.subject.toString());
				buffer.writeUtf(this.value.toString());
				break;
		}
	}

	public static ToastPopupPacket decode(FriendlyByteBuf buffer) {
		ToastPopupType type = ToastPopupType.valueOf(buffer.readUtf(32767));

		return switch (type) {
			case SKILL_REQUIREMENT -> new ToastPopupPacket(AoASkills.getSkill(new ResourceLocation(buffer.readUtf(32767))), buffer.readInt());
			case RESOURCE_REQUIREMENT -> new ToastPopupPacket(AoAResources.getResource(new ResourceLocation(buffer.readUtf(32767))), buffer.readFloat());
			case ABILITY_UNLOCK -> new ToastPopupPacket(AoASkills.getSkill(new ResourceLocation(buffer.readUtf(32767))), AoAAbilities.getAbility(new ResourceLocation(buffer.readUtf(32767))));
		};
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(() -> ClientOperations.addToast(this.type, this.subject, this.value));
	}

	public enum ToastPopupType {
		SKILL_REQUIREMENT,
		RESOURCE_REQUIREMENT,
		ABILITY_UNLOCK
	}
}
