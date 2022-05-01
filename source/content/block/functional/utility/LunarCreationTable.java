package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.tileentity.LunarCreationTableTileEntity;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class LunarCreationTable extends Block implements EntityBlock {
	private static final Component CONTAINER_TITLE = new TranslatableComponent("container." + AdventOfAscension.MOD_ID + ".lunar_creation_table");

	public LunarCreationTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_PURPLE).stats(10f, 15f).get());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new LunarCreationTableTileEntity(pos, state);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			BlockEntity tile = world.getBlockEntity(pos);

			if (tile instanceof LunarCreationTableTileEntity)
				((LunarCreationTableTileEntity)tile).dropContents(world, pos);

			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide()) {
			player.openMenu(state.getMenuProvider(world, pos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);

			return InteractionResult.CONSUME;
		}

		return InteractionResult.SUCCESS;
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, player) -> {
			CraftingMenu container = new CraftingMenu(id, inventory, ContainerLevelAccess.create(world, pos)) {
				@Override
				public void removed(Player player) {
					LunarCreationTableTileEntity tileEntity = (LunarCreationTableTileEntity)access.evaluate((BiFunction<Level, BlockPos, Object>)this::getTileEntity, null);

					if (tileEntity != null) {
						tileEntity.setContents(craftSlots.items);

						craftSlots.clearContent();
					}

					super.removed(player);
				}

				@Nullable
				private LunarCreationTableTileEntity getTileEntity(Level world, BlockPos pos) {
					BlockEntity te = world.getBlockEntity(pos);

					return te instanceof LunarCreationTableTileEntity ? (LunarCreationTableTileEntity)te : null;
				}

				@Override
				public void slotsChanged(Container inventory) {
					LunarCreationTableTileEntity tileEntity = (LunarCreationTableTileEntity)access.evaluate((BiFunction<Level, BlockPos, Object>)this::getTileEntity, null);

					if (tileEntity != null)
						tileEntity.setContents(craftSlots.items);

					super.slotsChanged(inventory);
				}

				@Override
				public boolean stillValid(Player player) {
					return stillValid(access, player, AoABlocks.LUNAR_CREATION_TABLE.get());
				}
			};

			BlockEntity te = world.getBlockEntity(pos);

			if (te instanceof LunarCreationTableTileEntity) {
				NonNullList<ItemStack> cachedContents = ((LunarCreationTableTileEntity)te).getContents();

				for (int i = 0; i < cachedContents.size(); i++) {
					container.craftSlots.items.set(i, cachedContents.get(i));
				}

				container.slotsChanged(container.craftSlots);
			}

			return container;
		}, CONTAINER_TITLE);
	}
}
