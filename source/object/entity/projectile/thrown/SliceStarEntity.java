package net.tslat.aoa3.object.entity.projectile.thrown;

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
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class SliceStarEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	public SliceStarEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public SliceStarEntity(World world) {
		super(AoAEntities.Projectiles.SLICE_STAR.get(), world);
	}

	public SliceStarEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.SLICE_STAR.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public SliceStarEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.SLICE_STAR.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public SliceStarEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.SLICE_STAR.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.hurt(DamageSource.thrown(this, null), (float)AoAWeapons.SLICE_STAR.get().getDamage());
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.SLICE_STAR.get());
	}
}
