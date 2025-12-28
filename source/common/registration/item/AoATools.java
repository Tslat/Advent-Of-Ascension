package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tslat.aoa3.content.item.misc.DistortingArtifact;
import net.tslat.aoa3.content.item.misc.Gravitator;
import net.tslat.aoa3.content.item.tool.axe.*;
import net.tslat.aoa3.content.item.tool.hoe.BaseHoe;
import net.tslat.aoa3.content.item.tool.hoe.DryadsBlessing;
import net.tslat.aoa3.content.item.tool.misc.*;
import net.tslat.aoa3.content.item.tool.pickaxe.*;
import net.tslat.aoa3.content.item.tool.shovel.*;
import net.tslat.aoa3.library.constant.AttackSpeed;

import java.util.function.Supplier;

public final class AoATools {
	public static void init() {}

	public static final DeferredItem<EmberstonePickaxe> EMBERSTONE_PICKAXE = registerTool("emberstone_pickaxe", () -> new EmberstonePickaxe(AoAItemStats.EMBERSTONE, BasePickaxe.baseProperties(AoAItemStats.EMBERSTONE, -4.5f, AttackSpeed.PICKAXE)));
	public static final DeferredItem<EnergisticPickaxe> ENERGISTIC_PICKAXE = registerTool("energistic_pickaxe", () -> new EnergisticPickaxe(AoAItemStats.ENERGISTIC, BasePickaxe.baseProperties(AoAItemStats.ENERGISTIC, -2, AttackSpeed.PICKAXE).component(AoADataComponents.CHARGE, 0f)));
	public static final DeferredItem<Gemcracker> GEMCRACKER = registerTool("gemcracker", () -> new Gemcracker(AoAItemStats.GEMCRACKER, BasePickaxe.baseProperties(AoAItemStats.GEMCRACKER, -2, AttackSpeed.PICKAXE)));
	public static final DeferredItem<GoofyPickaxe> GOOFY_PICKAXE = registerTool("goofy_pickaxe", () -> new GoofyPickaxe(AoAItemStats.GOOFY, BasePickaxe.baseProperties(AoAItemStats.GOOFY)));
	public static final DeferredItem<BasePickaxe> JADE_PICKAXE = registerTool("jade_pickaxe", () -> new BasePickaxe(AoAItemStats.JADE, BasePickaxe.baseProperties(AoAItemStats.JADE, -2, AttackSpeed.PICKAXE)));
	public static final DeferredItem<BasePickaxe> LIMONITE_PICKAXE = registerTool("limonite_pickaxe", () -> new BasePickaxe(AoAItemStats.LIMONITE, BasePickaxe.baseProperties(AoAItemStats.LIMONITE, -2, AttackSpeed.PICKAXE)));
	public static final DeferredItem<OccultPickaxe> OCCULT_PICKAXE = registerTool("occult_pickaxe", () -> new OccultPickaxe(AoAItemStats.OCCULT, BasePickaxe.baseProperties(AoAItemStats.OCCULT, -2, AttackSpeed.PICKAXE).rarity(Rarity.RARE)));
	public static final DeferredItem<OrnamytePickaxe> ORNAMYTE_PICKAXE = registerTool("ornamyte_pickaxe", () -> new OrnamytePickaxe(AoAItemStats.ORNAMYTE, BasePickaxe.baseProperties(AoAItemStats.ORNAMYTE, -2, AttackSpeed.PICKAXE)));
	public static final DeferredItem<Pickmax> PICKMAX = registerTool("pickmax", () -> new Pickmax(AoAItemStats.PICKMAX, BasePickaxe.baseProperties(AoAItemStats.PICKMAX, -2, AttackSpeed.PICKAXE)));
	public static final DeferredItem<SkeletalPickaxe> SKELETAL_PICKAXE = registerTool("skeletal_pickaxe", () -> new SkeletalPickaxe(AoAItemStats.SKELETAL, BasePickaxe.baseProperties(AoAItemStats.SKELETAL, -2, AttackSpeed.PICKAXE)));
	public static final DeferredItem<SoulstonePickaxe> SOULSTONE_PICKAXE = registerTool("soulstone_pickaxe", () -> new SoulstonePickaxe(AoAItemStats.SOULSTONE, BasePickaxe.baseProperties(AoAItemStats.SOULSTONE, -2, AttackSpeed.PICKAXE)));

