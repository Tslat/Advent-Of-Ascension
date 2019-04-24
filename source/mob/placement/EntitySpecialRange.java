package net.nevermine.mob.placement;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;

public interface EntitySpecialRange {
	boolean canDamage(IProjectile projectile, EntityLivingBase target, Entity shooter, float dmg);
}
