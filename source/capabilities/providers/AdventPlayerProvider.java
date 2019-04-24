package net.tslat.aoa3.capabilities.providers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBasePlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventPlayerProvider implements ICapabilitySerializable<NBTTagCompound> {
	@CapabilityInject(CapabilityBasePlayer.class)
	public static Capability<CapabilityBasePlayer> ADVENT_PLAYER = null;
	private CapabilityBasePlayer instance = ADVENT_PLAYER.getDefaultInstance();

	public AdventPlayerProvider(EntityPlayer pl) {
		if (instance != null)
			instance.addPlayer(pl);
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == ADVENT_PLAYER;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == ADVENT_PLAYER ? ADVENT_PLAYER.<T>cast(instance) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return instance.saveNBTData();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		instance.loadNBTData(nbt);
	}
}
