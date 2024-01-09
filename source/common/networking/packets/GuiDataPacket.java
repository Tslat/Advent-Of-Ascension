package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.container.FrameBenchContainer;

public record GuiDataPacket(Type type, String data) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("gui_data");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeEnum(this.type);
		buffer.writeUtf(this.data);
	}

	public static GuiDataPacket decode(FriendlyByteBuf buffer) {
		return new GuiDataPacket(buffer.readEnum(Type.class), buffer.readUtf());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		switch (type) {
			case FRAME_BENCH_SELECTION:
				if (context.player().get().containerMenu instanceof FrameBenchContainer frameBench)
					frameBench.changeSelection(this.data);
		}
	}

	public enum Type {
		FRAME_BENCH_SELECTION;
	}
}
