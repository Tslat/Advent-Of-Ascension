package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.block.generation.misc.RunePostBlock;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class RuneShrine extends Block {
	public RuneShrine() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_GRAY).stats(5f, 10f).get());
	}
	// TODO Fix level distribution across dimensions
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.UNPOWERED_RUNE.get() || heldStack.getItem() == AoAItems.CHARGED_RUNE.get()) {
			if (player instanceof ServerPlayerEntity) {
				BlockPos basePos = pos.above(2);
				Block post1 = world.getBlockState(basePos.north(3).east(3)).getBlock();
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);

				if (post1 instanceof RunePostBlock) {
					if (((RunePostBlock)post1).getLevelReq() <= plData.stats().getLevel(Skills.RUNATION)) {
						RuneItem rune = ((RunePostBlock)post1).getRune();

						if (rune.isCharged() && heldStack.getItem() != AoAItems.CHARGED_RUNE.get())
							return ActionResultType.FAIL;

						Block post2 = world.getBlockState(basePos.north(3).west(3)).getBlock();
						Block post3 = world.getBlockState(basePos.south(3).east(3)).getBlock();
						Block post4 = world.getBlockState(basePos.south(3).west(3)).getBlock();

						if (post1 == post2 && post2 == post3 && post3 == post4) {
							int runeCount = heldStack.getCount();

							if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.RUNATION)
								runeCount *= 2;

							plData.stats().addXp(Skills.RUNATION, ((RunePostBlock)post1).getXpGain() * heldStack.getCount(), false, false);
							player.level.playSound(null, player.getX(), player.getY(), player.getZ(), AoASounds.BLOCK_RUNE_SHRINE_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);

							if (!player.isCreative()) {
								int handCount = Math.min(64, runeCount);

								player.setItemInHand(hand, new ItemStack(rune, handCount));

								runeCount -= handCount;

								if (runeCount > 0)
									ItemUtil.givePlayerItemOrDrop(player, new ItemStack(rune, runeCount));
							}
							else {
								while (runeCount > 64) {
									ItemUtil.givePlayerItemOrDrop(player, new ItemStack(rune, 64));

									runeCount -= 64;
								}

								ItemUtil.givePlayerItemOrDrop(player, new ItemStack(rune, runeCount));
							}

							player.inventoryMenu.broadcastChanges();
						}
						else {
							return ActionResultType.FAIL;
						}
					}
					else {
						PlayerUtil.notifyPlayerOfInsufficientLevel(plData.player(), Skills.RUNATION, ((RunePostBlock)post1).getLevelReq());

						return ActionResultType.FAIL;
					}
				}
				else {
					if (!player.isCreative())
						player.setItemInHand(hand, ItemStack.EMPTY);

					plData.sendThrottledChatMessage("message.feedback.runeShrine.practice");
					plData.stats().addXp(Skills.RUNATION, 2 * heldStack.getCount(), false, false);
					player.level.playSound(null, player.getX(), player.getY(), player.getZ(), AoASounds.BLOCK_RUNE_SHRINE_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
					player.inventoryMenu.broadcastChanges();
				}
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
