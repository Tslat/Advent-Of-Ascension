package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class EmberstoneAxe extends BaseAxe implements LootModifyingItem {
	public EmberstoneAxe() {
		super(AoATiers.EMBERSTONE, 1.5f, AttackSpeed.AXE);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty())
			return;

		if (harvestedBlock.is(BlockTags.LOGS) && getDestroySpeed(getToolStack(lootContext), harvestedBlock) > 1) {
			ServerLevel world = lootContext.getLevel();
			BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootContextParams.ORIGIN));
			ItemStack blockDrop = ItemStack.EMPTY;
			Item blockItem = Item.byBlock(block);
			Iterator<ItemStack> stackIterator = existingLoot.iterator();

			while (stackIterator.hasNext()) {
				ItemStack stack = stackIterator.next();

				if (stack.getItem() == blockItem) {
					blockDrop = stack;

					stackIterator.remove();
					break;
				}
			}

			if (blockDrop != ItemStack.EMPTY) {
				block.popExperience(world, pos, RandomUtil.randomNumberBetween(1, 3) * blockDrop.getCount());

				for (int i = 0; i < 5; i++) {
					world.sendParticles(ParticleTypes.FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + RandomUtil.randomValueUpTo(1), pos.getZ() + RandomUtil.randomValueUpTo(1), 1, 0, 0, 0, 0);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
