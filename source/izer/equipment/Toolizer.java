package net.nevermine.izer.equipment;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.nevermine.creativetab.ToolsTab;
import net.nevermine.item.tool.axe.*;
import net.nevermine.item.tool.pickaxe.*;
import net.nevermine.item.tool.shovel.*;

public class Toolizer {
	public static CreativeTabs ToolsTab = new ToolsTab(CreativeTabs.getNextID(), "ToolsTab");

	public static Item.ToolMaterial AmethystTool = EnumHelper.addToolMaterial("AMETHYSTTOOL", 4, 1000, 8.0f, 2.0f, 10);
	public static Item.ToolMaterial RositeTool = EnumHelper.addToolMaterial("ROSITETOOL", 4, 2000, 10.5f, 2.0f, 10);
	public static Item.ToolMaterial LimoniteTool = EnumHelper.addToolMaterial("LIMONITETOOL", 4, 400, 6.0f, 2.0f, 10);
	public static Item.ToolMaterial JadeTool = EnumHelper.addToolMaterial("JADETOOL", 4, 4000, 11.0f, 2.0f, 10);
	public static Item.ToolMaterial SapphireTool = EnumHelper.addToolMaterial("SAPPHIRETOOL", 4, 5000, 12.0f, 2.0f, 10);
	public static Item.ToolMaterial EmberstoneTool = EnumHelper.addToolMaterial("EMBERSTONETOOL", 4, 3000, 14.0f, 2.0f, 10);
	public static Item.ToolMaterial SkeletalTool = EnumHelper.addToolMaterial("SKELETALTOOL", 4, 3000, 16.0f, 2.0f, 10);
	public static Item.ToolMaterial ChainsawTool = EnumHelper.addToolMaterial("CHAINSAW", 4, 2500, 13.0F, 14.0F, 10);

	public static Item AmethystPickaxe = new AmethystPickaxe(Toolizer.AmethystTool).setUnlocalizedName("AmethystPickaxe").setTextureName("nevermine:pickaxeAmethyst");
	public static Item AmethystAxe = new AmethystAxe(Toolizer.AmethystTool).setUnlocalizedName("AmethystAxe").setTextureName("nevermine:axeAmethyst");
	public static Item AmethystShovel = new AmethystShovel(Toolizer.AmethystTool).setUnlocalizedName("AmethystShovel").setTextureName("nevermine:shovelAmethyst");

	public static Item LimonitePickaxe = new LimonitePickaxe(Toolizer.LimoniteTool).setUnlocalizedName("LimonitePickaxe").setTextureName("nevermine:pickaxeLimonite");
	public static Item LimoniteAxe = new LimoniteAxe(Toolizer.LimoniteTool).setUnlocalizedName("LimoniteAxe").setTextureName("nevermine:axeLimonite");
	public static Item LimoniteShovel = new LimoniteShovel(Toolizer.LimoniteTool).setUnlocalizedName("LimoniteShovel").setTextureName("nevermine:shovelLimonite");

	public static Item RositePickaxe = new RositePickaxe(Toolizer.RositeTool).setUnlocalizedName("RositePickaxe").setTextureName("nevermine:pickaxeRosite");
	public static Item RositeAxe = new RositeAxe(Toolizer.RositeTool).setUnlocalizedName("RositeAxe").setTextureName("nevermine:axeRosite");
	public static Item RositeShovel = new RositeShovel(Toolizer.RositeTool).setUnlocalizedName("RositeShovel").setTextureName("nevermine:shovelRosite");

	public static Item JadePickaxe = new JadePickaxe(Toolizer.JadeTool).setUnlocalizedName("JadePickaxe").setTextureName("nevermine:pickaxeJade");
	public static Item JadeAxe = new JadeAxe(Toolizer.JadeTool).setUnlocalizedName("JadeAxe").setTextureName("nevermine:axeJade");
	public static Item JadeShovel = new JadeShovel(Toolizer.JadeTool).setUnlocalizedName("JadeShovel").setTextureName("nevermine:shovelJade");

	public static Item SapphirePickaxe = new SapphirePickaxe(Toolizer.SapphireTool).setUnlocalizedName("SapphirePickaxe").setTextureName("nevermine:pickaxeSapphire");
	public static Item SapphireAxe = new SapphireAxe(Toolizer.SapphireTool).setUnlocalizedName("SapphireAxe").setTextureName("nevermine:axeSapphire");
	public static Item SapphireShovel = new SapphireShovel(Toolizer.SapphireTool).setUnlocalizedName("SapphireShovel").setTextureName("nevermine:shovelSapphire");

