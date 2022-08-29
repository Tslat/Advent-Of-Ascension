package net.tslat.aoa3.content.block.functional.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

import java.util.function.Supplier;

public class ToxicWaste extends LiquidBlock {
	public ToxicWaste(Supplier<? extends FlowingFluid> supplier, Properties properties) {
		super(supplier, properties);
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		float sizeSpeedMod = Mth.clamp(1 / (2 / (entity.getBbWidth() * entity.getBbHeight())), 0.35f, 1f);

		entity.makeStuckInBlock(state, new Vec3(1, 1, 1));
		EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.POISON, 60).level(8), new EffectBuilder(MobEffects.CONFUSION, 150));
	}
}
