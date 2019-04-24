package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class AcidBlock extends BasicBlock {
	private static final AxisAlignedBB bounds = new AxisAlignedBB(0, 0, 0, 1, 0.125, 1);

	public AcidBlock(String name, String registryName) {
		super(name, registryName, Material.SNOW, 0, 0);
		setTickRandomly(true);
		setCreativeTab(null);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return bounds;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return bounds;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
		return bounds;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());

		return state.isFullCube() && state.getMaterial().blocksMovement() && state.getBlock() != Blocks.ICE && state.getBlock() != Blocks.PACKED_ICE && state.getBlock() != Blocks.FROSTED_ICE;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!canPlaceBlockAt(world, pos))
			world.setBlockToAir(pos);
	}

	@Override
	public int tickRate(World worldIn) {
		return 20;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote)
			world.setBlockToAir(pos);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (EntityUtil.isHostileMob(entity) || (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode)) {
			entity.attackEntityFrom(new DamageSource("acid_block"), 8.0f);
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 3));
		}
	}
}
