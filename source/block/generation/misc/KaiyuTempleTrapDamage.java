package net.tslat.aoa3.block.generation.misc;

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
import net.tslat.aoa3.block.UnbreakableBlock;

import javax.annotation.Nullable;

public class KaiyuTempleTrapDamage extends UnbreakableBlock {
	private final AxisAlignedBB COLLIDABLE_BLOCK_AABB = new AxisAlignedBB(0.05, 0.05, 0.05, 0.95, 0.95, 0.95);

	public KaiyuTempleTrapDamage() {
		super("KaiyuTempleTrapMaze", "kaiyu_temple_trap_maze", Material.ROCK);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return COLLIDABLE_BLOCK_AABB;
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) {
			entity.attackEntityFrom(new DamageSource("temple_trap").setDamageBypassesArmor(), 3.0f);
			((EntityPlayer)entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40, 3, true, false));
		}
	}
}
