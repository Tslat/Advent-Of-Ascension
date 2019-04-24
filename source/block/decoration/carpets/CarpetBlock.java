package net.tslat.aoa3.block.decoration.carpets;

import net.minecraft.block.BlockCarpet;
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

public class CarpetBlock extends BlockCarpet {
	public CarpetBlock(String name, String registryName) {
		super();
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0.3f);
		setResistance(0.0f);
		setSoundType(SoundType.CLOTH);
		setDefaultState(blockState.getBaseState());
		setTickRandomly(true);
		setCreativeTab(CreativeTabsRegister.decorationBlocksTab);
	}

	@SideOnly(Side.CLIENT)
	public StateMap getStateMapper() {
		return (new StateMap.Builder().ignore(BlockCarpet.COLOR).build());
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
		return new BlockStateContainer(this, COLOR);
	}
}
