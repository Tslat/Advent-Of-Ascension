package net.tslat.aoa3.object.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.BrainUtil;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class ParryStunlockTask<E extends MobEntity> extends Task<E> {
	private int lastHitTime = 0;
	private int stunCount = 0;
	private int blockTime = 0;

	public ParryStunlockTask() {
		super(ImmutableMap.of(MemoryModuleType.HURT_BY_ENTITY, MemoryModuleStatus.VALUE_PRESENT), 200);
	}

	@Override
	protected boolean checkExtraStartConditions(ServerWorld level, E owner) {
		int lastHitTime = owner.getLastHurtByMobTimestamp();

		return lastHitTime != 0 && lastHitTime >= owner.tickCount - 60;
	}

	@Override
	protected void start(ServerWorld level, E owner, long gameTime) {
		lastHitTime = owner.getLastHurtByMobTimestamp();
		stunCount = 1;
	}

	@Override
	protected void tick(ServerWorld level, E owner, long gameTime) {
		if (owner.isBlocking()) {
			blockTime++;

			LivingEntity target = owner.getLastHurtByMob();

			if (blockTime >= 5) {
				if (!owner.getBrain().hasMemoryValue(MemoryModuleType.LOOK_TARGET))
					BrainUtil.lookAtEntity(owner, target);

				if (BrainUtil.isWithinMeleeAttackRange(owner, target)) {
					owner.swing(Hand.MAIN_HAND);
					owner.doHurtTarget(target);
					doStop(level, owner, gameTime);
				}
				else if (owner.isOnGround()) {
					Vector3d lungeVector = new Vector3d(target.getX() - owner.getX(), 0, target.getZ() - owner.getZ());

					if (lungeVector.lengthSqr() > 1.0E-7D)
						lungeVector = lungeVector.normalize().scale(0.4d);

					owner.setDeltaMovement(lungeVector.x(), 0.1f, lungeVector.z());
				}
			}

			return;
		}

		if (lastHitTime != owner.getLastHurtByMobTimestamp()) {
			lastHitTime = owner.getLastHurtByMobTimestamp();
			stunCount++;
		}

		if (stunCount > 2) {
			if (!(owner.getItemInHand(Hand.OFF_HAND).getItem() instanceof ShieldItem))
				owner.setItemInHand(Hand.OFF_HAND, new ItemStack(Items.SHIELD));

			owner.startUsingItem(Hand.OFF_HAND);
		}
	}

	@Override
	protected boolean canStillUse(ServerWorld pLevel, E owner, long pGameTime) {
		return lastHitTime > owner.tickCount - 60 && owner.getLastHurtByMob() != null;
	}

	@Override
	protected void stop(ServerWorld pLevel, E owner, long pGameTime) {
		stunCount = 0;
		lastHitTime = 0;
		blockTime = 0;

		if (owner.isUsingItem())
			owner.stopUsingItem();
	}
}
