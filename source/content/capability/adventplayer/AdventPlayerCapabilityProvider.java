package net.tslat.aoa3.content.capability.adventplayer;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class AdventPlayerCapabilityProvider implements ICapabilitySerializable<CompoundTag> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "advent_player");
	public static final Capability<AdventPlayerCapabilityHandles> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	private final LazyOptional<AdventPlayerCapability> implContainer;

	public AdventPlayerCapabilityProvider(ServerPlayer player) {
		this.implContainer = LazyOptional.of(() -> new AdventPlayerCapability(player));
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return CAPABILITY == cap ? implContainer.cast() : LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		return implContainer.orElseThrow(() -> new IllegalStateException("Unable to find player capability! This probably shouldn't happen.")).serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		implContainer.orElseThrow(() -> new IllegalStateException("Unable to find player capability! This probably shouldn't happen.")).deserializeNBT(nbt);
	}
}
