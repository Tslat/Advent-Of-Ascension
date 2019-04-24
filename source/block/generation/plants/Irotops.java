package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Irotops extends GenericPlantBlock {
	public Irotops() {
		super("Irotops", "irotops", 0.4f, Material.GROUND, Material.GRASS);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer)
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 1, true, true));
	}
}
