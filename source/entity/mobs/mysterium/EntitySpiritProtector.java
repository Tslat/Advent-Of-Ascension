package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntitySpiritualShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntitySpiritProtector extends AoARangedMob {
	public static final float entityWidth = 0.6f;

	public EntitySpiritProtector(EntityEeo eeo) {
		this(eeo.world);

		setLocationAndAngles(eeo.posX, eeo.posY, eeo.posZ, eeo.rotationYaw, eeo.rotationPitch);
	}

	public EntitySpiritProtector(World world) {
		super(world, entityWidth, 1.8125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.15f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 11.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotSpiritProtectorFire;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entitySpiritProtector;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntitySpiritualShot(this, Enums.MobProjectileType.MAGIC);
	}
}
