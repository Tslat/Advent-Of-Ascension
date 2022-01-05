package net.tslat.aoa3.integration.tinkersconstruct;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.integration.tinkersconstruct.modifier.*;
import net.tslat.aoa3.integration.tinkersconstruct.trait.*;
import net.tslat.aoa3.util.FluidUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TinkersConstructIntegration {
	public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(Modifier.class, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Modifier> ANTI_AIR = registerModifier("anti_air", AntiAirTrait::new);
	public static final RegistryObject<Modifier> BARON = registerModifier("baron", BaronTrait::new);
	public static final RegistryObject<Modifier> BLAZING = registerModifier("blazing", BlazingTrait::new);
	public static final RegistryObject<Modifier> BONE_SHOCK = registerModifier("bone_shock", BoneShockTrait::new);
	public static final RegistryObject<Modifier> COUNTERWEIGHT = registerModifier("counterweight", CounterweightTrait::new);
	public static final RegistryObject<Modifier> DISCOUNTED = registerModifier("discounted", DiscountedTrait::new);
	public static final RegistryObject<Modifier> ETHEREAL_MINER = registerModifier("ethereal_miner", EtherealMinerTrait::new);
	public static final RegistryObject<Modifier> EVIL_PRESSURE = registerModifier("evil_pressure", EvilPressureTrait::new);
	public static final RegistryObject<Modifier> FLAMING_FURY = registerModifier("flaming_fury", FlamingFuryTrait::new);
	public static final RegistryObject<Modifier> HIGH_IN_CALCIUM = registerModifier("high_in_calcium", HighInCalciumTrait::new);
	public static final RegistryObject<Modifier> INFERNAL_ENERGY = registerModifier("infernal_energy", InfernalEnergyTrait::new);
	public static final RegistryObject<Modifier> LACED = registerModifier("laced", LacedTrait::new);
	public static final RegistryObject<Modifier> RUNIC = registerModifier("runic", RunicTrait::new);
	public static final RegistryObject<Modifier> SHYRE_SYNTHESIS = registerModifier("shyre_synthesis", ShyreSynthesisTrait::new);
	public static final RegistryObject<Modifier> SOUL_SAP = registerModifier("soul_sap", SoulSapTrait::new);
	public static final RegistryObject<Modifier> SOUL_HARVEST = registerModifier("soul_harvest", SoulHarvestTrait::new);
	public static final RegistryObject<Modifier> TOXIC = registerModifier("toxic", ToxicTrait::new);
	public static final RegistryObject<Modifier> TWEETING = registerModifier("tweeting", TweetingTrait::new);

	public static final RegistryObject<Modifier> AIR_BLADE = registerModifier("air_blade", AirBladeModifier::new);
	public static final RegistryObject<Modifier> CREEPIFIED = registerModifier("creepified", CreepifiedModifier::new);
	public static final RegistryObject<Modifier> MONEYBAGS = registerModifier("moneybags", MoneybagsModifier::new);
	public static final RegistryObject<Modifier> REFREEZING = registerModifier("refreezing", RefreezingModifier::new);
	public static final RegistryObject<Modifier> SURPRISE_ME = registerModifier("surprise_me", SurpriseMeModifier::new);

	public static void preInit() {
		MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	private static RegistryObject<Modifier> registerModifier(String id, Supplier<Modifier> modifier) {
		return MODIFIERS.register(id, modifier);
	}

	private static FluidUtil.RegisteredFluidHolder registerMoltenFluid(String id, Consumer<FluidUtil.Builder> fluidHandler) {
		FluidUtil.Builder fluidBuilder = new FluidUtil.Builder(id).isMolten().stillTexture(new ResourceLocation("tconstruct", "block/fluid/molten/still")).flowingTexture(new ResourceLocation("tconstruct", "block/fluid/molten/flowing"));

		fluidHandler.accept(fluidBuilder);

		return fluidBuilder.registerAll(AoAItems.ITEMS, AoABlocks.BLOCKS, AoABlocks.FLUIDS);
	}

	private static FluidUtil.RegisteredFluidHolder registerFluid(String id, Consumer<FluidUtil.Builder> fluidHandler) {
		FluidUtil.Builder fluidBuilder = new FluidUtil.Builder(id);

		fluidHandler.accept(fluidBuilder);

		return fluidBuilder.registerAll(AoAItems.ITEMS, AoABlocks.BLOCKS, AoABlocks.FLUIDS);
	}
}
