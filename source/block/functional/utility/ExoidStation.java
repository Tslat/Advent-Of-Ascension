package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.mob.voxponds.ExoidEntity;
import net.tslat.aoa3.util.BlockUtil;

public class ExoidStation extends Block {
	public ExoidStation() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.GREEN_TERRACOTTA, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand).getItem() == AoAItems.APOCO_STONE.get()) {
			if (!world.isRemote()) {
				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				ExoidEntity exoid = new ExoidEntity(world, 0);

				exoid.setLocationAndAngles(pos.getX(), pos.getY() + 3, pos.getZ(), 0, 0);
				world.addEntity(exoid);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.FAIL;
	}
}
