package net.tslat.aoa3.content.entity.boss.tyrosaur;

import com.google.common.collect.Iterables;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ScreenShakePacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.entity.misc.EarthquakeBlockEntity;
import net.tslat.aoa3.library.object.interfaces.TriFunction;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.behaviour.HeldBehaviour;
import net.tslat.tme.api.object.BlockSnapshot;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.BlockRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.internal.sound.BuildableMovingSoundInstance;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

class EliteTyrosaurGroundShakeBehaviour extends HeldBehaviour<EliteTyrosaurEntity> {
    private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));

    private TriFunction<EliteTyrosaurEntity, Vec3, @Nullable Iterable<Vec3>, Iterable<Vec3>> chosenPattern;

    public EliteTyrosaurGroundShakeBehaviour() {
        super();
    }

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    @Override
    protected void start(EliteTyrosaurEntity entity) {
        entity.setAttackState(EliteTyrosaurEntity.BRACE);
        entity.triggerAnim("Main", "brace_start");
        entity.setImmobile(true);
        SoundBuilder.at(AoASounds.FX_SLAM, entity.level(), entity.position().add(MathUtil.getBodyForward(entity).scale(1.35f)))
                .category(entity.getSoundSource())
                .radius(32)
                .play();

        this.chosenPattern = switch (RandomUtil.numberBetween(0, 2)) {
            case 1 -> this::getRingPatternIterable;
            default -> this::getSpinningPatternIterable;
        };
    }

    @Override
    protected void tick(EliteTyrosaurEntity entity) {
        if (this.runningTime < 30)
            return;

        ServerLevel level = (ServerLevel)entity.level();
        Vec3 stompPos = entity.position().add(MathUtil.getBodyForward(entity).scale(1.35f));
        Vec3 earthquakeOrigin = stompPos.subtract(0, 1, 0);

        doGroundShakesBlockTick(entity, earthquakeOrigin);
        doFX(entity, stompPos);

        for (ServerPlayer player : level.players()) {
            if (player.distanceToSqr(entity) > 20 * 20)
                continue;

            AoANetworking.sendToPlayer(player, new ScreenShakePacket(20, Math.max(0.1f, (1 - (float)earthquakeOrigin.distanceToSqr(player.position()) / (20f * 20f)) * 0.35f), 0.98f));
        }
    }

    @Override
    protected boolean shouldKeepRunning(EliteTyrosaurEntity entity) {
        return this.runningTime <= 200;
    }

    private Iterable<Vec3> getSpinningPatternIterable(EliteTyrosaurEntity entity, Vec3 earthquakeOrigin, @Nullable Iterable<Vec3> centerIterable) {
        Iterable<Vec3>[] iterables = new Iterable[centerIterable == null ? 4 : 5];

        if (centerIterable != null)
            iterables[4] = centerIterable;

        for (int i = 0; i < 4; i++) {
            float angle = ((entity.tickCount - i * 25) % 100) / 100f * Mth.TWO_PI;
            iterables[i] = MathUtil.inLine(earthquakeOrigin, earthquakeOrigin.add(new Vec3(Mth.cos(angle) * 20, 0, Mth.sin(angle) * 20)), 40);
        }

        return Iterables.concat(iterables);
    }

    private Iterable<Vec3> getRingPatternIterable(EliteTyrosaurEntity entity, Vec3 earthquakeOrigin, @Nullable Iterable<Vec3> centerIterable) {
        Iterable<Vec3>[] iterables = new Iterable[centerIterable == null ? 1 : 2];

        if (centerIterable != null)
            iterables[1] = centerIterable;

        int tick = Mth.ceil(this.runningTime) % 25;
        iterables[0] = MathUtil.inLateralCircle(earthquakeOrigin, tick, 64);

        if (this.runningTime % 25 == 0) {
            ServerLevel level = (ServerLevel)entity.level();

            for (ServerPlayer player : level.players()) {
                if (player.distanceToSqr(entity) > 32 * 32)
                    continue;

                Vec3 velocity = earthquakeOrigin.vectorTo(player.position()).normalize();

                SoundBuilder.at(AoASounds.FX_RUBBLE, level, earthquakeOrigin)
                        .customInstance(new BuildableMovingSoundInstance(velocity))
                        .pitch(0.75f)
                        .onlyFor(player)
                        .play();
            }
        }

        return Iterables.concat(iterables);
    }

    private void doGroundShakesBlockTick(EliteTyrosaurEntity entity, Vec3 earthquakeOrigin) {
        ServerLevel level = (ServerLevel)entity.level();
        List<BlockSnapshot> positions = BlockRetrievalUtil.getBlocks(level, reader -> {
            List<BlockSnapshot> list = new ObjectArrayList<>();
            Iterable<Vec3> centerIterable = entity.tickCount % 5 != 0 ? null : MathUtil.inLateralCircle(earthquakeOrigin, (entity.tickCount % 15) / 5 + 1, 64);
            Iterable<Vec3> iterable = this.chosenPattern.apply(entity, earthquakeOrigin, centerIterable);
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

        for (BlockSnapshot ringPos : positions) {
            EarthquakeBlockEntity block = new EarthquakeBlockEntity(AoAMiscEntities.EARTHQUAKE_BLOCK.get(), level, ringPos.state(), ringPos.pos(), entity);

            block.setDamage(14);
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

    private void doFX(EliteTyrosaurEntity entity, Vec3 stompPos) {
        Vec3 right = MathUtil.getBodyRight(entity);
        Vec3 leftFootPos = stompPos.add(right.scale(0.75f));
        Vec3 rightFootPos = stompPos.add(right.scale(-0.75f));

        BlockState leftFootState = entity.level().getBlockState(BlockPos.containing(leftFootPos.x, leftFootPos.y - 0.1f, leftFootPos.z));
        BlockState rightFootState = entity.level().getBlockState(BlockPos.containing(rightFootPos.x, rightFootPos.y - 0.1f, rightFootPos.z));
        TMEParticlePacket packet = new TMEParticlePacket();

        if (!leftFootState.isAir()) {
            for (int i = 0; i < 20; i++) {
                packet.particle(ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, leftFootState), leftFootPos)
                                        .lifespan(RandomUtil.numberBetween(40, 60))
                                        .velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.valueUpTo(0.1f), RandomUtil.scaledGaussianValue(0.05f)));

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
                                        .velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.valueUpTo(0.1f), RandomUtil.scaledGaussianValue(0.05f)));

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
    protected void stop(EliteTyrosaurEntity entity) {
        entity.triggerAnim("Main", "brace_stop");
        entity.setAttackState(TyrosaurEntity.BITE);
        entity.setImmobile(false);
    }
}