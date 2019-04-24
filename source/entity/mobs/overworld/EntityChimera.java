package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityChimera extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1f;

	public EntityChimera(World world) {
		super(world, entityWidth, 1.375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.05f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobChimeraLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobChimeraDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobChimeraHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(125 - lootingMod) == 0)
			dropItem(WeaponRegister.sniperHeadHunter, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		int amount = 1 + rand.nextInt(2 + lootingMod);

		if (source.isFireDamage() || this.isBurning()) {
			dropItem(ItemRegister.chimeraChop, amount);
		}
		else {
			dropItem(ItemRegister.chimeraChopRaw, amount);
		}
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 240, 2, true, true));

			return true;
		}

		return false;
	}

	@Override
	public int getHunterReq() {
		return 12;
	}

	@Override
	public float getHunterXp() {
		return 16;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
