package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityFleshEater extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1f;

	public EntityFleshEater(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFleshEaterLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFleshEaterDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFleshEaterHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensAbyss, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(19 - lootingMod) == 0)
			dropItem(WeaponRegister.swordLegbone, 1);

		if (rand.nextInt(9) == 0)
			dropItem(ItemRegister.staringEye, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
		dropItem(Items.ROTTEN_FLESH, 2 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(4) == 0)
			dropItem(ItemRegister.seedsEyeBulb, 1);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 160, 15, true, false));
	}

	@Override
	public int getHunterReq() {
		return 35;
	}

	@Override
	public float getHunterXp() {
		return 120;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
