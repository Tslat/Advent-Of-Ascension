package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicNonCubeBlock;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class ChargingTable extends BasicNonCubeBlock {
	public ChargingTable() {
		super("ChargingTable", "charging_table", Material.ROCK);
		setHardness(-1f);
		setResistance(999999999f);
		setCreativeTab(null);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() == ItemRegister.heavyBoulder) {
			if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.primordialDust))) {
				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.boulderDash));
			}
		}

		return true;
	}
}
