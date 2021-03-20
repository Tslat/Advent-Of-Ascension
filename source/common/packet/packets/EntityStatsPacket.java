package net.tslat.aoa3.common.packet.packets;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.data.server.EntityStatsManager;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityStatsPacket implements AoAPacket {
	private HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> entityAttributesMap;

	public EntityStatsPacket(HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> entityAttributesMap) {
		this.entityAttributesMap = entityAttributesMap;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(entityAttributesMap.size());

		for (Map.Entry<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> entry : entityAttributesMap.entrySet()) {
			buffer.writeUtf(entry.getKey().getRegistryName().toString());
			buffer.writeVarInt(entry.getValue().size());

			for (Map.Entry<Attribute, Double> attributeEntry : entry.getValue().entrySet()) {
				buffer.writeUtf(attributeEntry.getKey().getRegistryName().toString());
				buffer.writeDouble(attributeEntry.getValue());
			}
		}
	}

	public static EntityStatsPacket decode(PacketBuffer buffer) {
		HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> attributeMap = new HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>>();
		int mapSize = buffer.readInt();

		for (int i = 0; i < mapSize; i++) {
			ImmutableMap.Builder<Attribute, Double> entityAttributeMap = ImmutableMap.builder();
			EntityType<? extends LivingEntity> entityType;

			try {
				String entityTypeString = buffer.readUtf();
				EntityType<?> registryEntityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entityTypeString));

				if (registryEntityType == null)
					throw new IllegalStateException("Unknown entity type data received from server: '" + entityTypeString + "'. This should never happen.");

				try {
					entityType = (EntityType<? extends LivingEntity>)registryEntityType;
				}
				catch (ClassCastException ex) {
					Logging.logMessage(Level.ERROR, "Received invalid entity type from server: '" + entityTypeString + "'. Entity is not LivingEntity. This should never happen.");

					throw ex;
				}

				int attributeMapSize = buffer.readVarInt();

				for (int j = 0; j < attributeMapSize; j++) {
					String attributeId = buffer.readUtf();
					Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attributeId));
					double attributeValue = buffer.readDouble();

					if (attribute == null)
						throw new IllegalStateException("Received invalid attribute type from server: '" + attributeId + "'. This should never happen.");

					entityAttributeMap.put(attribute, attributeValue);
				}
			}
			catch (Exception ex) {
				Logging.logMessage(Level.ERROR, "Failed to interpret entity stats data from server. This is really bad.", ex);

				return new EntityStatsPacket(attributeMap);
			}

			attributeMap.put(entityType, entityAttributeMap.build());
		}

		return new EntityStatsPacket(attributeMap);
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		EntityStatsManager.applyToEntities(entityAttributesMap);

		context.get().setPacketHandled(true);
	}
}
