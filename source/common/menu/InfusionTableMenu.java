package net.tslat.aoa3.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;
import net.tslat.aoa3.common.menu.slot.CraftableResultSlot;
import net.tslat.aoa3.common.menu.slot.OutputSlot;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.blockentity.InfusionTableBlockEntity;

public class InfusionTableMenu extends ExtensibleContainerMenu<TransientCraftingContainer> {
	public InfusionTableMenu(int screenId, Inventory playerInventory, ContainerLevelAccess accessValidator) {
		super(AoAMenus.INFUSION_TABLE.get(), screenId, playerInventory, accessValidator);

		createPlayerInventory(playerInventory, 8, 84);
	}

	@Override
    public int inputSlotCount() {
		return 10;
	}

	@Override
	protected TransientCraftingContainer createInventory() {
		return new TransientCraftingContainer(this, 0, 0, NonNullList.withSize(10, ItemStack.EMPTY));
	}

	@Override
	protected Block getContainerBlock() {
		return AoABlocks.INFUSION_TABLE.get();
	}

	@Override
	protected Slot createInputSlot(int slotIndex, TransientCraftingContainer inventory) {
		if (slotIndex == 0)
			return new Slot(inventory, slotIndex, 17, 35);

		return new Slot(inventory, slotIndex, 45 + gridXFromIndex(slotIndex - 1) * 18, 17 + gridYFromIndex(slotIndex - 1) * 18);
	}

	@Override
	protected Slot createOutputSlot(int slotIndex, Player player) {
		return new CraftableResultSlot<>(player, getInventory(), new ResultContainer(), AoARecipes.INFUSION.type().get(), slotIndex, 139, 35);
	}

	@Override
	protected void handleContainerUpdate() {
		final OutputSlot outputSlot = (OutputSlot)getOutputSlot();

		updateRecipeOutput(AoARecipes.UPGRADE_KIT.type().get(), outputSlot.getPlayer(), (ResultContainer)outputSlot.container, recipe -> true);
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		final BlockEntity blockEntity = player.level().getBlockEntity(pos);

		if (!(blockEntity instanceof InfusionTableBlockEntity infusionTable))
			return;

		player.openMenu(infusionTable, pos);
	}
}