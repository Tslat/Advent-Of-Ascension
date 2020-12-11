package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class HeavyGrenadeEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	public HeavyGrenadeEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public HeavyGrenadeEntity(World world) {
		super(AoAEntities.Projectiles.HEAVY_GRENADE.get(), world);
	}

	public HeavyGrenadeEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HEAVY_GRENADE.get(), shooter, gun, hand, maxAge, 1, piercingValue);
	}

	public HeavyGrenadeEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HEAVY_GRENADE.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.GRENADE.get());
	}
}
