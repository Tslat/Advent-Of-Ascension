package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

public class GiantSnailAcid extends AcidBlock {
	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entity) {
		if (EntityUtil.isVulnerableEntity(entity, new DamageSource("acid"))) {
			entity.hurt(new DamageSource("acid"), 4);
			EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 40));
		}
	}
}
