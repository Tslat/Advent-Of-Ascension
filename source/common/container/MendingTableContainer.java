package net.tslat.aoa3.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.item.AoAItems;

import javax.annotation.Nullable;

public class MendingTableContainer extends UtilityBlockContainer {
	private int totalMaterialCost = 0;

	public MendingTableContainer(int screenId, Inventory plInventory, ContainerLevelAccess functionCaller) {
		super(AoAContainers.MENDING_TABLE.get(), screenId, plInventory, functionCaller);
	}

	@Override
	protected Slot initFirstInputSlot() {
		return new Slot(inputs, 0, 27, 23) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.isDamageableItem();
			}
		};
	}

	@Override
	protected Slot initOutputSlot() {
		return null;
		/*return new Slot(output, 2, 134, 23) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public boolean mayPickup(Player playerIn) {
				return hasItem();
			}

			@Override
			public ItemStack onTake(Player thePlayer, ItemStack stack) {
				if (totalMaterialCost > 0) {
					ItemStack repairMaterialStack = inputs.getItem(1);

					if (!repairMaterialStack.isEmpty() && repairMaterialStack.getCount() >= totalMaterialCost) {
						repairMaterialStack.shrink(totalMaterialCost);
					}
					else {
						inputs.setItem(1, ItemStack.EMPTY);
					}
				}
				else {
					inputs.setItem(1, ItemStack.EMPTY);
				}

				inputs.setItem(0, ItemStack.EMPTY);

				return stack;
			}
		};*/
	}

	@Override
	public void updateOutput() {
		ItemStack repairStack = inputs.getItem(0);

		if (repairStack.isEmpty() || !repairStack.isDamageableItem()) {
			resetMendingContainerState();
		}
		else {
			ItemStack repairMaterial = inputs.getItem(1);

			if (repairMaterial.isEmpty()) {
				resetMendingContainerState();
			}
			else {
				ItemStack repairedStack = repairStack.copy();

				if (repairMaterial.getItem() == AoAItems.MAGIC_REPAIR_DUST.get() || repairMaterial.getItem() == AoAItems.MAGIC_MENDING_COMPOUND.get()) {
					int repairPortionValue = (repairMaterial.getItem() == AoAItems.MAGIC_MENDING_COMPOUND.get() ? repairedStack.getDamageValue() : Math.min(repairedStack.getDamageValue(), repairedStack.getMaxDamage() / 5));

					if (repairPortionValue <= 0) {
						resetMendingContainerState();
					}
					else {
						int repairCount;

						for (repairCount = 0; repairPortionValue > 0 && repairCount < repairMaterial.getCount(); repairCount++) {
							repairedStack.setDamageValue(repairedStack.getDamageValue() - repairPortionValue);

							repairPortionValue = Math.min(repairedStack.getDamageValue(), repairedStack.getMaxDamage() / 5);
						}

						totalMaterialCost = repairCount;
						//output.setItem(0, repairedStack);
					}
				}
			}
		}
	}

	private void resetMendingContainerState() {
		//output.setItem(0, ItemStack.EMPTY);
		totalMaterialCost = 0;
	}

	@Override
	protected Block getBlock() {
		return AoABlocks.MENDING_TABLE.get();
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		NetworkHooks.openGui(player, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return new TranslatableComponent("container.aoa3.mending_table");
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
				return new MendingTableContainer(windowId, inv, ContainerLevelAccess.create(player.level, pos));
			}
		}, pos);
	}
}
