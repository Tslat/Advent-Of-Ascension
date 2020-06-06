package net.tslat.aoa3.entity.mobs.haven;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityAutomaton extends AoAMeleeMob {
	private static final DataParameter<Byte> COLOUR = EntityDataManager.<Byte>createKey(EntityAutomaton.class, DataSerializers.BYTE);
	public static final float entityWidth = 1.125f;

	public EntityAutomaton(World world) {
		super(world, entityWidth, 2.125f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(COLOUR, (byte)rand.nextInt(5));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed() && minion.isGlowing()));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10, true, false, (Predicate<EntityPlayer>)player -> player.isPotionActive(MobEffects.GLOWING)));
	}

	@Override
	public float getEyeHeight() {
		return 1.84375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 108;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25d;
	}

	@Override
	protected double getBaseArmour() {
		return 13;
	}

	public int getColour() {
		return (int)dataManager.get(COLOUR);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_AUTOMATON_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_AUTOMATON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_AUTOMATON_HIT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.MOB_GOLEM_STEP;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityAutomaton;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			target.addVelocity(motionX * 2 * resist, motionY * 0.5 * resist, motionZ * 2 * resist);
			target.velocityChanged = true;
		}
	}
}
