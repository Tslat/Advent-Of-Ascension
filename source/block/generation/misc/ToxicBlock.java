package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nullable;

public class ToxicBlock extends Block {
	public ToxicBlock() {
		super(Material.SPONGE);
		setUnlocalizedName("ToxicBlock");
		setRegistryName("aoa3:toxic_block");
		setHardness(1.5f);
		setResistance(1.0f);
		setSoundType(SoundType.SLIME);
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return FULL_BLOCK_AABB.contract(0, 0.125f, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World wordld, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) {
			entity.attackEntityFrom(new DamageSource("toxic_block").setDamageBypassesArmor(), 4.0f);
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 5, true, true));
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40, 5, true, true));
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 40, 5, true, true));
		}
	}
}
