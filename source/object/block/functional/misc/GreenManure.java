package net.tslat.aoa3.object.block.functional.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;

public class GreenManure extends CropBlock {
	public GreenManure() {
		super(MaterialColor.COLOR_GREEN, AoAItems.GREEN_MANURE_SEEDS);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (getAge(state) >= getMaxAge()) {
			ItemStack heldStack = player.getItemInHand(hand);

			if (heldStack.getItem().getToolTypes(heldStack).contains(ToolType.HOE)) {
				if (world.getBlockState(pos.below()).getBlock() instanceof FarmlandBlock)
					world.setBlockAndUpdate(pos.below(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FertilisedFarmland.WELL_FERTILISED, true));

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}
}
