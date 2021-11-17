package net.tslat.aoa3.entity.projectile.cannon;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.entity.projectile.HardProjectile;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class RockFragmentEntity extends BaseBullet implements HardProjectile {
	public RockFragmentEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public RockFragmentEntity(World world) {
		super(AoAEntities.Projectiles.ROCK_FRAGMENT.get(), world);
	}

	public RockFragmentEntity(LivingEntity shooter, BaseGun gun, Hand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.ROCK_FRAGMENT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public RockFragmentEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ROCK_FRAGMENT.get(), world, x, y, z);
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

			/*if (world.provider instanceof AoAWorldProvider && !((AoAWorldProvider)world.provider).canPlaceBlock(owner instanceof PlayerEntity ? (PlayerEntity)owner : null, getPosition(), Blocks.COBBLESTONE.getDefaultState()))
				return;*/

			level.setBlockAndUpdate(blockPosition().below(i - 1), Blocks.COBBLESTONE.defaultBlockState());
		}
	}

	@Override
	protected float getGravity() {
		return 0.06f;
	}
}
