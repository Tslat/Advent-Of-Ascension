package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class RockriderShrine extends BasicBlock {
	public RockriderShrine() {
		super("RockriderShrine", "rockrider_shrine", Material.ROCK, -1f, 999999999f);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem(hand).getItem() == ItemRegister.floatingStone) {
			if (!worldIn.isRemote) {
				if (PlayerUtil.consumeResource(playerIn, Enums.Resources.ENERGY, 200, false)) {
					playerIn.getHeldItem(hand).shrink(1);
					ItemUtil.givePlayerItemOrDrop(playerIn, new ItemStack(ItemRegister.heavyBoulder));
				}
				else {
					PlayerUtil.notifyPlayerOfInsufficientResources((EntityPlayerMP)playerIn, Enums.Resources.ENERGY, 200);
				}
			}

			return true;
		}

		return false;
	}
}
