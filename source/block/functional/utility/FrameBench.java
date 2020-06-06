package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.library.Enums;

public class FrameBench extends Block {
	public FrameBench() {
		super(Material.WOOD);
		setTranslationKey("FrameBench");
		setRegistryName("aoa3:frame_bench");
		setHardness(2.5f);
		setSoundType(SoundType.WOOD);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(AdventOfAscension.instance(), Enums.ModGuis.FRAME_BENCH.guiId, world, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}
}
