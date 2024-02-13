package net.tslat.aoa3.content.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ImbuingChamberBlockEntity extends BlockEntity implements Nameable, MenuProvider {
	private static final Component DEFAULT_NAME = Component.translatable("container." + AdventOfAscension.MOD_ID + ".imbuing_chamber");

	private final NonNullList<ItemStack> items = NonNullList.withSize(7, ItemStack.EMPTY);

	@Nullable
	private Component customName;

	public ImbuingChamberBlockEntity(BlockPos pos, BlockState state) {
		super(AoABlockEntities.IMBUING_CHAMBER.get(), pos, state);
	}

	public NonNullList<ItemStack> getContents() {
		return this.items;
	}

	public void dropContents(Level world, BlockPos pos) {
		NonNullList<ItemStack> contents = getContents();

		for (int i = 0; i < contents.size() - 1; i++) {
			world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, contents.get(i)));
		}

		this.items.clear();
		setChanged();
	}

	public void setContents(List<ItemStack> contents) {
		this.items.clear();

		for (int i = 0; i < 7 && contents.size() > i; i++) {
			this.items.set(i, contents.get(i));
		}

		setChanged();

		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();

		ContainerHelper.saveAllItems(tag, this.items, true);

		return tag;
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);

		ContainerHelper.saveAllItems(compound, this.items, true);

		if (this.customName != null)
			compound.putString("CustomName", Component.Serializer.toJson(this.customName));
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		this.items.clear();
		ContainerHelper.loadAllItems(compound, this.items);

		if (compound.contains("CustomName", Tag.TAG_STRING))
			this.customName = Component.Serializer.fromJson(compound.getString("CustomName"));
	}

	@Override
	public Component getName() {
		return this.customName != null ? this.customName : DEFAULT_NAME;
	}

	@Override
	public Component getDisplayName() {
		return getName();
	}

	public void setCustomName(@Nullable Component name) {
		this.customName = name;
	}

	@Nullable
	public Component getCustomName() {
		return this.customName;
	}

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Nullable
	@Override
	public ImbuingChamberMenu createMenu(int containerId, Inventory playerInventory, Player openingPlayer) {
		ImbuingChamberMenu container = new ImbuingChamberMenu(containerId, playerInventory, ContainerLevelAccess.create(openingPlayer.level(), getBlockPos())) {
			@Override
			protected void clearContainer(Player player, Container container) {
				setContents(getInventory().getItems());
				getInventory().clearContent();

				super.clearContainer(player, container);
			}

			@Override
			public void slotsChanged(Container inventory) {
				if (openingPlayer.containerMenu == this)
					setContents(getInventory().getItems());

				super.slotsChanged(inventory);
			}
		};

		final NonNullList<ItemStack> contents = getContents();

		for (int i = 0; i < 6 && i < contents.size(); i++) {
			container.getInventory().setItem(i, contents.get(i));
		}

		container.getOutputSlot().set(contents.get(6));

		return container;
	}
}
