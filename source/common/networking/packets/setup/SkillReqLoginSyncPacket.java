package net.tslat.aoa3.common.networking.packets.setup;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.configtask.SkillReqsHandshakeTask;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.tme.api.util.StreamCodecUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record SkillReqLoginSyncPacket(Optional<Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>>> skillReqData) implements AoAPacket {
	public static final Type<SkillReqLoginSyncPacket> TYPE = new Type<>(AdventOfAscension.id("skill_reqs_sync"));
	public static final StreamCodec<FriendlyByteBuf, SkillReqLoginSyncPacket> CODEC = StreamCodec.composite(
			ByteBufCodecs.optional(ByteBufCodecs.map(Object2ObjectOpenHashMap::new,
					ResourceLocation.STREAM_CODEC, ByteBufCodecs.map(Object2ObjectOpenHashMap::new,
																	 ByteBufCodecs.STRING_UTF8,
																	 StreamCodecUtil.pair(ObjectIntPair::of, ResourceLocation.STREAM_CODEC, ByteBufCodecs.VAR_INT).apply(ByteBufCodecs.list())))), SkillReqLoginSyncPacket::skillReqData,
			SkillReqLoginSyncPacket::new);

	public SkillReqLoginSyncPacket(Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> skillReqData) {
		this(Optional.of(skillReqData));
	}

	private SkillReqLoginSyncPacket() {
		this(Optional.empty());
	}

	@Override
	public Type<? extends SkillReqLoginSyncPacket> type() {
		return TYPE;
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		this.skillReqData.ifPresentOrElse(map -> {
			AoASkillReqReloadListener.parseAll(map);
			context.reply(new SkillReqLoginSyncPacket());
		}, () -> context.finishCurrentTask(SkillReqsHandshakeTask.TYPE));
	}
}
