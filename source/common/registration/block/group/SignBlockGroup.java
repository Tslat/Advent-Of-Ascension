package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

public final class SignBlockGroup {
	public final RegistryObject<StandingSignBlock> freestanding;
	public final RegistryObject<WallSignBlock> wall;
	public final RegistryObject<CeilingHangingSignBlock> hanging;
	public final RegistryObject<WallHangingSignBlock> wallHanging;

	public SignBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<Block>> baseBlockRegistrar, WoodType woodType) {
		this.freestanding = (RegistryObject)registry.register(baseId + "_sign", baseBlockRegistrar.andThen(registrar -> registrar.itemFactory((block, properties) -> new SignItem(properties.stacksTo(16), block, wall()))));
		this.wall = registry.register(baseId + "_wall_sign", registrar -> registrar.baseSign(this.freestanding, false).noItem().useDropsFrom(this.freestanding).factory(properties -> new WallSignBlock(properties, woodType)));
		this.hanging = registry.register(baseId + "_hanging_sign", registrar -> registrar.baseSign(this.freestanding, true).factory(properties -> new CeilingHangingSignBlock(properties, woodType)).itemFactory((block, properties) -> new HangingSignItem(block, wallHanging(), properties)));
		this.wallHanging = registry.register(baseId + "_wall_hanging_sign", registrar -> registrar.baseSign(this.freestanding, true).noItem().useDropsFrom(this.hanging).factory(properties -> new WallHangingSignBlock(properties, woodType)));
	}

	public StandingSignBlock standing() {
		return this.freestanding.get();
	}

	public WallSignBlock wall() {
		return this.wall.get();
	}

	public CeilingHangingSignBlock hanging() {
		return this.hanging.get();
	}

	public WallHangingSignBlock wallHanging() {
		return this.wallHanging.get();
	}
}
