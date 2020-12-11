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
import net.tslat.aoa3.item.weapon.thrown.Chakram;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = IRendersAsItem.class
)
public class ChakramEntity extends BaseBullet implements HardProjectile, IRendersAsItem {
	public ChakramEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ChakramEntity(World world) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), world);
	}

	public ChakramEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public ChakramEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public ChakramEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.attackEntityFrom(DamageSource.causeThrownDamage(this, null), Chakram.dmg);

		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 60).level(2));
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result instanceof BlockRayTraceResult && ticksExisted <= 1 && getThrower() == null)
			return;

		super.onImpact(result);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.CHAKRAM.get());
	}
}
