package net.tslat.aoa3.utils;

import com.google.common.collect.HashMultimap;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.FluidsRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.utils.skills.HunterUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModUtil {
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static final HashSet<Runnable> scheduledTasks = new HashSet<Runnable>();
	private static final HashMultimap<Integer, Runnable> scheduledSynchTasks = HashMultimap.<Integer, Runnable>create();

	private static final Gson gson = (new GsonBuilder()).registerTypeAdapter(RandomValueRange.class, new RandomValueRange.Serializer()).registerTypeAdapter(LootPool.class, new LootPool.Serializer()).registerTypeAdapter(LootTable.class, new LootTable.Serializer()).registerTypeHierarchyAdapter(LootEntry.class, new LootEntry.Serializer()).registerTypeHierarchyAdapter(LootFunction.class, new LootFunctionManager.Serializer()).registerTypeHierarchyAdapter(LootCondition.class, new LootConditionManager.Serializer()).registerTypeHierarchyAdapter(LootContext.EntityTarget.class, new LootContext.EntityTarget.Serializer()).create();

	public static void scrapeRegistries() {
		OreDictUtil.addDefaultEntries();
		AdventOfAscension.logOptionalMessage("Applying hunter statuses");

		for (String entry : ConfigurationUtil.EntityConfig.hunterEntities.hunterEntities) {
			String[] entryParts = entry.split(" ");

			if (entryParts.length < 3) {
				AdventOfAscension.logOptionalMessage("Invalid format for hunter entity entry: \"" + entry + "\"");

				continue;
			}

			try {
				String registryName = entryParts[0];
				int levelReq = Math.min(100, Integer.parseInt(entryParts[1].replace("lvl:", "")));
				float hunterXp = Math.max(0, Float.parseFloat(entryParts[2].replace("xp:", "")));
				EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(registryName));

				if (entityEntry == null) {
					AdventOfAscension.logOptionalMessage("Unable to find entity registered as: \"" + registryName + "\"");

					continue;
				}

				Class<? extends Entity> entityClass = entityEntry.getEntityClass();

				if (EntityLivingBase.class.isAssignableFrom(entityClass)) {
					HunterUtil.registerHunterCreature((Class<? extends EntityLivingBase>)entityClass, levelReq, hunterXp);
					AdventOfAscension.logOptionalMessage("Registered hunter entity: " + registryName + " , Lvl: " + levelReq + ", Xp: " + hunterXp);
				}
				else {
					AdventOfAscension.logOptionalMessage("Entity \"" + registryName + "\" is does not extend EntityLivingBase. Hunter entities must be alive to apply");
				}
			}
			catch (NumberFormatException ex) {
				AdventOfAscension.logOptionalMessage("Invalid format for value: \"" + entryParts[1] + "\"", ex);
			}
		}

		AdventOfAscension.logOptionalMessage("Patching entity stats overrides");

		for (String entry : ConfigurationUtil.EntityConfig.entityStats.entityStats) {

		}
	}

	public static void modConstructTasks() {
		FluidRegistry.enableUniversalBucket();
	}

	public static void preInitTasks() {
		patchMaxHP();
		FluidsRegister.registerFluids();
	}

	private static void patchMaxHP() {
		AdventOfAscension.logOptionalMessage("Patching out vanilla max HP attribute");
		ObfuscationReflectionHelper.setPrivateValue((Class)RangedAttribute.class, SharedMonsterAttributes.MAX_HEALTH, Double.MAX_VALUE, 1);
	}

	public static void scheduleRequiredAsyncTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
		scheduledTasks.add(run);
	}

	public static void scheduleSyncronisedTask(Runnable run, int ticks) {
		scheduledSynchTasks.put(GlobalEvents.tick + ticks, run);
	}

	public static void scheduleAsyncTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
	}

	public static void serverShutdownTasks() {
		for (Runnable task : scheduledTasks) {
			task.run();
		}

		AdventOfAscension.proxy.worldShutdown();
	}

	public static void handleSyncScheduledTasks(int tick) {
		if (scheduledSynchTasks.containsKey(tick)) {
			Iterator<Runnable> tasks = scheduledSynchTasks.get(tick).iterator();

			while (tasks.hasNext()) {
				tasks.next().run();
				tasks.remove();
			}
		}
	}

	public static Advancement getAdvancement(String resourcePath) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getAdvancementManager().getAdvancement(new ResourceLocation("aoa3", resourcePath));
	}

	public static boolean completeAdvancement(EntityPlayerMP player, String resourcePath, String criterion) {
		Advancement adv = getAdvancement(resourcePath);

		if (adv != null)
			return player.getAdvancements().grantCriterion(adv, criterion);

		return false;
	}

	public static boolean checkAdvancementCompleted(EntityPlayerMP player, String resourcePath) {
		Advancement adv = getAdvancement(resourcePath);

		if (adv != null)
			return player.getAdvancements().getProgress(adv).isDone();

		return false;
	}

	public static LootPool generateLootPoolFromResourceLocation(String poolName, ResourceLocation resource) {
		return new LootPool(new LootEntry[] {new LootEntryTable(resource, 1, 0, new LootCondition[0], poolName)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), poolName);
	}

	public static void replaceAndMergeLootTables(LootTableLoadEvent ev, ResourceLocation lootTableToMergeIn) {
		LootTable newTable = null;
		URL resourceURL = LootSystemRegister.class.getResource("/assets/" + lootTableToMergeIn.getNamespace() + "/loot_tables/" + lootTableToMergeIn.getPath() + ".json");

		if (resourceURL != null) {
			try {
				String jsonString = Resources.toString(resourceURL, StandardCharsets.UTF_8);
				newTable = ForgeHooks.loadLootTable(gson, lootTableToMergeIn, jsonString, false, ev.getLootTableManager());
			} catch (IOException | JsonParseException e) {
				AdventOfAscension.logMessage(Level.WARN, "Couldn't load loot table " + lootTableToMergeIn.toString() + " from " + resourceURL.toString());
				e.printStackTrace();
			}

			if (newTable == null)
				return;

			List<LootPool> existingPools = ObfuscationReflectionHelper.getPrivateValue(LootTable.class, ev.getTable(), "field_186466_c");

			for (LootPool pool : existingPools) {
				if (!pool.getName().equals("main"))
					newTable.addPool(pool);
			}

			ev.setTable(newTable);
		}
	}

	@Nullable
	public static String getTextFromResourceFile(ResourceLocation resourceLocation, String fileType, ResourceLocation... fallbackLocations) {
		Object modContainer = resourceLocation.getNamespace().equals("aoa3") ? AdventOfAscension.instance() : Loader.instance().getIndexedModList().get(resourceLocation.getNamespace());

		if (modContainer == null) {
			AdventOfAscension.logMessage(Level.ERROR, "Unable to find mod with domain: " + resourceLocation.getNamespace());

			return null;
		}

		ClassLoader loader = modContainer.getClass().getClassLoader();
		InputStream streamIn = loader.getResourceAsStream("assets/" + resourceLocation.getNamespace() + "/" + resourceLocation.getPath() + "." + fileType);

		if (streamIn == null) {
			for (ResourceLocation fallback : fallbackLocations) {
				streamIn = loader.getResourceAsStream("assets/" + fallback.getNamespace() + "/" + fallback.getPath() + "." + fileType);

				if (streamIn != null)
					break;
			}

			if (streamIn == null) {
				AdventOfAscension.logOptionalMessage("Unable to find resource from " + resourceLocation.getNamespace() + " with path: " + resourceLocation.getPath() + ". Tried " + fallbackLocations.length + " fallback locations");

				return null;
			}
		}

		try (InputStreamReader streamReader = new InputStreamReader(streamIn, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
			final StringBuilder content = new StringBuilder();

			reader.lines().forEach(line -> {content.append(line); content.append("\n");});

			return content.toString();
		}
		catch (IOException ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Buffered reader failed for mod: " + resourceLocation.getNamespace() + ", filepath: " + resourceLocation.getPath(), ex);

			return null;
		}
	}

	public static float[] initFixedArray(float size) {
		return new float[(int)size];
	}

	public static void doDebuggingPrintout(Logger logger) {
		Runtime runtime = Runtime.getRuntime();

		logger.log(Level.INFO, "RAM Usage: " + ((runtime.totalMemory() - runtime.freeMemory()) / 1000000L) + "MB/" + (runtime.totalMemory() / 1000000L) + "MB (" + (runtime.maxMemory() / 1000000L) + "MB max)");
		logger.log(Level.INFO, "Forge Version: " + ForgeVersion.getVersion());
		logger.log(Level.INFO, "Minecraft Version: " + ForgeVersion.mcVersion);
		logger.log(Level.INFO, "Mod Version: " + AdventOfAscension.version);
		logger.log(Level.INFO, "Total blocks registered: " + ForgeRegistries.BLOCKS.getValuesCollection().size());
		logger.log(Level.INFO, "Total items registered: " + ForgeRegistries.ITEMS.getValuesCollection().size());
		logger.log(Level.INFO, "Total potions registered: " + ForgeRegistries.POTIONS.getValuesCollection().size());
		logger.log(Level.INFO, "Total biomes registered: " + ForgeRegistries.BIOMES.getValuesCollection().size());
		logger.log(Level.INFO, "Total entities registered: " + ForgeRegistries.ENTITIES.getValuesCollection().size());
		logger.log(Level.INFO, "Total enchantments registered: " + ForgeRegistries.ENTITIES.getValuesCollection().size());
	}

	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}
}
