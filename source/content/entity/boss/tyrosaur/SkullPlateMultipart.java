package net.tslat.aoa3.content.entity.boss.tyrosaur;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.MathUtil;

public class SkullPlateMultipart extends AoAEntityPart<TyrosaurEntity> {
    public SkullPlateMultipart(TyrosaurEntity parent, EntityDimensions size, Vec3 posOffset) {
        super(parent, size, posOffset);
    }

    public SkullPlateMultipart(TyrosaurEntity parent, float width, float height, float offsetLeftRight, float offsetY, float offsetFrontBack) {
        super(parent, width, height, offsetLeftRight, offsetY, offsetFrontBack);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!DamageUtil.isMeleeDamage(source))
            return false;

        return super.hurt(source, amount);
    }

    @Override
    public ProjectileDeflection deflection(Projectile projectile) {
        return (projectile2, entity, random) -> {
            Vec3 deflection = projectile2.getDeltaMovement()
                    .cross(MathUtil.getEyelineForward(getParent()))
                    .add(0, 0.4f, 0)
                    .normalize();

            projectile2.setDeltaMovement(deflection);
            projectile2.hasImpulse = true;
        };
    }
}
