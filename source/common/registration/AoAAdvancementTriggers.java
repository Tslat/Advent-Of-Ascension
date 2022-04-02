package net.tslat.aoa3.common.registration;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.tslat.aoa3.advancement.trigger.AoACycleTrigger;
import net.tslat.aoa3.advancement.trigger.AoALevelUpTrigger;
import net.tslat.aoa3.advancement.trigger.AoAXpGainTrigger;

public final class AoAAdvancementTriggers {
	public static final AoALevelUpTrigger levelUpTrigger = new AoALevelUpTrigger();
	public static final AoAXpGainTrigger xpGainTrigger = new AoAXpGainTrigger();
	public static final AoACycleTrigger cycleTrigger = new AoACycleTrigger();

	public static void registerTriggers() {
		registerTriggers(
				levelUpTrigger,
				xpGainTrigger,
				cycleTrigger
		);
	}

	private static void registerTriggers(CriterionTrigger<?>... triggers) {
		for (CriterionTrigger<?> trigger : triggers) {
			CriteriaTriggers.register(trigger);
		}
	}
}
