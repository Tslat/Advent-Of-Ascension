package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;


public class InfernalEntity extends AoAMeleeMob<InfernalEntity> {
    public InfernalEntity(EntityType<? extends InfernalEntity> entityType, Level world) {
        super(entityType, world);

        setPathfindingMalus(BlockPathTypes.LAVA, 4);
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0);
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, 8);
        setPathfindingMalus(BlockPathTypes.WATER, -1);
    }

    @Override
    public BrainActivityGroup<InfernalEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && (pl.isCreative() || pl.isSpectator())) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()).whenActivating(entity -> {
                    Entity target = BrainUtils.getTargetOfEntity(entity);
                    BlockPos pos = (target == null ? entity : target).blockPosition();
                    BlockState state = level().getBlockState(pos);

                    if (state.isAir())
                        state = level().getBlockState(pos = pos.below());

                    BlockPos finalPos = pos;

                    ServerParticlePacket packet = new ServerParticlePacket(ParticleBuilder.forPos(new BlockParticleOption(ParticleTypes.BLOCK, state), () -> new Vec3(finalPos.getX() + RandomUtil.randomValueUpTo(1), finalPos.getY() + 1.1f, finalPos.getZ() + RandomUtil.randomValueUpTo(1))).velocity(0, 0.5f, 0).spawnNTimes(3));

                    entity.playSound(AoASounds.ROCK_SMASH.get(), 1, 0.2f);
                    AoANetworking.sendToAllPlayersTrackingEntity(packet, entity);
                    doSlam(finalPos, 0.75f);
                }));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 3.09375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.LAVA_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
    }

    @Override
    protected int getAttackSwingDuration() {
        return 22;
    }

    @Override
    protected int getPreAttackTime() {
        return 10;
    }

    private void doSlam(BlockPos fromPos, float chance) {
        ServerParticlePacket packet = new ServerParticlePacket();
        boolean sendPacket = false;

        for (Direction dir : Direction.values()) {
            if (RandomUtil.percentChance(chance)) {
                BlockPos pos = fromPos.offset(dir.getNormal());

                if (level().getBlockState(pos).getBlock() == Blocks.NETHERRACK) {
                    int tickTime = Math.max(1, 1 - pos.distManhattan(fromPos));
                    sendPacket = true;

                    packet.particle(ParticleBuilder.forPos(ParticleTypes.FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + 1.1, pos.getZ() + RandomUtil.randomValueUpTo(1)));
                    level().setBlock(pos, Blocks.MAGMA_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
                    AoAScheduler.scheduleSyncronisedTask(() -> doSlam(pos, chance * 0.8f), tickTime);
                    AoAScheduler.scheduleSyncronisedTask(() -> {
                        if (level().getBlockState(pos).getBlock() == Blocks.MAGMA_BLOCK)
                            level().setBlock(pos, Blocks.NETHERRACK.defaultBlockState(), Block.UPDATE_ALL);
                    }, tickTime + 100);
                }
            }
        }

        if (sendPacket)
            AoANetworking.sendToAllPlayersTrackingEntity(packet, this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericLivingController(this));
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
    }
}
