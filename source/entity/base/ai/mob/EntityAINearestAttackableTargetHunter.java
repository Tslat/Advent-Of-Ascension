package net.tslat.aoa3.entity.base.ai.mob;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityAINearestAttackableTargetHunter<T extends EntityLivingBase> extends EntityAITarget {
	private final Class<T> targetClass;
	private final int targetChance;
	private final EntityAINearestAttackableTarget.Sorter sorter;
	private final Predicate<? super T> targetSelectorPredicate;
	private T selectedTarget;
	private final int hunterLvl;

	public EntityAINearestAttackableTargetHunter(EntityCreature mob, Class<T> classTarget, boolean checkSight) {
		this(mob, classTarget, checkSight, false);
	}

	public EntityAINearestAttackableTargetHunter(EntityCreature mob, Class<T> classTarget, boolean checkSight, boolean onlyNearby) {
		this(mob, classTarget, 10, checkSight, onlyNearby, null);
	}

	public EntityAINearestAttackableTargetHunter(EntityCreature mob, Class<T> classTarget, int chance, boolean checkSight, boolean onlyNearby, @Nullable final Predicate <? super T > targetSelector) {
		super(mob, checkSight, onlyNearby);

		this.setMutexBits(1);

		this.targetClass = classTarget;
		this.targetChance = chance;
		this.sorter = new EntityAINearestAttackableTarget.Sorter(mob);
		this.hunterLvl = HunterUtil.getHunterLevel(mob);
		this.targetSelectorPredicate = (Predicate<T>)input -> {
			if (input == null || (targetSelector != null && !targetSelector.apply(input)))
				return false;

			if (input instanceof EntityPlayer) {
				EntityPlayer pl = (EntityPlayer)input;

				if (!PredicateUtil.IS_VULNERABLE_PLAYER.apply(pl))
					return false;

				if (hunterLvl > 0 && !PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, hunterLvl))
					return false;

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
		if (targetChance > 0 && taskOwner.getRNG().nextInt(targetChance) != 0)
			return false;

		if (targetClass == EntityPlayer.class || targetClass == EntityPlayerMP.class) {
			selectedTarget = (T)taskOwner.world.getNearestAttackablePlayer(taskOwner.posX, taskOwner.posY + taskOwner.getEyeHeight(), taskOwner.posZ, getTargetDistance(), getTargetDistance(), new Function<EntityPlayer, Double>() {
				@Nullable
				@Override
				public Double apply(@Nullable EntityPlayer input) {
					return 1.0;
				}
			}, (Predicate<EntityPlayer>)targetSelectorPredicate);

			return selectedTarget != null;
		}
		else {
			List<T> targetList = taskOwner.world.<T>getEntitiesWithinAABB(targetClass, taskOwner.getEntityBoundingBox().grow(getTargetDistance(), 4, getTargetDistance()), targetSelectorPredicate);

			if (!targetList.isEmpty()) {
				targetList.sort(sorter);

				selectedTarget = targetList.get(0);

				return true;
			}

			return false;
		}
	}

	@Override
	public void startExecuting() {
		taskOwner.setAttackTarget(selectedTarget);

		super.startExecuting();
	}
}
