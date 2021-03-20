package net.tslat.aoa3.common.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoAItems;

import javax.annotation.Nullable;

public class MendingTableContainer extends UtilityBlockContainer {
	private int totalMaterialCost = 0;

	public MendingTableContainer(int screenId, PlayerInventory plInventory, IWorldPosCallable functionCaller) {
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
		return new Slot(output, 2, 134, 23) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public boolean mayPickup(PlayerEntity playerIn) {
				return hasItem();
			}

			@Override
			public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
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
		};
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
						output.setItem(0, repairedStack);
					}
				}
			}
		}
	}

	private void resetMendingContainerState() {
		output.setItem(0, ItemStack.EMPTY);
		totalMaterialCost = 0;
	}

	@Override
	protected Block getBlock() {
		return AoABlocks.MENDING_TABLE.get();
	}

	public static void openContainer(ServerPlayerEntity player, BlockPos pos) {
		NetworkHooks.openGui(player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("container.aoa3.mending_table");
			}

			@Nullable
			@Override
			public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
				return new MendingTableContainer(windowId, inv, IWorldPosCallable.create(player.level, pos));
			}
		}, pos);
	}
}
