package net.tslat.aoa3.data.server;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.EntityStatsPacket;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

public class EntityStatsManager extends JsonReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> ATTRIBUTES = new HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>>();

	public EntityStatsManager() {
		super(GSON, "entity_stats");
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> fileMap, IResourceManager resourceManager, IProfiler profiler) {
		ATTRIBUTES.clear();
		// TODO Check this
		for (Map.Entry<ResourceLocation, JsonElement> entry : fileMap.entrySet()) {
			ImmutableMap.Builder<Attribute, Double> attributeMap = ImmutableMap.builder();
			EntityType<? extends LivingEntity> entity;

			try {
				JsonObject json = entry.getValue().getAsJsonObject();
				String entityId = entry.getKey().getPath();

				if (entityId.contains("/"))
					entityId = entityId.substring(entityId.lastIndexOf('/') + 1);

				EntityType<?> registryEntity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entityId));

				if (registryEntity == null)
					throw new IllegalArgumentException("Entity with id: '" + entityId + "' does not exist.");

				try {
					entity = (EntityType<? extends LivingEntity>)registryEntity;
				}
				catch (ClassCastException ex) {
					Logging.logMessage(Level.ERROR, "Provided entity type: '" + entityId + "' is not a LivingEntity. Unable to apply attribute values");

					throw ex;
				}

				for (Map.Entry<String, JsonElement> element : json.entrySet()) {
					Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(element.getKey()));
					double value = element.getValue().getAsDouble();

					if (attribute == null)
						throw new IllegalArgumentException("Attribute with id: '" + element.getKey() + "' does not exist.");

					double newValue = attribute.sanitizeValue(value);

					if (newValue != value) {
						double minValue = attribute.sanitizeValue(Double.MIN_VALUE);
						double maxValue = attribute.sanitizeValue(Double.MAX_VALUE);

						Logging.logMessage(Level.WARN, "Supplied value (" + value + ") for attribute '" + element.getKey() + "' is outside of valid range (" + minValue + "-" + maxValue + "). Clamping value for entity: '" + entry.getKey().getPath());
					}

					attributeMap.put(attribute, value);
				}
			}
			catch (Exception ex) {
				Logging.logMessage(Level.ERROR, "Couldn't parse entity stats file: " + entry.getKey().toString() + ". Entity will be unusable", ex);

				continue;
			}

			ATTRIBUTES.put(entity, attributeMap.build());
		}

		if (ServerLifecycleHooks.getCurrentServer() != null) {
			for (ServerPlayerEntity pl : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
				syncWithClient(pl);
			}
		}
	}

	public static void syncWithClient(ServerPlayerEntity pl) {
		AoAPackets.messagePlayer(pl, new EntityStatsPacket(ATTRIBUTES));
	}

	public static void applyToEntities(HashMap<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> attributeMap) {
		Logging.logMessage(Level.INFO, "Reloading AoA entity stats...");

		for (Map.Entry<EntityType<? extends LivingEntity>, ImmutableMap<Attribute, Double>> entry : attributeMap.entrySet()) {
			final AttributeModifierMap existingAttributes = GlobalEntityTypeAttributes.getSupplier(entry.getKey());
			final AttributeModifierMap.MutableAttribute mapBuilder = AttributeModifierMap.builder();

			if (existingAttributes != null) {
				for (Map.Entry<Attribute, ModifiableAttributeInstance> attribute : existingAttributes.instances.entrySet()) {
					mapBuilder.add(attribute.getKey(), attribute.getValue().getBaseValue());
				}
			}

			for (Map.Entry<Attribute, Double> attribute : entry.getValue().entrySet()) {
				mapBuilder.add(attribute.getKey(), attribute.getValue());
			}

			GlobalEntityTypeAttributes.put(entry.getKey(), mapBuilder.build());
		}
	}
}
