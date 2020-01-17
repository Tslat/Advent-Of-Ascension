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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({"unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public class ArmourRegister {
	@GameRegistry.ObjectHolder("achelos_helmet")
	public static final Item achelosHelmet = null;
	@GameRegistry.ObjectHolder("face_mask")
	public static final Item faceMask = null;
	@GameRegistry.ObjectHolder("night_vision_goggles")
	public static final Item nightVisionGoggles = null;
	@GameRegistry.ObjectHolder("oceanus_helmet")
	public static final Item oceanusHelmet = null;
	@GameRegistry.ObjectHolder("sealord_helmet")
	public static final Item sealordHelmet = null;

	@GameRegistry.ObjectHolder("alacrity_boots")
	public static final Item alacrityBoots = null;
	@GameRegistry.ObjectHolder("alacrity_legs")
	public static final Item alacrityLegs = null;
	@GameRegistry.ObjectHolder("alacrity_chestplate")
	public static final Item alacrityBody = null;
	@GameRegistry.ObjectHolder("alacrity_helmet")
	public static final Item alacrityHelmet = null;

	@GameRegistry.ObjectHolder("alchemy_boots")
	public static final Item alchemyBoots = null;
	@GameRegistry.ObjectHolder("alchemy_legs")
	public static final Item alchemyLegs = null;
	@GameRegistry.ObjectHolder("alchemy_chestplate")
	public static final Item alchemyBody = null;
	@GameRegistry.ObjectHolder("alchemy_helmet")
	public static final Item alchemyHelmet = null;

	@GameRegistry.ObjectHolder("amethind_boots")
	public static final Item amethindBoots = null;
	@GameRegistry.ObjectHolder("amethind_legs")
	public static final Item amethindLegs = null;
	@GameRegistry.ObjectHolder("amethind_chestplate")
	public static final Item amethindBody = null;
	@GameRegistry.ObjectHolder("amethind_helmet")
	public static final Item amethindHelmet = null;

	@GameRegistry.ObjectHolder("anima_boots")
	public static final Item animaBoots = null;
	@GameRegistry.ObjectHolder("anima_legs")
	public static final Item animaLegs = null;
	@GameRegistry.ObjectHolder("anima_chestplate")
	public static final Item animaBody = null;
	@GameRegistry.ObjectHolder("anima_helmet")
	public static final Item animaHelmet = null;

	@GameRegistry.ObjectHolder("archaic_boots")
	public static final Item archaicBoots = null;
	@GameRegistry.ObjectHolder("archaic_legs")
	public static final Item archaicLegs = null;
	@GameRegistry.ObjectHolder("archaic_chestplate")
	public static final Item archaicBody = null;
	@GameRegistry.ObjectHolder("archaic_helmet")
	public static final Item archaicHelmet = null;

	@GameRegistry.ObjectHolder("augury_boots")
	public static final Item auguryBoots = null;
	@GameRegistry.ObjectHolder("augury_legs")
	public static final Item auguryLegs = null;
	@GameRegistry.ObjectHolder("augury_chestplate")
	public static final Item auguryBody = null;
	@GameRegistry.ObjectHolder("augury_helmet")
	public static final Item auguryHelmet = null;

	@GameRegistry.ObjectHolder("baron_boots")
	public static final Item baronBoots = null;
	@GameRegistry.ObjectHolder("baron_legs")
	public static final Item baronLegs = null;
	@GameRegistry.ObjectHolder("baron_chestplate")
	public static final Item baronBody = null;
	@GameRegistry.ObjectHolder("baron_helmet")
	public static final Item baronHelmet = null;

	@GameRegistry.ObjectHolder("battleborn_boots")
	public static final Item battlebornBoots = null;
	@GameRegistry.ObjectHolder("battleborn_legs")
	public static final Item battlebornLegs = null;
	@GameRegistry.ObjectHolder("battleborn_chestplate")
	public static final Item battlebornBody = null;
	@GameRegistry.ObjectHolder("battleborn_helmet")
	public static final Item battlebornHelmet = null;

	@GameRegistry.ObjectHolder("biogenic_boots")
	public static final Item biogenicBoots = null;
	@GameRegistry.ObjectHolder("biogenic_legs")
	public static final Item biogenicLegs = null;
	@GameRegistry.ObjectHolder("biogenic_chestplate")
	public static final Item biogenicBody = null;
	@GameRegistry.ObjectHolder("biogenic_helmet")
	public static final Item biogenicHelmet = null;

	@GameRegistry.ObjectHolder("boreic_boots")
	public static final Item boreicBoots = null;
	@GameRegistry.ObjectHolder("boreic_legs")
	public static final Item boreicLegs = null;
	@GameRegistry.ObjectHolder("boreic_chestplate")
	public static final Item boreicBody = null;
	@GameRegistry.ObjectHolder("boreic_helmet")
	public static final Item boreicHelmet = null;

	@GameRegistry.ObjectHolder("butchery_boots")
	public static final Item butcheryBoots = null;
	@GameRegistry.ObjectHolder("butchery_legs")
	public static final Item butcheryLegs = null;
	@GameRegistry.ObjectHolder("butchery_chestplate")
	public static final Item butcheryBody = null;
	@GameRegistry.ObjectHolder("butchery_helmet")
	public static final Item butcheryHelmet = null;

	@GameRegistry.ObjectHolder("candy_boots")
	public static final Item candyBoots = null;
	@GameRegistry.ObjectHolder("candy_legs")
	public static final Item candyLegs = null;
	@GameRegistry.ObjectHolder("candy_chestplate")
	public static final Item candyBody = null;
	@GameRegistry.ObjectHolder("candy_helmet")
	public static final Item candyHelmet = null;

	@GameRegistry.ObjectHolder("commander_boots")
	public static final Item commanderBoots = null;
	@GameRegistry.ObjectHolder("commander_legs")
	public static final Item commanderLegs = null;
	@GameRegistry.ObjectHolder("commander_chestplate")
	public static final Item commanderBody = null;
	@GameRegistry.ObjectHolder("commander_helmet")
	public static final Item commanderHelmet = null;

	@GameRegistry.ObjectHolder("creation_boots")
	public static final Item creationBoots = null;
	@GameRegistry.ObjectHolder("creation_legs")
	public static final Item creationLegs = null;
	@GameRegistry.ObjectHolder("creation_chestplate")
	public static final Item creationBody = null;
	@GameRegistry.ObjectHolder("creation_helmet")
	public static final Item creationHelmet = null;

	@GameRegistry.ObjectHolder("crystallis_boots")
	public static final Item crystallisBoots = null;
	@GameRegistry.ObjectHolder("crystallis_legs")
	public static final Item crystallisLegs = null;
	@GameRegistry.ObjectHolder("crystallis_legs")
	public static final Item crystallisBody = null;
	@GameRegistry.ObjectHolder("crystallis_helmet")
	public static final Item crystallisHelmet = null;

	@GameRegistry.ObjectHolder("elecanyte_boots")
	public static final Item elecanyteBoots = null;
	@GameRegistry.ObjectHolder("elecanyte_legs")
	public static final Item elecanyteLegs = null;
	@GameRegistry.ObjectHolder("elecanyte_chestplate")
	public static final Item elecanyteBody = null;
	@GameRegistry.ObjectHolder("elecanyte_helmet")
	public static final Item elecanyteHelmet = null;

	@GameRegistry.ObjectHolder("embrodium_boots")
	public static final Item embrodiumBoots = null;
	@GameRegistry.ObjectHolder("embrodium_legs")
	public static final Item embrodiumLegs = null;
	@GameRegistry.ObjectHolder("embrodium_chestplate")
	public static final Item embrodiumBody = null;
	@GameRegistry.ObjectHolder("embrodium_helmet")
	public static final Item embrodiumHelmet = null;

	@GameRegistry.ObjectHolder("engineering_boots")
	public static final Item engineeringBoots = null;
	@GameRegistry.ObjectHolder("engineering_legs")
	public static final Item engineeringLegs = null;
	@GameRegistry.ObjectHolder("engineering_chestplate")
	public static final Item engineeringBody = null;
	@GameRegistry.ObjectHolder("engineering_helmet")
	public static final Item engineeringHelmet = null;

	@GameRegistry.ObjectHolder("exoplate_boots")
	public static final Item exoplateBoots = null;
	@GameRegistry.ObjectHolder("exoplate_legs")
	public static final Item exoplateLegs = null;
	@GameRegistry.ObjectHolder("exoplate_chestplate")
	public static final Item exoplateBody = null;
	@GameRegistry.ObjectHolder("exoplate_helmet")
	public static final Item exoplateHelmet = null;

	@GameRegistry.ObjectHolder("expedition_boots")
	public static final Item expeditionBoots = null;
	@GameRegistry.ObjectHolder("expedition_legs")
	public static final Item expeditionLegs = null;
	@GameRegistry.ObjectHolder("expedition_chestplate")
	public static final Item expeditionBody = null;
	@GameRegistry.ObjectHolder("expedition_helmet")
	public static final Item expeditionHelmet = null;

	@GameRegistry.ObjectHolder("explosive_boots")
	public static final Item explosiveBoots = null;
	@GameRegistry.ObjectHolder("explosive_legs")
	public static final Item explosiveLegs = null;
	@GameRegistry.ObjectHolder("explosive_chestplate")
	public static final Item explosiveBody = null;
	@GameRegistry.ObjectHolder("explosive_helmet")
	public static final Item explosiveHelmet = null;

	@GameRegistry.ObjectHolder("extraction_boots")
	public static final Item extractionBoots = null;
	@GameRegistry.ObjectHolder("extraction_legs")
	public static final Item extractionLegs = null;
	@GameRegistry.ObjectHolder("extraction_chestplate")
	public static final Item extractionBody = null;
	@GameRegistry.ObjectHolder("extraction_helmet")
	public static final Item extractionHelmet = null;

	@GameRegistry.ObjectHolder("foraging_boots")
	public static final Item foragingBoots = null;
	@GameRegistry.ObjectHolder("foraging_legs")
	public static final Item foragingLegs = null;
	@GameRegistry.ObjectHolder("foraging_chestplate")
	public static final Item foragingBody = null;
	@GameRegistry.ObjectHolder("foraging_helmet")
	public static final Item foragingHelmet = null;

	@GameRegistry.ObjectHolder("fungal_boots")
	public static final Item fungalBoots = null;
	@GameRegistry.ObjectHolder("fungal_legs")
	public static final Item fungalLegs = null;
	@GameRegistry.ObjectHolder("fungal_chestplate")
	public static final Item fungalBody = null;
	@GameRegistry.ObjectHolder("fungal_helmet")
	public static final Item fungalHelmet = null;

	@GameRegistry.ObjectHolder("ghastly_boots")
	public static final Item ghastlyBoots = null;
	@GameRegistry.ObjectHolder("ghastly_legs")
	public static final Item ghastlyLegs = null;
	@GameRegistry.ObjectHolder("ghastly_chestplate")
	public static final Item ghastlyBody = null;
	@GameRegistry.ObjectHolder("ghastly_helmet")
	public static final Item ghastlyHelmet = null;

	@GameRegistry.ObjectHolder("ghoulish_boots")
	public static final Item ghoulishBoots = null;
	@GameRegistry.ObjectHolder("ghoulish_legs")
	public static final Item ghoulishLegs = null;
	@GameRegistry.ObjectHolder("ghoulish_chestplate")
	public static final Item ghoulishBody = null;
	@GameRegistry.ObjectHolder("ghoulish_helmet")
	public static final Item ghoulishHelmet = null;

	@GameRegistry.ObjectHolder("hauling_boots")
	public static final Item haulingBoots = null;
	@GameRegistry.ObjectHolder("hauling_legs")
	public static final Item haulingLegs = null;
	@GameRegistry.ObjectHolder("hauling_chestplate")
	public static final Item haulingBody = null;
	@GameRegistry.ObjectHolder("hauling_helmet")
	public static final Item haulingHelmet = null;

	@GameRegistry.ObjectHolder("hazmat_boots")
	public static final Item hazmatBoots = null;
	@GameRegistry.ObjectHolder("hazmat_legs")
	public static final Item hazmatLegs = null;
	@GameRegistry.ObjectHolder("hazmat_chestplate")
	public static final Item hazmatBody = null;
	@GameRegistry.ObjectHolder("hazmat_helmet")
	public static final Item hazmatHelmet = null;

	@GameRegistry.ObjectHolder("hunter_boots")
	public static final Item hunterBoots = null;
	@GameRegistry.ObjectHolder("hunter_legs")
	public static final Item hunterLegs = null;
	@GameRegistry.ObjectHolder("hunter_chestplate")
	public static final Item hunterBody = null;
	@GameRegistry.ObjectHolder("hunter_helmet")
	public static final Item hunterHelmet = null;

	@GameRegistry.ObjectHolder("hydrangic_boots")
	public static final Item hydrangicBoots = null;
	@GameRegistry.ObjectHolder("hydrangic_legs")
	public static final Item hydrangicLegs = null;
	@GameRegistry.ObjectHolder("hydrangic_chestplate")
	public static final Item hydrangicBody = null;
	@GameRegistry.ObjectHolder("hydrangic_helmet")
	public static final Item hydrangicHelmet = null;

	@GameRegistry.ObjectHolder("hydroplate_boots")
	public static final Item hydroplateBoots = null;
	@GameRegistry.ObjectHolder("hydroplate_legs")
	public static final Item hydroplateLegs = null;
	@GameRegistry.ObjectHolder("hydroplate_chestplate")
	public static final Item hydroplateBody = null;
	@GameRegistry.ObjectHolder("hydroplate_helmet")
	public static final Item hydroplateHelmet = null;

	@GameRegistry.ObjectHolder("ice_boots")
	public static final Item iceBoots = null;
	@GameRegistry.ObjectHolder("ice_legs")
	public static final Item iceLegs = null;
	@GameRegistry.ObjectHolder("ice_chestplate")
	public static final Item iceBody = null;
	@GameRegistry.ObjectHolder("ice_helmet")
	public static final Item iceHelmet = null;

	@GameRegistry.ObjectHolder("infernal_boots")
	public static final Item infernalBoots = null;
	@GameRegistry.ObjectHolder("infernal_legs")
	public static final Item infernalLegs = null;
	@GameRegistry.ObjectHolder("infernal_chestplate")
	public static final Item infernalBody = null;
	@GameRegistry.ObjectHolder("infernal_helmet")
	public static final Item infernalHelmet = null;

	@GameRegistry.ObjectHolder("infusion_boots")
	public static final Item infusionBoots = null;
	@GameRegistry.ObjectHolder("infusion_legs")
	public static final Item infusionLegs = null;
	@GameRegistry.ObjectHolder("infusion_chestplate")
	public static final Item infusionBody = null;
	@GameRegistry.ObjectHolder("infusion_helmet")
	public static final Item infusionHelmet = null;

	@GameRegistry.ObjectHolder("innervation_boots")
	public static final Item innervationBoots = null;
	@GameRegistry.ObjectHolder("innervation_legs")
	public static final Item innervationLegs = null;
	@GameRegistry.ObjectHolder("innervation_chestplate")
	public static final Item innervationBody = null;
	@GameRegistry.ObjectHolder("innervation_helmet")
	public static final Item innervationHelmet = null;

	@GameRegistry.ObjectHolder("knight_boots")
	public static final Item knightBoots = null;
	@GameRegistry.ObjectHolder("knight_legs")
	public static final Item knightLegs = null;
	@GameRegistry.ObjectHolder("knight_chestplate")
	public static final Item knightBody = null;
	@GameRegistry.ObjectHolder("knight_helmet")
	public static final Item knightHelmet = null;

	@GameRegistry.ObjectHolder("logging_boots")
	public static final Item loggingBoots = null;
	@GameRegistry.ObjectHolder("logging_legs")
	public static final Item loggingLegs = null;
	@GameRegistry.ObjectHolder("logging_chestplate")
	public static final Item loggingBody = null;
	@GameRegistry.ObjectHolder("logging_helmet")
	public static final Item loggingHelmet = null;

	@GameRegistry.ObjectHolder("lunar_boots")
	public static final Item lunarBoots = null;
	@GameRegistry.ObjectHolder("lunar_legs")
	public static final Item lunarLegs = null;
	@GameRegistry.ObjectHolder("lunar_chestplate")
	public static final Item lunarBody = null;
	@GameRegistry.ObjectHolder("lunar_helmet")
	public static final Item lunarHelmet = null;

	@GameRegistry.ObjectHolder("lyndamyte_boots")
	public static final Item lyndamyteBoots = null;
	@GameRegistry.ObjectHolder("lyndamyte_legs")
	public static final Item lyndamyteLegs = null;
	@GameRegistry.ObjectHolder("lyndamyte_chestplate")
	public static final Item lyndamyteBody = null;
	@GameRegistry.ObjectHolder("lyndamyte_helmet")
	public static final Item lyndamyteHelmet = null;

	@GameRegistry.ObjectHolder("lyonic_boots")
	public static final Item lyonicBoots = null;
	@GameRegistry.ObjectHolder("lyonic_legs")
	public static final Item lyonicLegs = null;
	@GameRegistry.ObjectHolder("lyonic_chestplate")
	public static final Item lyonicBody = null;
	@GameRegistry.ObjectHolder("lyonic_helmet")
	public static final Item lyonicHelmet = null;

	@GameRegistry.ObjectHolder("mercurial_boots")
	public static final Item mercurialBoots = null;
	@GameRegistry.ObjectHolder("mercurial_legs")
	public static final Item mercurialLegs = null;
	@GameRegistry.ObjectHolder("mercurial_chestplate")
	public static final Item mercurialBody = null;
	@GameRegistry.ObjectHolder("mercurial_helmet")
	public static final Item mercurialHelmet = null;

	@GameRegistry.ObjectHolder("necro_boots")
	public static final Item necroBoots = null;
	@GameRegistry.ObjectHolder("necro_legs")
	public static final Item necroLegs = null;
	@GameRegistry.ObjectHolder("necro_chestplate")
	public static final Item necroBody = null;
	@GameRegistry.ObjectHolder("necro_helmet")
	public static final Item necroHelmet = null;

	@GameRegistry.ObjectHolder("nethengeic_boots")
	public static final Item nethengeicBoots = null;
	@GameRegistry.ObjectHolder("nethengeic_legs")
	public static final Item nethengeicLegs = null;
	@GameRegistry.ObjectHolder("nethengeic_chestplate")
	public static final Item nethengeicBody = null;
	@GameRegistry.ObjectHolder("nethengeic_helmet")
	public static final Item nethengeicHelmet = null;

	@GameRegistry.ObjectHolder("nightmare_boots")
	public static final Item nightmareBoots = null;
	@GameRegistry.ObjectHolder("nightmare_legs")
	public static final Item nightmareLegs = null;
	@GameRegistry.ObjectHolder("nightmare_chestplate")
	public static final Item nightmareBody = null;
	@GameRegistry.ObjectHolder("nightmare_helmet")
	public static final Item nightmareHelmet = null;

	@GameRegistry.ObjectHolder("omni_boots")
	public static final Item omniBoots = null;
	@GameRegistry.ObjectHolder("omni_legs")
	public static final Item omniLegs = null;
	@GameRegistry.ObjectHolder("omni_chestplate")
	public static final Item omniBody = null;
	@GameRegistry.ObjectHolder("omni_helmet")
	public static final Item omniHelmet = null;

	@GameRegistry.ObjectHolder("phantasm_boots")
	public static final Item phantasmBoots = null;
	@GameRegistry.ObjectHolder("phantasm_legs")
	public static final Item phantasmLegs = null;
	@GameRegistry.ObjectHolder("phantasm_chestplate")
	public static final Item phantasmBody = null;
	@GameRegistry.ObjectHolder("phantasm_helmet")
	public static final Item phantasmHelmet = null;

	@GameRegistry.ObjectHolder("poison_boots")
	public static final Item poisonBoots = null;
	@GameRegistry.ObjectHolder("poison_legs")
	public static final Item poisonLegs = null;
	@GameRegistry.ObjectHolder("poison_chestplate")
	public static final Item poisonBody = null;
	@GameRegistry.ObjectHolder("poison_helmet")
	public static final Item poisonHelmet = null;

	@GameRegistry.ObjectHolder("predatious_boots")
	public static final Item predatiousBoots = null;
	@GameRegistry.ObjectHolder("predatious_legs")
	public static final Item predatiousLegs = null;
	@GameRegistry.ObjectHolder("predatious_chestplate")
	public static final Item predatiousBody = null;
	@GameRegistry.ObjectHolder("predatious_helmet")
	public static final Item predatiousHelmet = null;

	@GameRegistry.ObjectHolder("primordial_boots")
	public static final Item primordialBoots = null;
	@GameRegistry.ObjectHolder("primordial_legs")
	public static final Item primordialLegs = null;
	@GameRegistry.ObjectHolder("primordial_chestplate")
	public static final Item primordialBody = null;
	@GameRegistry.ObjectHolder("primordial_helmet")
	public static final Item primordialHelmet = null;

	@GameRegistry.ObjectHolder("purity_boots")
	public static final Item purityBoots = null;
	@GameRegistry.ObjectHolder("purity_legs")
	public static final Item purityLegs = null;
	@GameRegistry.ObjectHolder("purity_chestplate")
	public static final Item purityBody = null;
	@GameRegistry.ObjectHolder("purity_helmet")
	public static final Item purityHelmet = null;

	@GameRegistry.ObjectHolder("rockbone_boots")
	public static final Item rockboneBoots = null;
	@GameRegistry.ObjectHolder("rockbone_legs")
	public static final Item rockboneLegs = null;
	@GameRegistry.ObjectHolder("rockbone_chestplate")
	public static final Item rockboneBody = null;
	@GameRegistry.ObjectHolder("rockbone_helmet")
	public static final Item rockboneHelmet = null;

	@GameRegistry.ObjectHolder("rosidian_boots")
	public static final Item rosidianBoots = null;
	@GameRegistry.ObjectHolder("rosidian_legs")
	public static final Item rosidianLegs = null;
	@GameRegistry.ObjectHolder("rosidian_chestplate")
	public static final Item rosidianBody = null;
	@GameRegistry.ObjectHolder("rosidian_helmet")
	public static final Item rosidianHelmet = null;

	@GameRegistry.ObjectHolder("runation_boots")
	public static final Item runationBoots = null;
	@GameRegistry.ObjectHolder("runation_legs")
	public static final Item runationLegs = null;
	@GameRegistry.ObjectHolder("runation_chestplate")
	public static final Item runationBody = null;
	@GameRegistry.ObjectHolder("runation_helmet")
	public static final Item runationHelmet = null;

	@GameRegistry.ObjectHolder("runic_boots")
	public static final Item runicBoots = null;
	@GameRegistry.ObjectHolder("runic_legs")
	public static final Item runicLegs = null;
	@GameRegistry.ObjectHolder("runic_chestplate")
	public static final Item runicBody = null;
	@GameRegistry.ObjectHolder("runic_helmet")
	public static final Item runicHelmet = null;

	@GameRegistry.ObjectHolder("sharpshot_boots")
	public static final Item sharpshotBoots = null;
	@GameRegistry.ObjectHolder("sharpshot_legs")
	public static final Item sharpshotLegs = null;
	@GameRegistry.ObjectHolder("sharpshot_chestplate")
	public static final Item sharpshotBody = null;
	@GameRegistry.ObjectHolder("sharpshot_helmet")
	public static final Item sharpshotHelmet = null;

	@GameRegistry.ObjectHolder("skeletal_boots")
	public static final Item skeletalBoots = null;
	@GameRegistry.ObjectHolder("skeletal_legs")
	public static final Item skeletalLegs = null;
	@GameRegistry.ObjectHolder("skeletal_chestplate")
	public static final Item skeletalBody = null;
	@GameRegistry.ObjectHolder("skeletal_helmet")
	public static final Item skeletalHelmet = null;

	@GameRegistry.ObjectHolder("spaceking_boots")
	public static final Item spacekingBoots = null;
	@GameRegistry.ObjectHolder("spaceking_legs")
	public static final Item spacekingLegs = null;
	@GameRegistry.ObjectHolder("spaceking_chestplate")
	public static final Item spacekingBody = null;
	@GameRegistry.ObjectHolder("spaceking_helmet")
	public static final Item spacekingHelmet = null;

	@GameRegistry.ObjectHolder("speed_boots")
	public static final Item speedBoots = null;
	@GameRegistry.ObjectHolder("speed_legs")
	public static final Item speedLegs = null;
	@GameRegistry.ObjectHolder("speed_chestplate")
	public static final Item speedBody = null;
	@GameRegistry.ObjectHolder("speed_helmet")
	public static final Item speedHelmet = null;

	@GameRegistry.ObjectHolder("subterranean_boots")
	public static final Item subterraneanBoots = null;
	@GameRegistry.ObjectHolder("subterranean_legs")
	public static final Item subterraneanLegs = null;
	@GameRegistry.ObjectHolder("subterranean_chestplate")
	public static final Item subterraneanBody = null;
	@GameRegistry.ObjectHolder("subterranean_helmet")
	public static final Item subterraneanHelmet = null;

	@GameRegistry.ObjectHolder("utopian_boots")
	public static final Item utopianBoots = null;
	@GameRegistry.ObjectHolder("utopian_legs")
	public static final Item utopianLegs = null;
	@GameRegistry.ObjectHolder("utopian_chestplate")
	public static final Item utopianBody = null;
	@GameRegistry.ObjectHolder("utopian_helmet")
	public static final Item UtopianHelmet = null;

	@GameRegistry.ObjectHolder("void_boots")
	public static final Item voidBoots = null;
	@GameRegistry.ObjectHolder("void_legs")
	public static final Item voidLegs = null;
	@GameRegistry.ObjectHolder("void_chestplate")
	public static final Item voidBody = null;
	@GameRegistry.ObjectHolder("void_helmet")
	public static final Item VoidHelmet = null;

	@GameRegistry.ObjectHolder("weaken_boots")
	public static final Item weakenBoots = null;
	@GameRegistry.ObjectHolder("weaken_legs")
	public static final Item weakenLegs = null;
	@GameRegistry.ObjectHolder("weaken_chestplate")
	public static final Item weakenBody = null;
	@GameRegistry.ObjectHolder("weaken_helmet")
	public static final Item weakenHelmet = null;

	@GameRegistry.ObjectHolder("wither_boots")
	public static final Item witherBoots = null;
	@GameRegistry.ObjectHolder("wither_legs")
	public static final Item witherLegs = null;
	@GameRegistry.ObjectHolder("wither_chestplate")
	public static final Item witherBody = null;
	@GameRegistry.ObjectHolder("wither_helmet")
	public static final Item witherHelmet = null;

	@GameRegistry.ObjectHolder("zargonite_boots")
	public static final Item zargoniteBoots = null;
	@GameRegistry.ObjectHolder("zargonite_legs")
	public static final Item zargoniteLegs = null;
	@GameRegistry.ObjectHolder("zargonite_chestplate")
	public static final Item zargoniteBody = null;
	@GameRegistry.ObjectHolder("zargonite_helmet")
	public static final Item zargoniteHelmet = null;

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
}
