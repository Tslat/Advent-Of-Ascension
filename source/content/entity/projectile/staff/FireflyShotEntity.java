package net.tslat.aoa3.content.entity.projectile.staff;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.EntityUtil;

import java.util.List;

public class FireflyShotEntity extends NonPhysicalWeaponProjectile {
	private final List<Entity> hitEntities = new ObjectArrayList<>();

	public FireflyShotEntity(EntityType<? extends FireflyShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public FireflyShotEntity(EntityType<? extends FireflyShotEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public FireflyShotEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.FIREFLY_SHOT.get(), level, context);
	}

	public boolean shouldSplitFrom(Entity hitEntity) {
		return !this.hitEntities.contains(EntityUtil.getPartOrPartOwner(hitEntity));
	}

	@Override
	public boolean canHitEntity(Entity target) {
		return super.canHitEntity(target) && !this.hitEntities.contains(EntityUtil.getPartOrPartOwner(target));
	}

	public FireflyShotEntity splitOnImpact(Entity hitEntity) {
		FireflyShotEntity newShot = new FireflyShotEntity(level(), getShotContext());

		newShot.hitEntities.addAll(this.hitEntities);
		newShot.hitEntities.add(hitEntity);

		return newShot;
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}
}
