package net.tslat.aoa3.entity.projectiles.thrown;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.misc.EntityHellfireProjectile;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ModUtil;

public class EntityHellfire extends BaseBullet implements HardProjectile {
	private float explosionStrength = 1.5f;
	private EntityLivingBase shooter;
	private BaseGun gun;

	public EntityHellfire(World world) {
		super(world);
	}

	public EntityHellfire(EntityLivingBase shooter, BaseGun gun) {
		super(shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
		this.gun = gun;
	}

	public EntityHellfire(EntityLivingBase shooter, BaseGun gun, EnumHand hand, int maxAge, int piercingValue) {
		super(shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public EntityHellfire(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.075f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	public void doImpactEffect() {
		int count = 0;

		for (EntityMob e : world.getEntitiesWithinAABB(EntityMob.class, getEntityBoundingBox().grow(7.0D))) {
			world.spawnEntity(new EntityHellfireProjectile(this, e.posX, e.posY, e.posZ));
			e.setFire(10);
			count++;
		}

		if (shooter instanceof EntityPlayer) {
			world.playSound(null, posX, posY, posZ, SoundsRegister.hellfireImpact, SoundCategory.PLAYERS, 1.0f, 1.0f);

			if (count >= 20 && shooter instanceof EntityPlayerMP)
				ModUtil.completeAdvancement((EntityPlayerMP)shooter, "overworld/heckfire", "20_target_hellfire");
		}
	}
}
