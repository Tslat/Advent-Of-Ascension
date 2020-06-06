package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityFleshEater extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1f;

	public EntityFleshEater(World world) {
		super(world, entityWidth, 1.25f);

		mobProperties.add(Enums.MobProperties.STATUS_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 95;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_FLESH_EATER_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_FLESH_EATER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_FLESH_EATER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityFleshEater;
	}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 160, 15, true, false));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
