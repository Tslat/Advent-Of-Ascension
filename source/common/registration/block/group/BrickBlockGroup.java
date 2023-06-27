package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

public final class BrickBlockGroup {
	public final RegistryObject<Block> stone;
	public final RegistryObject<SlabBlock> slab;
	public final RegistryObject<StairBlock> stairs;
	public final RegistryObject<WallBlock> wall;

	public BrickBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<Block>> baseBlockRegistrar) {
		this.stone = registry.register(baseId + "s", baseBlockRegistrar);
		this.slab = registry.register(baseId + "_slab", registrar -> registrar.baseSlab(this.stone));
		this.stairs = registry.register(baseId + "_stairs", registrar -> registrar.baseStairs(this.stone));
		this.wall = registry.register(baseId + "_wall", registrar -> registrar.baseWall(this.stone));
	}

	public Block stone() {
		return this.stone.get();
	}

	public SlabBlock slab() {
		return this.slab.get();
	}

	public StairBlock stairs() {
		return this.stairs.get();
	}

	public WallBlock wall() {
		return this.wall.get();
	}
}
