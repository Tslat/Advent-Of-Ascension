package net.tslat.aoa3.content.block.generation.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TangleThorns extends BushBlock {
	public TangleThorns(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends TangleThorns> codec() {
		return simpleCodec(TangleThorns::new);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity) {
			entity.makeStuckInBlock(state, new Vec3(0.8f, 0.75d, 0.8f));

			if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
				if (Math.abs(entity.getX() - entity.xOld) >= (double)0.003f || Math.abs(entity.getZ() - entity.zOld) >= (double)0.003f)
					entity.hurt(level.damageSources().sweetBerryBush(), 1);
			}
		}
	}
}
