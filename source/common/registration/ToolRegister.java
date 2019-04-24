package net.tslat.aoa3.common.registration;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.item.tool.axe.*;
import net.tslat.aoa3.item.tool.pickaxe.*;
import net.tslat.aoa3.item.tool.shovel.*;

@Mod.EventBusSubscriber
public class ToolRegister {
	public static final Item pickaxeAmethyst = new AmethystPickaxe(MaterialsRegister.AMETHYSTTOOL);
	public static final Item pickaxeEmberstone = new EmberstonePickaxe(MaterialsRegister.EMBERSTONETOOL);
	public static final Item pickaxeEnergistic = new EnergisticPickaxe(MaterialsRegister.ENERGISTICTOOL);
	public static final Item pickaxeGemcracker = new Gemcracker(MaterialsRegister.GEMCRACKER);
	public static final Item pickaxeGoofy = new GoofyPickaxe(MaterialsRegister.GOOFYTOOL);
	public static final Item pickaxeJade = new JadePickaxe(MaterialsRegister.JADETOOL);
	public static final Item pickaxeLimonite = new LimonitePickaxe(MaterialsRegister.LIMONITETOOL);
	public static final Item pickaxeOccult = new OccultPickaxe(MaterialsRegister.OCCULTTOOL);
	public static final Item pickaxeOrnamyte = new OrnamytePickaxe(MaterialsRegister.ORNAMYTETOOL);
	public static final Item pickaxePickmax = new Pickmax(MaterialsRegister.PICKMAX);
	public static final Item pickaxeRosite = new RositePickaxe(MaterialsRegister.ROSITETOOL);
	public static final Item pickaxeSapphire = new SapphirePickaxe(MaterialsRegister.SAPPHIRETOOL);
	public static final Item pickaxeSkeletal = new SkeletalPickaxe(MaterialsRegister.SKELETALTOOL);
	public static final Item pickaxeSoulstone = new SoulstonePickaxe(MaterialsRegister.SOULSTONETOOL);

	public static final Item shovelAmethyst = new AmethystShovel(MaterialsRegister.AMETHYSTTOOL);
	public static final Item shovelEmberstone = new EmberstoneShovel(MaterialsRegister.EMBERSTONETOOL);
	public static final Item shovelEnergistic = new EnergisticShovel(MaterialsRegister.ENERGISTICTOOL);
	public static final Item shovelGoofy = new GoofyShovel(MaterialsRegister.GOOFYTOOL);
	public static final Item shovelJade = new JadeShovel(MaterialsRegister.JADETOOL);
	public static final Item shovelLimonite = new LimoniteShovel(MaterialsRegister.LIMONITETOOL);
	public static final Item shovelOccult = new OccultShovel(MaterialsRegister.OCCULTTOOL);
	public static final Item shovelOrnamyte = new OrnamyteShovel(MaterialsRegister.ORNAMYTETOOL);
	public static final Item shovelRosite = new RositeShovel(MaterialsRegister.ROSITETOOL);
	public static final Item shovelSapphire = new SapphireShovel(MaterialsRegister.SAPPHIRETOOL);
	public static final Item shovelSkeletal = new SkeletalShovel(MaterialsRegister.SKELETALTOOL);
	public static final Item shovelSoulstone = new SoulstoneShovel(MaterialsRegister.SOULSTONETOOL);

	public static final Item axeAmethyst = new AmethystAxe(MaterialsRegister.AMETHYSTTOOL);
	public static final Item axeEmberstone = new EmberstoneAxe(MaterialsRegister.EMBERSTONETOOL);
	public static final Item axeEnergistic = new EnergisticAxe(MaterialsRegister.ENERGISTICTOOL);
	public static final Item axeGoofy = new GoofyAxe(MaterialsRegister.GOOFYTOOL);
	public static final Item axeJade = new JadeAxe(MaterialsRegister.JADETOOL);
	public static final Item axeLimonite = new LimoniteAxe(MaterialsRegister.LIMONITETOOL);
	public static final Item axeOccult = new OccultAxe(MaterialsRegister.OCCULTTOOL);
	public static final Item axeOrnamyte = new OrnamyteAxe(MaterialsRegister.ORNAMYTETOOL);
	public static final Item axeRosite = new RositeAxe(MaterialsRegister.ROSITETOOL);
	public static final Item axeSapphire = new SapphireAxe(MaterialsRegister.SAPPHIRETOOL);
	public static final Item axeSkeletal = new SkeletalAxe(MaterialsRegister.SKELETALTOOL);
	public static final Item axeSoulstone = new SoulstoneAxe(MaterialsRegister.SOULSTONETOOL);
	