	public static Item EmberstonePickaxe = new EmberstonePickaxe(Toolizer.EmberstoneTool).setUnlocalizedName("EmberstonePickaxe").setTextureName("nevermine:pickaxeEmberstone");
	public static Item EmberstoneAxe = new EmberstoneAxe(Toolizer.EmberstoneTool).setUnlocalizedName("EmberstoneAxe").setTextureName("nevermine:axeEmberstone");
	public static Item EmberstoneShovel = new EmberstoneShovel(Toolizer.EmberstoneTool).setUnlocalizedName("EmberstoneShovel").setTextureName("nevermine:shovelEmberstone");

	public static Item SkeletalPickaxe = new SkeletalPickaxe(Toolizer.SkeletalTool).setUnlocalizedName("SkeletalPickaxe").setTextureName("nevermine:pickaxeSkeletal");
	public static Item SkeletalAxe = new SkeletalAxe(Toolizer.SkeletalTool).setUnlocalizedName("SkeletalAxe").setTextureName("nevermine:axeSkeletal");
	public static Item SkeletalShovel = new SkeletalShovel(Toolizer.SkeletalTool).setUnlocalizedName("SkeletalShovel").setTextureName("nevermine:shovelSkeletal");

	public static Item OrnamytePickaxe = new OrnamytePickaxe(Toolizer.SkeletalTool).setUnlocalizedName("OrnamytePickaxe").setTextureName("nevermine:pickaxeOrnamyte");
	public static Item OrnamyteAxe = new OrnamyteAxe(Toolizer.SkeletalTool).setUnlocalizedName("OrnamyteAxe").setTextureName("nevermine:axeOrnamyte");
	public static Item OrnamyteShovel = new OrnamyteShovel(Toolizer.SkeletalTool).setUnlocalizedName("OrnamyteShovel").setTextureName("nevermine:shovelOrnamyte");

	public static Item SoulstonePickaxe = new SoulstonePickaxe(Toolizer.SkeletalTool).setUnlocalizedName("SoulstonePickaxe").setTextureName("nevermine:pickaxeSoulstone");
	public static Item SoulstoneAxe = new SoulstoneAxe(Toolizer.SkeletalTool).setUnlocalizedName("SoulstoneAxe").setTextureName("nevermine:axeSoulstone");
	public static Item SoulstoneShovel = new SoulstoneShovel(Toolizer.SkeletalTool).setUnlocalizedName("SoulstoneShovel").setTextureName("nevermine:shovelSoulstone");

	public static Item OccultPickaxe = new OccultPickaxe(Toolizer.SkeletalTool).setUnlocalizedName("OccultPickaxe").setTextureName("nevermine:pickaxeOccult");
	public static Item OccultAxe = new OccultAxe(Toolizer.SkeletalTool).setUnlocalizedName("OccultAxe").setTextureName("nevermine:axeOccult");
	public static Item OccultShovel = new OccultShovel(Toolizer.SkeletalTool).setUnlocalizedName("OccultShovel").setTextureName("nevermine:shovelOccult");

	public static Item GoofyPickaxe = new GoofyPickaxe(Toolizer.SkeletalTool).setUnlocalizedName("GoofyPickaxe").setTextureName("nevermine:pickaxeGoofy");
	public static Item GoofyAxe = new GoofyAxe(Toolizer.SkeletalTool).setUnlocalizedName("GoofyAxe").setTextureName("nevermine:axeGoofy");
	public static Item GoofyShovel = new GoofyShovel(Toolizer.SkeletalTool).setUnlocalizedName("GoofyShovel").setTextureName("nevermine:shovelGoofy");

	public static Item EnergisticPickaxe = new EnergisticPickaxe(Toolizer.SkeletalTool).setUnlocalizedName("EnergisticPickaxe").setTextureName("nevermine:pickaxeEnergistic");
	public static Item EnergisticAxe = new EnergisticAxe(Toolizer.SkeletalTool).setUnlocalizedName("EnergisticAxe").setTextureName("nevermine:axeEnergistic");
	public static Item EnergisticShovel = new EnergisticShovel(Toolizer.SkeletalTool).setUnlocalizedName("EnergisticShovel").setTextureName("nevermine:shovelEnergistic");

	public static Item Chainsaw = new Chainsaw(ChainsawTool).setUnlocalizedName("Chainsaw").setTextureName("nevermine:animateditem/chainsaw");

	public static Item Pickmax = new Pickmax(RositeTool).setUnlocalizedName("Pickmax").setTextureName("nevermine:pickaxePickmax");
	public static Item Gemcracker = new Gemcracker(JadeTool).setUnlocalizedName("Gemcracker").setTextureName("nevermine:pickaxeGemcracker");
}
