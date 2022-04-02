package net.tslat.aoa3.content.capability.volatilestack;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VolatileStackCapabilityProvider implements ICapabilityProvider {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "volatile_stack");
	public static final Capability<VolatileStackCapabilityHandles> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

	private final LazyOptional<VolatileStackCapability> implContainer = LazyOptional.of(VolatileStackCapability::new);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return CAPABILITY == cap ? implContainer.cast() : LazyOptional.empty();
	}

	public static VolatileStackCapabilityHandles getOrDefault(ItemStack stack, Direction side) {
		return stack.getCapability(CAPABILITY, side).orElse(new VolatileStackCapability());
	}
}
