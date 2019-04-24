package net.tslat.aoa3.common.registration;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.item.armour.*;

@Mod.EventBusSubscriber
public class ArmourRegister {
	public static final Item AlacrityBoots = new AlacrityArmour("AlacrityBoots", "alacrity_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item AlacrityLegs = new AlacrityArmour("AlacrityLegs", "alacrity_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item AlacrityBody = new AlacrityArmour("AlacrityChestplate", "alacrity_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item AlacrityHelmet = new AlacrityArmour("AlacrityHelmet", "alacrity_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item AlchemyBoots = new AlchemyArmour("AlchemyBoots", "alchemy_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item AlchemyLegs = new AlchemyArmour("AlchemyLegs", "alchemy_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item AlchemyBody = new AlchemyArmour("AlchemyChestplate", "alchemy_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item AlchemyHelmet = new AlchemyArmour("AlchemyHelmet", "alchemy_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item AmethindBoots = new AmethindArmour("AmethindBoots", "amethind_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item AmethindLegs = new AmethindArmour("AmethindLegs", "amethind_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item AmethindBody = new AmethindArmour("AmethindChestplate", "amethind_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item AmethindHelmet = new AmethindArmour("AmethindHelmet", "amethind_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item AnimaBoots = new AnimaArmour("AnimaBoots", "anima_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item AnimaLegs = new AnimaArmour("AnimaLegs", "anima_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item AnimaBody = new AnimaArmour("AnimaChestplate", "anima_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item AnimaHelmet = new AnimaArmour("AnimaHelmet", "anima_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ArchaicBoots = new ArchaicArmour("ArchaicBoots", "archaic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ArchaicLegs = new ArchaicArmour("ArchaicLegs", "archaic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ArchaicBody = new ArchaicArmour("ArchaicChestplate", "archaic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ArchaicHelmet = new ArchaicArmour("ArchaicHelmet", "archaic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item AuguryBoots = new AuguryArmour("AuguryBoots", "augury_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item AuguryLegs = new AuguryArmour("AuguryLegs", "augury_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item AuguryBody = new AuguryArmour("AuguryChestplate", "augury_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item AuguryHelmet = new AuguryArmour("AuguryHelmet", "augury_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item BaronBoots = new BaronArmour("BaronBoots", "baron_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item BaronLegs = new BaronArmour("BaronLegs", "baron_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item BaronBody = new BaronArmour("BaronChestplate", "baron_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item BaronHelmet = new BaronArmour("BaronHelmet", "baron_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item BattlebornBoots = new BattlebornArmour("BattlebornBoots", "battleborn_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item BattlebornLegs = new BattlebornArmour("BattlebornLegs", "battleborn_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item BattlebornBody = new BattlebornArmour("BattlebornChestplate", "battleborn_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item BattlebornHelmet = new BattlebornArmour("BattlebornHelmet", "battleborn_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item BiogenicBoots = new BiogenicArmour("BiogenicBoots", "biogenic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item BiogenicLegs = new BiogenicArmour("BiogenicLegs", "biogenic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item BiogenicBody = new BiogenicArmour("BiogenicChestplate", "biogenic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item BiogenicHelmet = new BiogenicArmour("BiogenicHelmet", "biogenic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item BoreicBoots = new BoreicArmour("BoreicBoots", "boreic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item BoreicLegs = new BoreicArmour("BoreicLegs", "boreic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item BoreicBody = new BoreicArmour("BoreicChestplate", "boreic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item BoreicHelmet = new BoreicArmour("BoreicHelmet", "boreic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ButcheryBoots = new ButcheryArmour("ButcheryBoots", "butchery_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ButcheryLegs = new ButcheryArmour("ButcheryLegs", "butchery_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ButcheryBody = new ButcheryArmour("ButcheryChestplate", "butchery_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ButcheryHelmet = new ButcheryArmour("ButcheryHelmet", "butchery_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item CandyBoots = new CandyArmour("CandyBoots", "candy_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item CandyLegs = new CandyArmour("CandyLegs", "candy_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item CandyBody = new CandyArmour("CandyChestplate", "candy_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item CandyHelmet = new CandyArmour("CandyHelmet", "candy_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item CommanderBoots = new CommanderArmour("CommanderBoots", "commander_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item CommanderLegs = new CommanderArmour("CommanderLegs", "commander_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item CommanderBody = new CommanderArmour("CommanderChestplate", "commander_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item CommanderHelmet = new CommanderArmour("CommanderHelmet", "commander_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item CreationBoots = new CreationArmour("CreationBoots", "creation_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item CreationLegs = new CreationArmour("CreationLegs", "creation_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item CreationBody = new CreationArmour("CreationChestplate", "creation_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item CreationHelmet = new CreationArmour("CreationHelmet", "creation_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item CrystallisBoots = new CrystallisArmour("CrystallisBoots", "crystallis_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item CrystallisLegs = new CrystallisArmour("CrystallisLegs", "crystallis_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item CrystallisBody = new CrystallisArmour("CrystallisChestplate", "crystallis_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item CrystallisHelmet = new CrystallisArmour("CrystallisHelmet", "crystallis_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ElecanyteBoots = new ElecanyteArmour("ElecanyteBoots", "elecanyte_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ElecanyteLegs = new ElecanyteArmour("ElecanyteLegs", "elecanyte_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ElecanyteBody = new ElecanyteArmour("ElecanyteChestplate", "elecanyte_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ElecanyteHelmet = new ElecanyteArmour("ElecanyteHelmet", "elecanyte_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item EmbrodiumBoots = new EmbrodiumArmour("EmbrodiumBoots", "embrodium_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item EmbrodiumLegs = new EmbrodiumArmour("EmbrodiumLegs", "embrodium_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item EmbrodiumBody = new EmbrodiumArmour("EmbrodiumChestplate", "embrodium_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item EmbrodiumHelmet = new EmbrodiumArmour("EmbrodiumHelmet", "embrodium_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item EngineeringBoots = new EngineeringArmour("EngineeringBoots", "engineering_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item EngineeringLegs = new EngineeringArmour("EngineeringLegs", "engineering_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item EngineeringBody = new EngineeringArmour("EngineeringChestplate", "engineering_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item EngineeringHelmet = new EngineeringArmour("EngineeringHelmet", "engineering_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ExoplateBoots = new ExoplateArmour("ExoplateBoots", "exoplate_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ExoplateLegs = new ExoplateArmour("ExoplateLegs", "exoplate_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ExoplateBody = new ExoplateArmour("ExoplateChestplate", "exoplate_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ExoplateHelmet = new ExoplateArmour("ExoplateHelmet", "exoplate_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ExpeditionBoots = new ExpeditionArmour("ExpeditionBoots", "expedition_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ExpeditionLegs = new ExpeditionArmour("ExpeditionLegs", "expedition_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ExpeditionBody = new ExpeditionArmour("ExpeditionChestplate", "expedition_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ExpeditionHelmet = new ExpeditionArmour("ExpeditionHelmet", "expedition_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ExplosiveBoots = new ExplosiveArmour("ExplosiveBoots", "explosive_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ExplosiveLegs = new ExplosiveArmour("ExplosiveLegs", "explosive_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ExplosiveBody = new ExplosiveArmour("ExplosiveChestplate", "explosive_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ExplosiveHelmet = new ExplosiveArmour("ExplosiveHelmet", "explosive_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ExtractionBoots = new ExtractionArmour("ExtractionBoots", "extraction_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ExtractionLegs = new ExtractionArmour("ExtractionLegs", "extraction_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ExtractionBody = new ExtractionArmour("ExtractionChestplate", "extraction_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ExtractionHelmet = new ExtractionArmour("ExtractionHelmet", "extraction_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ForagingBoots = new ForagingArmour("ForagingBoots", "foraging_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ForagingLegs = new ForagingArmour("ForagingLegs", "foraging_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ForagingBody = new ForagingArmour("ForagingChestplate", "foraging_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ForagingHelmet = new ForagingArmour("ForagingHelmet", "foraging_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item FungalBoots = new FungalArmour("FungalBoots", "fungal_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item FungalLegs = new FungalArmour("FungalLegs", "fungal_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item FungalBody = new FungalArmour("FungalChestplate", "fungal_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item FungalHelmet = new FungalArmour("FungalHelmet", "fungal_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item GhastlyBoots = new GhastlyArmour("GhastlyBoots", "ghastly_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item GhastlyLegs = new GhastlyArmour("GhastlyLegs", "ghastly_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item GhastlyBody = new GhastlyArmour("GhastlyChestplate", "ghastly_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item GhastlyHelmet = new GhastlyArmour("GhastlyHelmet", "ghastly_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item GhoulishBoots = new GhoulishArmour("GhoulishBoots", "ghoulish_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item GhoulishLegs = new GhoulishArmour("GhoulishLegs", "ghoulish_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item GhoulishBody = new GhoulishArmour("GhoulishChestplate", "ghoulish_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item GhoulishHelmet = new GhoulishArmour("GhoulishHelmet", "ghoulish_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item HaulingBoots = new HaulingArmour("HaulingBoots", "hauling_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item HaulingLegs = new HaulingArmour("HaulingLegs", "hauling_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item HaulingBody = new HaulingArmour("HaulingChestplate", "hauling_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item HaulingHelmet = new HaulingArmour("HaulingHelmet", "hauling_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item HazmatBoots = new HazmatArmour("HazmatBoots", "hazmat_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item HazmatLegs = new HazmatArmour("HazmatLegs", "hazmat_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item HazmatBody = new HazmatArmour("HazmatChestplate", "hazmat_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item HazmatHelmet = new HazmatArmour("HazmatHelmet", "hazmat_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item HunterBoots = new HunterArmour("HunterBoots", "hunter_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item HunterLegs = new HunterArmour("HunterLegs", "hunter_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item HunterBody = new HunterArmour("HunterChestplate", "hunter_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item HunterHelmet = new HunterArmour("HunterHelmet", "hunter_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item HydrangicBoots = new HydrangicArmour("HydrangicBoots", "hydrangic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item HydrangicLegs = new HydrangicArmour("HydrangicLegs", "hydrangic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item HydrangicBody = new HydrangicArmour("HydrangicChestplate", "hydrangic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item HydrangicHelmet = new HydrangicArmour("HydrangicHelmet", "hydrangic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item HydroplateBoots = new HydroplateArmour("HydroplateBoots", "hydroplate_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item HydroplateLegs = new HydroplateArmour("HydroplateLegs", "hydroplate_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item HydroplateBody = new HydroplateArmour("HydroplateChestplate", "hydroplate_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item HydroplateHelmet = new HydroplateArmour("HydroplateHelmet", "hydroplate_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item IceBoots = new IceArmour("IceBoots", "ice_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item IceLegs = new IceArmour("IceLegs", "ice_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item IceBody = new IceArmour("IceChestplate", "ice_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item IceHelmet = new IceArmour("IceHelmet", "ice_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item InfernalBoots = new InfernalArmour("InfernalBoots", "infernal_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item InfernalLegs = new InfernalArmour("InfernalLegs", "infernal_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item InfernalBody = new InfernalArmour("InfernalChestplate", "infernal_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item InfernalHelmet = new InfernalArmour("InfernalHelmet", "infernal_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item InfusionBoots = new InfusionArmour("InfusionBoots", "infusion_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item InfusionLegs = new InfusionArmour("InfusionLegs", "infusion_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item InfusionBody = new InfusionArmour("InfusionChestplate", "infusion_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item InfusionHelmet = new InfusionArmour("InfusionHelmet", "infusion_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item InnervationBoots = new InnervationArmour("InnervationBoots", "innervation_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item InnervationLegs = new InnervationArmour("InnervationLegs", "innervation_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item InnervationBody = new InnervationArmour("InnervationChestplate", "innervation_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item InnervationHelmet = new InnervationArmour("InnervationHelmet", "innervation_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item KnightBoots = new KnightArmour("KnightBoots", "knight_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item KnightLegs = new KnightArmour("KnightLegs", "knight_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item KnightBody = new KnightArmour("KnightChestplate", "knight_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item KnightHelmet = new KnightArmour("KnightHelmet", "knight_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item LoggingBoots = new LoggingArmour("LoggingBoots", "logging_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item LoggingLegs = new LoggingArmour("LoggingLegs", "logging_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item LoggingBody = new LoggingArmour("LoggingChestplate", "logging_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item LoggingHelmet = new LoggingArmour("LoggingHelmet", "logging_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item LunarBoots = new LunarArmour("LunarBoots", "lunar_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item LunarLegs = new LunarArmour("LunarLegs", "lunar_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item LunarBody = new LunarArmour("LunarChestplate", "lunar_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item LunarHelmet = new LunarArmour("LunarHelmet", "lunar_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item LyndamyteBoots = new LyndamyteArmour("LyndamyteBoots", "lyndamyte_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item LyndamyteLegs = new LyndamyteArmour("LyndamyteLegs", "lyndamyte_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item LyndamyteBody = new LyndamyteArmour("LyndamyteChestplate", "lyndamyte_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item LyndamyteHelmet = new LyndamyteArmour("LyndamyteHelmet", "lyndamyte_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item LyonicBoots = new LyonicArmour("LyonicBoots", "lyonic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item LyonicLegs = new LyonicArmour("LyonicLegs", "lyonic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item LyonicBody = new LyonicArmour("LyonicChestplate", "lyonic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item LyonicHelmet = new LyonicArmour("LyonicHelmet", "lyonic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item MercurialBoots = new MercurialArmour("MercurialBoots", "mercurial_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item MercurialLegs = new MercurialArmour("MercurialLegs", "mercurial_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item MercurialBody = new MercurialArmour("MercurialChestplate", "mercurial_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item MercurialHelmet = new MercurialArmour("MercurialHelmet", "mercurial_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item NecroBoots = new NecroArmour("NecroBoots", "necro_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item NecroLegs = new NecroArmour("NecroLegs", "necro_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item NecroBody = new NecroArmour("NecroChestplate", "necro_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item NecroHelmet = new NecroArmour("NecroHelmet", "necro_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item NethengeicBoots = new NethengeicArmour("NethengeicBoots", "nethengeic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item NethengeicLegs = new NethengeicArmour("NethengeicLegs", "nethengeic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item NethengeicBody = new NethengeicArmour("NethengeicChestplate", "nethengeic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item NethengeicHelmet = new NethengeicArmour("NethengeicHelmet", "nethengeic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item NightmareBoots = new NightmareArmour("NightmareBoots", "nightmare_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item NightmareLegs = new NightmareArmour("NightmareLegs", "nightmare_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item NightmareBody = new NightmareArmour("NightmareChestplate", "nightmare_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item NightmareHelmet = new NightmareArmour("NightmareHelmet", "nightmare_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item OmniBoots = new OmniArmour("OmniBoots", "omni_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item OmniLegs = new OmniArmour("OmniLegs", "omni_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item OmniBody = new OmniArmour("OmniChestplate", "omni_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item OmniHelmet = new OmniArmour("OmniHelmet", "omni_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item PhantasmBoots = new PhantasmArmour("PhantasmBoots", "phantasm_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item PhantasmLegs = new PhantasmArmour("PhantasmLegs", "phantasm_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item PhantasmBody = new PhantasmArmour("PhantasmChestplate", "phantasm_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item PhantasmHelmet = new PhantasmArmour("PhantasmHelmet", "phantasm_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item PoisonBoots = new PoisonArmour("PoisonBoots", "poison_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item PoisonLegs = new PoisonArmour("PoisonLegs", "poison_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item PoisonBody = new PoisonArmour("PoisonChestplate", "poison_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item PoisonHelmet = new PoisonArmour("PoisonHelmet", "poison_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item PredatiousBoots = new PredatiousArmour("PredatiousBoots", "predatious_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item PredatiousLegs = new PredatiousArmour("PredatiousLegs", "predatious_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item PredatiousBody = new PredatiousArmour("PredatiousChestplate", "predatious_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item PredatiousHelmet = new PredatiousArmour("PredatiousHelmet", "predatious_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item PrimordialBoots = new PrimordialArmour("PrimordialBoots", "primordial_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item PrimordialLegs = new PrimordialArmour("PrimordialLegs", "primordial_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item PrimordialBody = new PrimordialArmour("PrimordialChestplate", "primordial_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item PrimordialHelmet = new PrimordialArmour("PrimordialHelmet", "primordial_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item PurityBoots = new PurityArmour("PurityBoots", "purity_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item PurityLegs = new PurityArmour("PurityLegs", "purity_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item PurityBody = new PurityArmour("PurityChestplate", "purity_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item PurityHelmet = new PurityArmour("PurityHelmet", "purity_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item RockboneBoots = new RockboneArmour("RockboneBoots", "rockbone_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item RockboneLegs = new RockboneArmour("RockboneLegs", "rockbone_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item RockboneBody = new RockboneArmour("RockboneChestplate", "rockbone_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item RockboneHelmet = new RockboneArmour("RockboneHelmet", "rockbone_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item RosidianBoots = new RosidianArmour("RosidianBoots", "rosidian_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item RosidianLegs = new RosidianArmour("RosidianLegs", "rosidian_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item RosidianBody = new RosidianArmour("RosidianChestplate", "rosidian_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item RosidianHelmet = new RosidianArmour("RosidianHelmet", "rosidian_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item RunationBoots = new RunationArmour("RunationBoots", "runation_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item RunationLegs = new RunationArmour("RunationLegs", "runation_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item RunationBody = new RunationArmour("RunationChestplate", "runation_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item RunationHelmet = new RunationArmour("RunationHelmet", "runation_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item RunicBoots = new RunicArmour("RunicBoots", "runic_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item RunicLegs = new RunicArmour("RunicLegs", "runic_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item RunicBody = new RunicArmour("RunicChestplate", "runic_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item RunicHelmet = new RunicArmour("RunicHelmet", "runic_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item SharpshotBoots = new SharpshotArmour("SharpshotBoots", "sharpshot_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item SharpshotLegs = new SharpshotArmour("SharpshotLegs", "sharpshot_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item SharpshotBody = new SharpshotArmour("SharpshotChestplate", "sharpshot_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item SharpshotHelmet = new SharpshotArmour("SharpshotHelmet", "sharpshot_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item SkeletalBoots = new SkeletalArmour("SkeletalBoots", "skeletal_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item SkeletalLegs = new SkeletalArmour("SkeletalLegs", "skeletal_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item SkeletalBody = new SkeletalArmour("SkeletalChestplate", "skeletal_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item SkeletalHelmet = new SkeletalArmour("SkeletalHelmet", "skeletal_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item SpacekingBoots = new SpacekingArmour("SpacekingBoots", "spaceking_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item SpacekingLegs = new SpacekingArmour("SpacekingLegs", "spaceking_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item SpacekingBody = new SpacekingArmour("SpacekingChestplate", "spaceking_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item SpacekingHelmet = new SpacekingArmour("SpacekingHelmet", "spaceking_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item SpeedBoots = new SpeedArmour("SpeedBoots", "speed_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item SpeedLegs = new SpeedArmour("SpeedLegs", "speed_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item SpeedBody = new SpeedArmour("SpeedChestplate", "speed_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item SpeedHelmet = new SpeedArmour("SpeedHelmet", "speed_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item SubterraneanBoots = new SubterraneanArmour("SubterraneanBoots", "subterranean_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item SubterraneanLegs = new SubterraneanArmour("SubterraneanLegs", "subterranean_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item SubterraneanBody = new SubterraneanArmour("SubterraneanChestplate", "subterranean_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item SubterraneanHelmet = new SubterraneanArmour("SubterraneanHelmet", "subterranean_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item UtopianBoots = new UtopianArmour("UtopianBoots", "utopian_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item UtopianLegs = new UtopianArmour("UtopianLegs", "utopian_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item UtopianBody = new UtopianArmour("UtopianChestplate", "utopian_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item UtopianHelmet = new UtopianArmour("UtopianHelmet", "utopian_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item VoidBoots = new VoidArmour("VoidBoots", "void_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item VoidLegs = new VoidArmour("VoidLegs", "void_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item VoidBody = new VoidArmour("VoidChestplate", "void_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item VoidHelmet = new VoidArmour("VoidHelmet", "void_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item WeakenBoots = new WeakenArmour("WeakenBoots", "weaken_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item WeakenLegs = new WeakenArmour("WeakenLegs", "weaken_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item WeakenBody = new WeakenArmour("WeakenChestplate", "weaken_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item WeakenHelmet = new WeakenArmour("WeakenHelmet", "weaken_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item WitherBoots = new WitherArmour("WitherBoots", "wither_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item WitherLegs = new WitherArmour("WitherLegs", "wither_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item WitherBody = new WitherArmour("WitherChestplate", "wither_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item WitherHelmet = new WitherArmour("WitherHelmet", "wither_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item ZargoniteBoots = new ZargoniteArmour("ZargoniteBoots", "zargonite_boots", 0, EntityEquipmentSlot.FEET);
	public static final Item ZargoniteLegs = new ZargoniteArmour("ZargoniteLegs", "zargonite_legs", 0, EntityEquipmentSlot.LEGS);
	public static final Item ZargoniteBody = new ZargoniteArmour("ZargoniteChestplate", "zargonite_chestplate", 0, EntityEquipmentSlot.CHEST);
	public static final Item ZargoniteHelmet = new ZargoniteArmour("ZargoniteHelmet", "zargonite_helmet", 0, EntityEquipmentSlot.HEAD);

	public static final Item AchelosHelmet = new AchelosHelmet("AchelosHelmet", "achelos_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item FaceMask = new FaceMask("FaceMask", "face_mask", 0, EntityEquipmentSlot.HEAD);
	public static final Item NightVisionGoggles = new NightVisionGoggles("NightVisionGoggles", "night_vision_goggles", 0, EntityEquipmentSlot.HEAD);
	public static final Item OceanusHelmet = new OceanusHelmet("OceanusHelmet", "oceanus_helmet", 0, EntityEquipmentSlot.HEAD);
	public static final Item SealordHelmet = new SealordHelmet("SealordHelmet", "sealord_helmet", 0, EntityEquipmentSlot.HEAD);

	@SubscribeEvent
	public static void registerArmours(final RegistryEvent.Register<Item> ev) {
		ev.getRegistry().registerAll(
			AlacrityBoots,
			AlacrityLegs,
			AlacrityBody,
			AlacrityHelmet,
			AlchemyBody,
			AlchemyBoots,
			AlchemyHelmet,
			AlchemyLegs,
			AmethindBody,
			AmethindBoots,
			AmethindHelmet,
			AmethindLegs,
			AnimaBody,
			AnimaBoots,
			AnimaHelmet,
			AnimaLegs,
			ArchaicBody,
			ArchaicBoots,
			ArchaicHelmet,
			ArchaicLegs,
			AuguryBody,
			AuguryBoots,
			AuguryHelmet,
			AuguryLegs,
			BaronBody,
			BaronBoots,
			BaronHelmet,
			BaronLegs,
			BattlebornBody,
			BattlebornBoots,
			BattlebornHelmet,
			BattlebornLegs,
			BiogenicBody,
			BiogenicBoots,
			BiogenicHelmet,
			BiogenicLegs,
			BoreicBody,
			BoreicBoots,
			BoreicHelmet,
			BoreicLegs,
			ButcheryBody,
			ButcheryBoots,
			ButcheryHelmet,
			ButcheryLegs,
			CandyBody,
			CandyBoots,
			CandyHelmet,
			CandyLegs,
			CommanderBody,
			CommanderBoots,
			CommanderHelmet,
			CommanderLegs,
			CreationBody,
			CreationBoots,
			CreationHelmet,
			CreationLegs,
			CrystallisBody,
			CrystallisBoots,
			CrystallisHelmet,
			CrystallisLegs,
			ElecanyteBody,
			ElecanyteBoots,
			ElecanyteHelmet,
			ElecanyteLegs,
			EmbrodiumBody,
			EmbrodiumBoots,
			EmbrodiumHelmet,
			EmbrodiumLegs,
			EngineeringBody,
			EngineeringBoots,
			EngineeringHelmet,
			EngineeringLegs,
			ExoplateBody,
			ExoplateBoots,
			ExoplateHelmet,
			ExoplateLegs,
			ExpeditionBody,
			ExpeditionBoots,
			ExpeditionHelmet,
			ExpeditionLegs,
			ExplosiveBoots,
			ExplosiveLegs,
			ExplosiveBody,
			ExplosiveHelmet,
			ExtractionBoots,
			ExtractionLegs,
			ExtractionBody,
			ExtractionHelmet,
			ForagingBoots,
			ForagingLegs,
			ForagingBody,
			ForagingHelmet,
			FungalBoots,
			FungalLegs,
			FungalBody,
			FungalHelmet,
			GhastlyBoots,
			GhastlyLegs,
			GhastlyBody,
			GhastlyHelmet,
			GhoulishBoots,
			GhoulishLegs,
			GhoulishBody,
			GhoulishHelmet,
			HaulingBoots,
			HaulingLegs,
			HaulingBody,
			HaulingHelmet,
			HazmatBoots,
			HazmatLegs,
			HazmatBody,
			HazmatHelmet,
			HunterBoots,
			HunterLegs,
			HunterBody,
			HunterHelmet,
			HydrangicBoots,
			HydrangicLegs,
			HydrangicBody,
			HydrangicHelmet,
			HydroplateBoots,
			HydroplateLegs,
			HydroplateBody,
			HydroplateHelmet,
			IceBoots,
			IceLegs,
			IceBody,
			IceHelmet,
			InfernalBoots,
			InfernalLegs,
			InfernalBody,
			InfernalHelmet,
			InfusionBoots,
			InfusionLegs,
			InfusionBody,
			InfusionHelmet,
			InnervationBoots,
			InnervationLegs,
			InnervationBody,
			InnervationHelmet,
			KnightBoots,
			KnightLegs,
			KnightBody,
			KnightHelmet,
			LoggingBoots,
			LoggingLegs,
			LoggingBody,
			LoggingHelmet,
			LunarBoots,
			LunarLegs,
			LunarBody,
			LunarHelmet,
			LyndamyteBoots,
			LyndamyteLegs,
			LyndamyteBody,
			LyndamyteHelmet,
			LyonicBoots,
			LyonicLegs,
			LyonicBody,
			LyonicHelmet,
			MercurialBoots,
			MercurialLegs,
			MercurialBody,
			MercurialHelmet,
			NecroBoots,
			NecroLegs,
			NecroBody,
			NecroHelmet,
			NethengeicBoots,
			NethengeicLegs,
			NethengeicBody,
			NethengeicHelmet,
			NightmareBoots,
			NightmareLegs,
			NightmareBody,
			NightmareHelmet,
			OmniBoots,
			OmniLegs,
			OmniBody,
			OmniHelmet,
			PhantasmBoots,
			PhantasmLegs,
			PhantasmBody,
			PhantasmHelmet,
			PoisonBoots,
			PoisonLegs,
			PoisonBody,
			PoisonHelmet,
			PredatiousBoots,
			PredatiousLegs,
			PredatiousBody,
			PredatiousHelmet,
			PrimordialBoots,
			PrimordialLegs,
			PrimordialBody,
			PrimordialHelmet,
			PurityBoots,
			PurityLegs,
			PurityBody,
			PurityHelmet,
			RockboneBoots,
			RockboneLegs,
			RockboneBody,
			RockboneHelmet,
			RosidianBoots,
			RosidianLegs,
			RosidianBody,
			RosidianHelmet,
			RunationBoots,
			RunationLegs,
			RunationBody,
			RunationHelmet,
			RunicBoots,
			RunicLegs,
			RunicBody,
			RunicHelmet,
			SharpshotBoots,
			SharpshotLegs,
			SharpshotBody,
			SharpshotHelmet,
			SkeletalBoots,
			SkeletalLegs,
			SkeletalBody,
			SkeletalHelmet,
			SpacekingBoots,
			SpacekingLegs,
			SpacekingBody,
			SpacekingHelmet,
			SpeedBoots,
			SpeedLegs,
			SpeedBody,
			SpeedHelmet,
			SubterraneanBoots,
			SubterraneanLegs,
			SubterraneanBody,
			SubterraneanHelmet,
			UtopianBoots,
			UtopianLegs,
			UtopianBody,
			UtopianHelmet,
			VoidBoots,
			VoidLegs,
			VoidBody,
			VoidHelmet,
			WeakenBoots,
			WeakenLegs,
			WeakenBody,
			WeakenHelmet,
			WitherBoots,
			WitherLegs,
			WitherBody,
			WitherHelmet,
			ZargoniteBoots,
			ZargoniteLegs,
			ZargoniteBody,
			ZargoniteHelmet,
			AchelosHelmet,
			OceanusHelmet,
			SealordHelmet,
			FaceMask,
			NightVisionGoggles
		);
	}

	@SubscribeEvent
	public static void registerWeaponRender(final ModelRegistryEvent ev) {
		registerRender(AlacrityBody, "alacrity_body");
		registerRender(AlacrityBoots, "alacrity_boots");
		registerRender(AlacrityHelmet, "alacrity_helmet");
		registerRender(AlacrityLegs, "alacrity_legs");
		registerRender(AlchemyBody, "alchemy_body");
		registerRender(AlchemyBoots, "alchemy_boots");
		registerRender(AlchemyHelmet, "alchemy_helmet");
		registerRender(AlchemyLegs, "alchemy_legs");
		registerRender(AmethindBody, "amethind_body");
		registerRender(AmethindBoots, "amethind_boots");
		registerRender(AmethindHelmet, "amethind_helmet");
		registerRender(AmethindLegs, "amethind_legs");
		registerRender(AnimaBody, "anima_body");
		registerRender(AnimaBoots, "anima_boots");
		registerRender(AnimaHelmet, "anima_helmet");
		registerRender(AnimaLegs, "anima_legs");
		registerRender(ArchaicBody, "archaic_body");
		registerRender(ArchaicBoots, "archaic_boots");
		registerRender(ArchaicHelmet, "archaic_helmet");
		registerRender(ArchaicLegs, "archaic_legs");
		registerRender(AuguryBody, "augury_body");
		registerRender(AuguryBoots, "augury_boots");
		registerRender(AuguryHelmet, "augury_helmet");
		registerRender(AuguryLegs, "augury_legs");
		registerRender(BaronBody, "baron_body");
		registerRender(BaronBoots, "baron_boots");
		registerRender(BaronHelmet, "baron_helmet");
		registerRender(BaronLegs, "baron_legs");
		registerRender(BattlebornBody, "battleborn_body");
		registerRender(BattlebornBoots, "battleborn_boots");
		registerRender(BattlebornHelmet, "battleborn_helmet");
		registerRender(BattlebornLegs, "battleborn_legs");
		registerRender(BiogenicBody, "biogenic_body");
		registerRender(BiogenicBoots, "biogenic_boots");
		registerRender(BiogenicHelmet, "biogenic_helmet");
		registerRender(BiogenicLegs, "biogenic_legs");
		registerRender(BoreicBody, "boreic_body");
		registerRender(BoreicBoots, "boreic_boots");
		registerRender(BoreicHelmet, "boreic_helmet");
		registerRender(BoreicLegs, "boreic_legs");
		registerRender(ButcheryBody, "butchery_body");
		registerRender(ButcheryBoots, "butchery_boots");
		registerRender(ButcheryHelmet, "butchery_helmet");
		registerRender(ButcheryLegs, "butchery_legs");
		registerRender(CandyBody, "candy_body");
		registerRender(CandyBoots, "candy_boots");
		registerRender(CandyHelmet, "candy_helmet");
		registerRender(CandyLegs, "candy_legs");
		registerRender(CommanderBody, "commander_body");
		registerRender(CommanderBoots, "commander_boots");
		registerRender(CommanderHelmet, "commander_helmet");
		registerRender(CommanderLegs, "commander_legs");
		registerRender(CreationBody, "creation_body");
		registerRender(CreationBoots, "creation_boots");
		registerRender(CreationHelmet, "creation_helmet");
		registerRender(CreationLegs, "creation_legs");
		registerRender(CrystallisBody, "crystallis_body");
		registerRender(CrystallisBoots, "crystallis_boots");
		registerRender(CrystallisHelmet, "crystallis_helmet");
		registerRender(CrystallisLegs, "crystallis_legs");
		registerRender(ElecanyteBody, "elecanyte_body");
		registerRender(ElecanyteBoots, "elecanyte_boots");
		registerRender(ElecanyteHelmet, "elecanyte_helmet");
		registerRender(ElecanyteLegs, "elecanyte_legs");
		registerRender(EmbrodiumBody, "embrodium_body");
		registerRender(EmbrodiumBoots, "embrodium_boots");
		registerRender(EmbrodiumHelmet, "embrodium_helmet");
		registerRender(EmbrodiumLegs, "embrodium_legs");
		registerRender(EngineeringBody, "engineering_body");
		registerRender(EngineeringBoots, "engineering_boots");
		registerRender(EngineeringHelmet, "engineering_helmet");
		registerRender(EngineeringLegs, "engineering_legs");
		registerRender(ExoplateBody, "exoplate_body");
		registerRender(ExoplateBoots, "exoplate_boots");
		registerRender(ExoplateHelmet, "exoplate_helmet");
		registerRender(ExoplateLegs, "exoplate_legs");
		registerRender(ExpeditionBody, "expedition_body");
		registerRender(ExpeditionBoots, "expedition_boots");
		registerRender(ExpeditionHelmet, "expedition_helmet");
		registerRender(ExpeditionLegs, "expedition_legs");
		registerRender(ExplosiveBody, "explosive_body");
		registerRender(ExplosiveBoots, "explosive_boots");
		registerRender(ExplosiveHelmet, "explosive_helmet");
		registerRender(ExplosiveLegs, "explosive_legs");
		registerRender(ExtractionBody, "extraction_body");
		registerRender(ExtractionBoots, "extraction_boots");
		registerRender(ExtractionHelmet, "extraction_helmet");
		registerRender(ExtractionLegs, "extraction_legs");
		registerRender(ForagingBody, "foraging_body");
		registerRender(ForagingBoots, "foraging_boots");
		registerRender(ForagingHelmet, "foraging_helmet");
		registerRender(ForagingLegs, "foraging_legs");
		registerRender(FungalBody, "fungal_body");
		registerRender(FungalBoots, "fungal_boots");
		registerRender(FungalHelmet, "fungal_helmet");
		registerRender(FungalLegs, "fungal_legs");
		registerRender(GhastlyBody, "ghastly_body");
		registerRender(GhastlyBoots, "ghastly_boots");
		registerRender(GhastlyHelmet, "ghastly_helmet");
		registerRender(GhastlyLegs, "ghastly_legs");
		registerRender(GhoulishBody, "ghoulish_body");
		registerRender(GhoulishBoots, "ghoulish_boots");
		registerRender(GhoulishHelmet, "ghoulish_helmet");
		registerRender(GhoulishLegs, "ghoulish_legs");
		registerRender(HaulingBody, "hauling_body");
		registerRender(HaulingBoots, "hauling_boots");
		registerRender(HaulingHelmet, "hauling_helmet");
		registerRender(HaulingLegs, "hauling_legs");
		registerRender(HazmatBody, "hazmat_body");
		registerRender(HazmatBoots, "hazmat_boots");
		registerRender(HazmatHelmet, "hazmat_helmet");
		registerRender(HazmatLegs, "hazmat_legs");
		registerRender(HunterBody, "hunter_body");
		registerRender(HunterBoots, "hunter_boots");
		registerRender(HunterHelmet, "hunter_helmet");
		registerRender(HunterLegs, "hunter_legs");
		registerRender(HydrangicBody, "hydrangic_body");
		registerRender(HydrangicBoots, "hydrangic_boots");
		registerRender(HydrangicHelmet, "hydrangic_helmet");
		registerRender(HydrangicLegs, "hydrangic_legs");
		registerRender(HydroplateBody, "hydroplate_body");
		registerRender(HydroplateBoots, "hydroplate_boots");
		registerRender(HydroplateHelmet, "hydroplate_helmet");
		registerRender(HydroplateLegs, "hydroplate_legs");
		registerRender(IceBody, "ice_body");
		registerRender(IceBoots, "ice_boots");
		registerRender(IceHelmet, "ice_helmet");
		registerRender(IceLegs, "ice_legs");
		registerRender(InfernalBody, "infernal_body");
		registerRender(InfernalBoots, "infernal_boots");
		registerRender(InfernalHelmet, "infernal_helmet");
		registerRender(InfernalLegs, "infernal_legs");
		registerRender(InfusionBody, "infusion_body");
		registerRender(InfusionBoots, "infusion_boots");
		registerRender(InfusionHelmet, "infusion_helmet");
		registerRender(InfusionLegs, "infusion_legs");
		registerRender(InnervationBody, "innervation_body");
		registerRender(InnervationBoots, "innervation_boots");
		registerRender(InnervationHelmet, "innervation_helmet");
		registerRender(InnervationLegs, "innervation_legs");
		registerRender(KnightBody, "knight_body");
		registerRender(KnightBoots, "knight_boots");
		registerRender(KnightHelmet, "knight_helmet");
		registerRender(KnightLegs, "knight_legs");
		registerRender(LoggingBody, "logging_body");
		registerRender(LoggingBoots, "logging_boots");
		registerRender(LoggingHelmet, "logging_helmet");
		registerRender(LoggingLegs, "logging_legs");
		registerRender(LunarBody, "lunar_body");
		registerRender(LunarBoots, "lunar_boots");
		registerRender(LunarHelmet, "lunar_helmet");
		registerRender(LunarLegs, "lunar_legs");
		registerRender(LyndamyteBody, "lyndamyte_body");
		registerRender(LyndamyteBoots, "lyndamyte_boots");
		registerRender(LyndamyteHelmet, "lyndamyte_helmet");
		registerRender(LyndamyteLegs, "lyndamyte_legs");
		registerRender(LyonicBody, "lyonic_body");
		registerRender(LyonicBoots, "lyonic_boots");
		registerRender(LyonicHelmet, "lyonic_helmet");
		registerRender(LyonicLegs, "lyonic_legs");
		registerRender(MercurialBody, "mercurial_body");
		registerRender(MercurialBoots, "mercurial_boots");
		registerRender(MercurialHelmet, "mercurial_helmet");
		registerRender(MercurialLegs, "mercurial_legs");
		registerRender(NecroBody, "necro_body");
		registerRender(NecroBoots, "necro_boots");
		registerRender(NecroHelmet, "necro_helmet");
		registerRender(NecroLegs, "necro_legs");
		registerRender(NethengeicBody, "nethengeic_body");
		registerRender(NethengeicBoots, "nethengeic_boots");
		registerRender(NethengeicHelmet, "nethengeic_helmet");
		registerRender(NethengeicLegs, "nethengeic_legs");
		registerRender(NightmareBody, "nightmare_body");
		registerRender(NightmareBoots, "nightmare_boots");
		registerRender(NightmareHelmet, "nightmare_helmet");
		registerRender(NightmareLegs, "nightmare_legs");
		registerRender(OmniBody, "omni_body");
		registerRender(OmniBoots, "omni_boots");
		registerRender(OmniHelmet, "omni_helmet");
		registerRender(OmniLegs, "omni_legs");
		registerRender(PhantasmBody, "phantasm_body");
		registerRender(PhantasmBoots, "phantasm_boots");
		registerRender(PhantasmHelmet, "phantasm_helmet");
		registerRender(PhantasmLegs, "phantasm_legs");
		registerRender(PoisonBody, "poison_body");
		registerRender(PoisonBoots, "poison_boots");
		registerRender(PoisonHelmet, "poison_helmet");
		registerRender(PoisonLegs, "poison_legs");
		registerRender(PredatiousBody, "predatious_body");
		registerRender(PredatiousBoots, "predatious_boots");
		registerRender(PredatiousHelmet, "predatious_helmet");
		registerRender(PredatiousLegs, "predatious_legs");
		registerRender(PrimordialBody, "primordial_body");
		registerRender(PrimordialBoots, "primordial_boots");
		registerRender(PrimordialHelmet, "primordial_helmet");
		registerRender(PrimordialLegs, "primordial_legs");
		registerRender(PurityBody, "purity_body");
		registerRender(PurityBoots, "purity_boots");
		registerRender(PurityHelmet, "purity_helmet");
		registerRender(PurityLegs, "purity_legs");
		registerRender(RockboneBody, "rockbone_body");
		registerRender(RockboneBoots, "rockbone_boots");
		registerRender(RockboneHelmet, "rockbone_helmet");
		registerRender(RockboneLegs, "rockbone_legs");
		registerRender(RosidianBody, "rosidian_body");
		registerRender(RosidianBoots, "rosidian_boots");
		registerRender(RosidianHelmet, "rosidian_helmet");
		registerRender(RosidianLegs, "rosidian_legs");
		registerRender(RunationBody, "runation_body");
		registerRender(RunationBoots, "runation_boots");
		registerRender(RunationHelmet, "runation_helmet");
		registerRender(RunationLegs, "runation_legs");
		registerRender(RunicBody, "runic_body");
		registerRender(RunicBoots, "runic_boots");
		registerRender(RunicHelmet, "runic_helmet");
		registerRender(RunicLegs, "runic_legs");
		registerRender(SharpshotBody, "sharpshot_body");
		registerRender(SharpshotBoots, "sharpshot_boots");
		registerRender(SharpshotHelmet, "sharpshot_helmet");
		registerRender(SharpshotLegs, "sharpshot_legs");
		registerRender(SkeletalBody, "skeletal_body");
		registerRender(SkeletalBoots, "skeletal_boots");
		registerRender(SkeletalHelmet, "skeletal_helmet");
		registerRender(SkeletalLegs, "skeletal_legs");
		registerRender(SpacekingBody, "spaceking_body");
		registerRender(SpacekingBoots, "spaceking_boots");
		registerRender(SpacekingHelmet, "spaceking_helmet");
		registerRender(SpacekingLegs, "spaceking_legs");
		registerRender(SpeedBody, "speed_body");
		registerRender(SpeedBoots, "speed_boots");
		registerRender(SpeedHelmet, "speed_helmet");
		registerRender(SpeedLegs, "speed_legs");
		registerRender(SubterraneanBody, "subterranean_body");
		registerRender(SubterraneanBoots, "subterranean_boots");
		registerRender(SubterraneanHelmet, "subterranean_helmet");
		registerRender(SubterraneanLegs, "subterranean_legs");
		registerRender(UtopianBody, "utopian_body");
		registerRender(UtopianBoots, "utopian_boots");
		registerRender(UtopianHelmet, "utopian_helmet");
		registerRender(UtopianLegs, "utopian_legs");
		registerRender(VoidBody, "void_body");
		registerRender(VoidBoots, "void_boots");
		registerRender(VoidHelmet, "void_helmet");
		registerRender(VoidLegs, "void_legs");
		registerRender(WeakenBody, "weaken_body");
		registerRender(WeakenBoots, "weaken_boots");
		registerRender(WeakenHelmet, "weaken_helmet");
		registerRender(WeakenLegs, "weaken_legs");
		registerRender(WitherBody, "wither_body");
		registerRender(WitherBoots, "wither_boots");
		registerRender(WitherHelmet, "wither_helmet");
		registerRender(WitherLegs, "wither_legs");
		registerRender(ZargoniteBody, "zargonite_body");
		registerRender(ZargoniteBoots, "zargonite_boots");
		registerRender(ZargoniteHelmet, "zargonite_helmet");
		registerRender(ZargoniteLegs, "zargonite_legs");
		registerRender(AchelosHelmet, "achelos_helmet");
		registerRender(OceanusHelmet, "oceanus_helmet");
		registerRender(SealordHelmet, "sealord_helmet");
		registerRender(FaceMask, "face_mask");
		registerRender(NightVisionGoggles, "night_vision_goggles");
	}

	private static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:armour/" + location), "inventory"));
	}
}
