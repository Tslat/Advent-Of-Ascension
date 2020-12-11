package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.minionslab.BaseSlab;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class CreationForge extends Block {
	public CreationForge() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.PURPLE, 10, 15, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand).getItem() instanceof BaseSlab) {
			if (player instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
				BaseSlab slab = (BaseSlab)player.getHeldItem(hand).getItem();

				if (plData.stats().getLevel(Skills.CREATION) >= slab.sacrificeLvl) {
					if (!player.isCreative())
						player.getHeldItem(hand).shrink(1);

					plData.stats().addXp(Skills.CREATION, slab.sacrificeXp, false, false);
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_CREATION_FORGE_SACRIFICE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
					player.container.detectAndSendChanges();
				}
				else {
					if (player instanceof ServerPlayerEntity)
						PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.CREATION, slab.sacrificeLvl);
				}
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.FAIL;
	}
}
