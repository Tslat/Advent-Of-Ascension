package net.tslat.aoa3.block.generation.special;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class DustopianLampOff extends Block {
	public DustopianLampOff() {
		super(Material.ROCK);
		setUnlocalizedName("DustopianLampOff");
		setRegistryName("aoa3:dustopian_lamp_off");
		setHardness(5.0f);
		setResistance(10.0f);
		setLightLevel(0.0f);
		setCreativeTab(null);
	}


	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.isSneaking() && player.getHeldItem(hand).getItem() == ItemRegister.primordialDust) {
			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			world.setBlockState(pos, BlockRegister.dustopianLamp.getDefaultState());
		}

		return true;
	}
}
