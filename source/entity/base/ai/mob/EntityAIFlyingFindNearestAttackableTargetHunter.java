package net.tslat.aoa3.entity.base.ai.mob;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityAIFlyingFindNearestAttackableTargetHunter<T extends EntityLivingBase> extends EntityAIBase {
	private final EntityLiving mob;
	private final Predicate<? super T> targetSelectorPredicate;
	private final EntityAINearestAttackableTarget.Sorter sorter;
	private T selectedTarget;
	private final Class <? extends T> targetClass;
	private final int hunterLvl;

	public EntityAIFlyingFindNearestAttackableTargetHunter(EntityLiving mob, Class<T> targetClass) {
		this(mob, targetClass, null);
	}

	public EntityAIFlyingFindNearestAttackableTargetHunter(EntityLiving mob, Class<T> targetClass, @Nullable final com.google.common.base.Predicate<? super T> targetSelector) {
		setMutexBits(1);

		this.mob = mob;
		this.targetClass = targetClass;
		this.sorter = new EntityAINearestAttackableTarget.Sorter(mob);
		this.hunterLvl = HunterUtil.getHunterLevel(mob);
		this.targetSelectorPredicate = (com.google.common.base.Predicate<T>)input -> {
			if (input == null || (targetSelector != null && !targetSelector.apply(input)))
				return false;

			if (input instanceof EntityPlayer) {
				EntityPlayer pl = (EntityPlayer)input;

				if (!PredicateUtil.IS_VULNERABLE_PLAYER.apply(pl))
					return false;

				if (hunterLvl > 0 && !PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, hunterLvl))
					return false;

				return isSuitableTarget(input, true);
			}
			else if (hunterLvl > 0 && input instanceof EntityTameable) {
				EntityPlayer pl = (EntityPlayer)((EntityTameable)input).getOwner();

				if (pl != null && !PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, hunterLvl))
					return false;
			}

			return isSuitableTarget(input, false);
		};
	}

	@Override
	public boolean shouldExecute() {
		double targetingRange = getTargetingRange();
		List<T> targetList = mob.world.<T>getEntitiesWithinAABB(targetClass, mob.getEntityBoundingBox().grow(targetingRange, 4, targetingRange), targetSelectorPredicate);

		if (!targetList.isEmpty()) {
			targetList.sort(sorter);

			selectedTarget = targetList.get(0);

			return true;
		}

		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase target = mob.getAttackTarget();

		if (target == null || !target.isEntityAlive())
			return false;

		if (target instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)target;

			if (pl.isCreative() || pl.isSpectator() || pl.capabilities.disableDamage)
				return false;
		}

		if (target.getTeam() != null && target.getTeam() == mob.getTeam())
			return false;

		double targetingRange = getTargetingRange();

		return !(mob.getDistanceSq(target) > targetingRange * targetingRange);
	}

	@Override
	public void startExecuting() {
		mob.setAttackTarget(selectedTarget);
	}

	@Override
	public void resetTask() {
		mob.setAttackTarget(null);
	}

	private boolean isSuitableTarget(EntityLivingBase target, boolean isPlayer) {
		if (target.getTeam() != null && target.getTeam() == mob.getTeam())
			return false;

		double targetingRange = getTargetingRange();

		if (target.isSneaking())
			targetingRange *= 0.8;

		if (target.isInvisible()) {
			if (!isPlayer)
				return false;

			targetingRange *= 0.7 * Math.max(0.1, ((EntityPlayer)target).getArmorVisibility());
		}

		return !(target.getDistance(mob) > targetingRange) && EntityAITarget.isSuitableTarget(mob, target, false, true);
	}

	private double getTargetingRange() {
		IAttributeInstance followRange = mob.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);

		return followRange == null ? 16 : followRange.getAttributeValue();
	}
}
