package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

public final class SignBlockGroup {
	public final DeferredBlock<StandingSignBlock> freestanding;
	public final DeferredBlock<WallSignBlock> wall;
	public final DeferredBlock<CeilingHangingSignBlock> hanging;
	public final DeferredBlock<WallHangingSignBlock> wallHanging;

	public SignBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<Block>> baseBlockRegistrar, WoodType woodType) {
		this.freestanding = (DeferredBlock)registry.register(baseId + "_sign", baseBlockRegistrar.andThen(registrar -> registrar.itemFactory((block, properties) -> new SignItem(properties.stacksTo(16), block, wall()))));
		this.wall = registry.register(baseId + "_wall_sign", registrar -> registrar.baseSign(this.freestanding, false).noItem().useDropsFrom(this.freestanding).factory(properties -> new WallSignBlock(woodType, properties)));
		this.hanging = registry.register(baseId + "_hanging_sign", registrar -> registrar.baseSign(this.freestanding, true).factory(properties -> new CeilingHangingSignBlock(woodType, properties)).itemFactory((block, properties) -> new HangingSignItem(block, wallHanging(), properties)));
		this.wallHanging = registry.register(baseId + "_wall_hanging_sign", registrar -> registrar.baseSign(this.freestanding, true).noItem().useDropsFrom(this.hanging).factory(properties -> new WallHangingSignBlock(woodType, properties)));
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
