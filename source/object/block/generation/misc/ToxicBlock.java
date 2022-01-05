package net.tslat.aoa3.object.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
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
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

public class ToxicBlock extends Block {
	private final VoxelShape SHAPE = VoxelShapes.create(new AxisAlignedBB(0.002, 0.002, 0.002, 0.998, 0.998, 0.998));

	public ToxicBlock() {
		super(new BlockUtil.CompactProperties(Material.DIRT, MaterialColor.TERRACOTTA_GREEN).stats(1.5f, 1f).tool(ToolType.SHOVEL).sound(SoundType.SLIME_BLOCK).get());
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative()) {
			entity.hurt(new DamageSource("toxic_block").bypassArmor(), 4);
			EntityUtil.applyPotions(entity,
					new PotionUtil.EffectBuilder(Effects.POISON, 60).level(6),
					new PotionUtil.EffectBuilder(Effects.CONFUSION, 40),
					new PotionUtil.EffectBuilder(Effects.BLINDNESS, 40));
		}
	}
}
