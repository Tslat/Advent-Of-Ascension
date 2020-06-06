package net.tslat.aoa3.block.decoration.glass;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nonnull;

public class GlassBlock extends BlockGlass {
	public GlassBlock(String name, String registryName, float hardness, float resistance) {
		super(Material.GLASS, true);
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(hardness);
		setResistance(resistance);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
	}

	public GlassBlock(String name, String registryName) {
		this(name, registryName, 0.2f, 0f);
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockAccess.getBlockState(pos.offset(side)).getBlock() != this && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
}
