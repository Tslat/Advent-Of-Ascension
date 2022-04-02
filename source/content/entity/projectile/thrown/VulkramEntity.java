package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class VulkramEntity extends BaseBullet implements HardProjectile, ItemSupplier {
	public VulkramEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public VulkramEntity(Level world) {
		super(AoAProjectiles.VULKRAM.get(), world);
	}

	public VulkramEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAProjectiles.VULKRAM.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public VulkramEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.VULKRAM.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public VulkramEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.VULKRAM.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.hurt(DamageSource.thrown(this, null), (float)AoAWeapons.VULKRAM.get().getDamage());
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.VULKRAM.get());
	}
}
