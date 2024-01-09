package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
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
import net.tslat.effectslib.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;

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

		ServerLevel level = lootContext.getLevel();
		BlockPos pos = BlockPos.containing(lootContext.getParamOrNull(LootContextParams.ORIGIN));

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);
			Optional<SmeltingRecipe> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level).map(RecipeHolder::value);
			final int index = i;

			smeltRecipe.ifPresent(recipe -> {
				ItemStack smeltedStack = recipe.getResultItem(level.registryAccess()).copy();

				smeltedStack.setCount(smeltedStack.getCount() * stack.getCount());
				existingLoot.set(index, smeltedStack);
				block.popExperience(level, pos, (int)recipe.getExperience());

				ParticleBuilder.forRandomPosInBlock(ParticleTypes.FLAME, pos)
						.spawnNTimes(5)
						.sendToAllPlayersTrackingBlock(level, pos);
			});
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
