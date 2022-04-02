package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

public class MysticBush extends GenericPlantBlock {
	public MysticBush() {
		super(Material.PLANT, MaterialColor.COLOR_PURPLE, Material.DIRT, Material.GRASS);
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity instanceof Player)
			EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.JUMP, 30).level(4));
	}
}
