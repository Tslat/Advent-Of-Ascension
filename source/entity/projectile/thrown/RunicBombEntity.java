package net.tslat.aoa3.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.mob.mysterium.RunicGolemEntity;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.WorldUtil;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class RunicBombEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	private float explosionStrength = 1.5f;
	private LivingEntity shooter;

	public RunicBombEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public RunicBombEntity(World world) {
		super(AoAEntities.Projectiles.RUNIC_BOMB.get(), world);
	}

	public RunicBombEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.RUNIC_BOMB.get(), shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
	}

	public RunicBombEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.RUNIC_BOMB.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public RunicBombEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.RUNIC_BOMB.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.1f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && ticksExisted <= 1 && getThrower() == null)
			return;

		super.onImpact(result);
	}

	@Override
	public void doImpactEffect() {
		explode();
	}

	@Override
	public void doEntityImpact(Entity target) {
		explode();
	}

	private void explode() {
		WorldUtil.createExplosion(shooter, world, this, explosionStrength);

		for (LivingEntity e : world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(3.0D), EntityUtil.Predicates.HOSTILE_MOB)) {
			EntityUtil.applyPotions(e, new PotionUtil.EffectBuilder(Effects.SLOWNESS, 30).level(100));

			if (e instanceof RunicGolemEntity && ((RunicGolemEntity)e).isShielded())
				((RunicGolemEntity)e).deactivateShield();
		}
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.RUNIC_BOMB.get());
	}
}
