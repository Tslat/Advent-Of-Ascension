package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockFalling;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.BlockRegister;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitEtherealMiner extends AbstractTrait {
	public TraitEtherealMiner() {
		super("ethereal_miner", 0xCFF99F);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		if (event.getWorld().isOutsideBuildHeight(event.getPos().up()) || (!event.getWorld().isRemote && event.getWorld().getWorldInfo().getTerrainType() == WorldType.DEBUG_ALL_BLOCK_STATES))
			return;

		Block block = event.getWorld().getBlockState(event.getPos().up()).getBlock();

		if (!(block instanceof BlockFalling) && !(block instanceof BlockDragonEgg))
			return;

		event.getWorld().setBlockState(event.getPos(), BlockRegister.AIR_GAP.getDefaultState(), 16);
	}
}
