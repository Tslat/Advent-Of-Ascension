package net.tslat.aoa3.entity.projectile.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.WorldUtil;

public class LuxonSticklerStuckEntity extends ThrowableEntity {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;

	public LuxonSticklerStuckEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public LuxonSticklerStuckEntity(World world) {
		super(AoAEntities.Projectiles.LUXON_STICKLER_STUCK.get(), world);
	}

	public LuxonSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAEntities.Projectiles.LUXON_STICKLER_STUCK.get(), shooter.world);
		this.target = target;
		this.shooter = shooter;
		setLocationAndAngles(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public LuxonSticklerStuckEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LUXON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	protected void onImpact(RayTraceResult result) {}

	@Override
	public void tick() {
		super.tick();

		age++;

		if (world.isRemote)
			return;

		if (target != null && target.isAlive()) {
			setLocationAndAngles(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(owner, world, this, 2.0f);

			if (!world.isRemote)
				remove();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(owner, world, getPosX(), getPosY() + 1, getPosZ(), 2.0f);

			if (!world.isRemote)
				remove();

			return;
		}

		if (world.getGameTime() % 40 == 0)
			EntityUtil.applyPotions(world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(7), EntityUtil.Predicates.HOSTILE_MOB), new PotionUtil.EffectBuilder(Effects.GLOWING, 45));
	}

	@Override
	protected void registerData() {}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
