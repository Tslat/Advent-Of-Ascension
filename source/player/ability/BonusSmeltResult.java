package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.ItemSmeltingEvent;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Random;

public class BonusSmeltResult extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ITEM_SMELTING};

	private final Random random = new Random();
	private final int uniqueIdHash;

	public BonusSmeltResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);

		this.uniqueIdHash = this.getUniqueIdentifier().hashCode();
	}

	public BonusSmeltResult(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);

		this.uniqueIdHash = this.getUniqueIdentifier().hashCode();
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemSmelting(ItemSmeltingEvent ev) {
		ItemStack smeltedStack = ev.getOutputStack();

		if (smeltedStack.getItem().getFoodProperties() == null) {
			random.setSeed(ev.getPlayer().tickCount + uniqueIdHash);

			if (random.nextFloat() < getScaledValue())
				smeltedStack.setCount(smeltedStack.getCount() + 1);
		}
	}
}
