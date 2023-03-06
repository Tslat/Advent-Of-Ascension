package net.tslat.aoa3.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.library.object.MutableSupplier;

import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class FluidUtil {
	public static class Builder {
		private final String id;

		private final MutableSupplier<ForgeFlowingFluid.Source> sourceFluid = new MutableSupplier<ForgeFlowingFluid.Source>(null);
		private final MutableSupplier<ForgeFlowingFluid.Flowing> flowingFluid = new MutableSupplier<ForgeFlowingFluid.Flowing>(null);

		private BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockCreationFunction = (flowingFluid, blockProperties) -> () -> new FlowingFluidBlock(flowingFluid, blockProperties);
		private BiFunction<MutableSupplier<ForgeFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction = (sourceFluid, itemProperties) -> () -> new BucketItem(sourceFluid, itemProperties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> sourceFluidFunction = properties -> () ->  new ForgeFlowingFluid.Source(properties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> flowingFluidFunction = properties -> () ->  new ForgeFlowingFluid.Flowing(properties);

		private Material material = Material.WATER;
		private boolean isGas = false;
		private int colour = 0xFFFFFFFF;

		private int luminosity = 0;
		private int density = 1000;
		private int temperature = 300;
		private int viscosity = 1000;

		private Rarity rarity = Rarity.COMMON;

		private ResourceLocation stillTexture = new ResourceLocation("block/water_still");
		private ResourceLocation flowingTexture = new ResourceLocation("block/water_flow");
		private ResourceLocation overlayTexture = new ResourceLocation("block/water_overlay");

		private String localeKey;

		private SoundEvent fillSound = SoundEvents.BUCKET_FILL;
		private SoundEvent emptySound = SoundEvents.BUCKET_EMPTY;

		private ForgeFlowingFluid.Properties fluidProperties = null;

		public Builder(String id) {
			this.id	= id;
			this.localeKey = "block." + AdventOfAscension.MOD_ID + "." + id;
		}

		public Builder material(Material material) {
			this.material = material;

			return this;
		}

		public Builder isGas() {
			this.isGas = true;

			return this;
		}

		public Builder colour(int colour) {
			this.colour = colour;

			return this;
		}

		public Builder colour(int red, int green, int blue, int alpha) {
			return colour(ColourUtil.RGBA(red, green, blue, alpha));
		}

		public Builder luminosity(int luminosity) {
			this.luminosity = luminosity;

			return this;
		}

		public Builder density(int density) {
			this.density = density;

			return this;
		}

		public Builder temperature(int temperature) {
			this.temperature = temperature;

			return this;
		}

		public Builder viscosity(int viscosity) {
			this.viscosity = viscosity;

			return this;
		}

		public Builder rarity(Rarity rarity) {
			this.rarity = rarity;

			return this;
		}

		public Builder stillTexture(ResourceLocation stillTexture) {
			this.stillTexture = stillTexture;

			return this;
		}

		public Builder flowingTexture(ResourceLocation flowingTexture) {
			this.flowingTexture = flowingTexture;

			return this;
		}

		public Builder submergedOverlay(ResourceLocation overlayTexture) {
			this.overlayTexture = overlayTexture;

			return this;
		}

		public Builder localeKey(String key) {
			this.localeKey = key;

			return this;
		}

		public Builder bucketFillSound(SoundEvent sound) {
			this.fillSound = sound;

			return this;
		}

		public Builder bucketEmptySound(SoundEvent sound) {
			this.emptySound = sound;

			return this;
		}

		public Builder isMolten() {
			material(Material.LAVA);
			luminosity(15);
			density(3000);
			viscosity(6000);
			temperature(1300);
			bucketFillSound(SoundEvents.BUCKET_FILL_LAVA);
			bucketEmptySound(SoundEvents.BUCKET_EMPTY_LAVA);
			stillTexture(new ResourceLocation("block/lava_still"));
			flowingTexture(new ResourceLocation("block/lava_flow"));

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

		public Builder customBlock(BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, AbstractBlock.Properties, Supplier<FlowingFluidBlock>> blockCreationFunction) {
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

		public RegistryObject<FlowingFluidBlock> defaultRegisterAll() {
			return registerAll(AoAItems.ITEMS, AoABlocks.BLOCKS, AoABlocks.FLUIDS).getFluidBlock();
		}

		public RegisteredFluidHolder registerAll(DeferredRegister<Item> itemRegistry, DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry) {
			final RegistryObject<BucketItem> bucket = registerBucket(itemRegistry);
			final RegistryObject<FlowingFluidBlock> fluidBlock = registerBlock(blockRegistry);
			final RegistryObject<ForgeFlowingFluid.Source> fluid = registerFluid(fluidRegistry);

			return new RegisteredFluidHolder(bucket, fluidBlock, fluid);
		}

		@Nullable
		public RegistryObject<FlowingFluidBlock> registerBlock(DeferredRegister<Block> blockRegistry) {
			if (this.blockCreationFunction == null)
				return null;

			makeFluidProperties();

			RegistryObject<FlowingFluidBlock> block = blockRegistry.register(id, blockCreationFunction.apply(flowingFluid, AbstractBlock.Properties.of(this.material).noCollission().strength(100).noDrops().lightLevel(state -> luminosity)));

			this.fluidProperties.block(block);

			if (this.material == Material.WATER)
				AoABlocks.customRender(block, AoABlocks.CustomRenderType.TRANSLUCENT);

			return block;
		}

		@Nullable
		public RegistryObject<BucketItem> registerBucket(DeferredRegister<Item> itemRegistry) {
			if (this.bucketCreationFunction == null)
				return null;

			makeFluidProperties();

			RegistryObject<BucketItem> bucket = itemRegistry.register(id + "_bucket", this.bucketCreationFunction.apply(sourceFluid, new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1).craftRemainder(Items.BUCKET)));

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
			if (this.fluidProperties == null) {
				FluidAttributes.Builder attributes = FluidAttributes.builder(stillTexture, flowingTexture)
						.overlay(overlayTexture)
						.translationKey(localeKey)
						.color(colour)
						.rarity(rarity)
						.temperature(temperature)
						.viscosity(viscosity)
						.density(density)
						.luminosity(luminosity)
						.sound(fillSound, emptySound);

				if (isGas)
					attributes.gaseous();

				this.fluidProperties = new ForgeFlowingFluid.Properties(sourceFluid, flowingFluid, attributes);
			}
		}
	}

	public static final class RegisteredFluidHolder {
		private final RegistryObject<BucketItem> bucket;
		private final RegistryObject<FlowingFluidBlock> fluidBlock;
		private final RegistryObject<ForgeFlowingFluid.Source> fluid;

		public RegisteredFluidHolder(RegistryObject<BucketItem> bucket, RegistryObject<FlowingFluidBlock> fluidBlock, RegistryObject<ForgeFlowingFluid.Source> fluid) {
			this.bucket = bucket;
			this.fluidBlock = fluidBlock;
			this.fluid = fluid;
		}

		public RegistryObject<BucketItem> getBucket() {
			return bucket;
		}

		public RegistryObject<ForgeFlowingFluid.Source> getFluid() {
			return this.fluid;
		}

		public RegistryObject<FlowingFluidBlock> getFluidBlock() {
			return this.fluidBlock;
		}
	}
}
