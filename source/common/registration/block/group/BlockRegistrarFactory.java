package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

@FunctionalInterface
public interface BlockRegistrarFactory {
	<T extends Block> RegistryObject<T> register(String baseId, Consumer<BlockRegistrar<T>> registrar);
}
