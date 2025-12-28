package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerSkillsLootModificationEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.List;

public class DoubleDropsChance extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerSkillsLootModificationEvent.class, serverOnly(this::handleLootModification)));

	public DoubleDropsChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);
	}

	public DoubleDropsChance(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DOUBLE_DROPS_CHANCE.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleLootModification(final PlayerSkillsLootModificationEvent ev) {
		if (!ev.getLootContext().hasParam(LootContextParams.THIS_ENTITY))
			return;

		List<ItemStack> loot = ev.getGeneratedLoot();
		Entity killedEntity = ev.getLootContext().getParamOrNull(LootContextParams.THIS_ENTITY);

		if (killedEntity instanceof Player || !(killedEntity instanceof LivingEntity))
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
