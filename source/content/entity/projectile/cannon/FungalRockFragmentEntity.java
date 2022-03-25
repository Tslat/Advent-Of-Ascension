package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class FungalRockFragmentEntity extends BaseBullet implements HardProjectile {
	public FungalRockFragmentEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public FungalRockFragmentEntity(World world) {
		super(AoAEntities.Projectiles.FUNGAL_ROCK_FRAGMENT.get(), world);
	}

	public FungalRockFragmentEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.FUNGAL_ROCK_FRAGMENT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public FungalRockFragmentEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FUNGAL_ROCK_FRAGMENT.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect() {
		if (!level.isClientSide && AoAGameRules.checkDestructiveWeaponPhysics(level) && level.isEmptyBlock(blockPosition())) {
			int i = 1;

			while (level.getBlockState(blockPosition().below(i)).getMaterial().isReplaceable() && blockPosition().getY() - i >= 0) {
				i++;
			}

			if (blockPosition().getY() - i <= 0)
				return;

			Entity shooter = getOwner();

			if (!WorldUtil.canPlaceBlock(level, blockPosition(), shooter instanceof PlayerEntity ? shooter : null, null))
				return;

			level.setBlockAndUpdate(blockPosition().below(i - 1), Blocks.COBBLESTONE.defaultBlockState());
		}
	}

	@Override
	protected float getGravity() {
		return 0.06f;
	}
}
