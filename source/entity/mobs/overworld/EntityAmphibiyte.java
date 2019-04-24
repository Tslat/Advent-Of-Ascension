package net.tslat.aoa3.entity.mobs.overworld;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.corallus.EntityCorallus;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityAmphibiyte extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.95f;

	public EntityAmphibiyte(World world) {
		super(world, entityWidth, 1.25f);

		if (navigator instanceof PathNavigateGround)
			((PathNavigateGround)navigator).setCanSwim(true);
	}

	public EntityAmphibiyte(World world, double posX, double posY, double posZ) {
		this(world);
		setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360, 1.0f);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3285;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98f;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobAmphibiyteLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobAmphibiyteDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobAmphibiyteHit;
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(8 + lootingMod));
		dropItem(ItemRegister.amphibiyteLung, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerSea), 1);
	}

	@Override
	public int getHunterReq() {
		return 30;
	}

	@Override
	public float getHunterXp() {
		return 25;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater()) {
			if (navigator instanceof PathNavigateGround)
				navigator = new PathNavigateSwimmer(this, world);

			if (getAttackTarget() != null && posY < getAttackTarget().posY)
				motionY = 0.25;

			fallDistance = -0.5f;
		}
		else if (navigator instanceof PathNavigateSwimmer) {
			navigator = new PathNavigateGround(this, world);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (world.provider.getDimension() == 0 && rand.nextInt(50) == 0 && cause.getTrueSource() instanceof EntityPlayer) {
			EntityCorallus corallus = new EntityCorallus(world);

			corallus.setLocationAndAngles(posX, posY + 7, posZ, rand.nextFloat() * 360f, 0);
			world.spawnEntity(corallus);

			StringUtil.sendMessageWithinRadius(StringUtil.getLocale("message.mob.corallus.spawn"), cause.getTrueSource(), 50);
		}
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
