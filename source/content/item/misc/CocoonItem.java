package net.tslat.aoa3.content.item.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CocoonItem extends BlockItem {
    public CocoonItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos()).is(Blocks.COBWEB)) {
            return place(new BlockPlaceContext(context) {
                @Override
                public BlockPos getClickedPos() {
                    return getHitResult().getBlockPos();
                }

                @Override
                public boolean canPlace() {
                    return true;
                }

                @Override
                public boolean replacingClickedOnBlock() {
                    return true;
                }

                @Override
                public Direction[] getNearestLookingDirections() {
                    return Direction.orderedByNearest(getPlayer());
                }
            });
        }

        return super.useOn(context);
    }
}
