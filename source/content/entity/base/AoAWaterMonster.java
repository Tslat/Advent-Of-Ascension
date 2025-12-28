package net.tslat.aoa3.content.entity.base;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathType;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.brain.task.temp.SetRandomSwimTarget;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.navigation.SmoothWaterBoundPathNavigation;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;

import java.util.List;

public abstract class AoAWaterMonster<T extends AoAWaterMonster<T>> extends AoAMonster<T> {
    protected AoAWaterMonster(EntityType<? extends AoAMonster> entityType, Level level) {
        super(entityType, level);

        setPathfindingMalus(PathType.WATER, 0);

        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    @Override
    protected MoveControl createMoveControl() {
        return new SmoothSwimmingMoveControl(this, 85, 30, 1, 0.1f, true);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return isAmphibious() ? new AmphibiousPathNavigation(this, level) : new SmoothWaterBoundPathNavigation(this, level);
    }

    @Override
    public List<ExtendedSensor<? extends T>> getSensors() {
        return ObjectArrayList.of(
                new AggroBasedNearbyPlayersSensor<T>().setPredicate((player, entity) -> player.isInWater()),
                new AggroBasedNearbyLivingEntitySensor<T>().setPredicate((target, entity) -> target.isInWater() && target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
                new HurtBySensor<>());
    }

    @Override
    public BrainActivityGroup<? extends T> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new WalkOrRunToWalkTarget<>().startCondition(entity -> !isDoingStationaryActivity()));
    }

    @Override
    public BrainActivityGroup<? extends T> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new TargetOrRetaliate<>()
                        .useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
                        .attackablePredicate(target -> DamageUtil.isAttackable(target) && target.isInWater() && !isAlliedTo(target)),
                new OneRandomBehaviour<>(
                        new SetRandomSwimTarget<>().speedModifier(0.9f),
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return level.isUnobstructed(this);
    }

    @Override
    public int getAmbientSoundInterval() {
        return 280;
    }

    protected boolean drownsOnLand() {
        return true;
    }

    protected boolean isAmphibious() {
        return false;
    }

    protected void handleAirSupply(int currentAir) {
        if (isEffectiveAi() && drownsOnLand() && isAlive() && !isInWaterOrBubble()) {
            setAirSupply(currentAir - 1);

            if (getAirSupply() == -20) {
                setAirSupply(0);
                hurt(damageSources().drown(), 2);
            }
        }
        else {
            setAirSupply(300);
        }
    }

    @Override
    public void baseTick() {
        final int airSupply = getAirSupply();

        super.baseTick();
        handleAirSupply(airSupply);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }
}
