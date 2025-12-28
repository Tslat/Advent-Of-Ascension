package net.tslat.aoa3.player.ability.extraction;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerSkillsLootModificationEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ItemUtil;

import java.util.List;

public class BonusMiningResult extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerSkillsLootModificationEvent.class, serverOnly(this::handleLootModification)));


	public BonusMiningResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	public BonusMiningResult(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleLootModification(final PlayerSkillsLootModificationEvent ev) {
		BlockState state = ev.getLootContext().getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		if (!testAsChance())
			return;

		if (!state.is(Tags.Blocks.ORES) || state.is(AoATags.Blocks.NO_BONUS_MINING_RESULT))
			return;

		Item blockItem = state.getBlock().asItem();
		List<ItemStack> loot = ev.getGeneratedLoot();

		if (blockItem != Items.AIR) {
			for (ItemStack stack : loot) {
				if (stack.getItem() == blockItem)
					return;
			}
		}

		List<ItemStack> extraStacks = new ObjectArrayList<>();

		for (ItemStack stack : loot) {
			extraStacks.addAll(ItemUtil.increaseStackSize(stack, 1));
		}

		loot.addAll(extraStacks);
	}
}
