package net.tslat.aoa3.block.functional.crops;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.misc.SeedsItem;

public class CropBlock extends BlockCrops {
	private static final AxisAlignedBB[] GROWTH_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	private SeedsItem seeds;
	private Item crop;

	public CropBlock(String name, String registryName) {
		super();
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0);
		setTickRandomly(true);
		disableStats();
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(AGE, 0));
	}

	@Override
	protected int getBonemealAgeIncrease(World worldIn) {
		return AdventOfAscension.rand.nextInt(3) + 1;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return GROWTH_AABB[state.getValue(AGE)];
	}

	public void setSeeds(SeedsItem seeds) {
		this.seeds = seeds;
	}

	public void setCrop(Item crop) {
		if (this.crop == null)
			this.crop = crop;
	}

	@Override
	public Item getCrop() {
		return crop;
	}

	@Override
	protected Item getSeed() {
		return seeds;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		super.getDrops(drops, world, pos, state, 0);

		/*for (ItemStack st : drops) {
			if (st.getItem() == seeds) {
				drops.remove(seeds);
				return;
			}
		}*/
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AGE);
	}
}
