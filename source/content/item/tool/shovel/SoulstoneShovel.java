package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SoulstoneShovel extends BaseShovel implements LootModifyingItem {
	public SoulstoneShovel() {
		super(AoATiers.SOULSTONE, -3.5f, AttackSpeed.SHOVEL);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1 || !lootContext.hasParam(LootContextParams.THIS_ENTITY))
			return;

		if (!BlockUtil.isBlockTaggedAs(block, BlockTags.DIRT, Tags.Blocks.SAND, Tags.Blocks.GRAVEL, AoATags.Blocks.GRASS) && !(block instanceof GrassBlock))
			return;

		Entity harvestingPlayer = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (!(harvestingPlayer instanceof ServerPlayer))
			return;

		ServerLevel world = lootContext.getLevel();
		BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootContextParams.ORIGIN));
		ItemStack blockDrop = ItemStack.EMPTY;
		Item blockItem = byBlock(block);

		for (ItemStack stack : existingLoot) {
			if (stack.getItem() == blockItem) {
				blockDrop = stack;

				break;
			}
		}

		if (blockDrop == ItemStack.EMPTY)
			blockDrop = existingLoot.get(0);

		if (blockDrop != ItemStack.EMPTY && PlayerUtil.consumeResource((ServerPlayer)harvestingPlayer, AoAResources.SPIRIT.get(), 5, false)) {
			blockDrop.setCount(blockDrop.getCount() * 2);

			for (int i = 0; i < 5; i++) {
				world.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + RandomUtil.randomValueUpTo(1), pos.getZ() + RandomUtil.randomValueUpTo(1), 1, 0, 0, 0, 0);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
