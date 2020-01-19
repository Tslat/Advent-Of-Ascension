package net.tslat.aoa3.entity.mobs.candyland;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityCaneBug extends AoAMeleeMob {
	public static final float entityWidth = 1f;

	public EntityCaneBug(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 0.53125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.2f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 94d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.29d;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.candyThump;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.candyThump;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCaneBug;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.4f);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
