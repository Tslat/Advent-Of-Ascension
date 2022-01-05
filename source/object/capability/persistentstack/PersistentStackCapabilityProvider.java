package net.tslat.aoa3.object.capability.persistentstack;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.FloatNBT;
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

public class PersistentStackCapabilityProvider implements ICapabilitySerializable<FloatNBT> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "persistent_stack");
	@CapabilityInject(PersistentStackCapabilityHandles.class)
	public static final Capability<PersistentStackCapabilityHandles> INSTANCE = null;

	private final LazyOptional<PersistentStackCapability> implContainer = LazyOptional.of(PersistentStackCapability::new);
	private final Direction dir;

	public PersistentStackCapabilityProvider(@Nullable Direction dir) {
		this.dir = dir;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? implContainer.cast() : LazyOptional.empty();
	}

	@Override
	public FloatNBT serializeNBT() {
		return (FloatNBT)INSTANCE.writeNBT(implContainer.orElse(new PersistentStackCapability()), dir);
	}

	@Override
	public void deserializeNBT(FloatNBT nbt) {
		INSTANCE.readNBT(implContainer.orElse(new PersistentStackCapability()), dir, nbt);
	}

	public static PersistentStackCapabilityHandles getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(INSTANCE, side).orElse(new PersistentStackCapability());
	}

	public static void register() {
		CapabilityManager.INSTANCE.register(PersistentStackCapabilityHandles.class, new PersistentStackStorage(() -> INSTANCE), PersistentStackCapability::new);
	}
}
