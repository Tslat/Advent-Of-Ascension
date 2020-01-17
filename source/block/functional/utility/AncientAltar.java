package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.UnbreakableBlock;

public class AncientAltar extends UnbreakableBlock {
	public AncientAltar() {
		super("AncientAltar", "ancient_altar", Material.ROCK);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/*if (!world.isRemote) { // TODO Something here
			ItemStack stack = player.getHeldItem(hand);

			if (stack.getItem() == ItemRegister.gemShyregem && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.gemShyregem))) {
				if (AdventOfAscension.rand.nextInt(4) == 0) {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.ancientRing));

					return true;
				}
			}
			else if (stack.getItem() == ItemRegister.ingotShyrestone && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.ingotShyrestone))) {
				if (AdventOfAscension.rand.nextInt(8) == 0) {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.ancientRing));

					return true;
				}
			}
		}
		*/
		return true;
	}
}
