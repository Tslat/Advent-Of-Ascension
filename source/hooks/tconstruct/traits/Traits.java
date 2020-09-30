package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.hooks.tconstruct.modifiers.*;
import net.tslat.aoa3.utils.ConfigurationUtil;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class Traits {
	public static final AbstractTrait BARON = new TraitBaron();
	public static final AbstractTrait BLAZING = new TraitBlazing();
	public static final AbstractTrait BONE_SHOCK = new TraitBoneShock();
	public static final AbstractTrait DISCOUNTED = new TraitDiscounted();
	public static final AbstractTrait ETHEREAL_MINER = new TraitEtherealMiner();
	public static final AbstractTrait EVIL_PRESSURE = new TraitEvilPressure();
	public static final AbstractTrait FLAMING_FURY = new TraitFlamingFury();
	public static final AbstractTrait HIGH_IN_CALCIUM = new TraitHighInCalcium();
	public static final AbstractTrait INFERNAL_ENERGY = new TraitInfernalEnergy();
	public static final AbstractTrait LACED = new TraitLaced();
	public static final AbstractTrait SHYRE_SYNTHESIS = new TraitShyreSynthesis();
	public static final AbstractTrait SOUL_SAP = new TraitSoulSap();
	public static final AbstractTrait SOUL_HARVEST = new TraitSoulHarvest();
	public static final AbstractTrait TOXIC = new TraitToxic();
	public static final AbstractTrait TWEETING = new TraitTweeting();

	public static final AbstractTrait ANTI_AIR_1 = new TraitAntiAir(1);
	public static final AbstractTrait ANTI_AIR_2 = new TraitAntiAir(2);
	public static final AbstractTrait COUNTERWEIGHT_1 = new TraitCounterweight(1);
	public static final AbstractTrait COUNTERWEIGHT_2 = new TraitCounterweight(2);
	public static final AbstractTraitLeveled RUNIC_1 = new TraitRunic(1);
	public static final AbstractTraitLeveled RUNIC_2 = new TraitRunic(2);

	public static final ModifierTrait AIR_BLADE;
	public static final ModifierTrait BUTCHERER;
	public static final ModifierTrait REFREEZING;
	public static final ModifierTrait CREEPIFIED;
	public static final ModifierTrait SURPRISE_ME;
	public static final ModifierTrait MONEYBAGS;

	static {
		AIR_BLADE = ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers ? new ModAirBlade() : null;
		BUTCHERER = ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers ? new ModButcherer() : null;
		REFREEZING = ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers ? new ModRefreezing() : null;
		CREEPIFIED = ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers ? new ModCreepified() : null;
		SURPRISE_ME = ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers ? new ModSurpriseMe() : null;
		MONEYBAGS = ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers ? new ModMoneybags() : null;
	}

	public static void preInit() {}

	public static void init() {
		if (ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers) {
			AIR_BLADE.addItem(ItemRegister.SHARP_CLAW);
			BUTCHERER.addItem(ItemRegister.BLOODSTONE);
			REFREEZING.addItem(ItemRegister.ICE_CRYSTAL);
			CREEPIFIED.addItem(ItemRegister.UNSTABLE_GUNPOWDER);
			SURPRISE_ME.addItem(ItemRegister.CONFETTI_PILE);
			MONEYBAGS.addItem(ItemRegister.GOLD_COIN);
		}
	}

	public static int getModifierStage(ItemStack tool, ModifierTrait trait) {
		ModifierNBT.IntegerNBT tag = trait.getData(tool);

		if (tag.current == 0)
			return tag.level;

		return tag.current;
	}

	public static int getTraitLevel(ItemStack tool, AbstractTraitLeveled trait) {
		return ModifierNBT.readTag(TinkerUtil.getModifierTag(tool, trait.getModifierIdentifier())).level;
	}
}
