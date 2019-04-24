package net.tslat.aoa3.block.generation.sand;

import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class SandBlock extends BlockSand {
	public SandBlock(String name, String registryName) {
		super();
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0.5f);
		setDefaultState(blockState.getBaseState());
		setSoundType(SoundType.SAND);
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
	}

	@SideOnly(Side.CLIENT)
	public StateMap getStateMapper() {
		return (new StateMap.Builder().ignore(BlockSand.VARIANT)).build();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}
}
