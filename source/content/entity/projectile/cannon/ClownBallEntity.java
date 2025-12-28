package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class ClownBallEntity extends PhysicalWeaponProjectile {
    public ClownBallEntity(EntityType<? extends ClownBallEntity> entityType, Level level) {
        super(entityType, level);
    }

    public ClownBallEntity(EntityType<? extends ClownBallEntity> entityType, Level level, WeaponFiringContext context) {
        super(entityType, level, context);
    }

    public ClownBallEntity(Level level, WeaponFiringContext context) {
        this(AoAProjectiles.CLOWN_BALL.get(), level, context);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.1f;
    }
}
