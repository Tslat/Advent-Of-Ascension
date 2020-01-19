package net.tslat.aoa3.dimension;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;

public interface AoAWorldProvider {
	AoATeleporter getTeleporter(WorldServer fromWorld);

	default boolean canPlaceBlock(EntityPlayer player, BlockPos pos, IBlockState block) {return true;}

	default boolean canInteractWith(EntityPlayer player, @Nullable BlockPos pos, @Nullable Entity interactedEntity, ItemStack heldStack) {return true;}
}
