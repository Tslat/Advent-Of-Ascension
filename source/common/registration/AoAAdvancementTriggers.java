package net.tslat.aoa3.common.registration;

import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advancement.trigger.AoACycleTrigger;
import net.tslat.aoa3.advancement.trigger.AoALevelUpTrigger;
import net.tslat.aoa3.advancement.trigger.AoAXpGainTrigger;

import java.util.function.Supplier;

public final class AoAAdvancementTriggers {
	public static void init() {}

	public static final DeferredHolder<CriterionTrigger<?>, AoALevelUpTrigger> LEVEL_UP = register("level_up", AoALevelUpTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, AoAXpGainTrigger> XP_GAIN = register("gain_xp", AoAXpGainTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, AoACycleTrigger> CYCLE_SKILL = register("cycle_skill", AoACycleTrigger::new);

	private static <T extends CriterionTrigger<?>> DeferredHolder<CriterionTrigger<?>, T> register(String id, Supplier<T> trigger) {
		return AoARegistries.ADVANCEMENT_CRITERIA.register(id, trigger);
	}
}
