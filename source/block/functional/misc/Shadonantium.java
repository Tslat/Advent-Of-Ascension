package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nullable;

public class Shadonantium extends BasicBlock {
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.90, 1);

	public Shadonantium() {
		super("Shadonantium", "shadonantium", Material.GLASS, 5.0f, 10.0f);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return AABB;
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!(entity instanceof EntityItem) && !(entity instanceof IProjectile))
			entity.motionY = 2.0f;
	}
}
