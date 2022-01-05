package net.tslat.aoa3.object.capability.volatilestack;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.object.capability.EmptyCapabilityStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VolatileStackCapabilityProvider implements ICapabilityProvider {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "volatile_stack");
	@CapabilityInject(VolatileStackCapabilityHandles.class)
	public static final Capability<VolatileStackCapabilityHandles> INSTANCE = null;

	private final LazyOptional<VolatileStackCapability> implContainer = LazyOptional.of(VolatileStackCapability::new);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return INSTANCE == cap ? implContainer.cast() : LazyOptional.empty();
	}

	public static VolatileStackCapabilityHandles getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(INSTANCE, side).orElse(new VolatileStackCapability());
	}

	public static void register() {
		CapabilityManager.INSTANCE.register(VolatileStackCapabilityHandles.class, new EmptyCapabilityStorage<VolatileStackCapabilityHandles>(), VolatileStackCapability::new);
	}
}
