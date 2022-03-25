package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.ItemSmeltingEvent;
import net.tslat.aoa3.player.skill.AoASkill;

public class BonusSmeltResult extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_SMELTING};

	public BonusSmeltResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);
	}

	public BonusSmeltResult(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemSmelting(ItemSmeltingEvent ev) {
		ItemStack smeltedStack = ev.getOutputStack();

		if (smeltedStack.getItem().getFoodProperties() == null && testAsChance())
			smeltedStack.setCount(smeltedStack.getCount() + 1);
	}
}
