package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class EmberstoneAxe extends BaseAxe implements LootModifyingItem {
	public EmberstoneAxe() {
		super(ItemUtil.customItemTier(2000, 10.0f, 11f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty())
			return;

		if (block.is(BlockTags.LOGS) && harvestedBlock.isToolEffective(ToolType.AXE)) {
			ServerWorld world = lootContext.getLevel();
			BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootParameters.ORIGIN));
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
				block.popExperience(world, pos, (1 + random.nextInt(3)) * blockDrop.getCount());

				for (int i = 0; i < 5; i++) {
					world.sendParticles(ParticleTypes.FLAME, pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), 1, 0, 0, 0, 0);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
