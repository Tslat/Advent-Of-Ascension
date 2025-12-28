package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

public class BaronLooseSand extends ColoredFallingBlock {
    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0, 0, 0, 1, 0.9f, 1);

    public BaronLooseSand(Properties properties) {
        super(new ColorRGBA(0x71494A), properties);
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState blockState, BlockGetter blockGetter, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    protected VoxelShape getVisualShape(BlockState blockState, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        return Shapes.empty();
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        if (collisionContext instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();

            if (entity != null) {
                if (entity.fallDistance > 2.5F)
                    return FALLING_COLLISION_SHAPE;

                if (entity instanceof FallingBlockEntity || canEntityWalkOnLooseSand(entity) && collisionContext.isAbove(Shapes.block(), pos, false) && !collisionContext.isDescending())
                    return super.getCollisionShape(blockState, blockGetter, pos, collisionContext);
            }
        }


        return Shapes.empty();
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float fallDistance) {
        if (fallDistance >= 4 && entity instanceof LivingEntity livingEntity) {
            LivingEntity.Fallsounds fallFound = livingEntity.getFallSounds();

            entity.playSound(fallDistance < 7 ? fallFound.small() : fallFound.big(), 1.0F, 1.0F);
        }
    }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getInBlockState().is(this)) {
            entity.makeStuckInBlock(blockState, new Vec3(0.8f, 0.2f, 0.8f));

            if (!level.isClientSide && BlockPos.containing(entity.getEyePosition()).equals(pos))
                DamageUtil.safelyDealDamage(level.damageSources().inWall(), entity, 1);

            if (level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) && level.getRandom().nextBoolean()) {
                ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, AoABlocks.BARON_LOOSE_SAND.get().defaultBlockState()), entity)
                        .power(new Vec3(RandomUtil.scaledGaussianValue(1 / 12f), 0.05f, RandomUtil.scaledGaussianValue(1 / 12f)))
                        .spawnClientParticles(level);
            }
        }
    }

    @Override
    protected boolean isPathfindable(BlockState blockState, PathComputationType pathType) {
        return true;
    }

    public static boolean canEntityWalkOnLooseSand(Entity entity) {
        if (entity.getType().is(AoATags.Entities.LOOSE_SAND_WALKABLE_MOBS))
            return true;

        return entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.FEET).canWalkOnPowderedSnow(livingEntity);
    }
}
