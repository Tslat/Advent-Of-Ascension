package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.RandomUtil;

public class EnigmaTable extends Block {
	public EnigmaTable() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.BLACK_TERRACOTTA, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			for (int i = 0; i < 4; i++) {
				ItemEntity unchargedStone = new ItemEntity(world, pos.getX(), pos.getY() + 0.2, pos.getZ(), new ItemStack(AoAItems.UNCHARGED_STONE.get()));

				unchargedStone.setPickupDelay(10);
				unchargedStone.addVelocity(RandomUtil.randomGaussianValue(), 1 + RandomUtil.randomValueUpTo(1d), RandomUtil.randomGaussianValue());
				world.addEntity(unchargedStone);
			}

			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}

		return ActionResultType.SUCCESS;
	}
}
