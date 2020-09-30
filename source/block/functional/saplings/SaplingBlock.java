package net.tslat.aoa3.block.functional.saplings;

import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public abstract class SaplingBlock extends BlockSapling {
	private final boolean growsInDark;
	private final float growthTimeModifier;

	private boolean completedInitialConstruct = false;

	public SaplingBlock(String name, String registryName) {
		this(name, registryName, false, 1);
	}

	public SaplingBlock(String name, String registryName, boolean growsInDark) {
		this(name, registryName, growsInDark, 1);
	}

	public SaplingBlock(String name, String registryName, boolean growsInDark, float growthTimeModifier) {
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0);
		setResistance(0);
		setSoundType(SoundType.PLANT);
		setTickRandomly(true);
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
		ObfuscationReflectionHelper.setPrivateValue(Block.class, this, new BlockStateContainer(this), "field_176227_L");
		setDefaultState(getBlockState().getBaseState());

		this.growsInDark = growsInDark;
		this.growthTimeModifier = growthTimeModifier;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			checkAndDropBlock(world, pos, state);

			if (!world.isAreaLoaded(pos, 1))
				return;

			if (rand.nextInt((int)(Math.pow(7 * growthTimeModifier, 2))) == 0 && (growsInDark || world.getLightFromNeighbors(pos.up()) >= 9))
				grow(world, rand, pos, state);
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return world.rand.nextFloat() < Math.pow(Math.max(0.0001, 0.45 / growthTimeModifier), 2);
	}

	@Override
	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		grow(worldIn, rand, pos, state);
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		generateTree(worldIn, pos, state, rand);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return super.canSustainBush(state) || state.getBlock() instanceof BlockGrass || state.getBlock() instanceof BlockDirt;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (!completedInitialConstruct) {
			completedInitialConstruct = true;
			return new BlockStateContainer(this, new IProperty[] {TYPE, STAGE});
		}
		else {
			return new BlockStateContainer(this);
		}
	}

	@Override
	public abstract void generateTree(World world, BlockPos pos, IBlockState state, Random rand);
}
