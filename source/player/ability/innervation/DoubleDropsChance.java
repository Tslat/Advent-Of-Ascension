package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.List;

public class DoubleDropsChance extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	public DoubleDropsChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);
	}

	public DoubleDropsChance(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!context.hasParam(LootContextParams.THIS_ENTITY))
			return;

		Entity killedEntity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (killedEntity instanceof Player)
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
							extras = new ObjectArrayList<>();

						ItemStack newStack = stack.copy();

						newStack.setCount(stack.getCount() * 2 - newStack.getMaxStackSize());
						stack.setCount(stack.getMaxStackSize());
						extras.add(newStack);
					}
				}
				else {
					if (extras == null)
						extras = new ObjectArrayList<>();

					extras.add(stack.copy());
				}
			}

			if (extras != null)
				loot.addAll(extras);
		}
	}
}
