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

public class MysticBush extends GenericPlantBlock {
	public MysticBush() {
		super(Material.PLANTS, MaterialColor.PURPLE, Material.EARTH, Material.ORGANIC);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof PlayerEntity)
			EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.JUMP_BOOST, 30).level(4));
	}
}
