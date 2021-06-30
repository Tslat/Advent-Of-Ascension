package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.tileentity.LunarCreationTableTileEntity;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class LunarCreationTable extends Block {
	private static final ITextComponent CONTAINER_TITLE = new TranslationTextComponent("container." + AdventOfAscension.MOD_ID + ".lunar_creation_table");

	public LunarCreationTable() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_PURPLE).stats(10f, 15f).get());
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new LunarCreationTableTileEntity();
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isClientSide()) {
			player.openMenu(state.getMenuProvider(world, pos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);

			return ActionResultType.CONSUME;
		}

		return ActionResultType.SUCCESS;
	}

	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedContainerProvider((id, inventory, player) -> {
			WorkbenchContainer container = new WorkbenchContainer(id, inventory, IWorldPosCallable.create(world, pos)) {
				@Override
				public void removed(PlayerEntity player) {
					LunarCreationTableTileEntity tileEntity = (LunarCreationTableTileEntity)access.evaluate((BiFunction<World, BlockPos, Object>)this::getTileEntity, null);

					if (tileEntity != null) {
						tileEntity.setContents(craftSlots.items);

						craftSlots.clearContent();
					}

					super.removed(player);
				}

				@Nullable
				private LunarCreationTableTileEntity getTileEntity(World world, BlockPos pos) {
					TileEntity te = world.getBlockEntity(pos);

					return te instanceof LunarCreationTableTileEntity ? (LunarCreationTableTileEntity)te : null;
				}

				@Override
				public void slotsChanged(IInventory inventory) {
					LunarCreationTableTileEntity tileEntity = (LunarCreationTableTileEntity)access.evaluate((BiFunction<World, BlockPos, Object>)this::getTileEntity, null);

					if (tileEntity != null)
						tileEntity.setContents(craftSlots.items);

					super.slotsChanged(inventory);
				}

				@Override
				public boolean stillValid(PlayerEntity player) {
					return stillValid(access, player, AoABlocks.LUNAR_CREATION_TABLE.get());
				}
			};

			TileEntity te = world.getBlockEntity(pos);

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
