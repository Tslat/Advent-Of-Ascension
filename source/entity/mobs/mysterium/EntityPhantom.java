package net.tslat.aoa3.entity.mobs.mysterium;

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

public class EntityPhantom extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	public EntityPhantom(World world) {
		super(world, entityWidth, 2.0f);

		mobProperties.add(Enums.MobProperties.STATUS_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.71875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 74d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.27d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobPhantomLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobPhantomHit;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobPhantomHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPhantom;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 130, 1, false, true));

			if (rand.nextInt(6) == 0) {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 130, 2, false, true));
				addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 80, 0, false, true));
			}
		}
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
