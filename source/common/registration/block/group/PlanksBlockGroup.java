package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

public final class PlanksBlockGroup {
	public final RegistryObject<Block> planks;
	public final RegistryObject<SlabBlock> slab;
	public final RegistryObject<StairBlock> stairs;
	public final RegistryObject<FenceBlock> fence;
	public final RegistryObject<FenceGateBlock> fenceGate;
	public final RegistryObject<PressurePlateBlock> pressurePlate;
	public final RegistryObject<ButtonBlock> button;

	public PlanksBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<Block>> baseBlockRegistrar, WoodType woodType, BlockSetType blockSetType) {
		this.planks = registry.register(baseId + "_planks", baseBlockRegistrar);
		this.slab = registry.register(baseId + "_slab", registrar -> registrar.baseSlab(this.planks).factory(properties -> new SlabBlock(properties) {
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 5;
			}

			@Override
			public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 20;
			}
		}));
		this.stairs = registry.register(baseId + "_stairs", registrar -> registrar.baseStairs(this.planks).factory(properties -> new StairBlock(this.planks.get().defaultBlockState(), properties) {
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 5;
			}

			@Override
			public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 20;
			}
		}));
		this.fence = registry.register(baseId + "_fence", registrar -> registrar.baseFence(this.planks).factory(properties -> new FenceBlock(properties) {
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 5;
			}

			@Override
			public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 20;
			}
		}));
		this.fenceGate = registry.register(baseId + "_fence_gate", registrar -> registrar.basedOn(this.planks).alwaysSolid().factory(properties -> new FenceGateBlock(properties, woodType) {
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 5;
			}

			@Override
			public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 20;
			}
		}));
		this.pressurePlate = registry.register(baseId + "_pressure_plate", registrar -> registrar.basePressurePlate(this.planks).factory(properties -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, properties, blockSetType)));
		this.button = registry.register(baseId + "_button", registrar -> registrar.baseButton(this.planks).factory(properties -> new ButtonBlock(properties, blockSetType, 30, true)));
	}

	public Block planks() {
		return this.planks.get();
	}

	public SlabBlock slab() {
		return this.slab.get();
	}

	public StairBlock stairs() {
		return this.stairs.get();
	}

	public FenceBlock fence() {
		return this.fence.get();
	}

	public FenceGateBlock fenceGate() {
		return this.fenceGate.get();
	}

	public PressurePlateBlock pressurePlate() {
		return this.pressurePlate.get();
	}

	public ButtonBlock button() {
		return this.button.get();
	}
}
