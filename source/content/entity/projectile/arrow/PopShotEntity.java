package net.tslat.aoa3.content.entity.projectile.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.item.weapon.bow.BaseBow;

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

		setPos(x, y, z);
	}

	public PopShotEntity(World world, BaseBow bow, LivingEntity shooter, double damageBase) {
		super(AoAEntities.Projectiles.POP_SHOT.get(), world);

		this.isExplosive = true;

		setOwner(shooter);
		setBaseDamage(damageBase);

		this.bow = bow;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	public PopShotEntity(World world, BaseBow bow, LivingEntity shooter, double damageBase, boolean isExplosive) {
		super(AoAEntities.Projectiles.POP_SHOT.get(), world);

		setOwner(shooter);
		setBaseDamage(damageBase);

		this.bow = bow;
		this.isExplosive = isExplosive;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	protected void doPostHurtEffects(LivingEntity target) {}

	protected ItemStack getPickupItem() {
		return new ItemStack(AoAItems.POP_SHOT.get());
	}
}
