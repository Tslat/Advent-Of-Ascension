package net.tslat.aoa3.common.registration.block.group;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;
import net.tslat.aoa3.content.block.decoration.banner.BannerBlock;

import java.util.function.Consumer;

public final class BannerBlockGroup {
	public final DeferredBlock<BannerBlock> base;
	public final DeferredBlock<BannerBlock> gilded;
	public final DeferredBlock<BannerBlock> encrusted;
	public final DeferredBlock<BannerBlock> bejewelled;

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
