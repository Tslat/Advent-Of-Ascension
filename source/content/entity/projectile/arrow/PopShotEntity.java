package net.tslat.aoa3.content.entity.projectile.arrow;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.weapon.bow.BaseBow;

public class PopShotEntity extends CustomArrowEntity {
	public final boolean isExplosive;

	public PopShotEntity(EntityType<? extends Arrow> entityType, Level world) {
		super(entityType, world);

		this.isExplosive = true;
	}

	public PopShotEntity(Level world) {
		super(AoAProjectiles.POP_SHOT.get(), world);

		this.isExplosive = true;
	}

	public PopShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.POP_SHOT.get(), world);

		this.isExplosive = true;

		setPos(x, y, z);
	}

	public PopShotEntity(Level world, BaseBow bow, LivingEntity shooter, double damageBase) {
		super(AoAProjectiles.POP_SHOT.get(), world);

		this.isExplosive = true;

		setOwner(shooter);
		setBaseDamage(damageBase);

		this.bow = bow;

		setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
	}

	public PopShotEntity(Level world, BaseBow bow, LivingEntity shooter, double damageBase, boolean isExplosive) {
		super(AoAProjectiles.POP_SHOT.get(), world);

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
