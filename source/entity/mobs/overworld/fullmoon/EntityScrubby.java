package net.tslat.aoa3.entity.mobs.overworld.fullmoon;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIHopAtTarget;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityScrubby extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.0f;

	public EntityScrubby(World world) {
		super(world, entityWidth, 1.125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.875f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIHopAtTarget(this, 0.9f));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0d, false));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0d));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.5227;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobScrubbyLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobScrubbyHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobScrubbyHit;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.moonstone, 1);

		if (rand.nextInt(50 - lootingMod) == 0) {
			dropItem(WeaponRegister.blasterMoonShiner, 1);
		}
		else if (rand.nextInt(10 - lootingMod) == 0) {
			dropItem(WeaponRegister.gunHotShot, 1);
		}

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerSoul), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		target.setFire(3);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.FULL_MOON;
	}

	@Override
	public int getHunterReq() {
		return 1;
	}

	@Override
	public float getHunterXp() {
		return 15;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}
}
