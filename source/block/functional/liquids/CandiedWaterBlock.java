package net.tslat.aoa3.block.functional.liquids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicFluidBlock;
import net.tslat.aoa3.common.registration.FluidsRegister;

public class CandiedWaterBlock extends BasicFluidBlock {
	
	public CandiedWaterBlock() {
		super("CandiedWater", this, Material.WATER, MapColor.PINK);
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityLivingBase)
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 0, true, false));
	}
}
