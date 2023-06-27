package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GiantSnailAcid extends AcidBlock {
	public GiantSnailAcid(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
		/*if (EntityUtil.isVulnerableEntity(entity, new DamageSource("acid"))) {
			entity.hurt(new DamageSource("acid"), 4);
			EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40));
		}*/
	}
}
