package net.tslat.aoa3.content.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.tme.api.object.RayTrace;

public interface ProjectileFiringWeapon {
	ProjectileFiringWeapon DEFAULT = new ProjectileFiringWeapon() {};

	default boolean doBlockImpact(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		return true;
	}

	default boolean doEntityImpact(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity) {
		return true;
	}

	default float getForwardOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return 1.5f;
	}

	default float getRightOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return 0.4f;
	}

	default float getEyeHeightOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return -0.2f;
	}
}
