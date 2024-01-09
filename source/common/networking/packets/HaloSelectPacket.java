package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.player.halo.HaloTypes;
import net.tslat.aoa3.player.halo.PlayerHaloManager;
import org.apache.logging.log4j.Level;

public record HaloSelectPacket(HaloTypes.Selectable selected) implements AoAPacket<IPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("halo_select");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeEnum(this.selected);
	}

	public static HaloSelectPacket decode(FriendlyByteBuf buffer) {
		return new HaloSelectPacket(buffer.readEnum(HaloTypes.Selectable.class));
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		context.player().ifPresentOrElse(
				pl -> PlayerHaloManager.updateSelectedHalo(pl.getGameProfile().getId(), this.selected.toBaseType(), true),
				() -> Logging.logMessage(Level.WARN, "No sender assigned to received " + getClass().getSimpleName() + ", skipping"));

	}
}
