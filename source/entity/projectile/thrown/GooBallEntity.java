package net.tslat.aoa3.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class GooBallEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	public GooBallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public GooBallEntity(World world) {
		super(AoAEntities.Projectiles.GOO_BALL.get(), world);
	}

	public GooBallEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.GOO_BALL.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public GooBallEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.GOO_BALL.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public GooBallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.GOO_BALL.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.hurt(DamageSource.thrown(this, null), (float)AoAWeapons.GOO_BALL.get().getDamage());

		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 60).level(2));

		level.playSound(null, getX(), getY(), getZ(), AoASounds.GOO_BALL_IMPACT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.GOO_BALL.get());
	}
}