	public static final Item chainsaw = new Chainsaw(MaterialsRegister.CHAINSAWTOOL);
	
	@SubscribeEvent
	public static void registerWeapon(final RegistryEvent.Register<Item> ev) {
		final IForgeRegistry<Item> registry = ev.getRegistry();

		registry.registerAll(
				axeAmethyst,
				pickaxeAmethyst,
				shovelAmethyst,
				axeEmberstone,
				pickaxeEmberstone,
				shovelEmberstone,
				axeEnergistic,
				pickaxeEnergistic,
				shovelEnergistic,
				axeGoofy,
				pickaxeGoofy,
				shovelGoofy,
				axeJade,
				pickaxeJade,
				shovelJade,
				axeLimonite,
				pickaxeLimonite,
				shovelLimonite,
				axeOccult,
				pickaxeOccult,
				shovelOccult,
				axeOrnamyte,
				pickaxeOrnamyte,
				shovelOrnamyte,
				axeRosite,
				pickaxeRosite,
				shovelRosite,
				axeSapphire,
				pickaxeSapphire,
				shovelSapphire,
				axeSkeletal,
				pickaxeSkeletal,
				shovelSkeletal,
				axeSoulstone,
				pickaxeSoulstone,
				shovelSoulstone,
				pickaxePickmax,
				pickaxeGemcracker,
				chainsaw
		);
	}

	@SubscribeEvent
	public static void registerWeaponRender(final ModelRegistryEvent ev) {
		registerRender(pickaxeAmethyst, "pickaxes/amethyst_pickaxe");
		registerRender(pickaxeEmberstone, "pickaxes/emberstone_pickaxe");
		registerRender(pickaxeEnergistic, "pickaxes/energistic_pickaxe");
		registerRender(pickaxeGemcracker, "pickaxes/gemcracker");
		registerRender(pickaxeGoofy, "pickaxes/goofy_pickaxe");
		registerRender(pickaxeJade, "pickaxes/jade_pickaxe");
		registerRender(pickaxeLimonite, "pickaxes/limonite_pickaxe");
		registerRender(pickaxeOccult, "pickaxes/occult_pickaxe");
		registerRender(pickaxeOrnamyte, "pickaxes/ornamyte_pickaxe");
		registerRender(pickaxePickmax, "pickaxes/pickmax");
		registerRender(pickaxeRosite, "pickaxes/rosite_pickaxe");
		registerRender(pickaxeSapphire, "pickaxes/sapphire_pickaxe");
		registerRender(pickaxeSkeletal, "pickaxes/skeletal_pickaxe");
		registerRender(pickaxeSoulstone, "pickaxes/soulstone_pickaxe");
		registerRender(axeAmethyst, "axes/amethyst_axe");
		registerRender(axeEmberstone, "axes/emberstone_axe");
		registerRender(axeEnergistic, "axes/energistic_axe");
		registerRender(axeGoofy, "axes/goofy_axe");
		registerRender(axeJade, "axes/jade_axe");
		registerRender(axeLimonite, "axes/limonite_axe");
		registerRender(axeOccult, "axes/occult_axe");
		registerRender(axeOrnamyte, "axes/ornamyte_axe");
		registerRender(axeRosite, "axes/rosite_axe");
		registerRender(axeSapphire, "axes/sapphire_axe");
		registerRender(axeSkeletal, "axes/skeletal_axe");
		registerRender(axeSoulstone, "axes/soulstone_axe");
		registerRender(shovelAmethyst, "shovels/amethyst_shovel");
		registerRender(shovelEmberstone, "shovels/emberstone_shovel");
		registerRender(shovelEnergistic, "shovels/energistic_shovel");
		registerRender(shovelGoofy, "shovels/goofy_shovel");
		registerRender(shovelJade, "shovels/jade_shovel");
		registerRender(shovelLimonite, "shovels/limonite_shovel");
		registerRender(shovelOccult, "shovels/occult_shovel");
		registerRender(shovelOrnamyte, "shovels/ornamyte_shovel");
		registerRender(shovelRosite, "shovels/rosite_shovel");
		registerRender(shovelSapphire, "shovels/sapphire_shovel");
		registerRender(shovelSkeletal, "shovels/skeletal_shovel");
		registerRender(shovelSoulstone, "shovels/soulstone_shovel");
		registerRender(chainsaw, "axes/chainsaw");
	}

	private static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:tools/" + location), "inventory"));
	}
}
