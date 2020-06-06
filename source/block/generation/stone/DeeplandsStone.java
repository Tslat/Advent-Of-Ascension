package net.tslat.aoa3.block.generation.stone;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.passive.EntityShik;

public class DeeplandsStone extends StoneBlock {
	public DeeplandsStone() {
		super("DeeplandsStone", "deeplands_stone");
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote && AdventOfAscension.rand.nextInt(50) == 0) {
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItem(EnumHand.MAIN_HAND)) == 0) {
				EntityShik shik = new EntityShik(world);

				shik.setPositionAndUpdate(pos.getX(), pos.getY() + 0.1f, pos.getZ());
				world.spawnEntity(shik);
			}
		}
	}
}
