package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class EmberstoneAxe extends BaseAxe implements LootModifyingItem {
	public EmberstoneAxe() {
		super(AoATiers.EMBERSTONE, 1.5f, AttackSpeed.AXE);
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
			Optional<SmeltingRecipe> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level).map(RecipeHolder::value);
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

		block.popExperience(level, pos, RandomUtil.randomNumberBetween(1, 3));
	}/*

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty())
			return;

		if (harvestedBlock.is(BlockTags.LOGS) && getDestroySpeed(getToolStack(lootContext), harvestedBlock) > 1) {
			ServerLevel world = lootContext.getLevel();
			Vec3 pos = lootContext.getParamOrNull(LootContextParams.ORIGIN);
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
				block.popExperience(world, BlockPos.containing(pos), RandomUtil.randomNumberBetween(1, 3) * blockDrop.getCount());

				for (int i = 0; i < 5; i++) {
					world.sendParticles(ParticleTypes.FLAME, pos.x + RandomUtil.randomValueUpTo(1), pos.y + RandomUtil.randomValueUpTo(1), pos.z + RandomUtil.randomValueUpTo(1), 1, 0, 0, 0, 0);
				}
			}
		}
	}*/

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
