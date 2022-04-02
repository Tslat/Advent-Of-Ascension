package net.tslat.aoa3.content.capability.persistentstack;

import net.minecraft.core.Direction;
import net.minecraft.nbt.FloatTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PersistentStackCapabilityProvider implements ICapabilitySerializable<FloatTag> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "persistent_stack");
	public static final Capability<PersistentStackCapabilityHandles> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	private final LazyOptional<PersistentStackCapability> implContainer = LazyOptional.of(PersistentStackCapability::new);
	private final Direction dir;

	public PersistentStackCapabilityProvider(@Nullable Direction dir) {
		this.dir = dir;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return CAPABILITY == cap && dir == side ? implContainer.cast() : LazyOptional.empty();
	}

	@Override
	public FloatTag serializeNBT() {
		if (!implContainer.isPresent())
			return FloatTag.ZERO;

		return implContainer.resolve().get().serializeNBT();
	}

	@Override
	public void deserializeNBT(FloatTag nbt) {
		implContainer.ifPresent(cap -> cap.deserializeNBT(nbt));
	}

	public static PersistentStackCapabilityHandles getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(CAPABILITY, side).orElse(new PersistentStackCapability());
	}
}
