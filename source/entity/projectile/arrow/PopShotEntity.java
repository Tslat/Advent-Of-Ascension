package net.tslat.aoa3.entity.projectile.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class PopShotEntity extends CustomArrowEntity {
	public final boolean isExplosive;

	public PopShotEntity(EntityType<? extends ArrowEntity> entityType, World world) {
		super(entityType, world);

		this.isExplosive = true;
	}

	public PopShotEntity(World world) {
		super(AoAEntities.Projectiles.POP_SHOT.get(), world);

		this.isExplosive = true;
	}

	public PopShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.POP_SHOT.get(), world);

		this.isExplosive = true;

		setPosition(x, y, z);
	}

	public PopShotEntity(World world, BaseBow bow, LivingEntity shooter, double damageBase) {
		super(AoAEntities.Projectiles.POP_SHOT.get(), world);

		this.isExplosive = true;

		setShooter(shooter);
		setDamage(damageBase);

		this.bow = bow;

		setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1f, shooter.getPosZ());
	}

	public PopShotEntity(World world, BaseBow bow, LivingEntity shooter, double damageBase, boolean isExplosive) {
		super(AoAEntities.Projectiles.POP_SHOT.get(), world);

		setShooter(shooter);
		setDamage(damageBase);

		this.bow = bow;
		this.isExplosive = isExplosive;

		setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1f, shooter.getPosZ());
	}

	protected void arrowHit(LivingEntity target) {}

	protected ItemStack getArrowStack() {
		return new ItemStack(AoAItems.POP_SHOT.get());
	}
}
