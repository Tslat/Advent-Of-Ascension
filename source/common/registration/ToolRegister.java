package net.tslat.aoa3.common.registration;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.item.tool.axe.*;
import net.tslat.aoa3.item.tool.pickaxe.*;
import net.tslat.aoa3.item.tool.shovel.*;

@SuppressWarnings({"unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class ToolRegister {
	@GameRegistry.ObjectHolder("amethyst_pickaxe")
	public static final Item pickaxeAmethyst = null;
	@GameRegistry.ObjectHolder("emberstone_pickaxe")
	public static final Item pickaxeEmberstone = null;
	@GameRegistry.ObjectHolder("energistic_pickaxe")
	public static final Item pickaxeEnergistic = null;
	@GameRegistry.ObjectHolder("gemcracker")
	public static final Item pickaxeGemcracker = null;
	@GameRegistry.ObjectHolder("goofy_pickaxe")
	public static final Item pickaxeGoofy = null;
	@GameRegistry.ObjectHolder("jade_pickaxe")
	public static final Item pickaxeJade = null;
	@GameRegistry.ObjectHolder("limonite_pickaxe")
	public static final Item pickaxeLimonite = null;
	@GameRegistry.ObjectHolder("occult_pickaxe")
	public static final Item pickaxeOccult = null;
	@GameRegistry.ObjectHolder("ornamyte_pickaxe")
	public static final Item pickaxeOrnamyte = null;
	@GameRegistry.ObjectHolder("pickmax")
	public static final Item pickaxePickmax = null;
	@GameRegistry.ObjectHolder("rosite_pickaxe")
	public static final Item pickaxeRosite = null;
	@GameRegistry.ObjectHolder("sapphire_pickaxe")
	public static final Item pickaxeSapphire = null;
	@GameRegistry.ObjectHolder("skeletal_pickaxe")
	public static final Item pickaxeSkeletal = null;
	@GameRegistry.ObjectHolder("soulstone_pickaxe")
	public static final Item pickaxeSoulstone = null;

	@GameRegistry.ObjectHolder("amethyst_shovel")
	public static final Item shovelAmethyst = null;
	@GameRegistry.ObjectHolder("emberstone_shovel")
	public static final Item shovelEmberstone = null;
	@GameRegistry.ObjectHolder("energistic_shovel")
	public static final Item shovelEnergistic = null;
	@GameRegistry.ObjectHolder("goofy_shovel")
	public static final Item shovelGoofy = null;
	@GameRegistry.ObjectHolder("jade_shovel")
	public static final Item shovelJade = null;
	@GameRegistry.ObjectHolder("limonite_shovel")
	public static final Item shovelLimonite = null;
	@GameRegistry.ObjectHolder("occult_shovel")
	public static final Item shovelOccult = null;
	@GameRegistry.ObjectHolder("ornamyte_shovel")
	public static final Item shovelOrnamyte = null;
	@GameRegistry.ObjectHolder("rosite_shovel")
	public static final Item shovelRosite = null;
	@GameRegistry.ObjectHolder("sapphire_shovel")
	public static final Item shovelSapphire = null;
	@GameRegistry.ObjectHolder("skeletal_shovel")
	public static final Item shovelSkeletal = null;
	@GameRegistry.ObjectHolder("soulstone_shovel")
	public static final Item shovelSoulstone = null;

	@GameRegistry.ObjectHolder("amethyst_axe")
	public static final Item axeAmethyst = null;
	@GameRegistry.ObjectHolder("emberstone_axe")
	public static final Item axeEmberstone = null;
	@GameRegistry.ObjectHolder("energistic_axe")
	public static final Item axeEnergistic = null;
	@GameRegistry.ObjectHolder("goofy_axe")
	public static final Item axeGoofy = null;
	@GameRegistry.ObjectHolder("jade_axe")
	public static final Item axeJade = null;
	@GameRegistry.ObjectHolder("limonite_axe")
	public static final Item axeLimonite = null;
	@GameRegistry.ObjectHolder("occult_axe")
	public static final Item axeOccult = null;
	@GameRegistry.ObjectHolder("ornamyte_axe")
	public static final Item axeOrnamyte = null;
	@GameRegistry.ObjectHolder("rosite_axe")
	public static final Item axeRosite = null;
	@GameRegistry.ObjectHolder("sapphire_axe")
	public static final Item axeSapphire = null;
	@GameRegistry.ObjectHolder("skeletal_axe")
	public static final Item axeSkeletal = null;
	@GameRegistry.ObjectHolder("soulstone_axe")
	public static final Item axeSoulstone = null;

	@GameRegistry.ObjectHolder("chainsaw")
	public static final Item chainsaw = null;
	
	@SubscribeEvent
	public static void registerTools(final RegistryEvent.Register<Item> ev) {
		final IForgeRegistry<Item> registry = ev.getRegistry();

		registerPickaxes(registry,
				new BasePickaxe("AmethystPickaxe", "amethyst_pickaxe", MaterialsRegister.TOOL_AMETHYST),
				new EmberstonePickaxe(),
				new EnergisticPickaxe(),
				new Gemcracker(),
				new GoofyPickaxe(),
				new BasePickaxe("JadePickaxe", "jade_pickaxe", MaterialsRegister.TOOL_JADE),
				new BasePickaxe("LimonitePickaxe", "limonite_pickaxe", MaterialsRegister.TOOL_LIMONITE),
				new OccultPickaxe(),
				new OrnamytePickaxe(),
				new Pickmax(),
				new BasePickaxe("RositePickaxe", "rosite_pickaxe", MaterialsRegister.TOOL_ROSITE),
				new BasePickaxe("SapphirePickaxe", "sapphire_pickaxe", MaterialsRegister.TOOL_SAPPHIRE),
				new SkeletalPickaxe(),
				new SoulstonePickaxe()
		);

		registerShovels(registry,
				new BaseShovel("AmethystShovel", "amethyst_shovel", MaterialsRegister.TOOL_AMETHYST),
				new EmberstoneShovel(),
				new EnergisticShovel(),
				new GoofyShovel(),
				new BaseShovel("JadeShovel", "jade_shovel", MaterialsRegister.TOOL_JADE),
				new BaseShovel("LimoniteShovel", "limonite_shovel", MaterialsRegister.TOOL_LIMONITE),
				new OccultShovel(),
				new OrnamyteShovel(),
				new BaseShovel("RositeShovel", "rosite_shovel", MaterialsRegister.TOOL_ROSITE),
				new BaseShovel("SapphireShovel", "sapphire_shovel", MaterialsRegister.TOOL_SAPPHIRE),
				new SkeletalShovel(),
				new SoulstoneShovel()
		);

		registerAxes(registry,
				new BaseAxe("AmethystAxe", "amethyst_axe", MaterialsRegister.TOOL_AMETHYST),
				new EmberstoneAxe(),
				new EnergisticAxe(),
				new GoofyAxe(),
				new BaseAxe("JadeAxe", "jade_axe", MaterialsRegister.TOOL_JADE),
				new BaseAxe("LimoniteAxe", "limonite_axe", MaterialsRegister.TOOL_LIMONITE),
				new OccultAxe(),
				new OrnamyteAxe(),
				new BaseAxe("RositeAxe", "rosite_axe", MaterialsRegister.TOOL_ROSITE),
				new BaseAxe("SapphireAxe", "sapphire_axe", MaterialsRegister.TOOL_SAPPHIRE),
				new SkeletalAxe(),
				new SoulstoneAxe(),
				new Chainsaw()
		);
	}

	private static void registerAxes(IForgeRegistry<Item> registry, BaseAxe... axes) {
		for (BaseAxe axe : axes) {
			ItemRegister.registerItem(registry, axe, "tools/axes/");
		}
	}

	private static void registerPickaxes(IForgeRegistry<Item> registry, BasePickaxe... pickaxes) {
		for (BasePickaxe pickaxe : pickaxes) {
			ItemRegister.registerItem(registry, pickaxe, "tools/pickaxes/");
		}
	}
	private static void registerShovels(IForgeRegistry<Item> registry, BaseShovel... shovels) {
		for (BaseShovel shovel : shovels) {
			ItemRegister.registerItem(registry, shovel, "tools/shovels/");
		}
	}
}
