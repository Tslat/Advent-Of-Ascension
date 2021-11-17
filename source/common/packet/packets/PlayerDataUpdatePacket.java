package net.tslat.aoa3.common.packet.packets;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.player.ClientPlayerDataManager;

import java.util.function.Supplier;

public class PlayerDataUpdatePacket implements AoAPacket {
	private final CompoundNBT data;

	public PlayerDataUpdatePacket(final CompoundNBT data) {
		this.data = data;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeNbt(data);
	}

	public static PlayerDataUpdatePacket decode(PacketBuffer buffer) {
		return new PlayerDataUpdatePacket(buffer.readNbt());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ClientPlayerDataManager.updateData(data);

		context.get().setPacketHandled(true);
	}
}
