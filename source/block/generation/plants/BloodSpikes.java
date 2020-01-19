package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodSpikes extends PlantStackable {
	public BloodSpikes() {
		super("BloodSpikes", "blood_spikes", Material.GRASS, Material.GROUND);
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) {
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 3, true, true));
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 2, true, true));
		}
	}
}
