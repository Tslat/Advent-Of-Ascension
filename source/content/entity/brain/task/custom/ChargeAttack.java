package net.tslat.aoa3.content.entity.brain.task.custom;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.core.behaviour.DelayedBehaviour;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import java.util.List;

/**
 * Special attack for tackle-charging directly at the target at speed. <br>
 * Defaults:
 * <ul>
 *     <li>1x movespeed modifier</li>
 *     <li>Target is fixed at time of task start</li>
 * </ul>
 * @param <E> This
 */
public class ChargeAttack<E extends PathfinderMob> extends DelayedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED));

	protected float speedMod = 1;

	private boolean targetWhenCharging = false;
	private Vec3 chargeVelocity;

	public ChargeAttack(int delayTicks) {
		super(delayTicks);
	}

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	/**
	 * Set the movespeed modifier for when charging.
	 * @param speedMod The speed modifier/multiplier
	 * @return this
	 */
	public ChargeAttack<E> speedModifier(float speedMod) {
		this.speedMod = speedMod;

		return this;
	}

	/**
	 * Recalculate the target position and vector at the time the charge starts. This makes for a more aggressive charge.
	 * @return this
	 */
	public ChargeAttack<E> targetWhenCharging() {
		this.targetWhenCharging = true;

		return this;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		return Math.abs(BrainUtils.getTargetOfEntity(entity).getY() - entity.getY()) < 2;
	}

	@Override
	protected void start(E entity) {
		calculateTarget(entity);
		BrainUtils.clearMemories(entity, MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.PATH);
		entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0);
	}

	public void calculateTarget(E entity) {
		LivingEntity target = BrainUtils.getTargetOfEntity(entity);

		if (target == null)
			return;

		double moveSpeed = Math.max(entity.getSpeed(), entity.getAttributeValue(Attributes.MOVEMENT_SPEED)) * 2;
		Vec3 targetPos = new Vec3(target.getX(0.5f), 0, target.getZ(0.5f));
		Vec3 entityPos = new Vec3(entity.getX(0.5f), 0, entity.getZ(0.5f));
		this.chargeVelocity = targetPos.subtract(entityPos).normalize().multiply(moveSpeed, 0, moveSpeed).subtract(0, entity.getAttributeValue(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get()), 0);
		this.endTimestamp = this.delayFinishedAt + 1 + (Math.max(20, (int)(entityPos.distanceTo(targetPos) / (moveSpeed * 0.25))));

		entity.lookAt(EntityAnchorArgument.Anchor.FEET, targetPos);
	}

	@Override
	protected boolean canStillUse(ServerLevel level, E entity, long gameTime) {
		return !entity.horizontalCollision;
	}

	@Override
	protected void tick(E entity) {
		if (this.delayFinishedAt <= entity.level().getGameTime()) {
			entity.setSharedFlag(3, true);
			entity.setDeltaMovement(this.chargeVelocity);
			entity.lookAt(EntityAnchorArgument.Anchor.FEET, entity.position().add(this.chargeVelocity));

			for (LivingEntity target : EntityRetrievalUtil.<LivingEntity>getEntities(entity.level(), entity.getBoundingBox().expandTowards(this.chargeVelocity), target -> target != entity && target.isAlive() && target instanceof LivingEntity && (!(target instanceof Player pl) || !pl.isCreative()))) {
				entity.doHurtTarget(target);
				EntityUtil.pushEntityAway(entity, target, 1.5f);
			}
		}
	}

	@Override
	protected void doDelayedAction(E entity) {
		if (this.targetWhenCharging)
			calculateTarget(entity);
	}

	@Override
	protected void stop(E entity) {
		entity.setSharedFlag(3, false);
	}
}
