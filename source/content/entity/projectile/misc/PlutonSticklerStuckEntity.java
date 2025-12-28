package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;

public class PlutonSticklerStuckEntity extends AttachedSticklerEntity {
	public PlutonSticklerStuckEntity(EntityType<? extends AttachedSticklerEntity> entityType, Level world) {
		super(entityType, world);
	}

	public PlutonSticklerStuckEntity(Level level, Entity shooter, LivingEntity target, Vec3 stuckOffset, WeaponFiringContext context) {
		super(AoAProjectiles.PLUTON_STICKLER_STUCK.get(), level, shooter, target, stuckOffset, context);
	}

	@Override
	protected void explode() {
		super.explode();

		for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
			for (float y = -0.5f; y <= 0.5f; y += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					ItemEntity coin = new ItemEntity(level(), getX(), getY(), getZ(), new ItemStack(AoAItems.COPPER_COIN.get()));

					coin.setPickUpDelay(120);
					coin.push(x, y, z);
					coin.lifespan = 140;
					level().addFreshEntity(coin);
				}
			}
		}
	}
}
