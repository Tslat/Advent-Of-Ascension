package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nullable;

public class ToxicWaste extends BasicBlock {
	private static final AxisAlignedBB bounds = new AxisAlignedBB(0, 0, 0, 1, 0.90, 1);

	public ToxicWaste() {
		super("ToxicWaste", "toxic_waste", Material.SPONGE, 0.25f, 0.1f);
		setCreativeTab(CreativeTabsRegister.GENERATION_BLOCKS);
		setHarvestLevel("shovel", 1);
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
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) {
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 7));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 150, 5));
		}
	}
}
