package net.tslat.aoa3.common.registration;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.tslat.aoa3.advancement.AoALevelUpTrigger;
import net.tslat.aoa3.advancement.AoAXpGainTrigger;

public class AdvancementTriggerRegister {
	public static final AoALevelUpTrigger levelUpTrigger = new AoALevelUpTrigger();
	public static final AoAXpGainTrigger xpGainTrigger = new AoAXpGainTrigger();

	public static void registerTriggers() {
		registerTriggers(
				levelUpTrigger,
				xpGainTrigger
		);
	}

	private static void registerTriggers(ICriterionTrigger... triggers) {
		for (ICriterionTrigger trigger : triggers) {
			CriteriaTriggers.register(trigger);
		}
	}
}
