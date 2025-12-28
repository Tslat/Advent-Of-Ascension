package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;
import java.util.Optional;

public class EmberstoneAxe extends BaseAxe implements LootModifyingItem {
	public EmberstoneAxe(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (existingLoot.isEmpty() || !harvestedBlock.is(BlockTags.LOGS) || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1)
			return;

		ServerLevel level = lootContext.getLevel();
		BlockPos pos = BlockPos.containing(lootContext.getParamOrNull(LootContextParams.ORIGIN));

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);
			Optional<SmeltingRecipe> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(stack), level).map(RecipeHolder::value);
			final int index = i;

			smeltRecipe.ifPresent(recipe -> {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem(level.registryAccess()).copy();

				smeltedStack.setCount(smeltedStack.getCount() * stack.getCount());
				existingLoot.set(index, smeltedStack);
				block.popExperience(level, pos, (int)smeltRecipe.get().getExperience());
			});
		}

		ParticleBuilder.forRandomPosInBlock(ParticleTypes.FLAME, pos)
				.spawnNTimes(5)
				.sendToAllPlayersTrackingBlock(level, pos);

		block.popExperience(level, pos, RandomUtil.numberBetween(1, 3));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
