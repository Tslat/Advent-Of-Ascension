package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.particles.ParticleTypes;
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
import java.util.List;
import java.util.Optional;

public class EmberstonePickaxe extends BasePickaxe implements LootModifyingItem {
	public EmberstonePickaxe() {
		super(ItemUtil.customItemTier(2000, 10.0f, 5.5f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || !harvestedBlock.isToolEffective(ToolType.PICKAXE))
			return;

		ServerWorld world = lootContext.getLevel();
		BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootParameters.ORIGIN));
		ItemStack blockDrop = ItemStack.EMPTY;
		int blockDropIndex = -1;
		Item blockItem = byBlock(block);

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);

			if (stack.getItem() == blockItem)  {
				blockDrop = stack;
				blockDropIndex = i;

				break;
			}
		}

		if (blockDrop == ItemStack.EMPTY) {
			if (existingLoot.get(0).getItem() instanceof BlockItem) {
				blockDrop = existingLoot.get(0);
				blockDropIndex = 0;
			}
		}

		if (blockDrop != ItemStack.EMPTY) {
			Optional<FurnaceRecipe> smeltRecipe = world.getRecipeManager().getRecipeFor(IRecipeType.SMELTING, new Inventory(blockDrop), world);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem();
				int xp = (int)smeltRecipe.get().getExperience();

				existingLoot.set(blockDropIndex, smeltedStack.copy());
				block.popExperience(world, pos, xp);

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
