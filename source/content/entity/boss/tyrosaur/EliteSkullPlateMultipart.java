package net.tslat.aoa3.content.entity.boss.tyrosaur;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.util.DamageUtil;

public class EliteSkullPlateMultipart extends AoAEntityPart<EliteTyrosaurEntity> {
    public EliteSkullPlateMultipart(EliteTyrosaurEntity parent, EntityDimensions size, Vec3 posOffset) {
        super(parent, size, posOffset);
    }

    public EliteSkullPlateMultipart(EliteTyrosaurEntity parent, float width, float height, float offsetLeftRight, float offsetY, float offsetFrontBack) {
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
            Vec3 projectileCenter = projectile2.position().add(0, projectile2.getBbHeight() * 0.5f, 0);
            Vec3 angle = (projectile2.getOwner() instanceof LivingEntity owner ?
                          projectileCenter.vectorTo(owner.getEyePosition()) :
                          projectile2.getDeltaMovement().scale(-1)).normalize();

            projectile2.setDeltaMovement(angle.scale(projectile2.getDeltaMovement().length() * 1.1f));
            projectile2.hasImpulse = true;
        };
    }
}
