package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

public class KaiyuTempleTrapDamage extends Block {
	private final VoxelShape SHAPE = VoxelShapes.create(new AxisAlignedBB(0.002, 0.002, 0.002, 0.998, 0.998, 0.998));

	public KaiyuTempleTrapDamage() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).stats(12f, 15f).get());
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative()) {
			entity.hurt(new DamageSource("temple_trap").bypassArmor(), 3.0f);
			EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 40).level(4).isAmbient());
		}
	}
}
