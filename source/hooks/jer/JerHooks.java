package net.tslat.aoa3.hooks.jer;

import jeresources.api.*;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.api.restrictions.DimensionRestriction;
import jeresources.api.restrictions.Restriction;
import jeresources.util.LootTableHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.functional.crops.CropBlock;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class JerHooks {
	@JERPlugin
	public static IJERAPI jerAPI = null;

	public static void init() {
		if (jerAPI == null)
			return;

		integrateMobDrops();
		integrateDungeonLoot();
		integrateCrops();
		integrateWorldGen();
	}

	private static void integrateWorldGen() {
		IWorldGenRegistry worldGenRegistry = jerAPI.getWorldGenRegistry();

		worldGenRegistry.register(new ItemStack(BlockRegister.AMETHYST_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.amethyst.maxVeinsPerChunk / 2, (ConfigurationUtil.OreConfig.amethyst.minOresPerVein + ConfigurationUtil.OreConfig.amethyst.maxOresPerVein) / 2, 14, 30), true, new LootDrop(new ItemStack(ItemRegister.AMETHYST)));
		worldGenRegistry.register(new ItemStack(BlockRegister.JADE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.jade.maxVeinsPerChunk / 2, (ConfigurationUtil.OreConfig.jade.minOresPerVein + ConfigurationUtil.OreConfig.jade.maxOresPerVein) / 2, 7, 19));
		worldGenRegistry.register(new ItemStack(BlockRegister.LIMONITE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.limonite.maxVeinsPerChunk / 2, (ConfigurationUtil.OreConfig.limonite.minOresPerVein + ConfigurationUtil.OreConfig.limonite.maxOresPerVein) / 2, 8, 67));
		worldGenRegistry.register(new ItemStack(BlockRegister.ROSITE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.rosite.maxVeinsPerChunk / 2, (ConfigurationUtil.OreConfig.rosite.minOresPerVein + ConfigurationUtil.OreConfig.rosite.maxOresPerVein) / 2, 17, 47));
		worldGenRegistry.register(new ItemStack(BlockRegister.RUNIUM_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.runium.maxVeinsPerChunk / 2, (ConfigurationUtil.OreConfig.runium.minOresPerVein + ConfigurationUtil.OreConfig.runium.maxOresPerVein) / 2, 5, 132));
		worldGenRegistry.register(new ItemStack(BlockRegister.SAPPHIRE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.sapphire.maxVeinsPerChunk / 2, (ConfigurationUtil.OreConfig.sapphire.minOresPerVein + ConfigurationUtil.OreConfig.sapphire.maxOresPerVein) / 2, 4, 11), true, new LootDrop(new ItemStack(ItemRegister.SAPPHIRE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.BLOODSTONE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.bloodstone.veinsPerChunk, (ConfigurationUtil.OreConfig.bloodstone.minOresPerVein + ConfigurationUtil.OreConfig.bloodstone.maxOresPerVein) / 2, 45, 49), new Restriction(new DimensionRestriction(DimensionRegister.DIM_ABYSS)), true, new LootDrop(new ItemStack(ItemRegister.BLOODSTONE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.BARONYTE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.baronyte.veinsPerChunk, (ConfigurationUtil.OreConfig.baronyte.minOresPerVein + ConfigurationUtil.OreConfig.baronyte.maxOresPerVein) / 2, 25, 34), new Restriction(new DimensionRestriction(DimensionRegister.DIM_BARATHOS)));
		worldGenRegistry.register(new ItemStack(BlockRegister.BLAZIUM_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.blazium.veinsPerChunk, (ConfigurationUtil.OreConfig.blazium.minOresPerVein + ConfigurationUtil.OreConfig.blazium.maxOresPerVein) / 2, 0, 19), new Restriction(new DimensionRestriction(DimensionRegister.DIM_BARATHOS)));
		worldGenRegistry.register(new ItemStack(BlockRegister.VARSIUM_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.varsium.veinsPerChunk, (ConfigurationUtil.OreConfig.varsium.minOresPerVein + ConfigurationUtil.OreConfig.varsium.maxOresPerVein) / 2, 25, 62), new Restriction(new DimensionRestriction(DimensionRegister.DIM_BARATHOS)));
		worldGenRegistry.register(new ItemStack(BlockRegister.BLUE_CRYSTAL_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.blueCrystal.veinsPerChunk, (ConfigurationUtil.OreConfig.blueCrystal.minOresPerVein + ConfigurationUtil.OreConfig.blueCrystal.maxOresPerVein) / 2, 10, 109), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CRYSTEVIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.GREEN_CRYSTAL_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.greenCrystal.veinsPerChunk, (ConfigurationUtil.OreConfig.greenCrystal.minOresPerVein + ConfigurationUtil.OreConfig.greenCrystal.maxOresPerVein) / 2, 10, 109), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CRYSTEVIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.RED_CRYSTAL_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.redCrystal.veinsPerChunk, (ConfigurationUtil.OreConfig.redCrystal.minOresPerVein + ConfigurationUtil.OreConfig.redCrystal.maxOresPerVein) / 2, 10, 109), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CRYSTEVIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.PURPLE_CRYSTAL_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.purpleCrystal.veinsPerChunk, (ConfigurationUtil.OreConfig.purpleCrystal.minOresPerVein + ConfigurationUtil.OreConfig.purpleCrystal.maxOresPerVein) / 2, 10, 109), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CRYSTEVIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.WHITE_CRYSTAL_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.whiteCrystal.veinsPerChunk, (ConfigurationUtil.OreConfig.whiteCrystal.minOresPerVein + ConfigurationUtil.OreConfig.whiteCrystal.maxOresPerVein) / 2, 10, 109), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CRYSTEVIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.YELLOW_CRYSTAL_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.yellowCrystal.veinsPerChunk, (ConfigurationUtil.OreConfig.yellowCrystal.minOresPerVein + ConfigurationUtil.OreConfig.yellowCrystal.maxOresPerVein) / 2, 10, 109), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CRYSTEVIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.CHARGED_RUNIUM_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.chargedRunium.veinsPerChunk, (ConfigurationUtil.OreConfig.chargedRunium.minOresPerVein + ConfigurationUtil.OreConfig.chargedRunium.maxOresPerVein) / 2, 70, 114), new Restriction(new DimensionRestriction(DimensionRegister.DIM_DEEPLANDS)));
		worldGenRegistry.register(new ItemStack(BlockRegister.DEEP_CASE), new DistributionSquare(4, 5, 70, 114), new Restriction(new DimensionRestriction(DimensionRegister.DIM_DEEPLANDS)));
		worldGenRegistry.register(new ItemStack(BlockRegister.CHESTBONE_FRAGMENTS_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.chestboneFragments.veinsPerChunk, (ConfigurationUtil.OreConfig.chestboneFragments.minOresPerVein + ConfigurationUtil.OreConfig.chestboneFragments.maxOresPerVein) / 2, 0, 40), new Restriction(new DimensionRestriction(DimensionRegister.DIM_PRECASIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.FOOTBONE_FRAGMENTS_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.footboneFragments.veinsPerChunk, (ConfigurationUtil.OreConfig.footboneFragments.minOresPerVein + ConfigurationUtil.OreConfig.footboneFragments.maxOresPerVein) / 2, 0, 40), new Restriction(new DimensionRestriction(DimensionRegister.DIM_PRECASIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.LEGBONE_FRAGMENTS_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.legboneFragments.veinsPerChunk, (ConfigurationUtil.OreConfig.legboneFragments.minOresPerVein + ConfigurationUtil.OreConfig.legboneFragments.maxOresPerVein) / 2, 0, 40), new Restriction(new DimensionRestriction(DimensionRegister.DIM_PRECASIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.SKULLBONE_FRAGMENTS_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.skullboneFragments.veinsPerChunk, (ConfigurationUtil.OreConfig.skullboneFragments.minOresPerVein + ConfigurationUtil.OreConfig.skullboneFragments.maxOresPerVein) / 2, 0, 40), new Restriction(new DimensionRestriction(DimensionRegister.DIM_PRECASIA)));
		worldGenRegistry.register(new ItemStack(BlockRegister.CRYSTALLITE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.crystallite.veinsPerChunk, (ConfigurationUtil.OreConfig.crystallite.minOresPerVein + ConfigurationUtil.OreConfig.crystallite.maxOresPerVein) / 2, 30, 69), new Restriction(new DimensionRestriction(DimensionRegister.DIM_HAVEN)), true, new LootDrop(new ItemStack(ItemRegister.CRYSTALLITE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.ELECANIUM_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.elecanium.veinsPerChunk, (ConfigurationUtil.OreConfig.elecanium.minOresPerVein + ConfigurationUtil.OreConfig.elecanium.maxOresPerVein) / 2, 1, 35), new Restriction(new DimensionRestriction(DimensionRegister.DIM_RUNANDOR)));
		worldGenRegistry.register(new ItemStack(BlockRegister.EMBERSTONE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.emberstone.veinsPerChunk, (ConfigurationUtil.OreConfig.emberstone.minOresPerVein + ConfigurationUtil.OreConfig.emberstone.maxOresPerVein) / 2, 5, 124), new Restriction(new DimensionRestriction(-1)));
		worldGenRegistry.register(new ItemStack(BlockRegister.GEMENYTE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.gemenyte.veinsPerChunk, (ConfigurationUtil.OreConfig.gemenyte.minOresPerVein + ConfigurationUtil.OreConfig.gemenyte.maxOresPerVein) / 2, 22, 36), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CREEPONIA)), true, new LootDrop(new ItemStack(ItemRegister.GEMENYTE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.JEWELYTE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.jewelyte.veinsPerChunk, (ConfigurationUtil.OreConfig.jewelyte.minOresPerVein + ConfigurationUtil.OreConfig.jewelyte.maxOresPerVein) / 2, 22, 36), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CREEPONIA)), true, new LootDrop(new ItemStack(ItemRegister.JEWELYTE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.ORNAMYTE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.ornamyte.veinsPerChunk, (ConfigurationUtil.OreConfig.ornamyte.minOresPerVein + ConfigurationUtil.OreConfig.ornamyte.maxOresPerVein) / 2, 3, 14), new Restriction(new DimensionRestriction(DimensionRegister.DIM_CREEPONIA)), true, new LootDrop(new ItemStack(ItemRegister.ORNAMYTE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.GHASTLY_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.ghastly.veinsPerChunk, (ConfigurationUtil.OreConfig.ghastly.minOresPerVein + ConfigurationUtil.OreConfig.ghastly.maxOresPerVein) / 2, 3, 27), new Restriction(new DimensionRestriction(DimensionRegister.DIM_GRECKON)));
		worldGenRegistry.register(new ItemStack(BlockRegister.GHOULISH_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.ghoulish.veinsPerChunk, (ConfigurationUtil.OreConfig.ghoulish.minOresPerVein + ConfigurationUtil.OreConfig.ghoulish.maxOresPerVein) / 2, 30, 59), new Restriction(new DimensionRestriction(DimensionRegister.DIM_GRECKON)));
		worldGenRegistry.register(new ItemStack(BlockRegister.LYON_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.lyon.veinsPerChunk, (ConfigurationUtil.OreConfig.lyon.minOresPerVein + ConfigurationUtil.OreConfig.lyon.maxOresPerVein) / 2, 45, 64), new Restriction(new DimensionRestriction(DimensionRegister.DIM_IROMINE)));
		worldGenRegistry.register(new ItemStack(BlockRegister.MYSTITE_ORE), new DistributionSquare(ConfigurationUtil.OreConfig.mystite.veinsPerChunk, (ConfigurationUtil.OreConfig.mystite.minOresPerVein + ConfigurationUtil.OreConfig.mystite.maxOresPerVein) / 2, 2, 21), new Restriction(new DimensionRestriction(DimensionRegister.DIM_MYSTERIUM)));
		worldGenRegistry.register(new ItemStack(BlockRegister.SHYREGEM_ORE), new DistributionSquare((int)Math.ceil(((ConfigurationUtil.OreConfig.shyre.minBlocksPerChunk + ConfigurationUtil.OreConfig.shyre.maxBlocksPerChunk) / 2f * 0.125f)), 1, 2, 26), new Restriction(new DimensionRestriction(DimensionRegister.DIM_SHYRELANDS)), true, new LootDrop(new ItemStack(ItemRegister.SHYREGEM)));
		worldGenRegistry.register(new ItemStack(BlockRegister.SHYRESTONE_ORE), new DistributionSquare((int)Math.ceil(((ConfigurationUtil.OreConfig.shyre.minBlocksPerChunk + ConfigurationUtil.OreConfig.shyre.maxBlocksPerChunk) / 2f * 0.875f)), 1, 2, 26), new Restriction(new DimensionRestriction(DimensionRegister.DIM_SHYRELANDS)));
	}

	private static void integrateCrops() {
		IPlantRegistry plantRegistry = jerAPI.getPlantRegistry();

		try {
			Method getSeedsMethod = ObfuscationReflectionHelper.findMethod(BlockCrops.class, "func_149866_i", Item.class);

			for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
				if (block instanceof CropBlock) {
					CropBlock crop = (CropBlock)block;
					Item seedItem = (Item)getSeedsMethod.invoke(crop);
					ItemStack seeds = new ItemStack(seedItem);

					if (crop.dropsSeeds()) {
						plantRegistry.register(seeds, (IPlantable)seedItem, new PlantDrop(new ItemStack(crop.getCrop()), 1f), new PlantDrop(seeds, 0, 3));
					}
					else {
						plantRegistry.register(seeds, (IPlantable)seedItem, new PlantDrop(new ItemStack(crop.getCrop()), 1f), new PlantDrop(seeds, 0, 1));
					}
				}
			}
		}
		catch (Exception ex) {
			AdventOfAscension.logOptionalMessage("Error while reflecting crop blocks for JER, skipping.", ex);
		}
	}

	private static void integrateDungeonLoot() {
		IDungeonRegistry dungeonRegistry = jerAPI.getDungeonRegistry();

		dungeonRegistry.registerChest("Aquatic Castle", LootSystemRegister.structureAquaticCastle);
		dungeonRegistry.registerChest("Baron Castle", LootSystemRegister.structureBaronCastle);
		dungeonRegistry.registerChest("Dawnlight Dungeon", LootSystemRegister.structureDawnlightDungeon);
		dungeonRegistry.registerChest("Floro Castle", LootSystemRegister.structureFloroCastle);
		dungeonRegistry.registerChest("Garden Castle", LootSystemRegister.structureGardenCastle);
		dungeonRegistry.registerChest("Gingerbread House", LootSystemRegister.structureGingerbreadHouse);
		dungeonRegistry.registerChest("Guardian Tower", LootSystemRegister.structureGuardianTowerChests);
		dungeonRegistry.registerChest("Haunted Castle", LootSystemRegister.structureHauntedCastleTopChest);
		dungeonRegistry.registerChest("Iro Passages", LootSystemRegister.structureIroPassageChests);
		dungeonRegistry.registerChest("Lelyetian Tower", LootSystemRegister.structureLelyetianTower);
		dungeonRegistry.registerChest("Lunar Food Market", LootSystemRegister.structureLunarFoodMarket);
		dungeonRegistry.registerChest("Lunarade Stand", LootSystemRegister.structureLunaradeStand);
		dungeonRegistry.registerChest("Spellbinder House", LootSystemRegister.structureSpellbinderHouse);
	}

	private static void integrateMobDrops() {
		final World fakeClientWorld = jerAPI.getWorld();
		final IMobRegistry mobRegistry = jerAPI.getMobRegistry();
		final HashSet<EntitySpawnRegister.SpawnEntry> spawnEntries = new HashSet<EntitySpawnRegister.SpawnEntry>();
		Method getLootTableMethod;
		Field conditionsField;
		Field experienceField;

		spawnEntries.addAll(EntitySpawnRegister.getOverworldSpawns(true));
		spawnEntries.addAll(EntitySpawnRegister.getNetherSpawns());
		spawnEntries.addAll(EntitySpawnRegister.getDimensionSpawns());

		try {
			getLootTableMethod = ObfuscationReflectionHelper.findMethod(EntityLiving.class, "func_184647_J", ResourceLocation.class);
			conditionsField = ObfuscationReflectionHelper.findField(LootEntry.class, "field_186366_e");
			experienceField = ObfuscationReflectionHelper.findField(EntityLiving.class, "field_70728_aV");
		}
		catch (ReflectionHelper.UnableToFindMethodException ex) {
			AdventOfAscension.logOptionalMessage("Unable to reflect EntityLiving class, dropping JER support.", ex);

			return;
		}

		mobRegistry.register(new EntityWither(fakeClientWorld), LootSystemRegister.entityWither);

		for (EntityEntry entry : ForgeRegistries.ENTITIES.getValuesCollection()) {
			if (entry.getRegistryName().getNamespace().equals("aoa3") && EntityLivingBase.class.isAssignableFrom(entry.getEntityClass())) {
				EntityLivingBase entity = (EntityLivingBase)entry.newInstance(fakeClientWorld);
				LightLevel lightReq = entity instanceof IMob ? LightLevel.hostile : LightLevel.any;
				int xp = 0;
				ArrayList<LootDrop> drops;

				if (getLootTableMethod != null) {
					try {
						ResourceLocation lootTable = (ResourceLocation)getLootTableMethod.invoke(entity);

						if (lootTable == null)
							continue;

						if (entity instanceof EntityLiving)
							xp = (int)experienceField.get(entity);

						drops = new ArrayList<LootDrop>();
						LootTable table = LootTableHelper.getManager(fakeClientWorld).getLootTableFromLocation(lootTable);

						for (LootPool pool : LootTableHelper.getPools(table)) {
							List<LootEntryItem> entries = new ArrayList<LootEntryItem>();
							float totalWeight = 0;
							List<LootCondition> poolConditions = LootTableHelper.getConditions(pool);

							for (LootEntry lootEntry : LootTableHelper.getEntries(pool)) {
								if (!(lootEntry instanceof LootEntryItem))
									continue;

								totalWeight += lootEntry.getEffectiveWeight(0);
								entries.add((LootEntryItem)lootEntry);
							}

							for (LootEntryItem lootEntry : entries) {
								LootDrop drop = new LootDrop(LootTableHelper.getItem(lootEntry), lootEntry.getEffectiveWeight(0) / totalWeight, (LootCondition[])conditionsField.get(lootEntry), LootTableHelper.getFunctions(lootEntry));

								drop.addLootConditions(poolConditions);
								drops.add(drop);
							}
						}

						EntitySpawnRegister.SpawnEntry foundSpawnEntry = null;
						Iterator<EntitySpawnRegister.SpawnEntry> spawnEntryIterator = spawnEntries.iterator();

						while (spawnEntryIterator.hasNext()) {
							EntitySpawnRegister.SpawnEntry spawnEntry = spawnEntryIterator.next();

							if (spawnEntry.entityClass.equals(entity.getClass())) {
								foundSpawnEntry = spawnEntry;

								spawnEntryIterator.remove();
								break;
							}
						}

						if (foundSpawnEntry == null) {
							mobRegistry.register(entity, lightReq, xp, new String[] {"No Natural Spawns"}, drops.toArray(new LootDrop[0]));
						}
						else {
							String[] biomes = new String[foundSpawnEntry.biomes.length];

							for (int i = 0; i < foundSpawnEntry.biomes.length; i++) {
								biomes[i] = foundSpawnEntry.biomes[i].getBiomeName();
							}

							mobRegistry.register(entity, lightReq, xp, biomes, drops.toArray(new LootDrop[0]));
						}
					}
					catch (Exception ex) {
						AdventOfAscension.logOptionalMessage("Unable to reflect method or field from EntityLiving class.", ex);
					}
				}
			}
		}
	}
}
