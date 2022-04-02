package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

public class EnigmaTable extends Block {
	public EnigmaTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_BLACK).stats(10f, 15f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide && WorldUtil.isWorld(world, AoADimensions.VOX_PONDS.key)) {
			for (int i = 0; i < 4; i++) {
				ItemEntity unchargedStone = new ItemEntity(world, pos.getX(), pos.getY() + 0.2, pos.getZ(), new ItemStack(AoAItems.UNCHARGED_STONE.get()));

				unchargedStone.setPickUpDelay(10);
				unchargedStone.push(RandomUtil.randomGaussianValue(), 1 + RandomUtil.randomValueUpTo(1d), RandomUtil.randomGaussianValue());
				world.addFreshEntity(unchargedStone);
			}

			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}

		return InteractionResult.SUCCESS;
	}
}
