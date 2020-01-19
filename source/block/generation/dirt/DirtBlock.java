package net.tslat.aoa3.block.generation.dirt;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.block.CustomStateMapperBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class DirtBlock extends BlockDirt implements CustomStateMapperBlock {
	public DirtBlock(String name, String registryName) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0.5f);
		setSoundType(SoundType.GROUND);
		setDefaultState(blockState.getBaseState());
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
		setHarvestLevel("shovel", 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public StateMap getStateMapper() {
		return (new StateMap.Builder().ignore(BlockDirt.VARIANT, BlockDirt.SNOWY).build());
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
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
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT, SNOWY);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
}
