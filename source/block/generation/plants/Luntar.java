package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Luntar extends GenericPlantBlock {
	public Luntar() {
		super("Luntar", "luntar", 0.4f, Material.GROUND, Material.GRASS);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer)
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 30, 0, true, true));
	}
}
