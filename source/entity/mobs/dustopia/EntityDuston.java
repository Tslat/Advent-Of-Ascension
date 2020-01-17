package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityDuston extends AoAFlyingMeleeMob {
	public static final float entityWidth = 0.6f;

	private int cooldown = 600;

	public EntityDuston(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 0.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 129;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDustonHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityDuston;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote && isEntityAlive() && ticksExisted % 20 == 0 && world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(15), PredicateUtil.IS_VULNERABLE_PLAYER).size() > 0) {
			cooldown -= 20;

			if (cooldown <= 0) {
				world.spawnEntity(new EntityDustStrider(this));

				cooldown = 600;
			}
		}
	}
}
