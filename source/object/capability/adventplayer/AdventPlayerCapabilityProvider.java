package net.tslat.aoa3.object.capability.adventplayer;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventPlayerCapabilityProvider implements ICapabilitySerializable<CompoundNBT> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "advent_player");
	@CapabilityInject(AdventPlayerCapabilityHandles.class)
	public static final Capability<AdventPlayerCapabilityHandles> INSTANCE = null;

	private final LazyOptional<AdventPlayerCapability> implContainer;

	public AdventPlayerCapabilityProvider (ServerPlayerEntity player) {
		this.implContainer = LazyOptional.of(() -> new AdventPlayerCapability(player));
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? implContainer.cast() : LazyOptional.empty();
	}

	@Override
	public CompoundNBT serializeNBT() {
		return (CompoundNBT)INSTANCE.writeNBT(implContainer.orElseThrow(() -> new IllegalStateException("Unable to find player capability! This probably shouldn't happen.")), null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		INSTANCE.readNBT(implContainer.orElseThrow(() -> new IllegalStateException("Unable to find player capability! This probably shouldn't happen.")), null, nbt);
	}

	public static void register() {
		CapabilityManager.INSTANCE.register(AdventPlayerCapabilityHandles.class, new AdventPlayerStorage(() -> INSTANCE), AdventPlayerCapability::new);
	}
}
