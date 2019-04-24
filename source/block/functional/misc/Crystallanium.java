package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nullable;

public class Crystallanium extends BasicBlock {
	public Crystallanium() {
		super("Crystallanium", "crystallanium", Material.GLASS, 1.0f, 5.0f);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public float getSlipperiness(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity entity) {
		return 1.125f;
	}
}
