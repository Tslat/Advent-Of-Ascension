package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.ArrayList;
import java.util.List;

public class DoubleDropsChance extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	public DoubleDropsChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);
	}

	public DoubleDropsChance(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!context.hasParam(LootParameters.THIS_ENTITY))
			return;

		Entity killedEntity = context.getParamOrNull(LootParameters.THIS_ENTITY);

		if (killedEntity instanceof PlayerEntity)
			return;

		if (testAsChance()) {
			List<ItemStack> extras = null;

			for (ItemStack stack : loot) {
				if (stack.isStackable()) {
					if (stack.getCount() <= stack.getMaxStackSize() / 2) {
						stack.setCount(stack.getCount() * 2);
					}
					else {
						if (extras == null)
							extras = new ArrayList<ItemStack>();

						ItemStack newStack = stack.copy();

						newStack.setCount(stack.getCount() * 2 - newStack.getMaxStackSize());
						stack.setCount(stack.getMaxStackSize());
						extras.add(newStack);
					}
				}
				else {
					if (extras == null)
						extras = new ArrayList<ItemStack>();

					extras.add(stack.copy());
				}
			}

			if (extras != null)
				loot.addAll(extras);
		}
	}
}
