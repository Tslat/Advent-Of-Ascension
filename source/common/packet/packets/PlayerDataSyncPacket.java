package net.tslat.aoa3.common.packet.packets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.player.ClientPlayerDataManager;

import java.util.function.Supplier;

public class PlayerDataSyncPacket implements AoAPacket {
	private final CompoundTag data;

	public PlayerDataSyncPacket(final CompoundTag data) {
		this.data = data;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeNbt(data);
	}

	public static PlayerDataSyncPacket decode(FriendlyByteBuf buffer) {
		return new PlayerDataSyncPacket(buffer.readNbt());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ClientPlayerDataManager.get().loadFromNbt(data);

		context.get().setPacketHandled(true);
	}
}
