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
import net.tslat.aoa3.util.WorldUtil;

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
		if (!world.isRemote && WorldUtil.checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS) && world.isAirBlock(getPosition())) {
			int i = 1;

			while (world.getBlockState(getPosition().down(i)).getMaterial().isReplaceable() && getPosition().getY() - i >= 0) {
				i++;
			}

			if (getPosition().getY() - i <= 0)
				return;

			/*if (world.provider instanceof AoAWorldProvider && !((AoAWorldProvider)world.provider).canPlaceBlock(owner instanceof PlayerEntity ? (PlayerEntity)owner : null, getPosition(), Blocks.COBBLESTONE.getDefaultState()))
				return;*/

			world.setBlockState(getPosition().down(i - 1), Blocks.COBBLESTONE.getDefaultState());
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}
}
