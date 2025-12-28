package net.tslat.aoa3.content.entity.boss.tyrosaur;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ScreenShakePacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.misc.EarthquakeBlockEntity;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.behaviour.DelayedBehaviour;
import net.tslat.tme.api.object.BlockSnapshot;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.BlockRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.internal.sound.BuildableMovingSoundInstance;

import java.util.List;
import java.util.Set;

class TyrosaurEarthquakeBehaviour<T extends AoABoss> extends DelayedBehaviour<T> {
    private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));

    private final float damage;

    public TyrosaurEarthquakeBehaviour(float damage) {
        super(25);

        this.damage = damage;
    }

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    @Override
    protected void start(T entity) {
        entity.setAttackState(TyrosaurEntity.SLAM);
        entity.triggerAnim("Main", "slam");
        entity.setImmobile(true);
    }

    private void screenShakePlayers(ServerLevel level, T entity, Vec3 earthquakeOrigin) {
        for (ServerPlayer player : level.players()) {
            if (player.distanceToSqr(entity) > 20 * 20)
                continue;

            AoANetworking.sendToPlayer(player, new ScreenShakePacket(10, Math.max(0.1f, (1 - (float)earthquakeOrigin.distanceToSqr(player.position()) / (20f * 20f)) * 2), 0.98f));
        }
    }

    private void doBlocks(ServerLevel level, T entity, Vec3 earthquakeOrigin, int tick) {
        List<BlockSnapshot> positions = BlockRetrievalUtil.getBlocks(level, reader -> {
            List<BlockSnapshot> list = new ObjectArrayList<>();
            Iterable<Vec3> iterable = MathUtil.inLateralCircle(earthquakeOrigin, tick, 64);
            Set<BlockPos> checkedPositions = new ObjectOpenHashSet<>();

            for (Vec3 vecPos : iterable) {
                BlockPos pos = BlockPos.containing(vecPos);

                if (!checkedPositions.add(pos))
                    continue;

                BlockState state = reader.getBlockState(pos);

                if (!state.blocksMotion()) {
                    int offset = 10;

                    while (offset-- > 0 && !(state = reader.getBlockState(pos = pos.below())).blocksMotion());
                }
                else if (reader.getBlockState(pos.above()).blocksMotion()) {
                    int offset = 10;

                    while (offset-- > 0 && reader.getBlockState(pos = pos.above()).blocksMotion());

                    state = reader.getBlockState(pos = pos.below());
                }

                if (state.blocksMotion() && !reader.getBlockState(pos.above()).blocksMotion())
                    list.add(new BlockSnapshot(pos, state));
            }

            return list;
        });

        TMEParticlePacket packet = new TMEParticlePacket();

        if (entity.tickCount % 3 == 0) {
            for (ServerPlayer player : level.players()) {
                if (player.distanceToSqr(entity) > 32 * 32)
                    continue;

                Vec3 velocity = earthquakeOrigin.vectorTo(player.position()).normalize();

                SoundBuilder.at(AoASounds.FX_RUBBLE, level, earthquakeOrigin.add(velocity.scale(tick)))
                        .customInstance(new BuildableMovingSoundInstance(velocity))
                        .pitch(0.75f)
                        .onlyFor(player)
                        .play();
            }
        }

        for (BlockSnapshot ringPos : positions) {
            EarthquakeBlockEntity block = new EarthquakeBlockEntity(AoAMiscEntities.EARTHQUAKE_BLOCK.get(), level, ringPos.state(), ringPos.pos(), entity);

            block.setDamage(this.damage);
            block.setDeltaMovement(new Vec3(0, 0.35f, 0));
            block.setPos(Vec3.atCenterOf(ringPos.pos()));
            level.addFreshEntity(block);
            packet.particle(ParticleBuilder.forRandomPosInBlock(new BlockParticleOption(ParticleTypes.BLOCK, ringPos.state()), ringPos.pos())
                                    .lifespan(RandomUtil.numberBetween(20, 40))
                                    .scaleMod(0.5f)
                                    .velocity(RandomUtil.scaledGaussianValue(0.1f), RandomUtil.valueBetween(0.2f, 0.4f), RandomUtil.scaledGaussianValue(0.1f)));
        }

        packet.sendToAllPlayersTrackingEntity(entity);
    }

    @Override
    protected void doDelayedAction(T entity) {
        ServerLevel level = (ServerLevel)entity.level();
        Vec3 stompPos = entity.position().add(MathUtil.getBodyForward(entity).scale(1.35f));

        doFX(entity, stompPos);

        Vec3 earthquakeOrigin = stompPos.subtract(0, 1, 0);

        AoAScheduler.schedule(3, tick -> screenShakePlayers(level, entity, earthquakeOrigin));
        AoAScheduler.scheduleEveryTick(0, 20, tick -> doBlocks(level, entity, earthquakeOrigin, tick));
    }

    private void doFX(T entity, Vec3 stompPos) {
        Vec3 right = MathUtil.getBodyRight(entity);
        Vec3 leftFootPos = stompPos.add(right.scale(0.75f));
        Vec3 rightFootPos = stompPos.add(right.scale(-0.75f));

        BlockState leftFootState = entity.level().getBlockState(BlockPos.containing(leftFootPos.x, leftFootPos.y - 0.1f, leftFootPos.z));
        BlockState rightFootState = entity.level().getBlockState(BlockPos.containing(rightFootPos.x, rightFootPos.y - 0.1f, rightFootPos.z));
        SoundBuilder.at(AoASounds.FX_SLAM, entity.level(), stompPos)
                .category(entity.getSoundSource())
                .radius(32)
                .play();
        TMEParticlePacket packet = new TMEParticlePacket();

        if (!leftFootState.isAir()) {
            for (int i = 0; i < 20; i++) {
                packet.particle(ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, leftFootState), leftFootPos)
                                        .lifespan(RandomUtil.numberBetween(40, 60))
                                        .velocity(RandomUtil.scaledGaussianValue(0.1f), RandomUtil.valueBetween(0.2f, 0.5f), RandomUtil.scaledGaussianValue(0.1f)));

                if (i < 5) {
                    packet.particle(ParticleBuilder.forPositions(ParticleTypes.CAMPFIRE_COSY_SMOKE, leftFootPos)
                                            .colourTint(1f, 1f, 1f, 0.5f)
                                            .lifespan(RandomUtil.numberBetween(40, 60))
                                            .velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.valueUpTo(0.1f), RandomUtil.scaledGaussianValue(0.05f)));
                }
            }
        }

        if (!rightFootState.isAir()) {
            for (int i = 0; i < 10; i++) {
                packet.particle(ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, rightFootState), rightFootPos)
                                        .lifespan(RandomUtil.numberBetween(40, 60))
                                        .velocity(RandomUtil.scaledGaussianValue(0.1f), RandomUtil.valueBetween(0.2f, 0.5f), RandomUtil.scaledGaussianValue(0.1f)));

                if (i < 5) {
                    packet.particle(ParticleBuilder.forPositions(ParticleTypes.CAMPFIRE_COSY_SMOKE, rightFootPos)
                                            .colourTint(1f, 1f, 1f, 0.5f)
                                            .lifespan(RandomUtil.numberBetween(40, 60))
                                            .velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.valueUpTo(0.1f), RandomUtil.scaledGaussianValue(0.05f)));
                }
            }
        }

        packet.sendToAllPlayersTrackingEntity(entity);
    }

    @Override
    protected void stop(T entity) {
        entity.setAttackState(TyrosaurEntity.BITE);
        entity.setImmobile(false);
    }
}