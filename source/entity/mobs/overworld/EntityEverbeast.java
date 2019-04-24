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
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityEverbeast extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.4f;

	public EntityEverbeast(World world) {
		super(world, entityWidth, 1.75f);
		this.mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.65f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 24;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobEverbeastLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobEverbeastHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobEverbeastHit;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 25 && super.getCanSpawnHere();
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isMeleeDamage(source);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.amethyst, 4 + rand.nextInt(1 + lootingMod));

		if (rand.nextBoolean())
			dropItem(ItemRegister.ingotLimonite, 6 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(5) == 0)
			dropItem(ItemRegister.sapphire, 2 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(4) == 0)
			dropItem(ItemRegister.ingotRosite, 4 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.jade, 3 + rand.nextInt(1 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 140, 14, true, true));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}
}
