package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.tslat.aoa3.block.tileentity.TrophyTileEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class OrnateTrophyBlock extends TrophyBlock {
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		ItemStack stack = new ItemStack(asItem());
		TileEntity tile = world.getTileEntity(pos);
		TrophyTileEntity trophyTile;

		if (tile instanceof TrophyTileEntity && ((trophyTile = (TrophyTileEntity)tile).getEntityId() != null)) {
			CompoundNBT nbt = new CompoundNBT();
			CompoundNBT dataTag = new CompoundNBT();

			dataTag.putString("EntityID", ((TrophyTileEntity)tile).getEntityId());
			dataTag.putBoolean("OriginalTrophy", ((TrophyTileEntity)tile).isOriginal());
			nbt.put("BlockEntityTag", dataTag);

			stack.setTag(nbt);

			if (trophyTile.getCachedEntity() != null) {
				Entity cachedEntity = ((TrophyTileEntity)tile).getCachedEntity();
				String entityName = cachedEntity == null ? "" : LocaleUtil.getLocaleString(cachedEntity.getType().getTranslationKey());
				stack.setDisplayName(LocaleUtil.getLocaleMessage("block.aoa3.ornate_trophy.desc", TextFormatting.AQUA, entityName));
			}
		}

		return stack;
	}
}
