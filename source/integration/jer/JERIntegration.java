package net.tslat.aoa3.integration.jer;

import jeresources.api.*;
import jeresources.api.drop.PlantDrop;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.IPlantable;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import org.apache.commons.lang3.tuple.Triple;

import java.util.function.Consumer;

public class JERIntegration {
	@JERPlugin
	public static IJERAPI jerAPI = null;

	public static void postInit() {
		if (jerAPI == null)
			return;

		integrateDungeonLoot();
		integrateCrops();
		integrateWorldGen();
	}

	private static void integrateWorldGen() {
		IWorldGenRegistry worldGenRegistry = jerAPI.getWorldGenRegistry();

		//worldGenRegistry.register(new ItemStack(AoABlocks.AMETHYST_ORE.get()), new DistributionSquare(5, 7, 14, 30), true, new LootDrop(new ItemStack(AoAItems.AMETHYST.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.DEEPSLATE_JADE_ORE.get()), new DistributionSquare(4, 7, 7, 19), true, new LootDrop(new ItemStack(AoAItems.JADE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.LIMONITE_ORE.get()), new DistributionSquare(5, 11, 8, 67));
		//worldGenRegistry.register(new ItemStack(AoABlocks.ROSITE_ORE.get()), new DistributionSquare(4, 9, 17, 47));
		//worldGenRegistry.register(new ItemStack(AoABlocks.RUNIUM_ORE.get()), new DistributionSquare(5, 13, 5, 132));
		//worldGenRegistry.register(new ItemStack(AoABlocks.SAPPHIRE_ORE.get()), new DistributionSquare(2, 6, 4, 11), true, new LootDrop(new ItemStack(AoAItems.SAPPHIRE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.BLOODSTONE_ORE.get()), new DistributionSquare(6, 6, 0, 63), new Restriction(new DimensionRestriction(AoADimensions.ABYSS.key)), true, new LootDrop(new ItemStack(AoAItems.BLOODSTONE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.BARONYTE_ORE.get()), new DistributionSquare(3, 7, 25, 43), new Restriction(new DimensionRestriction(AoADimensions.BARATHOS.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.BLAZIUM_ORE.get()), new DistributionSquare(3, 6, 0, 19), new Restriction(new DimensionRestriction(AoADimensions.BARATHOS.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.VARSIUM_ORE.get()), new DistributionSquare(3, 6, 35, 73), new Restriction(new DimensionRestriction(AoADimensions.BARATHOS.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.BLUE_GEMSTONE_ORE.get()), new DistributionSquare(6, 9, 10, 116), new Restriction(new DimensionRestriction(AoADimensions.CRYSTEVIA.key)), true, new LootDrop(new ItemStack(AoAItems.BLUE_GEMSTONES.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.GREEN_GEMSTONE_ORE.get()), new DistributionSquare(6, 9, 10, 116), new Restriction(new DimensionRestriction(AoADimensions.CRYSTEVIA.key)), true, new LootDrop(new ItemStack(AoAItems.GREEN_GEMSTONES.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.RED_GEMSTONE_ORE.get()), new DistributionSquare(6, 9, 10, 116), new Restriction(new DimensionRestriction(AoADimensions.CRYSTEVIA.key)), true, new LootDrop(new ItemStack(AoAItems.RED_GEMSTONES.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.PURPLE_GEMSTONE_ORE.get()), new DistributionSquare(6, 9, 10, 116), new Restriction(new DimensionRestriction(AoADimensions.CRYSTEVIA.key)), true, new LootDrop(new ItemStack(AoAItems.PURPLE_GEMSTONES.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.WHITE_GEMSTONE_ORE.get()), new DistributionSquare(6, 9, 10, 116), new Restriction(new DimensionRestriction(AoADimensions.CRYSTEVIA.key)), true, new LootDrop(new ItemStack(AoAItems.WHITE_GEMSTONES.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.YELLOW_GEMSTONE_ORE.get()), new DistributionSquare(6, 9, 10, 116), new Restriction(new DimensionRestriction(AoADimensions.CRYSTEVIA.key)), true, new LootDrop(new ItemStack(AoAItems.YELLOW_GEMSTONES.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.CHARGED_RUNIUM_ORE.get()), new DistributionSquare(6, 11, 0, 119), new Restriction(new DimensionRestriction(AoADimensions.DEEPLANDS.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.DEEP_CASE.get()), new DistributionSquare(5, 1, 75, 124), new Restriction(new DimensionRestriction(AoADimensions.DEEPLANDS.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.CHESTBONE_FRAGMENTS_ORE.get()), new DistributionSquare(2, 5, 0, 39), new Restriction(new DimensionRestriction(AoADimensions.PRECASIA.key)), true, new LootDrop(new ItemStack(AoAItems.CHESTBONE_FRAGMENT.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.FOOTBONE_FRAGMENTS_ORE.get()), new DistributionSquare(2, 5, 0, 39), new Restriction(new DimensionRestriction(AoADimensions.PRECASIA.key)), true, new LootDrop(new ItemStack(AoAItems.FOOTBONE_FRAGMENT.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.LEGBONE_FRAGMENTS_ORE.get()), new DistributionSquare(2, 5, 0, 39), new Restriction(new DimensionRestriction(AoADimensions.PRECASIA.key)), true, new LootDrop(new ItemStack(AoAItems.LEGBONE_FRAGMENT.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.SKULLBONE_FRAGMENTS_ORE.get()), new DistributionSquare(2, 5, 0, 39), new Restriction(new DimensionRestriction(AoADimensions.PRECASIA.key)), true, new LootDrop(new ItemStack(AoAItems.SKULLBONE_FRAGMENT.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.CRYSTALLITE_ORE.get()), new DistributionSquare(10, 8, 30, 68), new Restriction(new DimensionRestriction(AoADimensions.HAVEN.key)), true, new LootDrop(new ItemStack(AoAItems.CRYSTALLITE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.ELECANIUM_ORE.get()), new DistributionSquare(3, 5, 0, 35), new Restriction(new DimensionRestriction(AoADimensions.RUNANDOR.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.EMBERSTONE_ORE.get()), new DistributionSquare(15, 7, 10, 117), Restriction.NETHER);
		//worldGenRegistry.register(new ItemStack(AoABlocks.GEMENYTE_ORE.get()), new DistributionSquare(4, 5, 22, 35), new Restriction(new DimensionRestriction(AoADimensions.CREEPONIA.key)), true, new LootDrop(new ItemStack(AoAItems.GEMENYTE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.JEWELYTE_ORE.get()), new DistributionSquare(4, 5, 22, 35), new Restriction(new DimensionRestriction(AoADimensions.CREEPONIA.key)), true, new LootDrop(new ItemStack(AoAItems.JEWELYTE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.ORNAMYTE_ORE.get()), new DistributionSquare(4, 5, 0, 14), new Restriction(new DimensionRestriction(AoADimensions.CREEPONIA.key)), true, new LootDrop(new ItemStack(AoAItems.ORNAMYTE.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.GHASTLY_ORE.get()), new DistributionSquare(3, 6, 30, 58), new Restriction(new DimensionRestriction(AoADimensions.GRECKON.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.GHOULISH_ORE.get()), new DistributionSquare(3, 6, 0, 28), new Restriction(new DimensionRestriction(AoADimensions.GRECKON.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.LYON_ORE.get()), new DistributionSquare(4, 7, 0, 64), new Restriction(new DimensionRestriction(AoADimensions.IROMINE.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.MYSTITE_ORE.get()), new DistributionSquare(7, 4, 0, 99), new Restriction(new DimensionRestriction(AoADimensions.MYSTERIUM.key)));
		//worldGenRegistry.register(new ItemStack(AoABlocks.SHYREGEM_ORE.get()), new DistributionSquare(4, 1, 65, 104), new Restriction(new DimensionRestriction(AoADimensions.SHYRELANDS.key)), true, new LootDrop(new ItemStack(AoAItems.SHYREGEM.get())));
		//worldGenRegistry.register(new ItemStack(AoABlocks.SHYRESTONE_ORE.get()), new DistributionSquare(12, 1, 65, 104), new Restriction(new DimensionRestriction(AoADimensions.SHYRELANDS.key)));
	}

