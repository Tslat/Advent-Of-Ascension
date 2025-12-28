package net.tslat.aoa3.content.block.functional.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ToxicWaste extends LiquidBlock {
	public ToxicWaste(Supplier<? extends FlowingFluid> supplier, Properties properties) {
		super(supplier.get(), properties);
	}

	@Override
	public @Nullable PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
		return PathType.DAMAGE_OTHER;
	}

	@Override
	public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
		return PathType.DAMAGE_OTHER;
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vec3(1, 1, 1));
		EntityUtil.applyPotions(entity, null, new EffectBuilder(MobEffects.POISON, 60).level(8), new EffectBuilder(MobEffects.CONFUSION, 150));
	}
}
