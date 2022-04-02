package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.content.item.misc.InfusionStone;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class InfusionTable extends Block {
	public InfusionTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_PURPLE).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player instanceof ServerPlayer) {
			ItemStack stack = player.getItemInHand(hand);
			Item item = stack.getItem();

			if (item instanceof InfusionStone) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)player);
				InfusionStone stone = (InfusionStone)item;
				int count = stack.getCount();

				/*if (player.isCreative() || plData.stats().getLevel(Skills.INFUSION) >= stone.getLvl()) {
					plData.stats().addXp(Skills.INFUSION, stone.getXp() * count, false, false);
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_INFUSION_TABLE_CONVERT.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

					int chanceMod = plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.INFUSION ? 33 : 100;
					int powerStoneCount = 0;

					for (int i = 0; i < count; i++) {
						if (RandomUtil.oneInNChance(chanceMod))
							powerStoneCount++;
					}

					if (!player.isCreative()) {
						if (powerStoneCount > 0) {
							player.setItemInHand(hand, new ItemStack(stone.getPowerStone(), powerStoneCount));
						}
						else {
							player.setItemInHand(hand, ItemStack.EMPTY);
						}
					}
					else {
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(stone.getPowerStone(), powerStoneCount));
					}
				}*/ // TODO
			}
			else {
				InfusionTableContainer.openContainer((ServerPlayer)player, pos);
			}
		}

		return InteractionResult.SUCCESS;
	}
}
