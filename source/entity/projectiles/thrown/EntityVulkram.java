package net.tslat.aoa3.entity.projectiles.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.thrown.Vulkram;

public class EntityVulkram extends BaseBullet implements HardProjectile {
	public EntityVulkram(World world) {
		super(world);
	}

	public EntityVulkram(EntityLivingBase shooter, BaseGun gun) {
		super(shooter, gun, 1.0f, 0, 3.0f);
	}

	public EntityVulkram(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityVulkram(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.attackEntityFrom(DamageSource.causeThrownDamage(this, null), Vulkram.dmg);
	}
}