	public static final DeferredItem<BaseShovel> EMBERSTONE_SHOVEL = registerTool("emberstone_shovel", () -> new EmberstoneShovel(AoAItemStats.EMBERSTONE, BaseShovel.baseProperties(AoAItemStats.EMBERSTONE, -4f, AttackSpeed.SHOVEL)));
	public static final DeferredItem<BaseShovel> ENERGISTIC_SHOVEL = registerTool("energistic_shovel", () -> new EnergisticShovel(AoAItemStats.ENERGISTIC, BaseShovel.baseProperties(AoAItemStats.ENERGISTIC, -3.5f, AttackSpeed.SHOVEL).component(AoADataComponents.CHARGE, 0f)));
	public static final DeferredItem<BaseShovel> GOOFY_SHOVEL = registerTool("goofy_shovel", () -> new GoofyShovel(AoAItemStats.GOOFY, BaseShovel.baseProperties(AoAItemStats.GOOFY)));
	public static final DeferredItem<BaseShovel> JADE_SHOVEL = registerTool("jade_shovel", () -> new BaseShovel(AoAItemStats.JADE, BaseShovel.baseProperties(AoAItemStats.JADE, -1.5f, AttackSpeed.SHOVEL)));
	public static final DeferredItem<BaseShovel> LIMONITE_SHOVEL = registerTool("limonite_shovel", () -> new BaseShovel(AoAItemStats.LIMONITE, BaseShovel.baseProperties(AoAItemStats.LIMONITE, -1.5f, AttackSpeed.SHOVEL)));
	public static final DeferredItem<BaseShovel> OCCULT_SHOVEL = registerTool("occult_shovel", () -> new OccultShovel(AoAItemStats.OCCULT, BaseShovel.baseProperties(AoAItemStats.OCCULT, -3.5f, AttackSpeed.SHOVEL).rarity(Rarity.RARE)));
	public static final DeferredItem<BaseShovel> ORNAMYTE_SHOVEL = registerTool("ornamyte_shovel", () -> new OrnamyteShovel(AoAItemStats.ORNAMYTE, BaseShovel.baseProperties(AoAItemStats.ORNAMYTE, -1.5f, AttackSpeed.SHOVEL)));
	public static final DeferredItem<BaseShovel> SKELETAL_SHOVEL = registerTool("skeletal_shovel", () -> new SkeletalShovel(AoAItemStats.SKELETAL, BaseShovel.baseProperties(AoAItemStats.SKELETAL, -1.5f, AttackSpeed.SHOVEL)));
	public static final DeferredItem<BaseShovel> SOULSTONE_SHOVEL = registerTool("soulstone_shovel", () -> new SoulstoneShovel(AoAItemStats.SOULSTONE, BaseShovel.baseProperties(AoAItemStats.SOULSTONE, -3.5f, AttackSpeed.SHOVEL)));

