package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ItemUtil;

import java.util.ArrayList;
import java.util.List;

public class BonusMiningResult extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};


	public BonusMiningResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	public BonusMiningResult(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		if (!testAsChance())
			return;

		if (!state.is(Tags.Blocks.ORES))
			return;

		Item blockItem = state.getBlock().asItem();

		if (blockItem != Items.AIR) {
			for (ItemStack stack : loot) {
				if (stack.getItem() == blockItem)
					return;
			}
		}

		List<ItemStack> extraStacks = new ArrayList<>();

		for (ItemStack stack : loot) {
			extraStacks.addAll(ItemUtil.increaseStackSize(stack, 1));
		}

		loot.addAll(extraStacks);
	}
}
