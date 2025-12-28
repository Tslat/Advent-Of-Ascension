package net.tslat.aoa3.content.entity.boss.tyrosaur;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class WoundedTyrosaurEntity extends AoAMeleeMob<WoundedTyrosaurEntity> {
    public WoundedTyrosaurEntity(EntityType<? extends WoundedTyrosaurEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Nullable
    @Override
    public MultipartBuilder<? extends WoundedTyrosaurEntity> definePartEntities() {
        return MultipartBuilder.of(this,
                                   MultipartBuilder.Part.sized(0.875f, getBbHeight() - 0.5625f).up(0.3125f).adjacentBehind().then(
                                           MultipartBuilder.Part.sized(0.5625f, 0.5f).down(0.125f).adjacentBehind().damageMod(0.85f).then(
                                                   MultipartBuilder.Part.sized(0.5625f, 0.4375f).down(0.0625f).adjacentBehind().damageMod(0.5f))),
                                   MultipartBuilder.Part.sized(0.9375f, getBbHeight() - 0.5f).up(0.375f).adjacentForward().then(
                                           MultipartBuilder.Part.sized(0.625f, 0.75f).down(0.125f).adjacentForward()));
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_TYROSAUR_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_TYROSAUR_HURT.get();
    }

    @Override
    protected float getStepWeight() {
        return 3f;
    }

    @Override
    protected boolean isQuadruped() {
        return true;
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);

        setHealth((float)RandomUtil.valueBetween(0.06f, 0.08f) * getMaxHealth());

        return spawnGroupData;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        if (getKillCredit() instanceof ServerPlayer pl)
            InventoryUtil.findItem(pl, stack -> stack.is(AoAItems.BONE_HORN) && stack.isDamaged() && stack.getDamageValue() == 1).ifPresent(pair -> pair.right().setDamageValue(0));
    }

    @Override
    public void onDamageTaken(DamageContainer damageContainer) {
        if (level() instanceof ServerLevel level && damageContainer.getSource().is(DamageTypeTags.IS_FIRE) && level().getFluidState(BlockPos.containing(getEyePosition())).getFluidType() == AoAFluidTypes.TAR.get() && level().getFluidState(blockPosition().above()).getFluidType() == AoAFluidTypes.TAR.get()) {
            ParticleBuilder.forRandomPosInEntity(ParticleTypes.LARGE_SMOKE, this)
                    .colourTint(0xFFFFFF)
                    .spawnNTimes(20)
                    .sendToAllPlayersTrackingEntity(this);

            if (isDeadOrDying()) {
                AoAScheduler.schedule(19 - this.deathTime, tick -> {
                    EntitySpawningUtil.spawnEntity(level, AoAMonsters.SKELETRON.get(), position(), MobSpawnType.CONVERSION, abomination -> {
                        abomination.setXRot(getXRot());
                        abomination.setYRot(getYRot());
                        abomination.setYHeadRot(getYHeadRot());
                    });
                });
            }
        }
    }

    @Override
    protected int getPreAttackTime() {
        return 5;
    }

    @Override
    public int getCurrentSwingDuration() {
        return 11;
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<WoundedTyrosaurEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(635)
                .moveSpeed(0.2)
                .meleeStrength(15)
                .knockbackResist(0.9)
                .followRange(100)
                .aggroRange(64)
                .armour(10, 10)
                .knockback(1f)
                .stepHeight(1.25f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0));
    }
}
