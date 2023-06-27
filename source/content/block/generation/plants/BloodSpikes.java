/*
package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

public class BloodSpikes extends StackablePlant {
	public BloodSpikes() {
		super(MaterialColor.COLOR_RED, Material.GRASS, Material.DIRT);
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
		if (entity instanceof Player && PlayerUtil.shouldPlayerBeAffected((Player)entity))
			EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.BLINDNESS, 100), new EffectBuilder(MobEffects.POISON, 100).level(3));
	}
}
*/
