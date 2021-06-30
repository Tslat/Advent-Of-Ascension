package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.config.AoAConfig;

import java.util.function.Supplier;

public class CommonConfigSyncPacket implements AoAPacket {
	private final boolean skillsEnabled;
	private final boolean resourcesEnabled;

	public CommonConfigSyncPacket(boolean skillsEnabled, boolean resourcesEnabled) {
		this.skillsEnabled = skillsEnabled;
		this.resourcesEnabled = resourcesEnabled;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeBoolean(skillsEnabled);
		buffer.writeBoolean(resourcesEnabled);
	}

	public static CommonConfigSyncPacket decode(PacketBuffer buffer) {
		return new CommonConfigSyncPacket(buffer.readBoolean(), buffer.readBoolean());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		AoAConfig.COMMON.skillsEnabled.set(skillsEnabled);
		AoAConfig.COMMON.resourcesEnabled.set(resourcesEnabled);

		context.get().setPacketHandled(true);
	}
}
