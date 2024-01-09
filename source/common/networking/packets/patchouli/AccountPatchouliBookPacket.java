package net.tslat.aoa3.common.networking.packets.patchouli;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;

public record AccountPatchouliBookPacket(ResourceLocation book) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("account_patchouli_book");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(book);
	}

	public static AccountPatchouliBookPacket decode(FriendlyByteBuf buffer) {
		return new AccountPatchouliBookPacket(buffer.readResourceLocation());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		((ServerPlayerDataManager)PlayerUtil.getAdventPlayer(context.player().get())).addPatchouliBook(book);
	}
}
