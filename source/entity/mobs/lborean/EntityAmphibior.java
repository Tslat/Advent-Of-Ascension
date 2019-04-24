package net.tslat.aoa3.entity.mobs.lborean;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityAmphibior extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.625f;

	public EntityAmphibior(World world) {
		super(world, entityWidth, 2.1875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 140;
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
		return SoundsRegister.mobAmphibiorLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobAmphibiorDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobAmphibiorHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(ItemRegister.waterloggedCoralArchergun, 1);

		if (rand.nextInt(25 - lootingMod) == 0)
			dropItem(ItemRegister.pureCoralStone, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerBoreic), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0)
			heal(0.2f);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote && world.provider.getDimension() != -1)
			world.setBlockState(target.getPosition(), Blocks.FLOWING_WATER.getDefaultState());
	}

	@Override
	public int getHunterReq() {
		return 73;
	}

	@Override
	public float getHunterXp() {
		return 750;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
