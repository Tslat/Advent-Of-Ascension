package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

@FunctionalInterface
public interface BlockRegistrarFactory {
	<T extends Block> DeferredHolder<Block, T> register(String baseId, Consumer<BlockRegistrar<T>> registrar);
}
