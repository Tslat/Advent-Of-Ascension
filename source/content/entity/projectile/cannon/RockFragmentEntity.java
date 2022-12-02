package net.tslat.aoa3.content.entity.projectile.cannon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;

public class RockFragmentEntity extends BaseBullet implements HardProjectile {
	public RockFragmentEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public RockFragmentEntity(Level world) {
		super(AoAProjectiles.ROCK_FRAGMENT.get(), world);
	}

	public RockFragmentEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.ROCK_FRAGMENT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public RockFragmentEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.ROCK_FRAGMENT.get(), world, x, y, z);
	}

	@Override
	public void doImpactEffect(Vec3 impactLocation) {
		if (!level.isClientSide && AoAGameRules.checkDestructiveWeaponPhysics(level) && level.isEmptyBlock(blockPosition())) {
			int i = 1;

			while (level.getBlockState(blockPosition().below(i)).getMaterial().isReplaceable() && blockPosition().getY() - i >= 0) {
				i++;
			}

			if (blockPosition().getY() - i <= 0)
				return;

			/*if (world.provider instanceof AoAWorldProvider && !((AoAWorldProvider)world.provider).canPlaceBlock(owner instanceof Player ? (Player)owner : null, getPosition(), Blocks.COBBLESTONE.getDefaultState()))
				return;*/

			level.setBlockAndUpdate(blockPosition().below(i - 1), Blocks.COBBLESTONE.defaultBlockState());
		}
	}

	@Override
	protected float getGravity() {
		return 0.06f;
	}
}
