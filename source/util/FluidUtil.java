package net.tslat.aoa3.util;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.AoAStartupCache;
import net.tslat.aoa3.common.registration.item.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.library.object.MutableSupplier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public final class FluidUtil {
	public static class Builder {
		private final String id;
		private final Supplier<FluidType> fluidType;

		private final MutableSupplier<BaseFlowingFluid.Source> sourceFluid = new MutableSupplier<BaseFlowingFluid.Source>(null);
		private final MutableSupplier<BaseFlowingFluid.Flowing> flowingFluid = new MutableSupplier<BaseFlowingFluid.Flowing>(null);

		private BiFunction<MutableSupplier<BaseFlowingFluid.Flowing>, Block.Properties, Supplier<LiquidBlock>> blockCreationFunction = (flowingFluid, blockProperties) -> () -> new LiquidBlock(flowingFluid, blockProperties);
		private BiFunction<MutableSupplier<BaseFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction = (sourceFluid, itemProperties) -> () -> new BucketItem(sourceFluid, itemProperties);
		private Function<BaseFlowingFluid.Properties, Supplier<BaseFlowingFluid.Source>> sourceFluidFunction = properties -> () ->  new BaseFlowingFluid.Source(properties);
		private Function<BaseFlowingFluid.Properties, Supplier<BaseFlowingFluid.Flowing>> flowingFluidFunction = properties -> () ->  new BaseFlowingFluid.Flowing(properties);

		private BaseFlowingFluid.Properties fluidProperties;

		private int tickRate = 5;
		private ToIntFunction<BlockState> lightFunction = state -> 0;

		public Builder(String id, Supplier<FluidType> fluidType) {
			this.id	= id;
			this.localeKey = "block." + AdventOfAscension.MOD_ID + "." + id;
			this.fluidType = fluidType;
		}

		private String localeKey;

		public Builder localeKey(String key) {
			this.localeKey = key;

			return this;
		}

		public Builder luminosity(int lightLevel) {
			return luminosity(state -> lightLevel);
		}

		public Builder luminosity(ToIntFunction<BlockState> lightFunction) {
			this.lightFunction = lightFunction;

			return this;
		}

		public Builder tickRate(int tickRate) {
			this.tickRate = tickRate;

			return this;
		}

		public Builder noBucket() {
			this.bucketCreationFunction = null;

			return this;
		}

		public Builder noBlock() {
			this.blockCreationFunction = null;

			return this;
		}

		public Builder customBlock(BiFunction<MutableSupplier<BaseFlowingFluid.Flowing>, Block.Properties, Supplier<LiquidBlock>> blockCreationFunction) {
			this.blockCreationFunction = blockCreationFunction;

			return this;
		}

		public Builder customBucket(BiFunction<MutableSupplier<BaseFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction) {
			this.bucketCreationFunction = bucketCreationFunction;

			return this;
		}

		public Builder customSourceFluid(Function<BaseFlowingFluid.Properties, Supplier<BaseFlowingFluid.Source>> sourceFluidCreationFunction) {
			this.sourceFluidFunction = sourceFluidCreationFunction;

			return this;
		}

		public Builder customFlowingFluid(Function<BaseFlowingFluid.Properties, Supplier<BaseFlowingFluid.Flowing>> flowingFluidCreationFunction) {
			this.flowingFluidFunction = flowingFluidCreationFunction;

			return this;
		}

		public RegisteredFluidHolder defaultRegisterAll() {
			return registerAll(AoARegistries.ITEMS.deferredRegister(), AoARegistries.BLOCKS.deferredRegister(), AoARegistries.FLUIDS.deferredRegister());
		}

		public RegisteredFluidHolder registerAll(DeferredRegister<Item> itemRegistry, DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry) {
			final DeferredHolder<Item, BucketItem> bucket = registerBucket(itemRegistry);
			final DeferredHolder<Fluid, BaseFlowingFluid.Source> fluid = registerFluid(fluidRegistry);
			final DeferredHolder<Block, LiquidBlock> fluidBlock = registerBlock(blockRegistry);

			return new RegisteredFluidHolder(bucket, fluidBlock, fluid);
		}

		@Nullable
		public DeferredHolder<Block, LiquidBlock> registerBlock(DeferredRegister<Block> blockRegistry) {
			if (this.blockCreationFunction == null)
				return null;

			makeFluidProperties();

			DeferredHolder<Block, LiquidBlock> block = blockRegistry.register(id, blockCreationFunction.apply(flowingFluid, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).lightLevel(lightFunction)));

			this.fluidProperties.block(block);
			AoABlocks.registeredLiquid(block);

			return block;
		}

		@Nullable
		public DeferredHolder<Item, BucketItem> registerBucket(DeferredRegister<Item> itemRegistry) {
			if (this.bucketCreationFunction == null)
				return null;

			makeFluidProperties();

			DeferredHolder<Item, BucketItem> bucket = itemRegistry.register(id + "_bucket", this.bucketCreationFunction.apply(sourceFluid, new Item.Properties().craftRemainder(Items.BUCKET)));

			AoAStartupCache.setItemCreativeTabs(bucket, List.of(AoACreativeModeTabs.MISC_ITEMS.getKey()));
			this.fluidProperties.bucket(bucket);

			return bucket;
		}

		public DeferredHolder<Fluid, BaseFlowingFluid.Source> registerFluid(DeferredRegister<Fluid> fluidRegistry) {
			makeFluidProperties();

			DeferredHolder<Fluid, BaseFlowingFluid.Source> fluid;
			fluid = fluidRegistry.register(this.id, this.sourceFluidFunction.apply(this.fluidProperties));

			sourceFluid.update(fluid);
			flowingFluid.update(fluidRegistry.register(id + "_flowing", this.flowingFluidFunction.apply(this.fluidProperties)));

			return fluid;
		}

		private void makeFluidProperties() {
			if (this.fluidProperties == null)
				this.fluidProperties = new BaseFlowingFluid.Properties(fluidType, sourceFluid, flowingFluid)
						.tickRate(tickRate);
		}
	}

	public record RegisteredFluidHolder(DeferredHolder<Item, BucketItem> bucket, DeferredHolder<Block, LiquidBlock> fluidBlock, DeferredHolder<Fluid, BaseFlowingFluid.Source> fluid) {
		public BucketItem getBucket() {
			return this.bucket.get();
		}

		public LiquidBlock getBlock() {
			return this.fluidBlock.get();
		}

		public BaseFlowingFluid.Source getFluid() {
			return this.fluid.get();
		}
	}
}
