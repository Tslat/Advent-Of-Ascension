package net.tslat.aoa3.utils;

import com.google.common.collect.HashMultimap;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.minecraft.advancements.Advancement;
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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.FluidsRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.utils.skills.ButcheryUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;
import net.tslat.aoa3.utils.skills.InnervationUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
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
			HunterUtil.parseHunterCreatureRegistration(entry);
		}

		for (String entry : ConfigurationUtil.EntityConfig.bloodlustBlacklist) {
			ButcheryUtil.blacklistEntityIdFromBloodlusts(new ResourceLocation(entry));
		}

		for (String entry : ConfigurationUtil.EntityConfig.heartstoneBlacklist) {
			InnervationUtil.blacklistEntityIdFromHeartstones(new ResourceLocation(entry));
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
				newTable = ForgeHooks.loadLootTable(gson, lootTableToMergeIn, jsonString, true, ev.getLootTableManager());
			} catch (IOException | JsonParseException e) {
				AdventOfAscension.logMessage(Level.WARN, "Couldn't load loot table " + lootTableToMergeIn.toString() + " from " + resourceURL.toString());
				e.printStackTrace();
			}

			if (newTable == null)
				return;

			List<LootPool> frozenPools = ObfuscationReflectionHelper.getPrivateValue(LootTable.class, newTable, "field_186466_c");
			List<LootPool> existingPools = ObfuscationReflectionHelper.getPrivateValue(LootTable.class, ev.getTable(), "field_186466_c");

			ReflectionHelper.setPrivateValue(LootTable.class, newTable, false, "isFrozen");

			for (LootPool pool : frozenPools) {
				if (pool.isFrozen())
					ReflectionHelper.setPrivateValue(LootPool.class, pool, false, "isFrozen");
			}

			for (LootPool pool : existingPools) {
				if (!pool.getName().equals("main"))
					newTable.addPool(pool);
			}

			ev.setTable(newTable);
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
		logger.log(Level.INFO, "Total enchantments registered: " + ForgeRegistries.ENCHANTMENTS.getValuesCollection().size());
	}

	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}
}
