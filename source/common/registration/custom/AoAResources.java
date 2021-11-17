package net.tslat.aoa3.common.registration.custom;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.resource.EnergyResource;
import net.tslat.aoa3.player.resource.RageResource;
import net.tslat.aoa3.player.resource.SpiritResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AoAResources {
	private static IForgeRegistry<AoAResource> REGISTRY = null;
	public static final DeferredRegister<AoAResource> RESOURCES = DeferredRegister.create(AoAResource.class, AdventOfAscension.MOD_ID);

	public static final AoAResource.Instance DEFAULT = new AoAResource.Instance(null, null) {
		@Override
		public float getCurrentValue() {
			return 0;
		}

		@Override
		public boolean hasAmount(float amount) {
			return false;
		}

		@Override
		public void setValue(float amount) {}

		@Override
		public float getMaxValue() {
			return 0;
		}

		@Nonnull
		@Override
		public CompoundNBT saveToNbt() {
			return new CompoundNBT();
		}
	};

	public static final RegistryObject<AoAResource> SPIRIT = RESOURCES.register("spirit", () -> new AoAResource(SpiritResource::new, SpiritResource::new));
	public static final RegistryObject<AoAResource> ENERGY = RESOURCES.register("energy", () -> new AoAResource(EnergyResource::new, EnergyResource::new));
	public static final RegistryObject<AoAResource> RAGE = RESOURCES.register("rage", () -> new AoAResource(RageResource::new, RageResource::new));

	@Nullable
	public static AoAResource getResource(ResourceLocation id) {
		return getRegistry().getValue(id);
	}

	public static IForgeRegistry<AoAResource> getRegistry() {
		if (REGISTRY == null)
			REGISTRY = RegistryManager.ACTIVE.getRegistry(AoAResource.class);

		return REGISTRY;
	}
}
