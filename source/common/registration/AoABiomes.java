package net.tslat.aoa3.common.registration;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.worlds.abyss.AbyssBiome;
import net.tslat.aoa3.worldgen.worlds.ancientcavern.AncientCavernBiome;
import net.tslat.aoa3.worldgen.worlds.barathos.BarathosBiome;
import net.tslat.aoa3.worldgen.worlds.candyland.CandylandBiome;
import net.tslat.aoa3.worldgen.worlds.celeve.CeleveBiome;
import net.tslat.aoa3.worldgen.worlds.creeponia.CreeponiaBiome;
import net.tslat.aoa3.worldgen.worlds.crystevia.CrysteviaBiome;
import net.tslat.aoa3.worldgen.worlds.deeplands.DeeplandsBiome;
import net.tslat.aoa3.worldgen.worlds.dustopia.DustopiaBiome;
import net.tslat.aoa3.worldgen.worlds.gardencia.GardenciaBiome;
import net.tslat.aoa3.worldgen.worlds.greckon.GreckonBiome;
import net.tslat.aoa3.worldgen.worlds.haven.HavenBiome;
import net.tslat.aoa3.worldgen.worlds.immortallis.ImmortallisBiome;
import net.tslat.aoa3.worldgen.worlds.iromine.IromineBiome;
import net.tslat.aoa3.worldgen.worlds.lborean.LBoreanBiome;
import net.tslat.aoa3.worldgen.worlds.lelyetia.LelyetiaBiome;
import net.tslat.aoa3.worldgen.worlds.lunalus.LunalusBiome;
import net.tslat.aoa3.worldgen.worlds.mysterium.MysteriumBiome;
import net.tslat.aoa3.worldgen.worlds.precasia.PrecasiaBiome;
import net.tslat.aoa3.worldgen.worlds.runandor.RunandorBiome;
import net.tslat.aoa3.worldgen.worlds.shyrelands.ShyrelandsBiome;
import net.tslat.aoa3.worldgen.worlds.voxponds.VoxPondsBiome;

import java.util.function.Supplier;

public final class AoABiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<AoABiome> ABYSS = registerBiome("abyss", AbyssBiome::new);
	public static final RegistryObject<AoABiome> ANCIENT_CAVERN = registerBiome("ancient_cavern", AncientCavernBiome::new);
	public static final RegistryObject<AoABiome> BARATHOS = registerBiome("barathos", BarathosBiome::new);
	public static final RegistryObject<AoABiome> CANDYLAND = registerBiome("candyland", CandylandBiome::new);
	public static final RegistryObject<AoABiome> CELEVE = registerBiome("celeve", CeleveBiome::new);
	public static final RegistryObject<AoABiome> CREEPONIA = registerBiome("creeponia", CreeponiaBiome::new);
	public static final RegistryObject<AoABiome> CRYSTEVIA = registerBiome("crystevia", CrysteviaBiome::new);
	public static final RegistryObject<AoABiome> DEEPLANDS = registerBiome("deeplands", DeeplandsBiome::new);
	public static final RegistryObject<AoABiome> DUSTOPIA = registerBiome("dustopia", DustopiaBiome::new);
	public static final RegistryObject<AoABiome> GARDENCIA = registerBiome("gardencia", GardenciaBiome::new);
	public static final RegistryObject<AoABiome> GRECKON = registerBiome("greckon", GreckonBiome::new);
	public static final RegistryObject<AoABiome> HAVEN = registerBiome("haven", HavenBiome::new);
	public static final RegistryObject<AoABiome> IMMORTALLIS = registerBiome("immortallis", ImmortallisBiome::new);
	public static final RegistryObject<AoABiome> IROMINE = registerBiome("iromine", IromineBiome::new);
	public static final RegistryObject<AoABiome> LBOREAN = registerBiome("lborean", LBoreanBiome::new);
	public static final RegistryObject<AoABiome> LELYETIA = registerBiome("lelyetia", LelyetiaBiome::new);
	public static final RegistryObject<AoABiome> LUNALUS = registerBiome("lunalus", LunalusBiome::new);
	public static final RegistryObject<AoABiome> MYSTERIUM = registerBiome("mysterium", MysteriumBiome::new);
	public static final RegistryObject<AoABiome> PRECASIA = registerBiome("precasia", PrecasiaBiome::new);
	public static final RegistryObject<AoABiome> RUNANDOR = registerBiome("runandor", RunandorBiome::new);
	public static final RegistryObject<AoABiome> SHYRELANDS = registerBiome("shyrelands", ShyrelandsBiome::new);
	public static final RegistryObject<AoABiome> VOX_PONDS = registerBiome("vox_ponds", VoxPondsBiome::new);

	private static RegistryObject<AoABiome> registerBiome(String id, Supplier<AoABiome> biome) {
		return BIOMES.register(id, biome);
	}

	public static void initBiomes() {
		for (RegistryObject<Biome> biome : BIOMES.getEntries()) {
			BiomeDictionary.addTypes(biome.get(), ((AoABiome)biome.get()).getBiomeTypes());
		}
	}
}
