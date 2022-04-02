package net.tslat.aoa3.common.packet.packets;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SkillRequirementDataPacket implements AoAPacket {
	private final Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> requirementData;

	public SkillRequirementDataPacket(Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> requirementData) {
		this.requirementData = requirementData;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeVarInt(requirementData.size());

		for (Map.Entry<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> entry : requirementData.entrySet()) {
			Map<String, List<Pair<ResourceLocation, Integer>>> reqEntry = entry.getValue();

			buffer.writeResourceLocation(entry.getKey());
			buffer.writeVarInt(reqEntry.size());

			for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> reqTypeEntry : reqEntry.entrySet()) {
				List<Pair<ResourceLocation, Integer>> reqs = reqTypeEntry.getValue();

				buffer.writeUtf(reqTypeEntry.getKey());
				buffer.writeVarInt(reqs.size());

				for (Pair<ResourceLocation, Integer> req : reqs) {
					buffer.writeResourceLocation(req.getFirst());
					buffer.writeVarInt(req.getSecond());
				}
			}
		}
	}

	public static SkillRequirementDataPacket decode(FriendlyByteBuf buffer) {
		int size = buffer.readVarInt();
		Map<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>> data = new HashMap<ResourceLocation, Map<String, List<Pair<ResourceLocation, Integer>>>>(size);

		for (int i = 0; i < size; i++) {
			ResourceLocation itemId = buffer.readResourceLocation();
			int reqTypesSize = buffer.readVarInt();
			Map<String, List<Pair<ResourceLocation, Integer>>> reqTypesMap = new HashMap<String, List<Pair<ResourceLocation, Integer>>>(reqTypesSize);

			for (int j = 0; j < reqTypesSize; j++) {
				String typeId = buffer.readUtf();
				int typeSize = buffer.readVarInt();
				List<Pair<ResourceLocation, Integer>> reqs = new ArrayList<Pair<ResourceLocation, Integer>>(typeSize);

				for (int k = 0; k < typeSize; k++) {
					reqs.add(Pair.of(buffer.readResourceLocation(), buffer.readVarInt()));
				}

				reqTypesMap.put(typeId, reqs);
			}

			data.put(itemId, reqTypesMap);
		}

		return new SkillRequirementDataPacket(data);
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> AoASkillReqReloadListener.parseAll(requirementData));
		context.get().setPacketHandled(true);
	}
}
