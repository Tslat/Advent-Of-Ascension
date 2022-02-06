package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
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

	public BonusMiningResult(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BONUS_MINING_RESULT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		BlockState state = context.getParamOrNull(LootParameters.BLOCK_STATE);

		if (state == null)
			return;

		if (!testAsChance())
			return;

		Block block = state.getBlock();

		if (!block.is(Tags.Blocks.ORES))
			return;

		Item blockItem = block.asItem();

		if (blockItem != Items.AIR) {
			for (ItemStack stack : loot) {
				if (stack.getItem() == blockItem)
					return;
			}
		}

		List<ItemStack> extraStacks = new ArrayList<ItemStack>();

		for (ItemStack stack : loot) {
			extraStacks.addAll(ItemUtil.increaseStackSize(stack, 1));
		}

		loot.addAll(extraStacks);
	}
}
