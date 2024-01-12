package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.item.armour.*;

import java.util.function.Function;
import java.util.function.Supplier;

public final class AoAArmour {
	public static void init() {}

	public static final DeferredItem<Item> ACHELOS_HELMET = registerArmour("achelos_helmet", AchelosHelmet::new);
	public static final DeferredItem<Item> OCEANUS_HELMET = registerArmour("oceanus_helmet", OceanusHelmet::new);
	public static final DeferredItem<Item> SEALORD_HELMET = registerArmour("sealord_helmet", SealordHelmet::new);
	public static final DeferredItem<Item> FACE_MASK = registerArmour("face_mask", FaceMask::new);
	public static final DeferredItem<Item> NIGHT_VISION_GOGGLES = registerArmour("night_vision_goggles", NightVisionGoggles::new);
	
	public static final ArmourSet ALACRITY_ARMOUR = registerArmourSet("alacrity", AlacrityArmour::new);
	public static final ArmourSet ARCHAIC_ARMOUR = registerArmourSet("archaic", ArchaicArmour::new);
	public static final ArmourSet BARON_ARMOUR = registerArmourSet("baron", BaronArmour::new);
	public static final ArmourSet BATTLEBORN_ARMOUR = registerArmourSet("battleborn", BattlebornArmour::new);
	public static final ArmourSet BIOGENIC_ARMOUR = registerArmourSet("biogenic", BiogenicArmour::new);
	public static final ArmourSet BOREIC_ARMOUR = registerArmourSet("boreic", BoreicArmour::new);
	public static final ArmourSet CANDY_ARMOUR = registerArmourSet("candy", CandyArmour::new);
	public static final ArmourSet COMMANDER_ARMOUR = registerArmourSet("commander", CommanderArmour::new);
	public static final ArmourSet CRYSTALLIS_ARMOUR = registerArmourSet("crystallis", CrystallisArmour::new);
	public static final ArmourSet ELECANYTE_ARMOUR = registerArmourSet("elecanyte", ElecanyteArmour::new);
	public static final ArmourSet EMBRODIUM_ARMOUR = registerArmourSet("embrodium", EmbrodiumArmour::new);
	public static final ArmourSet EXOPLATE_ARMOUR = registerArmourSet("exoplate", ExoplateArmour::new);
	public static final ArmourSet EXPLOSIVE_ARMOUR = registerArmourSet("explosive", ExplosiveArmour::new);
	public static final ArmourSet FUNGAL_ARMOUR = registerArmourSet("fungal", FungalArmour::new);
	public static final ArmourSet GHASTLY_ARMOUR = registerArmourSet("ghastly", GhastlyArmour::new);
	public static final ArmourSet GHOULISH_ARMOUR = registerArmourSet("ghoulish", GhoulishArmour::new);
	public static final ArmourSet HAZMAT_ARMOUR = registerArmourSet("hazmat", HazmatArmour::new);
	public static final ArmourSet HYDRANGIC_ARMOUR = registerArmourSet("hydrangic", HydrangicArmour::new);
	public static final ArmourSet HYDROPLATE_ARMOUR = registerArmourSet("hydroplate", HydroplateArmour::new);
	public static final ArmourSet ICE_ARMOUR = registerArmourSet("ice", IceArmour::new);
	public static final ArmourSet INFERNAL_ARMOUR = registerArmourSet("infernal", InfernalArmour::new);
	public static final ArmourSet KNIGHT_ARMOUR = registerArmourSet("knight", KnightArmour::new);
	public static final ArmourSet LUNAR_ARMOUR = registerArmourSet("lunar", LunarArmour::new);
	public static final ArmourSet LYNDAMYTE_ARMOUR = registerArmourSet("lyndamyte", LyndamyteArmour::new);
	public static final ArmourSet LYONIC_ARMOUR = registerArmourSet("lyonic", LyonicArmour::new);
	public static final ArmourSet MERCURIAL_ARMOUR = registerArmourSet("mercurial", MercurialArmour::new);
	public static final ArmourSet NECRO_ARMOUR = registerArmourSet("necro", NecroArmour::new);
	public static final ArmourSet NETHENGEIC_ARMOUR = registerArmourSet("nethengeic", NethengeicArmour::new);
	public static final ArmourSet NIGHTMARE_ARMOUR = registerArmourSet("nightmare", NightmareArmour::new);
	public static final ArmourSet OMNI_ARMOUR = registerArmourSet("omni", OmniArmour::new);
	public static final ArmourSet PHANTASM_ARMOUR = registerArmourSet("phantasm", PhantasmArmour::new);
	public static final ArmourSet POISON_ARMOUR = registerArmourSet("poison", PoisonArmour::new);
	public static final ArmourSet PREDATIOUS_ARMOUR = registerArmourSet("predatious", PredatiousArmour::new);
	public static final ArmourSet PRIMORDIAL_ARMOUR = registerArmourSet("primordial", PrimordialArmour::new);
	public static final ArmourSet PURITY_ARMOUR = registerArmourSet("purity", PurityArmour::new);
	public static final ArmourSet ROCKBONE_ARMOUR = registerArmourSet("rockbone", RockboneArmour::new);
	public static final ArmourSet ROSIDIAN_ARMOUR = registerArmourSet("rosidian", RosidianArmour::new);
	public static final ArmourSet RUNIC_ARMOUR = registerArmourSet("runic", RunicArmour::new);
	public static final ArmourSet SHARPSHOT_ARMOUR = registerArmourSet("sharpshot", SharpshotArmour::new);
	public static final ArmourSet SKELETAL_ARMOUR = registerArmourSet("skeletal", SkeletalArmour::new);
	public static final ArmourSet SPACEKING_ARMOUR = registerArmourSet("spaceking", SpacekingArmour::new);
	public static final ArmourSet SPEED_ARMOUR = registerArmourSet("speed", SpeedArmour::new);
	public static final ArmourSet SUBTERRANEAN_ARMOUR = registerArmourSet("subterranean", SubterraneanArmour::new);
	public static final ArmourSet UTOPIAN_ARMOUR = registerArmourSet("utopian", UtopianArmour::new);
	public static final ArmourSet VOID_ARMOUR = registerArmourSet("void", VoidArmour::new);
	public static final ArmourSet WEAKEN_ARMOUR = registerArmourSet("weaken", WeakenArmour::new);
	public static final ArmourSet WITHER_ARMOUR = registerArmourSet("wither", WitherArmour::new);
	public static final ArmourSet ZARGONITE_ARMOUR = registerArmourSet("zargonite", ZargoniteArmour::new);

