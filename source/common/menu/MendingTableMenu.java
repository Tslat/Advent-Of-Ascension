package net.tslat.aoa3.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.menu.container.UpdatingSimpleContainer;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;
import net.tslat.aoa3.common.menu.provider.GenericMenuProvider;
import net.tslat.aoa3.common.menu.slot.OutputSlot;
import net.tslat.aoa3.common.menu.slot.PredicatedSlot;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;

public class MendingTableMenu extends ExtensibleContainerMenu<SimpleContainer> {
	protected int totalRepairCost = 0;

	public MendingTableMenu(int screenId, Inventory playerInventory, ContainerLevelAccess accessValidator) {
		super(AoAMenus.MENDING_TABLE.get(), screenId, playerInventory, accessValidator);

		createPlayerInventory(playerInventory, 8, 60);
	}

	@Override
    public int inputSlotCount() {
		return 2;
	}

	@Override
	protected SimpleContainer createInventory() {
		return new UpdatingSimpleContainer(2, this::slotsChanged);
	}

	@Override
	protected Block getContainerBlock() {
		return AoABlocks.MENDING_TABLE.get();
	}

	@Override
	protected Slot createInputSlot(int slotIndex, SimpleContainer inventory) {
		final int slotX = slotIndex == 0 ? 27 : 76;

		return slotIndex == 0 ?
				new PredicatedSlot(inventory, slotIndex, slotX, 23, ItemStack::isRepairable) :
				new Slot(inventory, slotIndex, slotX, 23);
	}

	@Override
	protected Slot createOutputSlot(int slotIndex, Player player) {
		return new OutputSlot(new ResultContainer(), player, slotIndex, 134, 23) {
			@Override
			public void onItemRemoved(Player player, ItemStack stack) {
				setInputItem(0, ItemStack.EMPTY);
				consumeInputItem(1, MendingTableMenu.this.totalRepairCost);
			}
		};
	}

	@Override
	protected void handleContainerUpdate() {
		final ItemStack repairingStack = getInputItem(0);

		if (repairingStack.isEmpty() || !repairingStack.isDamaged()) {
			setOutputItem(ItemStack.EMPTY);

			return;
		}

		final ItemStack repairMaterial = getInputItem(1);

		if (repairMaterial.isEmpty()) {
			setOutputItem(ItemStack.EMPTY);

			return;
		}

		final ItemStack repairedStack = repairingStack.copy();

		if (repairMaterial.getItem() == AoAItems.MAGIC_REPAIR_DUST.get() || repairMaterial.getItem() == AoAItems.MAGIC_MENDING_COMPOUND.get()) {
			int repairPortionValue = (repairMaterial.getItem() == AoAItems.MAGIC_MENDING_COMPOUND.get() ? repairedStack.getDamageValue() : Math.min(repairedStack.getDamageValue(), repairedStack.getMaxDamage() / 5));

			if (repairPortionValue <= 0) {
				setOutputItem(ItemStack.EMPTY);
			}
			else {
				int repairCount;

				for (repairCount = 0; repairPortionValue > 0 && repairCount < repairMaterial.getCount(); repairCount++) {
					repairedStack.setDamageValue(repairedStack.getDamageValue() - repairPortionValue);

					repairPortionValue = Math.min(repairedStack.getDamageValue(), repairedStack.getMaxDamage() / 5);
				}

				this.totalRepairCost = repairCount;
				setOutputItem(repairedStack);
			}
		}
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		player.openMenu(new GenericMenuProvider("mending_table", pos, MendingTableMenu::new), pos);
	}
}
