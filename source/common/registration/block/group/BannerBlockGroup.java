package net.tslat.aoa3.common.registration.block.group;

import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;
import net.tslat.aoa3.content.block.decoration.banner.BannerBlock;

import java.util.function.Consumer;

public final class BannerBlockGroup {
	public final RegistryObject<BannerBlock> base;
	public final RegistryObject<BannerBlock> gilded;
	public final RegistryObject<BannerBlock> encrusted;
	public final RegistryObject<BannerBlock> bejewelled;

	public BannerBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<BannerBlock>> baseBlockRegistrar) {
		this.base = registry.register(baseId, baseBlockRegistrar);
		this.gilded = registry.register("gilded_" + baseId, baseBlockRegistrar);
		this.encrusted = registry.register("encrusted_" + baseId, baseBlockRegistrar);
		this.bejewelled = registry.register("bejewelled_" + baseId, baseBlockRegistrar);
	}

	public BannerBlock base() {
		return this.base.get();
	}

	public BannerBlock gilded() {
		return this.gilded.get();
	}

	public BannerBlock encrusted() {
		return this.encrusted.get();
	}

	public BannerBlock bejewelled() {
		return this.bejewelled.get();
	}
}
