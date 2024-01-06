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
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.AoAStartupCache;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
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

		private final MutableSupplier<ForgeFlowingFluid.Source> sourceFluid = new MutableSupplier<ForgeFlowingFluid.Source>(null);
		private final MutableSupplier<ForgeFlowingFluid.Flowing> flowingFluid = new MutableSupplier<ForgeFlowingFluid.Flowing>(null);

		private BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, Block.Properties, Supplier<LiquidBlock>> blockCreationFunction = (flowingFluid, blockProperties) -> () -> new LiquidBlock(flowingFluid, blockProperties);
		private BiFunction<MutableSupplier<ForgeFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction = (sourceFluid, itemProperties) -> () -> new BucketItem(sourceFluid, itemProperties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> sourceFluidFunction = properties -> () ->  new ForgeFlowingFluid.Source(properties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> flowingFluidFunction = properties -> () ->  new ForgeFlowingFluid.Flowing(properties);

		private ForgeFlowingFluid.Properties fluidProperties;

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

		public Builder customBlock(BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, Block.Properties, Supplier<LiquidBlock>> blockCreationFunction) {
			this.blockCreationFunction = blockCreationFunction;

			return this;
		}

		public Builder customBucket(BiFunction<MutableSupplier<ForgeFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction) {
			this.bucketCreationFunction = bucketCreationFunction;

			return this;
		}

		public Builder customSourceFluid(Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> sourceFluidCreationFunction) {
			this.sourceFluidFunction = sourceFluidCreationFunction;

			return this;
		}

		public Builder customFlowingFluid(Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> flowingFluidCreationFunction) {
			this.flowingFluidFunction = flowingFluidCreationFunction;

			return this;
		}

		public RegisteredFluidHolder defaultRegisterAll() {
			return registerAll(AoARegistries.ITEMS.deferredRegister(), AoARegistries.BLOCKS.deferredRegister(), AoARegistries.FLUIDS.deferredRegister());
		}

		public RegisteredFluidHolder registerAll(DeferredRegister<Item> itemRegistry, DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry) {
			final RegistryObject<BucketItem> bucket = registerBucket(itemRegistry);
			final RegistryObject<ForgeFlowingFluid.Source> fluid = registerFluid(fluidRegistry);
			final RegistryObject<LiquidBlock> fluidBlock = registerBlock(blockRegistry);

			return new RegisteredFluidHolder(bucket, fluidBlock, fluid);
		}

		@Nullable
		public RegistryObject<LiquidBlock> registerBlock(DeferredRegister<Block> blockRegistry) {
			if (this.blockCreationFunction == null)
				return null;

			makeFluidProperties();

			RegistryObject<LiquidBlock> block = blockRegistry.register(id, blockCreationFunction.apply(flowingFluid, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).lightLevel(lightFunction)));

			this.fluidProperties.block(block);
			AoABlocks.registeredLiquid(block);

			return block;
		}

		@Nullable
		public RegistryObject<BucketItem> registerBucket(DeferredRegister<Item> itemRegistry) {
			if (this.bucketCreationFunction == null)
				return null;

			makeFluidProperties();

			RegistryObject<BucketItem> bucket = itemRegistry.register(id + "_bucket", this.bucketCreationFunction.apply(sourceFluid, new Item.Properties().stacksTo(16).craftRemainder(Items.BUCKET)));

			AoAStartupCache.setItemCreativeTabs(bucket, List.of(AoACreativeModeTabs.MISC_ITEMS.getKey()));
			this.fluidProperties.bucket(bucket);

			return bucket;
		}

		public RegistryObject<ForgeFlowingFluid.Source> registerFluid(DeferredRegister<Fluid> fluidRegistry) {
			makeFluidProperties();

			RegistryObject<ForgeFlowingFluid.Source> fluid;
			fluid = fluidRegistry.register(this.id, this.sourceFluidFunction.apply(this.fluidProperties));

			sourceFluid.update(fluid);
			flowingFluid.update(fluidRegistry.register(id + "_flowing", this.flowingFluidFunction.apply(this.fluidProperties)));

			return fluid;
		}

		private void makeFluidProperties() {
			if (this.fluidProperties == null)
				this.fluidProperties = new ForgeFlowingFluid.Properties(fluidType, sourceFluid, flowingFluid)
						.tickRate(tickRate);
		}
	}

	public record RegisteredFluidHolder(RegistryObject<BucketItem> bucket, RegistryObject<LiquidBlock> fluidBlock, RegistryObject<ForgeFlowingFluid.Source> fluid) {
		public BucketItem getBucket() {
			return this.bucket.get();
		}

		public LiquidBlock getBlock() {
			return this.fluidBlock.get();
		}

		public ForgeFlowingFluid.Source getFluid() {
			return this.fluid.get();
		}
	}
}
