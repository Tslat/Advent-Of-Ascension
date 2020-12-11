package net.tslat.aoa3.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.thrown.Vulkram;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class VulkramEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	public VulkramEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public VulkramEntity(World world) {
		super(AoAEntities.Projectiles.VULKRAM.get(), world);
	}

	public VulkramEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.VULKRAM.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public VulkramEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.VULKRAM.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public VulkramEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.VULKRAM.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && ticksExisted <= 1 && getThrower() == null)
			return;

		super.onImpact(result);
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.attackEntityFrom(DamageSource.causeThrownDamage(this, null), Vulkram.dmg);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.VULKRAM.get());
	}
}
