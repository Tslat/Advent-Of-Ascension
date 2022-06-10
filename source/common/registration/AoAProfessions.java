package net.tslat.aoa3.common.registration;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.RegistryObject;

public final class AoAProfessions {
	public static void init() {}

	public static final RegistryObject<VillagerProfession> WANDERER = AoARegistries.VILLAGER_PROFESSIONS.register("wanderer", () -> new VillagerProfession("wanderer", PoiType.NONE, PoiType.NONE, ImmutableSet.of(), ImmutableSet.of(), null));
}
