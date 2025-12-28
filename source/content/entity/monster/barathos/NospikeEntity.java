package net.tslat.aoa3.content.entity.monster.barathos;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class NospikeEntity extends AoAMeleeMob<NospikeEntity> {
    public NospikeEntity(EntityType<? extends NospikeEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable MultipartBuilder<? extends NospikeEntity> definePartEntities() {
        return MultipartBuilder.of(this,
                                          Part.sized(getBbWidth(), 1.46875f).up(1.125f).adjacentForward().then(
                                                  Part.sized(0.75f, 0.75f).adjacentAbove().forward(0).then(
                                                          Part.sized(0.75f, 0.625f).adjacentForward())),
                                          Part.sized(0.8125f, 0.6875f).up(1.6875f).adjacentBehind().then(
                                                  Part.sized(0.5f, 0.5f).up(0.25f).adjacentBehind()));
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<NospikeEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(46)
                .moveSpeed(0.34)
                .meleeStrength(7.5f)
                .knockbackResist(0.25f)
                .aggroRange(12)
                .followRange(32);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
    }
}
