package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Lurchians extends GenericPlantBlock {
	public Lurchians() {
		super("Lurchians", "lurchians", 0.4f, Material.GROUND, Material.GRASS);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer) {
			entity.addVelocity(entity.motionX, 1.2, entity.motionZ);
			entity.fallDistance -= 5;
			entity.motionX = MathHelper.clamp(entity.motionX, -1.2, 1.2);
			entity.motionY = MathHelper.clamp(entity.motionY, 0, 2);
			entity.motionZ = MathHelper.clamp(entity.motionZ, -1.2, 1.2);
		}
	}
}
