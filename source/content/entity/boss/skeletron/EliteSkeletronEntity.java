package net.tslat.aoa3.content.entity.boss.skeletron;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class EliteSkeletronEntity extends AoABoss {
    public EliteSkeletronEntity(EntityType<? extends EliteSkeletronEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SoundEvent getMusic() {
        return null;
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<EliteSkeletronEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(600)
                .moveSpeed(0.315)
                .meleeStrength(30)
                .knockbackResist(1)
                .followRange(128)
                .aggroRange(128)
                .armour(15, 25)
                .knockback(1f)
                .stepHeight(1.25f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
    }
}
