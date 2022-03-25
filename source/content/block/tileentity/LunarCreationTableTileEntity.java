package net.tslat.aoa3.content.block.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.common.registration.AoATileEntities;

import javax.annotation.Nullable;
import java.util.List;

public class LunarCreationTableTileEntity extends TileEntity {
	private final NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

	public LunarCreationTableTileEntity() {
		super(AoATileEntities.LUNAR_CREATION_TABLE.get());
	}

	public NonNullList<ItemStack> getContents() {
		return this.items;
	}

	public void dropContents(World world, BlockPos pos) {
		for (ItemStack stack : getContents()) {
			world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, stack));
		}

		this.items.clear();
		setChanged();
	}

	public void setContents(int index, ItemStack item) {
		this.items.set(index, item);

		setChanged();

		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
	}

	public void setContents(List<ItemStack> contents) {
		this.items.clear();

		for (int i = 0; i < 9 && contents.size() > i; i++) {
			this.items.set(i, contents.get(i));
		}

		setChanged();

		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
	}

	private CompoundNBT serializeContents() {
		CompoundNBT tag = new CompoundNBT();

		for (int i = 0; i < 9; i++) {
			if (!items.get(i).isEmpty())
				tag.put(String.valueOf(i), items.get(i).serializeNBT());
		}

		return tag;
	}

	private void deserializeContents(CompoundNBT contentsTag) {
		items.clear();

		for (int i = 0; i < 9; i++) {
			if (contentsTag.contains(String.valueOf(i)))
				items.set(i, ItemStack.of(contentsTag.getCompound(String.valueOf(i))));
		}
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT tag = super.getUpdateTag();

		tag.put("contents", serializeContents());

		return tag;
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);

		compound.put("contents", serializeContents());

		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);

		deserializeContents(compound.getCompound("contents"));
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(getBlockPos(), 0, serializeContents());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		deserializeContents(pkt.getTag());
	}
}
