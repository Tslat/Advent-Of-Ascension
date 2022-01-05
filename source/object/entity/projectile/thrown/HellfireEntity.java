package net.tslat.aoa3.object.entity.projectile.thrown;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.object.entity.projectile.HardProjectile;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.entity.projectile.misc.HellfireProjectileEntity;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.EntityUtil;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class HellfireEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	private float explosionStrength = 1.5f;
	private LivingEntity shooter;
	private BaseGun gun;

	public HellfireEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public HellfireEntity(World world) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), world);
	}

	public HellfireEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
		this.gun = gun;
	}

	public HellfireEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HellfireEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), world, x, y, z);
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
		int count = 0;

		for (LivingEntity e : level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(7.0D), EntityUtil.Predicates.HOSTILE_MOB)) {
			level.addFreshEntity(new HellfireProjectileEntity(this, e.getX(), e.getY(), e.getZ()));
			e.setSecondsOnFire(10);
			count++;
		}

		if (shooter instanceof PlayerEntity) {
			level.playSound(null, getX(), getY(), getZ(), AoASounds.HELLFIRE_IMPACT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			if (count >= 20 && shooter instanceof ServerPlayerEntity)
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/heckfire"), "20_target_hellfire");
		}
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.HELLFIRE.get());
	}
}
