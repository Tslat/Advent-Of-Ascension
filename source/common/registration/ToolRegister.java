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

import javax.annotation.Nonnull;

@SuppressWarnings({"unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class ToolRegister {
	public static final Item AMETHYST_PICKAXE = ObjectHolder();
	public static final Item EMBERSTONE_PICKAXE = ObjectHolder();
	public static final Item ENERGISTIC_PICKAXE = ObjectHolder();
	public static final Item GEMCRACKER = ObjectHolder();
	public static final Item GOOFY_PICKAXE = ObjectHolder();
	public static final Item JADE_PICKAXE = ObjectHolder();
	public static final Item LIMONITE_PICKAXE = ObjectHolder();
	public static final Item OCCULT_PICKAXE = ObjectHolder();
	public static final Item ORNAMYTE_PICKAXE = ObjectHolder();
	public static final Item PICKMAX = ObjectHolder();
	public static final Item ROSITE_PICKAXE = ObjectHolder();
	public static final Item SAPPHIRE_PICKAXE = ObjectHolder();
	public static final Item SKELETAL_PICKAXE = ObjectHolder();
	public static final Item SOULSTONE_PICKAXE = ObjectHolder();

	public static final Item AMETHYST_SHOVEL = ObjectHolder();
	public static final Item EMBERSTONE_SHOVEL = ObjectHolder();
	public static final Item ENERGISTIC_SHOVEL = ObjectHolder();
	public static final Item GOOFY_SHOVEL = ObjectHolder();
	public static final Item JADE_SHOVEL = ObjectHolder();
	public static final Item LIMONITE_SHOVEL = ObjectHolder();
	public static final Item OCCULT_SHOVEL = ObjectHolder();
	public static final Item ORNAMYTE_SHOVEL = ObjectHolder();
	public static final Item ROSITE_SHOVEL = ObjectHolder();
	public static final Item SAPPHIRE_SHOVEL = ObjectHolder();
	public static final Item SKELETAL_SHOVEL = ObjectHolder();
	public static final Item SOULSTONE_SHOVEL = ObjectHolder();

	public static final Item AMETHYST_AXE = ObjectHolder();
	public static final Item EMBERSTONE_AXE = ObjectHolder();
	public static final Item ENERGISTIC_AXE = ObjectHolder();
	public static final Item GOOFY_AXE = ObjectHolder();
	public static final Item JADE_AXE = ObjectHolder();
	public static final Item LIMONITE_AXE = ObjectHolder();
	public static final Item OCCULT_AXE = ObjectHolder();
	public static final Item ORNAMYTE_AXE = ObjectHolder();
	public static final Item ROSITE_AXE = ObjectHolder();
	public static final Item SAPPHIRE_AXE = ObjectHolder();
	public static final Item SKELETAL_AXE = ObjectHolder();
	public static final Item SOULSTONE_AXE = ObjectHolder();

	public static final Item CHAINSAW = ObjectHolder();
	
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

	@SuppressWarnings("ConstantConditions")
	@Nonnull
	private static <T> T ObjectHolder() {
		return null;
	}
}
