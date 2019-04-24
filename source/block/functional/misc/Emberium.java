package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nullable;

public class Emberium extends BasicBlock {
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.05, 0.05, 0.05, 0.95, 0.95, 0.95);

	public Emberium() {
		super("Emberium", "emberium", Material.ROCK, 5.0f, 10.0f);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return AABB;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
				return;

			entity.setFire(5);
		}
	}
}
