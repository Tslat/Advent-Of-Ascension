package net.tslat.aoa3.common.registration.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.resource.EnergyResource;
import net.tslat.aoa3.player.resource.RageResource;
import net.tslat.aoa3.player.resource.SpiritResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AoAResources {
	public static void init() {}

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
		public CompoundTag saveToNbt() {
			return new CompoundTag();
		}
	};

	public static final RegistryObject<AoAResource> SPIRIT = AoARegistries.AOA_RESOURCES.register("spirit", () -> new AoAResource(SpiritResource::new, SpiritResource::new));
	public static final RegistryObject<AoAResource> ENERGY = AoARegistries.AOA_RESOURCES.register("energy", () -> new AoAResource(EnergyResource::new, EnergyResource::new));
	public static final RegistryObject<AoAResource> RAGE = AoARegistries.AOA_RESOURCES.register("rage", () -> new AoAResource(RageResource::new, RageResource::new));

	@Nullable
	public static AoAResource getResource(ResourceLocation id) {
		return AoARegistries.AOA_RESOURCES.getEntry(id);
	}
}
