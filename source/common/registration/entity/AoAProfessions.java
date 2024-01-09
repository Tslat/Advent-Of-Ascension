package net.tslat.aoa3.common.registration.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoAPoiTypes;
import net.tslat.aoa3.common.registration.AoARegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import java.util.function.Supplier;

public final class AoAProfessions {
	public static void init() {}

	public static final DeferredHolder<VillagerProfession, VillagerProfession> WANDERER = register("wanderer", PoiType.NONE, null);
	public static final DeferredHolder<VillagerProfession, VillagerProfession> ASSASSIN = register("assassin", AoAPoiTypes.ASSASSIN, null);

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(String name, Predicate<Holder<PoiType>> jobPoi, @Nullable SoundEvent workingSound) {
		return register(name, jobPoi, PoiType.NONE, ImmutableSet::of, ImmutableSet::of, workingSound);
	}

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(String name, DeferredHolder<PoiType, ? extends PoiType> jobPoi, @Nullable SoundEvent workingSound) {
		return register(name, holder -> holder.is(jobPoi.getKey()), holder -> holder.is(jobPoi.getKey()), ImmutableSet::of, ImmutableSet::of, workingSound);
	}

	private static DeferredHolder<VillagerProfession, VillagerProfession> register(String name, Predicate<Holder<PoiType>> jobPoi, Predicate<Holder<PoiType>> acquirablePoi, Supplier<ImmutableSet<Item>> interestedItems, Supplier<ImmutableSet<Block>> secondaryPoiBlock, @Nullable SoundEvent workingSound) {
		return AoARegistries.VILLAGER_PROFESSIONS.register(name, () -> new VillagerProfession(name, jobPoi, acquirablePoi, interestedItems.get(), secondaryPoiBlock.get(), workingSound));
	}
}
