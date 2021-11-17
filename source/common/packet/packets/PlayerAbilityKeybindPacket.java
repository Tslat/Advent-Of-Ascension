package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.event.AoAPlayerEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PlayerAbilityKeybindPacket implements AoAPacket {
	private final List<String> abilities;

	public PlayerAbilityKeybindPacket(List<String> abilities) {
		this.abilities = abilities;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(abilities.size());

		for (String st : abilities) {
			buffer.writeUtf(st);
		}
	}

	public static PlayerAbilityKeybindPacket decode(PacketBuffer buffer) {
		int size = buffer.readInt();
		ArrayList<String> abilities = new ArrayList<String>(size);

		for (int i = 0; i < size; i++) {
			abilities.add(buffer.readUtf());
		}

		return new PlayerAbilityKeybindPacket(abilities);
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> AoAPlayerEvents.onKeyPress(context.get().getSender(), abilities));
		context.get().setPacketHandled(true);
	}
}
