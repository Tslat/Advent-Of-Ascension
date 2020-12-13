package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class BloodSpikes extends StackablePlant {
	public BloodSpikes() {
		super(MaterialColor.RED, Material.ORGANIC, Material.EARTH);
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
		if (entity instanceof PlayerEntity && PlayerUtil.shouldPlayerBeAffected((PlayerEntity)entity))
			EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 100), new PotionUtil.EffectBuilder(Effects.POISON, 100).level(3));
	}
}
