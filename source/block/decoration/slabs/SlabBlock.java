package net.tslat.aoa3.block.decoration.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public abstract class SlabBlock extends BlockSlab {
	public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);
	private HalfSlabBlock halfBlock;

	public SlabBlock(String name, String registryName, Material material) {
		super(material);
		setTranslationKey(name);
		setRegistryName("aoa3:" + (isDouble() ? "double_" : "") + registryName);
		setHardness(2.0f);

		useNeighborBrightness = !isDouble();
		IBlockState state = blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

		if (!isDouble()) {
			state.withProperty(HALF, EnumBlockHalf.BOTTOM);
			halfBlock = (HalfSlabBlock)this;
		}

		if (material == Material.WOOD) {
			Blocks.FIRE.setFireInfo(this, 5, 20);
			setResistance(5.0f);
			setSoundType(SoundType.WOOD);
		}
		else if (material == Material.ROCK) {
			setResistance(10.0f);
			setSoundType(SoundType.STONE);
		}

		setDefaultState(state);
		setCreativeTab(CreativeTabsRegister.decorationBlocksTab);
	}

	@Override
	public String getTranslationKey(int meta) {
		return super.getTranslationKey();
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return Variant.DEFAULT;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(halfBlock);
	}


	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(halfBlock);
	}

	public HalfSlabBlock getHalfBlock() {
		if (halfBlock != null)
			return halfBlock;

		halfBlock = new HalfSlabBlock(this.getTranslationKey().replace("tile.", ""), this.getRegistryName().toString().replace("aoa3:double_", ""), this.getMaterial(getDefaultState()));

		return halfBlock;
	}

	@Override
	public final IBlockState getStateFromMeta(final int meta) {
		IBlockState state = blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

		if (!isDouble())
			state = state.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);

		return state;
	}

	@Override
	public final int getMetaFromState(final IBlockState state) {
		int meta = 0;

		if (!isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP)
			meta |= 8;

		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (!isDouble())
			return new BlockStateContainer(this, VARIANT, HALF);

		return new BlockStateContainer(this, VARIANT);
	}

	public static class DoubleSlabBlock extends SlabBlock {
		public DoubleSlabBlock(String name, String registryName, Material material) {
			super(name, registryName, material);
		}

		@Override
		public boolean isDouble() {
			return true;
		}

		public DoubleSlabBlock registerHalfSlab(IForgeRegistry<Block> registry) {
			HalfSlabBlock halfSlab = new SlabBlock.HalfSlabBlock(this.getTranslationKey().replace("tile.", ""), this.getRegistryName().toString().replace("aoa3:double_", ""), this.getMaterial(getDefaultState()));
			setHalfBlock(halfSlab);
			registry.register(halfSlab);

			return this;
		}
	}

	public static class HalfSlabBlock extends SlabBlock {
		public HalfSlabBlock(String name, String registryName, Material material) {
			super(name, registryName, material);
		}

		@Override
		public boolean isDouble() {
			return false;
		}
	}

	public void setHalfBlock(HalfSlabBlock block) {
		this.halfBlock = block;
	}

	public enum Variant implements IStringSerializable {
		DEFAULT;

		@Override
		public String getName() {
			return "default";
		}

	}
}
