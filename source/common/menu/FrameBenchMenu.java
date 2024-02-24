package net.tslat.aoa3.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;
import net.tslat.aoa3.common.menu.provider.GenericMenuProvider;
import net.tslat.aoa3.common.menu.slot.OutputSlot;
import net.tslat.aoa3.common.menu.slot.PredicatedSlot;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;

import java.util.function.Supplier;


public class FrameBenchMenu extends ExtensibleContainerMenu<TransientCraftingContainer> {
	private static final Supplier<Item>[] SELECTIONS = new Supplier[] {AoAItems.CROSSBOW_FRAME, AoAItems.BLASTER_FRAME, AoAItems.CANNON_FRAME, AoAItems.HELMET_FRAME, AoAItems.CHESTPLATE_FRAME, AoAItems.LEGGINGS_FRAME, AoAItems.BOOTS_FRAME, AoAItems.GUN_FRAME, AoAItems.SHOTGUN_FRAME, AoAItems.SNIPER_FRAME};

	private int currentSelection = 0;

	public FrameBenchMenu(int screenId, Inventory playerInventory, ContainerLevelAccess accessValidator) {
		super(AoAMenus.FRAME_BENCH.get(), screenId, playerInventory, accessValidator);

		createPlayerInventory(playerInventory, 8, 84);
	}

	public int getCurrentSelection() {
		return this.currentSelection;
	}

	@Override
    public int inputSlotCount() {
		return 1;
	}

	@Override
	protected TransientCraftingContainer createInventory() {
		return new TransientCraftingContainer(this, 1, 1, NonNullList.withSize(1, ItemStack.EMPTY));
	}

	@Override
	protected Block getContainerBlock() {
		return AoABlocks.FRAME_BENCH.get();
	}

	@Override
	protected Slot createInputSlot(int slotIndex, TransientCraftingContainer inventory) {
		return new PredicatedSlot(inventory, slotIndex, 11, 34, stack -> stack.getItem() == AoAItems.SCRAP_METAL.get());
	}

	@Override
	protected Slot createOutputSlot(int slotIndex, Player player) {
		return new OutputSlot(new ResultContainer(), player, slotIndex, 149, 34) {
			@Override
			public void onItemRemoved(Player player, ItemStack stack) {
				consumeInputItem(0, 1);
			}
		};
	}

	@Override
	protected void handleContainerUpdate() {
		setOutputItem(getInputItem(0).isEmpty() ? ItemStack.EMPTY : SELECTIONS[this.currentSelection].get().getDefaultInstance());
	}

	@Override
	public boolean clickMenuButton(Player player, int data) {
		if (this.currentSelection == data)
			return false;

		this.currentSelection = data;
		handleContainerUpdate();

		return true;
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		player.openMenu(new GenericMenuProvider("frame_bench", pos, FrameBenchMenu::new), pos);
	}
}