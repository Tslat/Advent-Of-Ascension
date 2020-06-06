package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitSoulHarvest extends AbstractTrait {
	public TraitSoulHarvest() {
		super("soul_harvest", 0x99A8FC);
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
		if (wasEffective && !world.isRemote && player instanceof EntityPlayer)
			PlayerUtil.addResourceToPlayer((EntityPlayer)player, Enums.Resources.SOUL, state.getBlockHardness(world, pos) * 0.75f);
	}
}
