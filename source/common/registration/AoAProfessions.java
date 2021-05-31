package net.tslat.aoa3.common.registration;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;

public class AoAProfessions {
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<VillagerProfession> WANDERER = PROFESSIONS.register("wanderer", () -> new VillagerProfession("wanderer", PointOfInterestType.UNEMPLOYED, ImmutableSet.of(), ImmutableSet.of(), null));
}
