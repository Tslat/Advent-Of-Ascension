package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkeletalPickaxe extends BasePickaxe implements LootModifyingItem {
	public SkeletalPickaxe() {
		super(AoATiers.SKELETAL, -2, AttackSpeed.PICKAXE);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1)
			return;

		if (RandomUtil.oneInNChance(3)) {
			int dropChoice = RandomUtil.randomNumberUpTo(50);
			ItemStack drop;

			if (dropChoice == 0) {
				drop = new ItemStack(RandomUtil.getRandomSelection(
						AoAItems.SKULLBONE_FRAGMENT.get(),
						AoAItems.CHESTBONE_FRAGMENT.get(),
						AoAItems.LEGBONE_FRAGMENT.get(),
						AoAItems.FOOTBONE_FRAGMENT.get()));
			}
			else if (dropChoice < 10) {
				drop = new ItemStack(Items.BONE_MEAL);
			}
			else {
				drop = new ItemStack(Items.BONE);
			}

			existingLoot.add(drop);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SKELETAL_TOOL_DESCRIPTION, LocaleUtil.ItemDescriptionType.UNIQUE));
	}
}
