package net.tslat.aoa3.common.registration;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.item.armour.*;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public final class AoAArmour {
	public static final DeferredRegister<Item> ARMOUR = DeferredRegister.create(ForgeRegistries.ITEMS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Item> ACHELOS_HELMET = registerArmour("achelos_helmet", AchelosHelmet::new);
	public static final RegistryObject<Item> OCEANUS_HELMET = registerArmour("oceanus_helmet", OceanusHelmet::new);
	public static final RegistryObject<Item> SEALORD_HELMET = registerArmour("sealord_helmet", SealordHelmet::new);
	public static final RegistryObject<Item> FACE_MASK = registerArmour("face_mask", FaceMask::new);
	public static final RegistryObject<Item> NIGHT_VISION_GOGGLES = registerArmour("night_vision_goggles", NightVisionGoggles::new);
	
	public static final ArmourSet ALACRITY_ARMOUR = registerArmourSet("alacrity", AlacrityArmour.class);
	public static final ArmourSet AMETHIND_ARMOUR = registerArmourSet("amethind", AmethindArmour.class);
	public static final ArmourSet ARCHAIC_ARMOUR = registerArmourSet("archaic", ArchaicArmour.class);
	public static final ArmourSet BARON_ARMOUR = registerArmourSet("baron", BaronArmour.class);
	public static final ArmourSet BATTLEBORN_ARMOUR = registerArmourSet("battleborn", BattlebornArmour.class);
	public static final ArmourSet BIOGENIC_ARMOUR = registerArmourSet("biogenic", BiogenicArmour.class);
	public static final ArmourSet BOREIC_ARMOUR = registerArmourSet("boreic", BoreicArmour.class);
	public static final ArmourSet CANDY_ARMOUR = registerArmourSet("candy", CandyArmour.class);
	public static final ArmourSet COMMANDER_ARMOUR = registerArmourSet("commander", CommanderArmour.class);
	public static final ArmourSet CRYSTALLIS_ARMOUR = registerArmourSet("crystallis", CrystallisArmour.class);
	public static final ArmourSet ELECANYTE_ARMOUR = registerArmourSet("elecanyte", ElecanyteArmour.class);
	public static final ArmourSet EMBRODIUM_ARMOUR = registerArmourSet("embrodium", EmbrodiumArmour.class);
	public static final ArmourSet EXOPLATE_ARMOUR = registerArmourSet("exoplate", ExoplateArmour.class);
	public static final ArmourSet EXPLOSIVE_ARMOUR = registerArmourSet("explosive", ExplosiveArmour.class);
	public static final ArmourSet FUNGAL_ARMOUR = registerArmourSet("fungal", FungalArmour.class);
	public static final ArmourSet GHASTLY_ARMOUR = registerArmourSet("ghastly", GhastlyArmour.class);
	public static final ArmourSet GHOULISH_ARMOUR = registerArmourSet("ghoulish", GhoulishArmour.class);
	public static final ArmourSet HAZMAT_ARMOUR = registerArmourSet("hazmat", HazmatArmour.class);
	public static final ArmourSet HYDRANGIC_ARMOUR = registerArmourSet("hydrangic", HydrangicArmour.class);
	public static final ArmourSet HYDROPLATE_ARMOUR = registerArmourSet("hydroplate", HydroplateArmour.class);
	public static final ArmourSet ICE_ARMOUR = registerArmourSet("ice", IceArmour.class);
	public static final ArmourSet INFERNAL_ARMOUR = registerArmourSet("infernal", InfernalArmour.class);
	public static final ArmourSet KNIGHT_ARMOUR = registerArmourSet("knight", KnightArmour.class);
	public static final ArmourSet LUNAR_ARMOUR = registerArmourSet("lunar", LunarArmour.class);
	public static final ArmourSet LYNDAMYTE_ARMOUR = registerArmourSet("lyndamyte", LyndamyteArmour.class);
	public static final ArmourSet LYONIC_ARMOUR = registerArmourSet("lyonic", LyonicArmour.class);
	public static final ArmourSet MERCURIAL_ARMOUR = registerArmourSet("mercurial", MercurialArmour.class);
	public static final ArmourSet NECRO_ARMOUR = registerArmourSet("necro", NecroArmour.class);
	public static final ArmourSet NETHENGEIC_ARMOUR = registerArmourSet("nethengeic", NethengeicArmour.class);
	public static final ArmourSet NIGHTMARE_ARMOUR = registerArmourSet("nightmare", NightmareArmour.class);
	public static final ArmourSet OMNI_ARMOUR = registerArmourSet("omni", OmniArmour.class);
	public static final ArmourSet PHANTASM_ARMOUR = registerArmourSet("phantasm", PhantasmArmour.class);
	public static final ArmourSet POISON_ARMOUR = registerArmourSet("poison", PoisonArmour.class);
	public static final ArmourSet PREDATIOUS_ARMOUR = registerArmourSet("predatious", PredatiousArmour.class);
	public static final ArmourSet PRIMORDIAL_ARMOUR = registerArmourSet("primordial", PrimordialArmour.class);
	public static final ArmourSet PURITY_ARMOUR = registerArmourSet("purity", PurityArmour.class);
	public static final ArmourSet ROCKBONE_ARMOUR = registerArmourSet("rockbone", RockboneArmour.class);
	public static final ArmourSet ROSIDIAN_ARMOUR = registerArmourSet("rosidian", RosidianArmour.class);
	public static final ArmourSet RUNIC_ARMOUR = registerArmourSet("runic", RunicArmour.class);
	public static final ArmourSet SHARPSHOT_ARMOUR = registerArmourSet("sharpshot", SharpshotArmour.class);
	public static final ArmourSet SKELETAL_ARMOUR = registerArmourSet("skeletal", SkeletalArmour.class);
	public static final ArmourSet SPACEKING_ARMOUR = registerArmourSet("spaceking", SpacekingArmour.class);
	public static final ArmourSet SPEED_ARMOUR = registerArmourSet("speed", SpeedArmour.class);
	public static final ArmourSet SUBTERRANEAN_ARMOUR = registerArmourSet("subterranean", SubterraneanArmour.class);
	public static final ArmourSet UTOPIAN_ARMOUR = registerArmourSet("utopian", UtopianArmour.class);
	public static final ArmourSet VOID_ARMOUR = registerArmourSet("void", VoidArmour.class);
	public static final ArmourSet WEAKEN_ARMOUR = registerArmourSet("weaken", WeakenArmour.class);
	public static final ArmourSet WITHER_ARMOUR = registerArmourSet("wither", WitherArmour.class);
	public static final ArmourSet ZARGONITE_ARMOUR = registerArmourSet("zargonite", ZargoniteArmour.class);

	//public static final RegistryObject<SkillHelmet> HELM_OF_THE_ALCHEMIST = registerArmour("helm_of_the_alchemist", () -> new SkillHelmet(AoASkills.ALCHEMY));
	//public static final RegistryObject<SkillHelmet> HELM_OF_THE_CREATOR = registerArmour("helm_of_the_creator", () -> new SkillHelmet(AoASkills.CREATION));
	public static final RegistryObject<SkillHelmet> HELM_OF_THE_DEXTROUS = registerArmour("helm_of_the_dextrous", () -> new SkillHelmet(AoASkills.DEXTERITY));
	public static final RegistryObject<SkillHelmet> HELM_OF_THE_DRYAD = registerArmour("helm_of_the_dryad", () -> new SkillHelmet(AoASkills.FARMING));
	//public static final RegistryObject<SkillHelmet> HELM_OF_THE_OCCULTIST = registerArmour("helm_of_the_occultist", () -> new SkillHelmet(AoASkills.FAUNAMANCY));
	//public static final RegistryObject<SkillHelmet> HELM_OF_THE_RITUALIST = registerArmour("helm_of_the_ritualist", () -> new SkillHelmet(AoASkills.IMBUING));
	//public static final RegistryObject<SkillHelmet> HELM_OF_THE_TINKERER = registerArmour("helm_of_the_tinkerer", () -> new SkillHelmet(AoASkills.ENGINEERING));
	public static final RegistryObject<SkillHelmet> HELM_OF_THE_TRAWLER = registerArmour("helm_of_the_trawler", () -> new SkillHelmet(AoASkills.HAULING));
	public static final RegistryObject<SkillHelmet> HELM_OF_THE_TREASURER = registerArmour("helm_of_the_treasurer", () -> new SkillHelmet(AoASkills.EXTRACTION));
	public static final RegistryObject<SkillHelmet> HELM_OF_THE_WARRIOR = registerArmour("helm_of_the_warrior", () -> new SkillHelmet(AoASkills.INNERVATION));

	private static <T extends Item> RegistryObject<T> registerArmour(String registryName, Supplier<T> item) {
		return ARMOUR.register(registryName, item);
	}
	
	private static ArmourSet registerArmourSet(String registryPrefix, Class<? extends AdventArmour> armourClass) {
		return new ArmourSet(registryPrefix, armourClass);
	}
	
	public static class ArmourSet {
		public final RegistryObject<Item> helmet;
		public final RegistryObject<Item> chestplate;
		public final RegistryObject<Item> leggings;
		public final RegistryObject<Item> boots;

		private ArmourSet(String registryPrefix, Class<? extends AdventArmour> armourClass) {
			RegistryObject<Item> helm = null;
			RegistryObject<Item> chest = null;
			RegistryObject<Item> legs = null;
			RegistryObject<Item> boot = null;

			try {
				Constructor<? extends AdventArmour> constructor = armourClass.getConstructor(EquipmentSlotType.class);
				helm = ARMOUR.register(registryPrefix + "_helmet", () -> construct(constructor, EquipmentSlotType.HEAD));
				chest = ARMOUR.register(registryPrefix + "_chestplate", () -> construct(constructor, EquipmentSlotType.CHEST));
				legs = ARMOUR.register(registryPrefix + "_legs", () -> construct(constructor, EquipmentSlotType.LEGS));
				boot = ARMOUR.register(registryPrefix + "_boots", () -> construct(constructor, EquipmentSlotType.FEET));
			}
			catch (NoSuchMethodException ex) {
				Logging.logMessage(Level.ERROR, "Somehow we've managed to throw an error while registering armours. I'm really not even sure how this is possible.", ex);
			}
			finally {
				this.helmet = helm;
				this.chestplate = chest;
				this.leggings = legs;
				this.boots = boot;
			}
		}

		private AdventArmour construct(Constructor<? extends AdventArmour> constructor, EquipmentSlotType slot) {
			try {
				return constructor.newInstance(slot);
			}
			catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
				Logging.logMessage(Level.ERROR, "Somehow we've managed to throw an error while registering armours. I'm really not even sure how this is possible.", ex);
			}

			return null;
		}
	}
}
