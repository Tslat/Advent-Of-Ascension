package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.config.AoAConfig;

import java.util.function.Supplier;

public class CommonConfigSyncPacket implements AoAPacket {
	private final boolean skillsEnabled;
	private final boolean resourcesEnabled;
	private final boolean hardcoreMode;

	public CommonConfigSyncPacket(boolean skillsEnabled, boolean resourcesEnabled, boolean hardcoreMode) {
		this.skillsEnabled = skillsEnabled;
		this.resourcesEnabled = resourcesEnabled;
		this.hardcoreMode = hardcoreMode;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeBoolean(skillsEnabled);
		buffer.writeBoolean(resourcesEnabled);
		buffer.writeBoolean(hardcoreMode);
	}

	public static CommonConfigSyncPacket decode(PacketBuffer buffer) {
		return new CommonConfigSyncPacket(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		AoAConfig.COMMON.skillsEnabled.set(skillsEnabled);
		AoAConfig.COMMON.resourcesEnabled.set(resourcesEnabled);
		AoAConfig.COMMON.hardcoreMode.set(hardcoreMode);

		context.get().setPacketHandled(true);
	}
}
