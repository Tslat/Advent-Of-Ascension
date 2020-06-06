package net.tslat.aoa3.common.registration;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.armour.*;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({"unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class ArmourRegister {
	public static final Item ACHELOS_HELMET = ObjectHolder();
	public static final Item FACE_MASK = ObjectHolder();
	public static final Item NIGHT_VISION_GOGGLES = ObjectHolder();
	public static final Item OCEANUS_HELMET = ObjectHolder();
	public static final Item SEALORD_HELMET = ObjectHolder();

	public static final Item ALACRITY_BOOTS = ObjectHolder();
	public static final Item ALACRITY_LEGS = ObjectHolder();
	public static final Item ALACRITY_CHESTPLATE = ObjectHolder();
	public static final Item ALACRITY_HELMET = ObjectHolder();

	public static final Item ALCHEMY_BOOTS = ObjectHolder();
	public static final Item ALCHEMY_LEGS = ObjectHolder();
	public static final Item ALCHEMY_CHESTPLATE = ObjectHolder();
	public static final Item ALCHEMY_HELMET = ObjectHolder();

	public static final Item AMETHIND_BOOTS = ObjectHolder();
	public static final Item AMETHIND_LEGS = ObjectHolder();
	public static final Item AMETHIND_CHESTPLATE = ObjectHolder();
	public static final Item AMETHIND_HELMET = ObjectHolder();

	public static final Item ANIMA_BOOTS = ObjectHolder();
	public static final Item ANIMA_LEGS = ObjectHolder();
	public static final Item ANIMA_CHESTPLATE = ObjectHolder();
	public static final Item ANIMA_HELMET = ObjectHolder();

	public static final Item ARCHAIC_BOOTS = ObjectHolder();
	public static final Item ARCHAIC_LEGS = ObjectHolder();
	public static final Item ARCHAIC_CHESTPLATE = ObjectHolder();
	public static final Item ARCHAIC_HELMET = ObjectHolder();

	public static final Item AUGURY_BOOTS = ObjectHolder();
	public static final Item AUGURY_LEGS = ObjectHolder();
	public static final Item AUGURY_CHESTPLATE = ObjectHolder();
	public static final Item AUGURY_HELMET = ObjectHolder();

	public static final Item BARON_BOOTS = ObjectHolder();
	public static final Item BARON_LEGS = ObjectHolder();
	public static final Item BARON_CHESTPLATE = ObjectHolder();
	public static final Item BARON_HELMET = ObjectHolder();

	public static final Item BATTLEBORN_BOOTS = ObjectHolder();
	public static final Item BATTLEBORN_LEGS = ObjectHolder();
	public static final Item BATTLEBORN_CHESTPLATE = ObjectHolder();
	public static final Item BATTLEBORN_HELMET = ObjectHolder();

	public static final Item BIOGENIC_BOOTS = ObjectHolder();
	public static final Item BIOGENIC_LEGS = ObjectHolder();
	public static final Item BIOGENIC_CHESTPLATE = ObjectHolder();
	public static final Item BIOGENIC_HELMET = ObjectHolder();

	public static final Item BOREIC_BOOTS = ObjectHolder();
	public static final Item BOREIC_LEGS = ObjectHolder();
	public static final Item BOREIC_CHESTPLATE = ObjectHolder();
	public static final Item BOREIC_HELMET = ObjectHolder();

	public static final Item BUTCHERY_BOOTS = ObjectHolder();
	public static final Item BUTCHERY_LEGS = ObjectHolder();
	public static final Item BUTCHERY_CHESTPLATE = ObjectHolder();
	public static final Item BUTCHERY_HELMET = ObjectHolder();

	public static final Item CANDY_BOOTS = ObjectHolder();
	public static final Item CANDY_LEGS = ObjectHolder();
	public static final Item CANDY_CHESTPLATE = ObjectHolder();
	public static final Item CANDY_HELMET = ObjectHolder();

	public static final Item COMMANDER_BOOTS = ObjectHolder();
	public static final Item COMMANDER_LEGS = ObjectHolder();
	public static final Item COMMANDER_CHESTPLATE = ObjectHolder();
	public static final Item COMMANDER_HELMET = ObjectHolder();

	public static final Item CREATION_BOOTS = ObjectHolder();
	public static final Item CREATION_LEGS = ObjectHolder();
	public static final Item CREATION_CHESTPLATE = ObjectHolder();
	public static final Item CREATION_HELMET = ObjectHolder();

	public static final Item CRYSTALLIS_BOOTS = ObjectHolder();
	public static final Item CRYSTALLIS_LEGS = ObjectHolder();
	public static final Item CRYSTALLIS_CHESTPLATE = ObjectHolder();
	public static final Item CRYSTALLIS_HELMET = ObjectHolder();

	public static final Item ELECANYTE_BOOTS = ObjectHolder();
	public static final Item ELECANYTE_LEGS = ObjectHolder();
	public static final Item ELECANYTE_CHESTPLATE = ObjectHolder();
	public static final Item ELECANYTE_HELMET = ObjectHolder();

	public static final Item EMBRODIUM_BOOTS = ObjectHolder();
	public static final Item EMBRODIUM_LEGS = ObjectHolder();
	public static final Item EMBRODIUM_CHESTPLATE = ObjectHolder();
	public static final Item EMBRODIUM_HELMET = ObjectHolder();

	public static final Item ENGINEERING_BOOTS = ObjectHolder();
	public static final Item ENGINEERING_LEGS = ObjectHolder();
	public static final Item ENGINEERING_CHESTPLATE = ObjectHolder();
	public static final Item ENGINEERING_HELMET = ObjectHolder();

	public static final Item EXOPLATE_BOOTS = ObjectHolder();
	public static final Item EXOPLATE_LEGS = ObjectHolder();
	public static final Item EXOPLATE_CHESTPLATE = ObjectHolder();
	public static final Item EXOPLATE_HELMET = ObjectHolder();

	public static final Item EXPEDITION_BOOTS = ObjectHolder();
	public static final Item EXPEDITION_LEGS = ObjectHolder();
	public static final Item EXPEDITION_CHESTPLATE = ObjectHolder();
	public static final Item EXPEDITION_HELMET = ObjectHolder();

	public static final Item EXPLOSIVE_BOOTS = ObjectHolder();
	public static final Item EXPLOSIVE_LEGS = ObjectHolder();
	public static final Item EXPLOSIVE_CHESTPLATE = ObjectHolder();
	public static final Item EXPLOSIVE_HELMET = ObjectHolder();

	public static final Item EXTRACTION_BOOTS = ObjectHolder();
	public static final Item EXTRACTION_LEGS = ObjectHolder();
	public static final Item EXTRACTION_CHESTPLATE = ObjectHolder();
	public static final Item EXTRACTION_HELMET = ObjectHolder();

	public static final Item FORAGING_BOOTS = ObjectHolder();
	public static final Item FORAGING_LEGS = ObjectHolder();
	public static final Item FORAGING_CHESTPLATE = ObjectHolder();
	public static final Item FORAGING_HELMET = ObjectHolder();

	public static final Item FUNGAL_BOOTS = ObjectHolder();
	public static final Item FUNGAL_LEGS = ObjectHolder();
	public static final Item FUNGAL_CHESTPLATE = ObjectHolder();
	public static final Item FUNGAL_HELMET = ObjectHolder();

	public static final Item GHASTLY_BOOTS = ObjectHolder();
	public static final Item GHASTLY_LEGS = ObjectHolder();
	public static final Item GHASTLY_CHESTPLATE = ObjectHolder();
	public static final Item GHASTLY_HELMET = ObjectHolder();

	public static final Item GHOULISH_BOOTS = ObjectHolder();
	public static final Item GHOULISH_LEGS = ObjectHolder();
	public static final Item GHOULISH_CHESTPLATE = ObjectHolder();
	public static final Item GHOULISH_HELMET = ObjectHolder();

	public static final Item HAULING_BOOTS = ObjectHolder();
	public static final Item HAULING_LEGS = ObjectHolder();
	public static final Item HAULING_CHESTPLATE = ObjectHolder();
	public static final Item HAULING_HELMET = ObjectHolder();

	public static final Item HAZMAT_BOOTS = ObjectHolder();
	public static final Item HAZMAT_LEGS = ObjectHolder();
	public static final Item HAZMAT_CHESTPLATE = ObjectHolder();
	public static final Item HAZMAT_HELMET = ObjectHolder();

	public static final Item HUNTER_BOOTS = ObjectHolder();
	public static final Item HUNTER_LEGS = ObjectHolder();
	public static final Item HUNTER_CHESTPLATE = ObjectHolder();
	public static final Item HUNTER_HELMET = ObjectHolder();

	public static final Item HYDRANGIC_BOOTS = ObjectHolder();
	public static final Item HYDRANGIC_LEGS = ObjectHolder();
	public static final Item HYDRANGIC_CHESTPLATE = ObjectHolder();
	public static final Item HYDRANGIC_HELMET = ObjectHolder();

	public static final Item HYDROPLATE_BOOTS = ObjectHolder();
	public static final Item HYDROPLATE_LEGS = ObjectHolder();
	public static final Item HYDROPLATE_CHESTPLATE = ObjectHolder();
	public static final Item HYDROPLATE_HELMET = ObjectHolder();

	public static final Item ICE_BOOTS = ObjectHolder();
	public static final Item ICE_LEGS = ObjectHolder();
	public static final Item ICE_CHESTPLATE = ObjectHolder();
	public static final Item ICE_HELMET = ObjectHolder();

	public static final Item INFERNAL_BOOTS = ObjectHolder();
	public static final Item INFERNAL_LEGS = ObjectHolder();
	public static final Item INFERNAL_CHESTPLATE = ObjectHolder();
	public static final Item INFERNAL_HELMET = ObjectHolder();

	public static final Item INFUSION_BOOTS = ObjectHolder();
	public static final Item INFUSION_LEGS = ObjectHolder();
	public static final Item INFUSION_CHESTPLATE = ObjectHolder();
	public static final Item INFUSION_HELMET = ObjectHolder();

	public static final Item INNERVATION_BOOTS = ObjectHolder();
	public static final Item INNERVATION_LEGS = ObjectHolder();
	public static final Item INNERVATION_CHESTPLATE = ObjectHolder();
	public static final Item INNERVATION_HELMET = ObjectHolder();

	public static final Item KNIGHT_BOOTS = ObjectHolder();
	public static final Item KNIGHT_LEGS = ObjectHolder();
	public static final Item KNIGHT_CHESTPLATE = ObjectHolder();
	public static final Item KNIGHT_HELMET = ObjectHolder();

	public static final Item LOGGING_BOOTS = ObjectHolder();
	public static final Item LOGGING_LEGS = ObjectHolder();
	public static final Item LOGGING_CHESTPLATE = ObjectHolder();
	public static final Item LOGGING_HELMET = ObjectHolder();

	public static final Item LUNAR_BOOTS = ObjectHolder();
	public static final Item LUNAR_LEGS = ObjectHolder();
	public static final Item LUNAR_CHESTPLATE = ObjectHolder();
	public static final Item LUNAR_HELMET = ObjectHolder();

	public static final Item LYNDAMYTE_BOOTS = ObjectHolder();
	public static final Item LYNDAMYTE_LEGS = ObjectHolder();
	public static final Item LYNDAMYTE_CHESTPLATE = ObjectHolder();
	public static final Item LYNDAMYTE_HELMET = ObjectHolder();

	public static final Item LYONIC_BOOTS = ObjectHolder();
	public static final Item LYONIC_LEGS = ObjectHolder();
	public static final Item LYONIC_CHESTPLATE = ObjectHolder();
	public static final Item LYONIC_HELMET = ObjectHolder();

	public static final Item MERCURIAL_BOOTS = ObjectHolder();
	public static final Item MERCURIAL_LEGS = ObjectHolder();
	public static final Item MERCURIAL_CHESTPLATE = ObjectHolder();
	public static final Item MERCURIAL_HELMET = ObjectHolder();

	public static final Item NECRO_BOOTS = ObjectHolder();
	public static final Item NECRO_LEGS = ObjectHolder();
	public static final Item NECRO_CHESTPLATE = ObjectHolder();
	public static final Item NECRO_HELMET = ObjectHolder();

	public static final Item NETHENGEIC_BOOTS = ObjectHolder();
	public static final Item NETHENGEIC_LEGS = ObjectHolder();
	public static final Item NETHENGEIC_CHESTPLATE = ObjectHolder();
	public static final Item NETHENGEIC_HELMET = ObjectHolder();

	public static final Item NIGHTMARE_BOOTS = ObjectHolder();
	public static final Item NIGHTMARE_LEGS = ObjectHolder();
	public static final Item NIGHTMARE_CHESTPLATE = ObjectHolder();
	public static final Item NIGHTMARE_HELMET = ObjectHolder();

	public static final Item OMNI_BOOTS = ObjectHolder();
	public static final Item OMNI_LEGS = ObjectHolder();
	public static final Item OMNI_CHESTPLATE = ObjectHolder();
	public static final Item OMNI_HELMET = ObjectHolder();

	public static final Item PHANTASM_BOOTS = ObjectHolder();
	public static final Item PHANTASM_LEGS = ObjectHolder();
	public static final Item PHANTASM_CHESTPLATE = ObjectHolder();
	public static final Item PHANTASM_HELMET = ObjectHolder();

	public static final Item POISON_BOOTS = ObjectHolder();
	public static final Item POISON_LEGS = ObjectHolder();
	public static final Item POISON_CHESTPLATE = ObjectHolder();
	public static final Item POISON_HELMET = ObjectHolder();

	public static final Item PREDATIOUS_BOOTS = ObjectHolder();
	public static final Item PREDATIOUS_LEGS = ObjectHolder();
	public static final Item PREDATIOUS_CHESTPLATE = ObjectHolder();
	public static final Item PREDATIOUS_HELMET = ObjectHolder();

	public static final Item PRIMORDIAL_BOOTS = ObjectHolder();
	public static final Item PRIMORDIAL_LEGS = ObjectHolder();
	public static final Item PRIMORDIAL_CHESTPLATE = ObjectHolder();
	public static final Item PRIMORDIAL_HELMET = ObjectHolder();

	public static final Item PURITY_BOOTS = ObjectHolder();
	public static final Item PURITY_LEGS = ObjectHolder();
	public static final Item PURITY_CHESTPLATE = ObjectHolder();
	public static final Item PURITY_HELMET = ObjectHolder();

	public static final Item ROCKBONE_BOOTS = ObjectHolder();
	public static final Item ROCKBONE_LEGS = ObjectHolder();
	public static final Item ROCKBONE_CHESTPLATE = ObjectHolder();
	public static final Item ROCKBONE_HELMET = ObjectHolder();

	public static final Item ROSIDIAN_BOOTS = ObjectHolder();
	public static final Item ROSIDIAN_LEGS = ObjectHolder();
	public static final Item ROSIDIAN_CHESTPLATE = ObjectHolder();
	public static final Item ROSIDIAN_HELMET = ObjectHolder();

	public static final Item RUNATION_BOOTS = ObjectHolder();
	public static final Item RUNATION_LEGS = ObjectHolder();
	public static final Item RUNATION_CHESTPLATE = ObjectHolder();
	public static final Item RUNATION_HELMET = ObjectHolder();

	public static final Item RUNIC_BOOTS = ObjectHolder();
	public static final Item RUNIC_LEGS = ObjectHolder();
	public static final Item RUNIC_CHESTPLATE = ObjectHolder();
	public static final Item RUNIC_HELMET = ObjectHolder();

	public static final Item SHARPSHOT_BOOTS = ObjectHolder();
	public static final Item SHARPSHOT_LEGS = ObjectHolder();
	public static final Item SHARPSHOT_CHESTPLATE = ObjectHolder();
	public static final Item SHARPSHOT_HELMET = ObjectHolder();

	public static final Item SKELETAL_BOOTS = ObjectHolder();
	public static final Item SKELETAL_LEGS = ObjectHolder();
	public static final Item SKELETAL_CHESTPLATE = ObjectHolder();
	public static final Item SKELETAL_HELMET = ObjectHolder();

	public static final Item SPACEKING_BOOTS = ObjectHolder();
	public static final Item SPACEKING_LEGS = ObjectHolder();
	public static final Item SPACEKING_CHESTPLATE = ObjectHolder();
	public static final Item SPACEKING_HELMET = ObjectHolder();

	public static final Item SPEED_BOOTS = ObjectHolder();
	public static final Item SPEED_LEGS = ObjectHolder();
	public static final Item SPEED_CHESTPLATE = ObjectHolder();
	public static final Item SPEED_HELMET = ObjectHolder();

	public static final Item SUBTERRANEAN_BOOTS = ObjectHolder();
	public static final Item SUBTERRANEAN_LEGS = ObjectHolder();
	public static final Item SUBTERRANEAN_CHESTPLATE = ObjectHolder();
	public static final Item SUBTERRANEAN_HELMET = ObjectHolder();

	public static final Item UTOPIAN_BOOTS = ObjectHolder();
	public static final Item UTOPIAN_LEGS = ObjectHolder();
	public static final Item UTOPIAN_CHESTPLATE = ObjectHolder();
	public static final Item UTOPIAN_HELMET = ObjectHolder();

	public static final Item VOID_BOOTS = ObjectHolder();
	public static final Item VOID_LEGS = ObjectHolder();
	public static final Item VOID_CHESTPLATE = ObjectHolder();
	public static final Item VOID_HELMET = ObjectHolder();

	public static final Item WEAKEN_BOOTS = ObjectHolder();
	public static final Item WEAKEN_LEGS = ObjectHolder();
	public static final Item WEAKEN_CHESTPLATE = ObjectHolder();
	public static final Item WEAKEN_HELMET = ObjectHolder();

	public static final Item WITHER_BOOTS = ObjectHolder();
	public static final Item WITHER_LEGS = ObjectHolder();
	public static final Item WITHER_CHESTPLATE = ObjectHolder();
	public static final Item WITHER_HELMET = ObjectHolder();

	public static final Item ZARGONITE_BOOTS = ObjectHolder();
	public static final Item ZARGONITE_LEGS = ObjectHolder();
	public static final Item ZARGONITE_CHESTPLATE = ObjectHolder();
	public static final Item ZARGONITE_HELMET = ObjectHolder();

	@SubscribeEvent
	public static void registerArmours(final RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> registry = ev.getRegistry();

		registerArmourPieces(registry,
				new AchelosHelmet("AchelosHelmet", "achelos_helmet", EntityEquipmentSlot.HEAD),
				new FaceMask("FaceMask", "face_mask", EntityEquipmentSlot.HEAD),
				new NightVisionGoggles("NightVisionGoggles", "night_vision_goggles", EntityEquipmentSlot.HEAD),
				new OceanusHelmet("OceanusHelmet", "oceanus_helmet", EntityEquipmentSlot.HEAD),
				new SealordHelmet("SealordHelmet", "sealord_helmet", EntityEquipmentSlot.HEAD)
		);

		registerArmourSets(registry,
				new ArmourSet("Alacrity", "alacrity", AlacrityArmour.class),
				new ArmourSet("Alchemy", "alchemy", AlchemyArmour.class),
				new ArmourSet("Amethind", "amethind", AmethindArmour.class),
				new ArmourSet("Anima", "anima", AnimaArmour.class),
				new ArmourSet("Archaic", "archaic", ArchaicArmour.class),
				new ArmourSet("Augury", "augury", AuguryArmour.class),
				new ArmourSet("Baron", "baron", BaronArmour.class),
				new ArmourSet("Battleborn", "battleborn", BattlebornArmour.class),
				new ArmourSet("Biogenic", "biogenic", BiogenicArmour.class),
				new ArmourSet("Boreic", "boreic", BoreicArmour.class),
				new ArmourSet("Butchery", "butchery", ButcheryArmour.class),
				new ArmourSet("Candy", "candy", CandyArmour.class),
				new ArmourSet("Commander", "commander", CommanderArmour.class),
				new ArmourSet("Creation", "creation", CreationArmour.class),
				new ArmourSet("Crystallis", "crystallis", CrystallisArmour.class),
				new ArmourSet("Elecanyte", "elecanyte", ElecanyteArmour.class),
				new ArmourSet("Embrodium", "embrodium", EmbrodiumArmour.class),
				new ArmourSet("Engineering", "engineering", EngineeringArmour.class),
				new ArmourSet("Exoplate", "exoplate", ExoplateArmour.class),
				new ArmourSet("Expedition", "expedition", ExpeditionArmour.class),
				new ArmourSet("Explosive", "explosive", ExplosiveArmour.class),
				new ArmourSet("Extraction", "extraction", ExtractionArmour.class),
				new ArmourSet("Foraging", "foraging", ForagingArmour.class),
				new ArmourSet("Fungal", "fungal", FungalArmour.class),
				new ArmourSet("Ghastly", "ghastly", GhastlyArmour.class),
				new ArmourSet("Ghoulish", "ghoulish", GhoulishArmour.class),
				new ArmourSet("Hauling", "hauling", HaulingArmour.class),
				new ArmourSet("Hazmat", "hazmat", HazmatArmour.class),
				new ArmourSet("Hunter", "hunter", HunterArmour.class),
				new ArmourSet("Hydrangic", "hydrangic", HydrangicArmour.class),
				new ArmourSet("Hydroplate", "hydroplate", HydroplateArmour.class),
				new ArmourSet("Ice", "ice", IceArmour.class),
				new ArmourSet("Infernal", "infernal", InfernalArmour.class),
				new ArmourSet("Infusion", "infusion", InfusionArmour.class),
				new ArmourSet("Innervation", "innervation", InnervationArmour.class),
				new ArmourSet("Knight", "knight", KnightArmour.class),
				new ArmourSet("Logging", "logging", LoggingArmour.class),
				new ArmourSet("Lunar", "lunar", LunarArmour.class),
				new ArmourSet("Lyndamyte", "lyndamyte", LyndamyteArmour.class),
				new ArmourSet("Lyonic", "lyonic", LyonicArmour.class),
				new ArmourSet("Mercurial", "mercurial", MercurialArmour.class),
				new ArmourSet("Necro", "necro", NecroArmour.class),
				new ArmourSet("Nethengeic", "nethengeic", NethengeicArmour.class),
				new ArmourSet("Nightmare", "nightmare", NightmareArmour.class),
				new ArmourSet("Omni", "omni", OmniArmour.class),
				new ArmourSet("Phantasm", "phantasm", PhantasmArmour.class),
				new ArmourSet("Poison", "poison", PoisonArmour.class),
				new ArmourSet("Predatious", "predatious", PredatiousArmour.class),
				new ArmourSet("Primordial", "primordial", PrimordialArmour.class),
				new ArmourSet("Purity", "purity", PurityArmour.class),
				new ArmourSet("Rockbone", "rockbone", RockboneArmour.class),
				new ArmourSet("Rosidian", "rosidian", RosidianArmour.class),
				new ArmourSet("Runation", "runation", RunationArmour.class),
				new ArmourSet("Runic", "runic", RunicArmour.class),
				new ArmourSet("Sharpshot", "sharpshot", SharpshotArmour.class),
				new ArmourSet("Skeletal", "skeletal", SkeletalArmour.class),
				new ArmourSet("Spaceking", "spaceking", SpacekingArmour.class),
				new ArmourSet("Speed", "speed", SpeedArmour.class),
				new ArmourSet("Subterranean", "subterranean", SubterraneanArmour.class),
				new ArmourSet("Utopian", "utopian", UtopianArmour.class),
				new ArmourSet("Void", "void", VoidArmour.class),
				new ArmourSet("Weaken", "weaken", WeakenArmour.class),
				new ArmourSet("Wither", "wither", WitherArmour.class),
				new ArmourSet("Zargonite", "zargonite", ZargoniteArmour.class)
		);
	}

	private static void registerArmourSets(IForgeRegistry<Item> registry, ArmourSet... sets) {
		for (ArmourSet set : sets) {
			ItemRegister.registerItem(registry, set.boots, "armour/" + set.name + "/");
			ItemRegister.registerItem(registry, set.leggings, "armour/" + set.name + "/");
			ItemRegister.registerItem(registry, set.chestplate, "armour/" + set.name + "/");
			ItemRegister.registerItem(registry, set.helmet, "armour/" + set.name + "/");
		}
	}

	private static void registerArmourPieces(IForgeRegistry<Item> registry, AdventArmour... armourPieces) {
		for (AdventArmour armour : armourPieces) {
			ItemRegister.registerItem(registry, armour, "armour/");
		}
	}

	private static class ArmourSet {
		private final AdventArmour helmet;
		private final AdventArmour chestplate;
		private final AdventArmour leggings;
		private final AdventArmour boots;
		private final String name;

		private ArmourSet(String name, String registryName, Class<? extends AdventArmour> clazz) {
			AdventArmour helm = null;
			AdventArmour chest = null;
			AdventArmour legs = null;
			AdventArmour boot = null;
			this.name = name.toLowerCase();

			try {
				Constructor constructor = clazz.getConstructor(String.class, String.class, EntityEquipmentSlot.class);

				helm = (AdventArmour)constructor.newInstance(name + "Helmet", registryName + "_helmet", EntityEquipmentSlot.HEAD);
				chest = (AdventArmour)constructor.newInstance(name + "Chestplate", registryName + "_chestplate", EntityEquipmentSlot.CHEST);
				legs = (AdventArmour)constructor.newInstance(name + "Legs", registryName + "_legs", EntityEquipmentSlot.LEGS);
				boot = (AdventArmour)constructor.newInstance(name + "Boots", registryName + "_boots", EntityEquipmentSlot.FEET);
			}
			catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
				AdventOfAscension.logMessage(Level.WARN, "Somehow we've managed to throw an error while registering armours. I'm really not even sure how this is possible.", ex);
			}
			finally {
				this.helmet = helm;
				this.chestplate = chest;
				this.leggings = legs;
				this.boots = boot;
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Nonnull
	private static <T> T ObjectHolder() {
		return null;
	}
}
