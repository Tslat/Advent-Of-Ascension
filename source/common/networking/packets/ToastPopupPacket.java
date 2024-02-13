package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.toast.CustomToastData;

public record ToastPopupPacket(CustomToastData<?, ?> toastData) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("toast_trigger");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeId(AoARegistries.CUSTOM_TOAST_TYPES, this.toastData.type());
		this.toastData.write(buffer);
	}

	public static ToastPopupPacket decode(FriendlyByteBuf buffer) {
		return new ToastPopupPacket(buffer.readById(AoARegistries.CUSTOM_TOAST_TYPES).create(buffer));
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(this.toastData::handleOnClient);
	}
}
