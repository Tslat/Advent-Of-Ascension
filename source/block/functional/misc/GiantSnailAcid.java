package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GiantSnailAcid extends AcidBlock {
	public GiantSnailAcid() {
		super("GiantSnailAcid", "giant_snail_acid");
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) {
			entity.attackEntityFrom(new DamageSource("giant_snail_acid"), 15.0f);
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 3));
		}
	}
}