	//public static final DeferredItem<SkillHelmet> HELM_OF_THE_ALCHEMIST = registerArmour("helm_of_the_alchemist", () -> new SkillHelmet(AoASkills.ALCHEMY));
	//public static final DeferredItem<SkillHelmet> HELM_OF_THE_CREATOR = registerArmour("helm_of_the_creator", () -> new SkillHelmet(AoASkills.CREATION));
	public static final DeferredItem<SkillHelmet> HELM_OF_THE_DEXTROUS = registerArmour("helm_of_the_dextrous", () -> new SkillHelmet(AoASkills.DEXTERITY));
	public static final DeferredItem<SkillHelmet> HELM_OF_THE_DRYAD = registerArmour("helm_of_the_dryad", () -> new SkillHelmet(AoASkills.FARMING));
	//public static final DeferredItem<SkillHelmet> HELM_OF_THE_OCCULTIST = registerArmour("helm_of_the_occultist", () -> new SkillHelmet(AoASkills.FAUNAMANCY));
	//public static final DeferredItem<SkillHelmet> HELM_OF_THE_RITUALIST = registerArmour("helm_of_the_ritualist", () -> new SkillHelmet(AoASkills.IMBUING));
	//public static final DeferredItem<SkillHelmet> HELM_OF_THE_TINKERER = registerArmour("helm_of_the_tinkerer", () -> new SkillHelmet(AoASkills.ENGINEERING));
	public static final DeferredItem<SkillHelmet> HELM_OF_THE_TRAWLER = registerArmour("helm_of_the_trawler", () -> new SkillHelmet(AoASkills.HAULING));
	public static final DeferredItem<SkillHelmet> HELM_OF_THE_TREASURER = registerArmour("helm_of_the_treasurer", () -> new SkillHelmet(AoASkills.EXTRACTION));
	public static final DeferredItem<SkillHelmet> HELM_OF_THE_WARRIOR = registerArmour("helm_of_the_warrior", () -> new SkillHelmet(AoASkills.INNERVATION));

	private static <T extends Item> DeferredItem<T> registerArmour(String registryName, Supplier<T> item) {
		return AoAItems.registerItem(registryName, item, AoACreativeModeTabs.ARMOUR.getKey());
	}
	
	private static ArmourSet registerArmourSet(String registryPrefix, Function<ArmorItem.Type, AdventArmour> constructor) {
		return new ArmourSet(registryPrefix, constructor);
	}
	
	public static class ArmourSet {
		public final DeferredItem<Item> helmet;
		public final DeferredItem<Item> chestplate;
		public final DeferredItem<Item> leggings;
		public final DeferredItem<Item> boots;

		private ArmourSet(String registryPrefix, Function<ArmorItem.Type, AdventArmour> constructor) {
			this.helmet = AoAItems.registerItem(registryPrefix + "_helmet", () -> constructor.apply(ArmorItem.Type.HELMET), AoACreativeModeTabs.ARMOUR.getKey());
			this.chestplate = AoAItems.registerItem(registryPrefix + "_chestplate", () -> constructor.apply(ArmorItem.Type.CHESTPLATE), AoACreativeModeTabs.ARMOUR.getKey());
			this.leggings = AoAItems.registerItem(registryPrefix + "_leggings", () -> constructor.apply(ArmorItem.Type.LEGGINGS), AoACreativeModeTabs.ARMOUR.getKey());
			this.boots = AoAItems.registerItem(registryPrefix + "_boots", () -> constructor.apply(ArmorItem.Type.BOOTS), AoACreativeModeTabs.ARMOUR.getKey());
		}
	}
}
