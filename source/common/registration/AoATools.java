package net.tslat.aoa3.common.registration;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.tool.axe.*;
import net.tslat.aoa3.item.tool.pickaxe.*;
import net.tslat.aoa3.item.tool.shovel.*;
import net.tslat.aoa3.util.ItemUtil;

import java.util.function.Supplier;

public final class AoATools {
	public static final DeferredRegister<Item> TOOLS = DeferredRegister.create(ForgeRegistries.ITEMS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Item> AMETHYST_PICKAXE = registerTool("amethyst_pickaxe", () -> new BasePickaxe(ItemUtil.customItemTier(1200, 8.0f, 4.5f, 3, 14, AoAItems.AMETHYST)));
	public static final RegistryObject<Item> EMBERSTONE_PICKAXE = registerTool("emberstone_pickaxe", EmberstonePickaxe::new);
	public static final RegistryObject<Item> ENERGISTIC_PICKAXE = registerTool("energistic_pickaxe", EnergisticPickaxe::new);
	public static final RegistryObject<Item> GEMCRACKER = registerTool("gemcracker", Gemcracker::new);
	public static final RegistryObject<Item> GOOFY_PICKAXE = registerTool("goofy_pickaxe", GoofyPickaxe::new);
	public static final RegistryObject<Item> JADE_PICKAXE = registerTool("jade_pickaxe", () -> new BasePickaxe(ItemUtil.customItemTier(650, 7.5f, 4.0f, 3, 11, AoAItems.JADE)));
	public static final RegistryObject<Item> LIMONITE_PICKAXE = registerTool("limonite_pickaxe", () -> new BasePickaxe(ItemUtil.customItemTier(350, 5.0f, 2.0f, 2, 3, AoAItems.LIMONITE_INGOT)));
	public static final RegistryObject<Item> OCCULT_PICKAXE = registerTool("occult_pickaxe", OccultPickaxe::new);
	public static final RegistryObject<Item> ORNAMYTE_PICKAXE = registerTool("ornamyte_pickaxe", OrnamytePickaxe::new);
	public static final RegistryObject<Item> PICKMAX = registerTool("pickmax", Pickmax::new);
	public static final RegistryObject<Item> ROSITE_PICKAXE = registerTool("rosite_pickaxe", () -> new BasePickaxe(ItemUtil.customItemTier(600, 6.5f, 3.5f, 3, 6, AoAItems.ROSITE_INGOT)));
	public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerTool("sapphire_pickaxe", () -> new BasePickaxe(ItemUtil.customItemTier(2500, 9.0f, 5.0f, 4, 19, AoAItems.SAPPHIRE)));
	public static final RegistryObject<Item> SKELETAL_PICKAXE = registerTool("skeletal_pickaxe", SkeletalPickaxe::new);
	public static final RegistryObject<Item> SOULSTONE_PICKAXE = registerTool("soulstone_pickaxe", SoulstonePickaxe::new);

	public static final RegistryObject<Item> AMETHYST_SHOVEL = registerTool("amethyst_shovel", () -> new BaseShovel(ItemUtil.customItemTier(1200, 8.0f, 4.5f, 3, 14, AoAItems.AMETHYST)));
	public static final RegistryObject<Item> EMBERSTONE_SHOVEL = registerTool("emberstone_shovel", EmberstoneShovel::new);
	public static final RegistryObject<Item> ENERGISTIC_SHOVEL = registerTool("energistic_shovel", EnergisticShovel::new);
	public static final RegistryObject<Item> GOOFY_SHOVEL = registerTool("goofy_shovel", GoofyShovel::new);
	public static final RegistryObject<Item> JADE_SHOVEL = registerTool("jade_shovel", () -> new BaseShovel(ItemUtil.customItemTier(650, 7.5f, 4.0f, 3, 11, AoAItems.JADE)));
	public static final RegistryObject<Item> LIMONITE_SHOVEL = registerTool("limonite_shovel", () -> new BaseShovel(ItemUtil.customItemTier(350, 5.0f, 2.0f, 2, 3, AoAItems.LIMONITE_INGOT)));
	public static final RegistryObject<Item> OCCULT_SHOVEL = registerTool("occult_shovel", OccultShovel::new);
	public static final RegistryObject<Item> ORNAMYTE_SHOVEL = registerTool("ornamyte_shovel", OrnamyteShovel::new);
	public static final RegistryObject<Item> ROSITE_SHOVEL = registerTool("rosite_shovel", () -> new BaseShovel(ItemUtil.customItemTier(600, 6.5f, 3.5f, 3, 6, AoAItems.ROSITE_INGOT)));
	public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerTool("sapphire_shovel", () -> new BaseShovel(ItemUtil.customItemTier(2500, 9.0f, 5.0f, 4, 19, AoAItems.SAPPHIRE)));
	public static final RegistryObject<Item> SKELETAL_SHOVEL = registerTool("skeletal_shovel", SkeletalShovel::new);
	public static final RegistryObject<Item> SOULSTONE_SHOVEL = registerTool("soulstone_shovel", SoulstoneShovel::new);

	public static final RegistryObject<Item> AMETHYST_AXE = registerTool("amethyst_axe", () -> new BaseAxe(ItemUtil.customItemTier(1200, 8.0f, 4.5f, 3, 14, AoAItems.AMETHYST)));
	public static final RegistryObject<Item> EMBERSTONE_AXE = registerTool("emberstone_axe", EmberstoneAxe::new);
	public static final RegistryObject<Item> ENERGISTIC_AXE = registerTool("energistic_axe", EnergisticAxe::new);
	public static final RegistryObject<Item> GOOFY_AXE = registerTool("goofy_axe", GoofyAxe::new);
	public static final RegistryObject<Item> JADE_AXE = registerTool("jade_axe", () -> new BaseAxe(ItemUtil.customItemTier(650, 7.5f, 4.0f, 3, 11, AoAItems.JADE)));
	public static final RegistryObject<Item> LIMONITE_AXE = registerTool("limonite_axe", () -> new BaseAxe(ItemUtil.customItemTier(350, 5.0f, 2.0f, 2, 3, AoAItems.LIMONITE_INGOT)));
	public static final RegistryObject<Item> OCCULT_AXE = registerTool("occult_axe", OccultAxe::new);
	public static final RegistryObject<Item> ORNAMYTE_AXE = registerTool("ornamyte_axe", OrnamyteAxe::new);
	public static final RegistryObject<Item> ROSITE_AXE = registerTool("rosite_axe", () -> new BaseAxe(ItemUtil.customItemTier(600, 6.5f, 3.5f, 3, 6, AoAItems.ROSITE_INGOT)));
	public static final RegistryObject<Item> SAPPHIRE_AXE = registerTool("sapphire_axe", () -> new BaseAxe(ItemUtil.customItemTier(2500, 9.0f, 5.0f, 4, 19, AoAItems.SAPPHIRE)));
	public static final RegistryObject<Item> SKELETAL_AXE = registerTool("skeletal_axe", SkeletalAxe::new);
	public static final RegistryObject<Item> SOULSTONE_AXE = registerTool("soulstone_axe", SoulstoneAxe::new);
	public static final RegistryObject<Item> CHAINSAW = registerTool("chainsaw", Chainsaw::new);

	private static RegistryObject<Item> registerTool(String registryName, Supplier<Item> item) {
		return TOOLS.register(registryName, item);
	}
}
