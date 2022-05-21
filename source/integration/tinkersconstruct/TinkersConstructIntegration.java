package net.tslat.aoa3.integration.tinkersconstruct;

import com.mojang.datafixers.util.Pair;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.integration.tinkersconstruct.modifier.*;
import net.tslat.aoa3.integration.tinkersconstruct.trait.*;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import java.util.function.Supplier;

public class TinkersConstructIntegration {
	public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(AdventOfAscension.MOD_ID);

	public static final Pair<ModifierId, StaticModifier<AntiAirTrait>> ANTI_AIR = registerModifier("anti_air", AntiAirTrait::new);
	public static final Pair<ModifierId, StaticModifier<BaronTrait>> BARON = registerModifier("baron", BaronTrait::new);
	public static final Pair<ModifierId, StaticModifier<BlazingTrait>> BLAZING = registerModifier("blazing", BlazingTrait::new);
	public static final Pair<ModifierId, StaticModifier<BoneShockTrait>> BONE_SHOCK = registerModifier("bone_shock", BoneShockTrait::new);
	public static final Pair<ModifierId, StaticModifier<CounterweightTrait>> COUNTERWEIGHT = registerModifier("counterweight", CounterweightTrait::new);
	public static final Pair<ModifierId, StaticModifier<DiscountedTrait>> DISCOUNTED = registerModifier("discounted", DiscountedTrait::new);
	public static final Pair<ModifierId, StaticModifier<EtherealMinerTrait>> ETHEREAL_MINER = registerModifier("ethereal_miner", EtherealMinerTrait::new);
	public static final Pair<ModifierId, StaticModifier<EvilPressureTrait>> EVIL_PRESSURE = registerModifier("evil_pressure", EvilPressureTrait::new);
	public static final Pair<ModifierId, StaticModifier<FlamingFuryTrait>> FLAMING_FURY = registerModifier("flaming_fury", FlamingFuryTrait::new);
	public static final Pair<ModifierId, StaticModifier<HighInCalciumTrait>> HIGH_IN_CALCIUM = registerModifier("high_in_calcium", HighInCalciumTrait::new);
	public static final Pair<ModifierId, StaticModifier<InfernalEnergyTrait>> INFERNAL_ENERGY = registerModifier("infernal_energy", InfernalEnergyTrait::new);
	public static final Pair<ModifierId, StaticModifier<LacedTrait>> LACED = registerModifier("laced", LacedTrait::new);
	public static final Pair<ModifierId, StaticModifier<RunicTrait>> RUNIC = registerModifier("runic", RunicTrait::new);
	public static final Pair<ModifierId, StaticModifier<ShyreSynthesisTrait>> SHYRE_SYNTHESIS = registerModifier("shyre_synthesis", ShyreSynthesisTrait::new);
	public static final Pair<ModifierId, StaticModifier<SoulSapTrait>> SOUL_SAP = registerModifier("soul_sap", SoulSapTrait::new);
	public static final Pair<ModifierId, StaticModifier<SoulHarvestTrait>> SOUL_HARVEST = registerModifier("soul_harvest", SoulHarvestTrait::new);
	public static final Pair<ModifierId, StaticModifier<ToxicTrait>> TOXIC = registerModifier("toxic", ToxicTrait::new);
	public static final Pair<ModifierId, StaticModifier<TweetingTrait>> TWEETING = registerModifier("tweeting", TweetingTrait::new);

	public static final Pair<ModifierId, StaticModifier<AirBladeModifier>> AIR_BLADE = registerModifier("air_blade", AirBladeModifier::new);
	public static final Pair<ModifierId, StaticModifier<CreepifiedModifier>> CREEPIFIED = registerModifier("creepified", CreepifiedModifier::new);
	public static final Pair<ModifierId, StaticModifier<MoneybagsModifier>> MONEYBAGS = registerModifier("moneybags", MoneybagsModifier::new);
	public static final Pair<ModifierId, StaticModifier<RefreezingModifier>> REFREEZING = registerModifier("refreezing", RefreezingModifier::new);
	public static final Pair<ModifierId, StaticModifier<SurpriseMeModifier>> SURPRISE_ME = registerModifier("surprise_me", SurpriseMeModifier::new);

	public static void preInit() {
		MODIFIERS.register(AdventOfAscension.modEventBus);
	}

	private static <T extends Modifier> Pair<ModifierId, StaticModifier<T>> registerModifier(String id, Supplier<T> modifier) {
		return Pair.of(new ModifierId(AdventOfAscension.id(id)), MODIFIERS.register(id, modifier));
	}
}
