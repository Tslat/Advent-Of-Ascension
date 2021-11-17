package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class CrystalExtensionShrine extends Block {
	public CrystalExtensionShrine() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_WHITE).stats(10f, 15f).sound(SoundType.GLASS).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!player.getItemInHand(hand).isEmpty()) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (heldStack.getItem() == AoAItems.RAINBOW_DRUSE.get()) {
				if (player instanceof ServerPlayerEntity) {
					List<ItemEntity> crystalList = world.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(pos.getX() - 5, pos.getY() - 1, pos.getZ() - 5, pos.getX() + 5, pos.getY() + 1, pos.getZ() + 5), entity -> isCrystal(entity.getItem().getItem()));
					int count = 0;

					for (ItemEntity item : crystalList) {
						count += item.getItem().getCount();

						if (count >= 10)
							break;
					}

					if (count < 10) {
						PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent("message.feedback.crystalExtensionShrine.crystals"));

						return ActionResultType.PASS;
					}

					for (int i = 10; i > 0; i--) {
						ItemEntity entity = crystalList.get(0);
						ItemStack stack = entity.getItem();
						int size = stack.getCount();

						stack.shrink(i);

						if (stack.getCount() <= 0)
							crystalList.remove(0);

						i -= size;
					}

					if (!player.isCreative())
						heldStack.shrink(1);
					
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.GIANT_CRYSTAL.get()));
				}

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.FAIL;
	}

	private static boolean isCrystal(Item item) {
		return item == AoAItems.BLUE_CRYSTAL.get() || item == AoAItems.GREEN_CRYSTAL.get() || item == AoAItems.PURPLE_CRYSTAL.get() || item == AoAItems.RED_CRYSTAL.get() || item == AoAItems.WHITE_CRYSTAL.get() || item == AoAItems.YELLOW_CRYSTAL.get();
	}
}
