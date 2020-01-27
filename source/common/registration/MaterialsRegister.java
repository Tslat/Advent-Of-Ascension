package net.tslat.aoa3.common.registration;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

@SuppressWarnings("ConstantConditions")
public class MaterialsRegister {
	public static final Item.ToolMaterial SWORD_AMETHYST 		= EnumHelper.addToolMaterial("AOAAMETHYST", 			4, 1200, 	4.0f, 	7.5f, 	14).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("amethyst")));
	public static final Item.ToolMaterial SWORD_BARON 			= EnumHelper.addToolMaterial("AOABARON", 			4, 1600, 	4.0f, 	12f, 	10).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("baronyte_ingot")));
	public static final Item.ToolMaterial SWORD_BLOODFURY 		= EnumHelper.addToolMaterial("AOABLOODFURY", 		4, 1770, 	4.0f, 	13.0f, 10);
	public static final Item.ToolMaterial SWORD_BLOODSTONE 		= EnumHelper.addToolMaterial("AOABLOODSTONE", 		4, 1700, 	4.0f, 	14.5f, 16);
	public static final Item.ToolMaterial SWORD_CANDLEFIRE 		= EnumHelper.addToolMaterial("AOACANDLEFIRE", 		4, 2670, 	4.0f, 	17.0f, 10);
	public static final Item.ToolMaterial SWORD_CARAMEL_CARVER 	= EnumHelper.addToolMaterial("AOACARAMELCARVER", 	4, 1900, 	4.0f, 	15.5f, 10);
	public static final Item.ToolMaterial SWORD_CORALSTORM 		= EnumHelper.addToolMaterial("AOACORALSTORM", 		4, 1700, 	4.0f, 	8.0f, 10);
	public static final Item.ToolMaterial SWORD_CREEPIFIED 		= EnumHelper.addToolMaterial("AOACREEPIFIED", 		4, 2000, 	4.0f, 	13.5f, 10);
	public static final Item.ToolMaterial SWORD_CRYSTALLITE 	= EnumHelper.addToolMaterial("AOACRYSTALLITE", 		4, 1800, 	4.0f, 	14.0f, 10).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("crystallite")));
	public static final Item.ToolMaterial SWORD_EMBERSTONE 		= EnumHelper.addToolMaterial("AOAEMBERSTONE", 		4, 1800, 	4.0f, 	11.0f, 10).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("emberstone_ingot")));
	public static final Item.ToolMaterial SWORD_EXPLOCHRON 		= EnumHelper.addToolMaterial("AOAEXPLOCHRON", 		4, 1800, 	4.0f, 	15.0f, 10);
	public static final Item.ToolMaterial SWORD_FIREBORNE 		= EnumHelper.addToolMaterial("AOAFIREBORNE", 		4, 1630, 	4.0f, 	12.0f, 10);
	public static final Item.ToolMaterial SWORD_GUARDIAN 		= EnumHelper.addToolMaterial("AOAGUARDIAN", 			4, 2050, 	4.0f, 	15.0f, 10);
	public static final Item.ToolMaterial SWORD_HARVESTER 		= EnumHelper.addToolMaterial("AOAHARVESTER", 		4, 2400, 	4.0f, 	17.5f, 10);
	public static final Item.ToolMaterial SWORD_HOLY 			= EnumHelper.addToolMaterial("AOAHOLY", 				4, 1000, 	4.0f, 	0.0f, 	10);
	public static final Item.ToolMaterial SWORD_ILLUSION 		= EnumHelper.addToolMaterial("AOAILLUSION", 			4, 1900, 	4.0f, 	14.5f, 10);
	public static final Item.ToolMaterial SWORD_JADE 			= EnumHelper.addToolMaterial("AOAJADE", 				3, 800, 	4.0f, 	7.0f, 	11).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("jade")));
	public static final Item.ToolMaterial SWORD_LEGBONE 		= EnumHelper.addToolMaterial("AOALEGBONE", 			4, 2000, 	4.0f, 	12.5f, 10);
	public static final Item.ToolMaterial SWORD_LIGHTS_WAY 		= EnumHelper.addToolMaterial("AOALIGHTSWAY", 		4, 2700, 	4.0f, 	8.5f, 10);
	public static final Item.ToolMaterial SWORD_LIMONITE 		= EnumHelper.addToolMaterial("AOALIMONITE", 			2, 400, 	4.0f, 	5.0f, 	3).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("limonite_ingot")));
	public static final Item.ToolMaterial SWORD_NETHENGEIC 		= EnumHelper.addToolMaterial("AOANETHENGEIC", 		4, 2040, 	4.0f, 	14.0f, 10);
	public static final Item.ToolMaterial SWORD_PRIMAL	 		= EnumHelper.addToolMaterial("AOAPRIMAL", 			4, 1960, 	4.0f, 	13.0f, 10);
	public static final Item.ToolMaterial SWORD_ROCKBASHER 		= EnumHelper.addToolMaterial("AOAROCKBASHER", 		4, 2020, 	4.0f, 	16.0f, 10);
	public static final Item.ToolMaterial SWORD_ROCKPICK 		= EnumHelper.addToolMaterial("AOAROCKPICK", 			4, 1950, 	8.0f, 	12.0f, 10);
	public static final Item.ToolMaterial SWORD_ROSIDIAN 		= EnumHelper.addToolMaterial("AOAROSIDIAN", 			4, 2000, 	4.0f, 	15.5f, 10);
	public static final Item.ToolMaterial SWORD_ROSITE 			= EnumHelper.addToolMaterial("AOAROSITE", 			3, 700, 	4.0f, 	6.5f, 	6).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("rosite_ingot")));
	public static final Item.ToolMaterial SWORD_RUNIC 			= EnumHelper.addToolMaterial("AOARUNIC", 			4, 2450, 	4.0f, 	17.5f, 10);
	public static final Item.ToolMaterial SWORD_SAPPHIRE 		= EnumHelper.addToolMaterial("AOASAPPHIRE", 			4, 1750, 	4.0f, 	10.0f, 19).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("sapphire")));
	public static final Item.ToolMaterial SWORD_SHADOW 			= EnumHelper.addToolMaterial("AOASHADOW", 			4, 2300, 	4.0f, 	17.0f, 10);
	public static final Item.ToolMaterial SWORD_SHROOMUS 		= EnumHelper.addToolMaterial("AOASHROOMUS", 			4, 2030, 	4.0f, 	15.0f, 10);
	public static final Item.ToolMaterial SWORD_SKELETAL 		= EnumHelper.addToolMaterial("AOASKELETAL", 			4, 2100, 	4.0f, 	10.5f, 10).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("skeletal_ingot")));
	public static final Item.ToolMaterial SWORD_SUPREMACY 		= EnumHelper.addToolMaterial("AOASUPREMACY", 		4, 1810, 	4.0f, 	14.5f, 10);
	public static final Item.ToolMaterial SWORD_SWEET 			= EnumHelper.addToolMaterial("AOASWEET", 			4, 1850, 	4.0f, 	15.0f, 10);
	public static final Item.ToolMaterial TROLL_BASHER_AXE 		= EnumHelper.addToolMaterial("AOATROLLBASHERAXE", 	4, 1800, 	4.0f, 	12.0f, 10);
	public static final Item.ToolMaterial SWORD_ULTRAFLAME 		= EnumHelper.addToolMaterial("AOAULTRAFLAME", 		4, 2250, 	4.0f, 	16.5f, 10);
	public static final Item.ToolMaterial SWORD_VOID 			= EnumHelper.addToolMaterial("AOAVOID", 				3, 1800, 	4.0f, 	10.5f, 10);

	public static final Item.ToolMaterial TOOL_AMETHYST 		= EnumHelper.addToolMaterial("AOAAMETHYSTTOOL", 		4, 1200, 	8.0f, 	4.5f, 	14).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("amethyst")));
	public static final Item.ToolMaterial TOOL_CHAINSAW 		= EnumHelper.addToolMaterial("AOACHAINSAWTOOL", 		2, 2500, 	18.0f, 	4.0f, 0);
	public static final Item.ToolMaterial TOOL_EMBERSTONE 		= EnumHelper.addToolMaterial("AOAEMBERSTONETOOL", 	6, 2000, 	10.0f, 	5.5f, 	10).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("emberstone_ingot")));
	public static final Item.ToolMaterial TOOL_ENERGISTIC 		= EnumHelper.addToolMaterial("AOAENERGISTICTOOL", 	6, 2000, 	11.0f, 	6.0f, 	10);
	public static final Item.ToolMaterial TOOL_GEMCRACKER 		= EnumHelper.addToolMaterial("AOAGEMCRACKER", 		6, 2100, 	10.0f, 	6.0f, 	10);
	public static final Item.ToolMaterial TOOL_GOOFY 			= EnumHelper.addToolMaterial("AOAGOOFYTOOL", 		4, 1500, 	8.0f, 	0.0f, 12);
	public static final Item.ToolMaterial TOOL_JADE 			= EnumHelper.addToolMaterial("AOAJADETOOL", 			3, 650, 	7.5f, 	4.0f, 	11).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("jade")));
	public static final Item.ToolMaterial TOOL_LIMONITE 		= EnumHelper.addToolMaterial("AOALIMONITETOOL", 		2, 350, 	5f, 		2.0f, 	3).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("limonite_ingot")));
	public static final Item.ToolMaterial TOOL_OCCULT 			= EnumHelper.addToolMaterial("AOAOCCULTTOOL", 		6, 3000, 	11.0f, 	6.0f, 	10);
	public static final Item.ToolMaterial TOOL_ORNAMYTE 		= EnumHelper.addToolMaterial("AOAORNAMYTETOOL", 		6, 2750, 	10.0f, 	6.0f, 	14);
	public static final Item.ToolMaterial TOOL_PICKMAX 			= EnumHelper.addToolMaterial("AOAPICKMAX", 			6, 3000, 	8.0f, 	6.0f, 	10);
	public static final Item.ToolMaterial TOOL_ROSITE 			= EnumHelper.addToolMaterial("AOAROSITETOOL", 		3, 600, 	6.5f, 	3.5f, 	6).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("rosite_ingot")));
	public static final Item.ToolMaterial TOOL_SAPPHIRE 		= EnumHelper.addToolMaterial("AOASAPPHIRETOOL", 		5, 2500, 	9.0f, 	5.0f, 	19).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("sapphire")));
	public static final Item.ToolMaterial TOOL_SKELETAL 		= EnumHelper.addToolMaterial("AOASKELETALTOOL", 		6, 2000, 	10.0f, 	6.0f, 	10).setRepairItem(new ItemStack(ItemRegister.getUnmappedItem("skeletal_ingot")));
	public static final Item.ToolMaterial TOOL_SOULSTONE 		= EnumHelper.addToolMaterial("AOASOULSTONETOOL", 	6, 2000, 	11.0f, 	6.0f, 	10);

	public static final ItemArmor.ArmorMaterial ARMOUR_ACHELOS_HELMET 			= EnumHelper.addArmorMaterial("AOAARMOURACHELOS", 			"aoa3:achelos_helmet", 			40, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // Overworld | Corallus
	public static final ItemArmor.ArmorMaterial ARMOUR_ALACRITY 				= EnumHelper.addArmorMaterial("AOAARMOURALACRITY", 			"aoa3:alacrity", 				55, new int[] {4, 8, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Haven | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ALCHEMY 					= EnumHelper.addArmorMaterial("AOAARMOURALCHEMY", 			"aoa3:alchemy", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Alchemy Master
	public static final ItemArmor.ArmorMaterial ARMOUR_AMETHIND 				= EnumHelper.addArmorMaterial("AOAARMOURAMETHIND", 			"aoa3:amethind", 				25, new int[] {3, 6, 7, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // Overworld | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ANIMA 					= EnumHelper.addArmorMaterial("AOAARMOURANIMA", 				"aoa3:anima", 					65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Anima Master
	public static final ItemArmor.ArmorMaterial ARMOUR_ARCHAIC 					= EnumHelper.addArmorMaterial("AOAARMOURARCHAIC", 			"aoa3:archaic", 				67, new int[] {5, 9, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Immortallis | Flash
	public static final ItemArmor.ArmorMaterial ARMOUR_AUGURY 					= EnumHelper.addArmorMaterial("AOAARMOURAUGURY", 			"aoa3:augury", 					65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Augury Master
	public static final ItemArmor.ArmorMaterial ARMOUR_BARON 					= EnumHelper.addArmorMaterial("AOAARMOURBARON", 				"aoa3:baron", 					150, new int[] {4, 6, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Barathos | Hive King
	public static final ItemArmor.ArmorMaterial ARMOUR_BATTLEBORN 				= EnumHelper.addArmorMaterial("AOAARMOURBATTLEBORN", 		"aoa3:battleborn", 				65, new int[] {4, 8, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Dustopia | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_BIOGENIC 				= EnumHelper.addArmorMaterial("AOAARMOURBIOGENIC", 			"aoa3:biogenic", 				38, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Overworld | Guardians
	public static final ItemArmor.ArmorMaterial ARMOUR_BOREIC 					= EnumHelper.addArmorMaterial("AOAARMOURBOREIC", 			"aoa3:boreic", 					62, new int[] {4, 8, 10, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // LBorean | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_BUTCHERY 				= EnumHelper.addArmorMaterial("AOAARMOURBUTCHERY", 			"aoa3:butchery", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Butchery Master
	public static final ItemArmor.ArmorMaterial ARMOUR_CANDY 					= EnumHelper.addArmorMaterial("AOAARMOURCANDY", 				"aoa3:candy", 					59, new int[] {4, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Candyland | Cotton Candor
	public static final ItemArmor.ArmorMaterial ARMOUR_COMMANDER 				= EnumHelper.addArmorMaterial("AOAARMOURCOMMANDER", 			"aoa3:commander", 				62, new int[] {4, 9, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Runandor | Rune Templars
	public static final ItemArmor.ArmorMaterial ARMOUR_CREATION 				= EnumHelper.addArmorMaterial("AOAARMOURCREATION", 			"aoa3:creation", 				65, new int[] {5, 8, 8, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Creation Master
	public static final ItemArmor.ArmorMaterial ARMOUR_CRYSTALLIS 				= EnumHelper.addArmorMaterial("AOAARMOURCRYSTALLIS", 		"aoa3:crystallis", 				56, new int[] {5, 6, 10, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Crystevia | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ELECANYTE 				= EnumHelper.addArmorMaterial("AOAARMOURELECANYTE", 			"aoa3:elecanyte", 				63, new int[] {4, 8, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Runandor | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_EMBRODIUM 				= EnumHelper.addArmorMaterial("AOAARMOUREMBRODIUM", 			"aoa3:embrodium", 				45, new int[] {4, 7, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Barathos | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ENGINEERING 				= EnumHelper.addArmorMaterial("AOAARMOURENGINEERING", 		"aoa3:engineering", 			60, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Engineering Master
	public static final ItemArmor.ArmorMaterial ARMOUR_EXOPLATE 				= EnumHelper.addArmorMaterial("AOAARMOUREXOPLATE", 			"aoa3:exoplate", 				46, new int[] {4, 6, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Deeplands | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_EXPEDITION 				= EnumHelper.addArmorMaterial("AOAARMOUREXPEDITION", 		"aoa3:expedition", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Expedition Master
	public static final ItemArmor.ArmorMaterial ARMOUR_EXPLOSIVE 				= EnumHelper.addArmorMaterial("AOAARMOUREXPLOSIVE", 			"aoa3:explosive", 				48, new int[] {4, 7, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Nether | King BamBamBam
	public static final ItemArmor.ArmorMaterial ARMOUR_EXTRACTION 				= EnumHelper.addArmorMaterial("AOAARMOUREXTRACTION", 		"aoa3:extraction", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Extraction Master
	public static final ItemArmor.ArmorMaterial ARMOUR_FACE_MASK 				= EnumHelper.addArmorMaterial("AOAARMOURFACEMASK", 			"aoa3:face_mask", 				36, new int[] {4, 7, 8, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Vox Ponds | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_FORAGING 				= EnumHelper.addArmorMaterial("AOAARMOURFORAGING", 			"aoa3:foraging", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Foraging Master
	public static final ItemArmor.ArmorMaterial ARMOUR_FUNGAL 					= EnumHelper.addArmorMaterial("AOAARMOURFUNGAL", 			"aoa3:fungal", 					50, new int[] {5, 6, 8, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Mysterium | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_GHASTLY 					= EnumHelper.addArmorMaterial("AOAARMOURGHASTLY", 			"aoa3:ghastly", 				62, new int[] {5, 8, 8, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Greckon | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_GHOULISH 				= EnumHelper.addArmorMaterial("AOAARMOURGHOULISH", 			"aoa3:ghoulish", 				61, new int[] {6, 6, 8, 6}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Greckon | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_HAULING 					= EnumHelper.addArmorMaterial("AOAARMOURHAULING", 			"aoa3:hauling", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Hauling Master
	public static final ItemArmor.ArmorMaterial ARMOUR_HAZMAT 					= EnumHelper.addArmorMaterial("AOAARMOURHAZMAT", 			"aoa3:hazmat", 					30, new int[] {2, 5, 6, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0); // Overworld | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_HUNTER 					= EnumHelper.addArmorMaterial("AOAARMOURHUNTER", 			"aoa3:hunter", 					65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Hunter Master
	public static final ItemArmor.ArmorMaterial ARMOUR_HYDRANGIC 				= EnumHelper.addArmorMaterial("AOAARMOURHYDRANGIC", 			"aoa3:hydrangic", 				54, new int[] {4, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Gardencia | Petal Crafting Station
	public static final ItemArmor.ArmorMaterial ARMOUR_HYDROPLATE 				= EnumHelper.addArmorMaterial("AOAARMOURHYDROPLATE", 		"aoa3:hydroplate", 				66, new int[] {4, 8, 10, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // LBorean | Hydrolisk
	public static final ItemArmor.ArmorMaterial ARMOUR_ICE 						= EnumHelper.addArmorMaterial("AOAARMOURICE", 				"aoa3:ice", 					12, new int[] {2, 6, 6, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // Overworld | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_INFERNAL 				= EnumHelper.addArmorMaterial("AOAARMOURINFERNAL", 			"aoa3:infernal", 				39, new int[] {4, 7, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Nether | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_INFUSION 				= EnumHelper.addArmorMaterial("AOAARMOURINFUSION", 			"aoa3:infusion", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Infusion Master
	public static final ItemArmor.ArmorMaterial ARMOUR_INNERVATION 				= EnumHelper.addArmorMaterial("AOAARMOURINNERVATION", 		"aoa3:innervation", 			65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Innervation Master
	public static final ItemArmor.ArmorMaterial ARMOUR_KNIGHT 					= EnumHelper.addArmorMaterial("AOAARMOURKNIGHT", 			"aoa3:knight", 					70, new int[] {4, 8, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Shyrelands | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_LOGGING 					= EnumHelper.addArmorMaterial("AOAARMOURLOGGING", 			"aoa3:logging", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Logging Master
	public static final ItemArmor.ArmorMaterial ARMOUR_LUNAR 					= EnumHelper.addArmorMaterial("AOAARMOURLUNAR", 				"aoa3:lunar", 					63, new int[] {4, 7, 10, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Lunalus | Lunar Creation Platform
	public static final ItemArmor.ArmorMaterial ARMOUR_LYNDAMYTE 				= EnumHelper.addArmorMaterial("AOAARMOURLYNDAMYTE", 			"aoa3:lyndamyte", 				35, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // Nether | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_LYONIC 					= EnumHelper.addArmorMaterial("AOAARMOURLYONIC", 			"aoa3:lyonic", 					56, new int[] {4, 7, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Iromine | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_MERCURIAL 				= EnumHelper.addArmorMaterial("AOAARMOURMERCURIAL", 			"aoa3:mercurial", 				42, new int[] {3, 8, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Creeponia | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_NECRO 					= EnumHelper.addArmorMaterial("AOAARMOURNECRO", 				"aoa3:necro", 					64, new int[] {5, 8, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Greckon | Faceless Floater
	public static final ItemArmor.ArmorMaterial ARMOUR_NETHENGEIC 				= EnumHelper.addArmorMaterial("AOAARMOURNETHENGEIC", 		"aoa3:nethengeic", 				41, new int[] {3, 7, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Nether | Nethengeic Beast
	public static final ItemArmor.ArmorMaterial ARMOUR_NIGHTMARE 				= EnumHelper.addArmorMaterial("AOAARMOURNIGHTMARE", 			"aoa3:nightmare", 				63, new int[] {4, 9, 8, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Greckon | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_NIGHT_VISION_GOGGLES 	= EnumHelper.addArmorMaterial("AOAARMOURNIGHTVISIONGOGGLES",	"aoa3:night_vision_goggles", 	27, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1); // Overworld | Crafting Recipe
	public static final ItemArmor.ArmorMaterial ARMOUR_OCEANUS_HELMET 			= EnumHelper.addArmorMaterial("AOAARMOUROCEANUS", 			"aoa3:oceanus_helmet", 			40, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // Overworld | Corallus
	public static final ItemArmor.ArmorMaterial ARMOUR_OMNI 					= EnumHelper.addArmorMaterial("AOAARMOUROMNI", 				"aoa3:omni", 					50, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1); // Creeponia | Explosives Expert
	public static final ItemArmor.ArmorMaterial ARMOUR_PHANTASM 				= EnumHelper.addArmorMaterial("AOAARMOURPHANTASM", 			"aoa3:phantasm", 				51, new int[] {3, 8, 8, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Mysterium | Gorb Engineer
	public static final ItemArmor.ArmorMaterial ARMOUR_POISON 					= EnumHelper.addArmorMaterial("AOAARMOURPOISON", 			"aoa3:poison", 					56, new int[] {5, 6, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Vox Ponds | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_PREDATIOUS 				= EnumHelper.addArmorMaterial("AOAARMOURPREDATIOUS", 		"aoa3:predatious", 				51, new int[] {3, 7, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Precasia | Tyrosaur
	public static final ItemArmor.ArmorMaterial ARMOUR_PRIMORDIAL 				= EnumHelper.addArmorMaterial("AOAARMOURPRIMORDIAL", 		"aoa3:primordial", 				62, new int[] {5, 8, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Dustopia | Primordial Merchant
	public static final ItemArmor.ArmorMaterial ARMOUR_PURITY 					= EnumHelper.addArmorMaterial("AOAARMOURPURITY", 			"aoa3:purity", 					61, new int[] {5, 8, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Runandor | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ROCKBONE 				= EnumHelper.addArmorMaterial("AOAARMOURROCKBONE", 			"aoa3:rockbone", 				45, new int[] {3, 7, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Deeplands | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ROSIDIAN 				= EnumHelper.addArmorMaterial("AOAARMOURROSIDIAN", 			"aoa3:rosidian", 				55, new int[] {4, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Gardencia | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_RUNATION 				= EnumHelper.addArmorMaterial("AOAARMOURRUNATION", 			"aoa3:runation", 				65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Runation Master
	public static final ItemArmor.ArmorMaterial ARMOUR_RUNIC 					= EnumHelper.addArmorMaterial("AOAARMOURRUNIC", 				"aoa3:runic", 					67, new int[] {5, 8, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Runandor | Clunkhead
	public static final ItemArmor.ArmorMaterial ARMOUR_SEALORD_HELMET 			= EnumHelper.addArmorMaterial("AOAARMOURSEALORD", 			"aoa3:sealord_helmet", 			60, new int[] {5, 7, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // LBorean | Dracyon
	public static final ItemArmor.ArmorMaterial ARMOUR_SHARPSHOT 				= EnumHelper.addArmorMaterial("AOAARMOURSHARPSHOT", 			"aoa3:sharpshot", 				54, new int[] {4, 6, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Celeve | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_SKELETAL 				= EnumHelper.addArmorMaterial("AOAARMOURSKELETAL", 			"aoa3:skeletal", 				43, new int[] {3, 7, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Precasia | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_SPACEKING 				= EnumHelper.addArmorMaterial("AOAARMOURSPACEKING", 			"aoa3:spaceking", 				62, new int[] {4, 8, 9, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Lunalus | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_SPEED 					= EnumHelper.addArmorMaterial("AOAARMOURSPEED", 				"aoa3:speed", 					63, new int[] {4, 9, 9, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Runandor | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_SUBTERRANEAN 			= EnumHelper.addArmorMaterial("AOAARMOURSUBTERRANEAN", 		"aoa3:subterranean", 			47, new int[] {3, 7, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Deeplands | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_UTOPIAN 					= EnumHelper.addArmorMaterial("AOAARMOURUTOPIAN", 			"aoa3:utopian", 				50, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // SPECIAL | Treasure Box
	public static final ItemArmor.ArmorMaterial ARMOUR_VOID 					= EnumHelper.addArmorMaterial("AOAARMOURVOID", 				"aoa3:void", 					25, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2); // Overworld | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_WEAKEN 					= EnumHelper.addArmorMaterial("AOAARMOURWEAKEN", 			"aoa3:weaken", 					44, new int[] {4, 6, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3); // Lelyetia | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_WITHER 					= EnumHelper.addArmorMaterial("AOAARMOURWITHER", 			"aoa3:wither", 					53, new int[] {4, 8, 8, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5); // Abyss | Crafting
	public static final ItemArmor.ArmorMaterial ARMOUR_ZARGONITE 				= EnumHelper.addArmorMaterial("AOAARMOURZARGONITE", 			"aoa3:zargonite", 				64, new int[] {5, 8, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7); // Lunalus | Zarg
}
