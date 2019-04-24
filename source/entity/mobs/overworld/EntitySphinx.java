package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntitySphinx extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.0f;

	public EntitySphinx(World world) {
		super(world, entityWidth, 1);
		this.mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 0.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSphinxLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSphinxDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSphinxHit;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isMeleeDamage(source);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(3 + lootingMod));
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
