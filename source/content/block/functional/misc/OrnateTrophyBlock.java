package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.content.block.tileentity.TrophyTileEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class OrnateTrophyBlock extends TrophyBlock {
	public OrnateTrophyBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
		ItemStack stack = new ItemStack(asItem());
		BlockEntity tile = world.getBlockEntity(pos);
		TrophyTileEntity trophyTile;

		if (tile instanceof TrophyTileEntity && ((trophyTile = (TrophyTileEntity)tile).getEntityId() != null)) {
			CompoundTag nbt = new CompoundTag();
			CompoundTag dataTag = new CompoundTag();

			dataTag.putString("EntityID", ((TrophyTileEntity)tile).getEntityId());
			dataTag.putBoolean("OriginalTrophy", ((TrophyTileEntity)tile).isOriginal());
			nbt.put("BlockEntityTag", dataTag);

			stack.setTag(nbt);

			if (trophyTile.getCachedEntity() != null) {
				Entity cachedEntity = ((TrophyTileEntity)tile).getCachedEntity();
				Component entityName = cachedEntity == null ? Component.literal("") : cachedEntity.getName();
				stack.setHoverName(LocaleUtil.getLocaleMessage("block.aoa3.ornate_trophy.desc", ChatFormatting.AQUA, entityName));
			}
		}

		return stack;
	}
}
