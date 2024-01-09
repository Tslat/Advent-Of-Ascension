package net.tslat.aoa3.common.networking.packets.setup;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ConfigurationPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.configtask.SkillReqsHandshakeTask;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;

import java.util.List;
import java.util.Map;

public record SkillReqLoginSyncPacket(Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> skillReqData, boolean ack) implements AoAPacket<ConfigurationPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("skill_reqs_sync");

	public SkillReqLoginSyncPacket(Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> skillReqData) {
		this(skillReqData, false);
	}

	private SkillReqLoginSyncPacket(boolean isAck) {
		this(null, true);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeBoolean(this.ack);

		if (!this.ack) {
			buffer.writeMap(this.skillReqData, FriendlyByteBuf::writeResourceLocation, (buf1, typeMap) ->
					buf1.writeMap(typeMap, FriendlyByteBuf::writeUtf, (buf2, list) ->
							buf2.writeCollection(list, (buf3, pair) -> {
								buf3.writeResourceLocation(pair.getFirst());
								buffer.writeVarInt(pair.getSecond());
							})));
		}
	}

	public static SkillReqLoginSyncPacket decode(FriendlyByteBuf buffer) {
		final boolean ack = buffer.readBoolean();

		return new SkillReqLoginSyncPacket(
				!ack ? buffer.readMap(FriendlyByteBuf::readResourceLocation, buf1 ->
						buf1.readMap(FriendlyByteBuf::readUtf, buf2 ->
								buf2.readList(buf3 -> Pair.of(buf3.readResourceLocation(), buf3.readVarInt())))) : null,
				ack);
	}

	@Override
	public void receiveMessage(ConfigurationPayloadContext context) {
		if (this.ack) {
			context.taskCompletedHandler().onTaskCompleted(SkillReqsHandshakeTask.TYPE);
		}
		else {
			AoASkillReqReloadListener.parseAll(this.skillReqData);
			context.replyHandler().send(new SkillReqLoginSyncPacket(true));
		}
	}
}
