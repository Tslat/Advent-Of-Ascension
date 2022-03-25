package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

public class MysticBush extends GenericPlantBlock {
	public MysticBush() {
		super(Material.PLANT, MaterialColor.COLOR_PURPLE, Material.DIRT, Material.GRASS);
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof PlayerEntity)
			EntityUtil.applyPotions(entity, new EffectBuilder(Effects.JUMP, 30).level(4));
	}
}
