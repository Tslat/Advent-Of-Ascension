package net.tslat.aoa3.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
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
import net.tslat.aoa3.util.WorldUtil;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class GrenadeEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	private float explosionStrength = 1.5f;

	public GrenadeEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public GrenadeEntity(World world) {
		super(AoAEntities.Projectiles.GRENADE.get(), world);
	}

	public GrenadeEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.GRENADE.get(), shooter, gun, 1.0f, 0, 1.5f);
	}

	public GrenadeEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.GRENADE.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public GrenadeEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GRENADE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doImpactEffect() {
		WorldUtil.createExplosion(getOwner(), level, this, explosionStrength);
	}

	@Override
	public void doEntityImpact(Entity target) {
		WorldUtil.createExplosion(getOwner(), level, this, explosionStrength);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.GRENADE.get());
	}
}
