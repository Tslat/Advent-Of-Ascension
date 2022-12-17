package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

public class KaiyuTempleTrapDamage extends Block {
	private final VoxelShape SHAPE = Shapes.create(new AABB(0.002, 0.002, 0.002, 0.998, 0.998, 0.998));

	public KaiyuTempleTrapDamage() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).stats(12f, 15f).get());
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity instanceof Player && !((Player)entity).isCreative()) {
			entity.hurt(new DamageSource("temple_trap").bypassArmor(), 3.0f);
			EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.BLINDNESS, 40).level(4).isAmbient());
		}
	}
}