	public static final DeferredItem<BaseAxe> EMBERSTONE_AXE = registerTool("emberstone_axe", () -> new EmberstoneAxe(AoAItemStats.EMBERSTONE, BaseAxe.baseProperties(AoAItemStats.EMBERSTONE, 1.5f, AttackSpeed.AXE)));
	public static final DeferredItem<BaseAxe> ENERGISTIC_AXE = registerTool("energistic_axe", () -> new EnergisticAxe(AoAItemStats.ENERGISTIC, BaseAxe.baseProperties(AoAItemStats.ENERGISTIC, 2f, AttackSpeed.AXE).component(AoADataComponents.CHARGE, 0f)));
	public static final DeferredItem<BaseAxe> GOOFY_AXE = registerTool("goofy_axe", () -> new GoofyAxe(AoAItemStats.GOOFY, BaseAxe.baseProperties(AoAItemStats.GOOFY)));
	public static final DeferredItem<BaseAxe> JADE_AXE = registerTool("jade_axe", () -> new BaseAxe(AoAItemStats.JADE, BaseShovel.baseProperties(AoAItemStats.JADE, 2f, AttackSpeed.AXE)));
	public static final DeferredItem<BaseAxe> LIMONITE_AXE = registerTool("limonite_axe", () -> new BaseAxe(AoAItemStats.LIMONITE, BaseShovel.baseProperties(AoAItemStats.LIMONITE, 2.5f, AttackSpeed.forAttacksPerSecond(0.95f))));
	public static final DeferredItem<BaseAxe> OCCULT_AXE = registerTool("occult_axe", () -> new OccultAxe(AoAItemStats.OCCULT, BaseAxe.baseProperties(AoAItemStats.OCCULT, 2f, AttackSpeed.AXE).rarity(Rarity.RARE)));
	public static final DeferredItem<BaseAxe> ORNAMYTE_AXE = registerTool("ornamyte_axe", () -> new OrnamyteAxe(AoAItemStats.ORNAMYTE, BaseAxe.baseProperties(AoAItemStats.ORNAMYTE, 4f, AttackSpeed.AXE)));
	public static final DeferredItem<BaseAxe> SKELETAL_AXE = registerTool("skeletal_axe", () -> new SkeletalAxe(AoAItemStats.SKELETAL, BaseAxe.baseProperties(AoAItemStats.SKELETAL, 3.5f, AttackSpeed.AXE)));
	public static final DeferredItem<BaseAxe> SOULSTONE_AXE = registerTool("soulstone_axe", () -> new SoulstoneAxe(AoAItemStats.SOULSTONE, BaseAxe.baseProperties(AoAItemStats.SOULSTONE, 1.5f, AttackSpeed.AXE)));
	public static final DeferredItem<Chainsaw> CHAINSAW = registerTool("chainsaw", () -> new Chainsaw(AoAItemStats.CHAINSAW, BaseAxe.baseProperties(AoAItemStats.CHAINSAW)));

	public static final DeferredItem<DryadsBlessing> DRYADS_BLESSING = registerTool("dryads_blessing", DryadsBlessing::new);
	public static final DeferredItem<BaseHoe> LIMONITE_HOE = registerTool("limonite_hoe", () -> new BaseHoe(AoAItemStats.LIMONITE, -5.5f, AttackSpeed.forAttacksPerSecond(3)));
	public static final DeferredItem<BaseHoe> JADE_HOE = registerTool("jade_hoe", () -> new BaseHoe(AoAItemStats.JADE, -6.5f, AttackSpeed.forAttacksPerSecond(4)));
	public static final DeferredItem<BaseHoe> EMBERSTONE_HOE = registerTool("emberstone_hoe", () -> new BaseHoe(AoAItemStats.EMBERSTONE, -11f, AttackSpeed.forAttacksPerSecond(4)));

	public static final DeferredItem<Item> HAULING_ROD = registerTool("hauling_rod", () -> new HaulingRod(new Item.Properties().durability(400)));
	public static final DeferredItem<Item> THERMALLY_INSULATED_ROD = registerTool("thermally_insulated_rod", () -> new ThermallyInsulatedRod(new Item.Properties().durability(400)));
	public static final DeferredItem<Item> LIGHT_ROD = registerTool("light_rod", () -> new LightRod(new Item.Properties().durability(160)));
	public static final DeferredItem<Item> GOLDEN_ROD = registerTool("golden_rod", () -> new GoldenRod(new Item.Properties().durability(280).rarity(Rarity.EPIC)));
	public static final DeferredItem<Item> FISHING_CAGE = registerTool("fishing_cage", () -> new FishingCage(new Item.Properties().durability(15)));

	public static final DeferredItem<Item> ATTUNING_BOWL = registerTool("attuning_bowl", AttuningBowl::new); // TODO Retexture
	public static final DeferredItem<Item> GRAVITATOR = registerTool("gravitator", Gravitator::new);
	public static final DeferredItem<Item> DISTORTING_ARTIFACT = registerTool("distorting_artifact", DistortingArtifact::new);

	private static <T extends Item> DeferredItem<T> registerTool(String registryName, Supplier<T> item) {
		return AoAItems.registerItem(registryName, item, AoACreativeModeTabs.TOOLS.getKey());
	}
}