	private static void integrateCrops() {
		IPlantRegistry plantRegistry = jerAPI.getPlantRegistry();

		Consumer<Triple<IPlantable, Item, Item>> cropTableConsumer1 = objects -> {
			plantRegistry.register(
					new ItemStack(objects.getRight()),
					objects.getLeft(),
					new PlantDrop(new ItemStack(objects.getMiddle()), 1f),
					new PlantDrop(new ItemStack(objects.getRight()), 1f),
					new PlantDrop(new ItemStack(objects.getRight()), 1, 4));
		};

		Consumer<Tuple<IPlantable, Item>> cropTableConsumer2 = objects -> {
			plantRegistry.register(
					new ItemStack(objects.getB()),
					objects.getA(),
					new PlantDrop(new ItemStack(objects.getB()), 1f),
					new PlantDrop(new ItemStack(objects.getB()), 1, 4));
		};

		Consumer<Triple<IPlantable, Item, Item>> cropTableConsumer3 = objects -> {
			plantRegistry.register(
					new ItemStack(objects.getRight()),
					objects.getLeft(),
					new PlantDrop(new ItemStack(objects.getMiddle()), 1f),
					new PlantDrop(new ItemStack(objects.getMiddle()), 1, 3));
		};

		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.BUBBLE_BERRY_CROP.get(), AoAItems.BUBBLE_BERRIES.get(), AoAItems.BUBBLE_BERRY_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.CHILLI_CROP.get(), AoAItems.CHILLI.get(), AoAItems.CHILLI_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.FLORACLES_CROP.get(), AoAItems.FLORACLE_STICKS.get(), AoAItems.FLORACLE_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.GOLDICAPS_CROP.get(), AoAItems.GOLDICAP_PETALS.get(), AoAItems.GOLDICAP_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.LUNACRIKE_CROP.get(), AoAItems.LUNACRIKE.get(), AoAItems.LUNACRIKE_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.LUNA_GLOBE_CROP.get(), AoAItems.LUNA_GLOBE.get(), AoAItems.LUNA_GLOBE_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.LUNALON_CROP.get(), AoAItems.LUNALONS.get(), AoAItems.LUNALON_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.ROSIDON_CROP.get(), AoAItems.ROSIDONS.get(), AoAItems.ROSIDON_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.TEA_CROP.get(), AoAItems.TEA_SHREDDINGS.get(), AoAItems.TEA_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.THORNY_PLANT_CROP.get(), AoAItems.THORNY_PETALS.get(), AoAItems.THORNY_PLANT_SEEDS.get()));
		cropTableConsumer1.accept(Triple.of((IPlantable)AoABlocks.TRILLIAD_CROP.get(), AoAItems.TRILLIAD_LEAVES.get(), AoAItems.TRILLIAD_SEEDS.get()));
		cropTableConsumer2.accept(new Tuple<IPlantable, Item>((IPlantable)AoABlocks.EYE_BULB_CROP.get(), AoAItems.EYE_BULB.get()));
		cropTableConsumer2.accept(new Tuple<IPlantable, Item>((IPlantable)AoABlocks.MYSTIC_SHROOM_CROP.get(), AoAItems.MYSTIC_SHROOMS.get()));
		cropTableConsumer3.accept(Triple.of((IPlantable)AoABlocks.HEART_FRUIT_CROP.get(), AoAItems.HEART_FRUIT.get(), AoAItems.HEART_FRUIT_SEEDS.get()));

		plantRegistry.register(
				new ItemStack(AoAItems.MAGIC_MARANG.get()),
				(IPlantable)AoABlocks.MAGIC_MARANG_CROP.get(),
				new PlantDrop(new ItemStack(AoAItems.MAGIC_MARANG.get()), 1f));
	}

	private static void integrateDungeonLoot() {
		IDungeonRegistry dungeonRegistry = jerAPI.getDungeonRegistry();

		dungeonRegistry.registerChest("Aquatic Castle", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/aquatic_castle"));
		dungeonRegistry.registerChest("Baron Castle", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/baron_castle"));
		dungeonRegistry.registerChest("Floro Castle", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/floro_castle"));
		dungeonRegistry.registerChest("Garden Castle", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/garden_castle"));
		dungeonRegistry.registerChest("Gingerbread House", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/gingerbread_house"));
		dungeonRegistry.registerChest("Guardian Tower", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/guardian_tower"));
		dungeonRegistry.registerChest("Haunted Castle", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/haunted_castle_top_floor"));
		dungeonRegistry.registerChest("Iro Passages", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/iro_passage"));
		dungeonRegistry.registerChest("Lelyetian Tower", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/lelyetian_tower"));
		dungeonRegistry.registerChest("Lunar Food Market", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/lunar_food_market"));
		dungeonRegistry.registerChest("Lunarade Stand", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/lunarade_stand"));
		dungeonRegistry.registerChest("Spellbinder House", new ResourceLocation(AdventOfAscension.MOD_ID, "structures/spellbinder_house"));
	}
}
