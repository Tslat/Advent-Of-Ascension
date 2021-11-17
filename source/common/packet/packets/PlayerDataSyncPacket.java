package net.tslat.aoa3.common.packet.packets;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.player.ClientPlayerDataManager;

import java.util.function.Supplier;

public class PlayerDataSyncPacket implements AoAPacket {
	private final CompoundNBT data;

	public PlayerDataSyncPacket(final CompoundNBT data) {
		this.data = data;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeNbt(data);
	}

	public static PlayerDataSyncPacket decode(PacketBuffer buffer) {
		return new PlayerDataSyncPacket(buffer.readNbt());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ClientPlayerDataManager.loadFromNbt(data);

		context.get().setPacketHandled(true);
	}
}
