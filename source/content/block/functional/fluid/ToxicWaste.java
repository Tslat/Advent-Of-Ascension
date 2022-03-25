package net.tslat.aoa3.content.block.functional.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

import java.util.function.Supplier;

public class ToxicWaste extends FlowingFluidBlock {
	public ToxicWaste(Supplier<? extends FlowingFluid > supplier, Properties properties) {
		super(supplier, properties);
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vector3d(0.5d, 0.1d, 0.5d));
		EntityUtil.applyPotions(entity, new EffectBuilder(Effects.POISON, 60).level(8), new EffectBuilder(Effects.CONFUSION, 150));
	}
}
