package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityShadowStalker extends AoAMinion {
	public static final float entityWidth = 1.125f;

	public EntityShadowStalker(World world, EntityLivingBase caster) {
		this(world);

		if (caster instanceof EntityPlayer)
			setTamedBy((EntityPlayer)caster);

		setPosition(caster.posX, caster.posY, caster.posZ);
	}

	public EntityShadowStalker(final World world){
		super(world, 250, entityWidth, 1.59375f);
	}

	@Override
	public float getEyeHeight() {
		return 0.9375f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25.0d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_DISTORTER_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_DISTORTER_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_DISTORTER_DEATH;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityShadowStalker;
	}
}
