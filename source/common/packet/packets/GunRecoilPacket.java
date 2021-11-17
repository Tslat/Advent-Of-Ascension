package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.ClientOperations;

import java.util.function.Supplier;

public class GunRecoilPacket implements AoAPacket {
	private final float recoilAmount;
	private final int firingTime;

	public GunRecoilPacket(final float recoil, final int firingDelay) {
		recoilAmount = recoil;
		firingTime = firingDelay;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeFloat(recoilAmount);
		buffer.writeInt(firingTime);
	}

	public static GunRecoilPacket decode(PacketBuffer buffer) {
		return new GunRecoilPacket(buffer.readFloat(), buffer.readInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ClientOperations.addRecoil(recoilAmount, firingTime);

		context.get().setPacketHandled(true);
	}
}
