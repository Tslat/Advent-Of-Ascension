package net.tslat.aoa3.common.networking.configtask;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.configuration.ICustomConfigurationTask;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.setup.SkillReqLoginSyncPacket;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;

import java.util.function.Consumer;

public record SkillReqsHandshakeTask(ServerConfigurationPacketListener listener) implements ICustomConfigurationTask {
	private static final ResourceLocation ID = AdventOfAscension.id("skill_reqs_sync");
	public static final Type TYPE = new Type(ID);

	@Override
	public Type type() {
		return TYPE;
	}

	@Override
	public void run(Consumer<CustomPacketPayload> sender) {
		if (!this.listener.isVanillaConnection())
			sender.accept(new SkillReqLoginSyncPacket(AoASkillReqReloadListener.getParsedReqData()));
	}
}