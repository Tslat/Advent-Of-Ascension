package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.Vec3;

public class ParryStunlockTask<E extends Mob> extends Behavior<E> {
	private int lastHitTime = 0;
	private int stunCount = 0;
	private int blockTime = 0;

	public ParryStunlockTask() {
		super(ImmutableMap.of(MemoryModuleType.HURT_BY_ENTITY, MemoryStatus.VALUE_PRESENT), 200);
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E owner) {
		int lastHitTime = owner.getLastHurtByMobTimestamp();

		return lastHitTime != 0 && lastHitTime >= owner.tickCount - 60;
	}

	@Override
	protected void start(ServerLevel level, E owner, long gameTime) {
		lastHitTime = owner.getLastHurtByMobTimestamp();
		stunCount = 1;
	}

	@Override
	protected void tick(ServerLevel level, E owner, long gameTime) {
		if (owner.isBlocking()) {
			blockTime++;

			LivingEntity target = owner.getLastHurtByMob();

			if (blockTime >= 5) {
				if (!owner.getBrain().hasMemoryValue(MemoryModuleType.LOOK_TARGET))
					BehaviorUtils.lookAtEntity(owner, target);

				if (BehaviorUtils.isWithinMeleeAttackRange(owner, target)) {
					owner.swing(InteractionHand.MAIN_HAND);
					owner.doHurtTarget(target);
					doStop(level, owner, gameTime);
				}
				else if (owner.isOnGround()) {
					Vec3 lungeVector = new Vec3(target.getX() - owner.getX(), 0, target.getZ() - owner.getZ());

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
			if (!(owner.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof ShieldItem))
				owner.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.SHIELD));

			owner.startUsingItem(InteractionHand.OFF_HAND);
		}
	}

	@Override
	protected boolean canStillUse(ServerLevel pLevel, E owner, long pGameTime) {
		return lastHitTime > owner.tickCount - 60 && owner.getLastHurtByMob() != null;
	}

	@Override
	protected void stop(ServerLevel pLevel, E owner, long pGameTime) {
		stunCount = 0;
		lastHitTime = 0;
		blockTime = 0;

		if (owner.isUsingItem())
			owner.stopUsingItem();
	}
}
