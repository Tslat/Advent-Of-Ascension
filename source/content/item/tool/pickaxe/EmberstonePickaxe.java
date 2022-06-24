package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class EmberstonePickaxe extends BasePickaxe implements LootModifyingItem {
	public EmberstonePickaxe() {
		super(AoATiers.EMBERSTONE.adjusted(AdventOfAscension.id("emberstone_pickaxe")).damage(7.5f));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1)
			return;

		ServerLevel world = lootContext.getLevel();
		BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootContextParams.ORIGIN));
		Item blockItem = block.asItem();

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack lootStack = existingLoot.get(i);

			if (lootStack.getItem() == blockItem)
				continue;

			Optional<SmeltingRecipe> smeltRecipe = world.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(lootStack), world);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem().copy();

				smeltedStack.setCount(lootStack.getCount());

				existingLoot.set(i, smeltedStack);
				block.popExperience(world, pos, (int)smeltRecipe.get().getExperience());

				for (int x = 0; x < 5; x++) {
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
