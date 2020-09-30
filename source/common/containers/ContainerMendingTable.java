package net.tslat.aoa3.common.containers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class ContainerMendingTable extends ContainerBasicUtility {
	private int totalMaterialCost = 0;

	public ContainerMendingTable(EntityPlayer player, World world, BlockPos pos) {
		super(player, world, pos);
	}

	@Override
	protected Slot initFirstInputSlot() {
		return new Slot(inputs, 0, 27, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.isItemStackDamageable();
			}
		};
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!player.world.isRemote)
			clearContainer(player, player.world, inputs);
	}

	@Override
	protected Slot initOutputSlot() {
		return new Slot(output, 2, 134, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}

			@Override
			public boolean canTakeStack(EntityPlayer playerIn) {
				return getHasStack();
			}

			@Override
			public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
				if (totalMaterialCost > 0) {
					ItemStack repairMaterialStack = inputs.getStackInSlot(1);

					if (!repairMaterialStack.isEmpty() && repairMaterialStack.getCount() >= totalMaterialCost) {
						repairMaterialStack.shrink(totalMaterialCost);
					}
					else {
						inputs.setInventorySlotContents(1, ItemStack.EMPTY);
					}
				}
				else {
					inputs.setInventorySlotContents(1, ItemStack.EMPTY);
				}

				inputs.setInventorySlotContents(0, ItemStack.EMPTY);

				return stack;
			}
		};
	}

	@Override
	public void updateOutput() {
		ItemStack repairStack = inputs.getStackInSlot(0);

		if (repairStack.isEmpty() || !repairStack.isItemStackDamageable()) {
			resetMendingContainerState();
		}
		else {
			ItemStack repairMaterial = inputs.getStackInSlot(1);

			if (repairMaterial.isEmpty()) {
				resetMendingContainerState();
			}
			else {
				ItemStack repairedStack = repairStack.copy();

				if (repairMaterial.getItem() == ItemRegister.MAGIC_REPAIR_DUST || repairMaterial.getItem() == ItemRegister.MAGIC_MENDING_COMPOUND) {
					int repairPortionValue = (repairMaterial.getItem() == ItemRegister.MAGIC_MENDING_COMPOUND ? repairedStack.getItemDamage() : Math.min(repairedStack.getItemDamage(), repairedStack.getMaxDamage() / 5));

					if (repairPortionValue <= 0) {
						resetMendingContainerState();
					}
					else {
						int repairCount;

						for (repairCount = 0; repairPortionValue > 0 && repairCount < repairMaterial.getCount(); repairCount++) {
							repairedStack.setItemDamage(repairedStack.getItemDamage() - repairPortionValue);

							repairPortionValue = Math.min(repairedStack.getItemDamage(), repairedStack.getMaxDamage() / 5);
						}

						totalMaterialCost = repairCount;
						output.setInventorySlotContents(0, repairedStack);
					}
				}
			}
		}
	}

	private void resetMendingContainerState() {
		output.setInventorySlotContents(0, ItemStack.EMPTY);
		totalMaterialCost = 0;
	}

	@Override
	protected String getGuiTitle() {
		return "Mending";
	}

	@Override
	protected Block getBlock() {
		return BlockRegister.MENDING_TABLE;
	}
}
