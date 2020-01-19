package net.tslat.aoa3.entity.mobs.overworld.soulscurry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityGhostlySasquatch extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	public EntityGhostlySasquatch(World world) {
		super(world, entityWidth, 2f);

		mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSasquatchLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobYetiDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobYetiHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityGhostlySasquatch;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.consumeResource((EntityPlayer)target, Enums.Resources.SOUL, 10f, true);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isMeleeDamage(source) || EntityUtil.isRangedDamage(source, this, damage);
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.SOUL_SCURRY;
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
