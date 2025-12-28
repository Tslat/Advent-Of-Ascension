package net.tslat.aoa3.content.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;

public abstract class AoAMeleeMob<T extends AoAMeleeMob<T>> extends AoAMonster<T> {
	protected double attackReach;

	protected AoAMeleeMob(EntityType<? extends AoAMeleeMob> entityType, Level world) {
		super(entityType, world);

		this.attackReach = DEFAULT_ATTACK_REACH + (getEyeHeight() / 3.6d * 0.25d);
	}

	@Override
	public int calculateKillXp() {
  		return !this.hasDrops ? 0 : (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 1.5f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);
	}

	@Override
	public BrainActivityGroup<? extends T> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetWalkTargetToAttackTarget<>(),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
	}

	protected double getAttackReach() {
		return this.attackReach;
	}

	@Override
	protected AABB getAttackBoundingBox() {
		AABB boundingBox = getBoundingBox();
		double reach = getAttackReach();

		if (getVehicle() != null) {
			AABB vehicleBounds = getVehicle().getBoundingBox();
			boundingBox = new AABB(
					Math.min(boundingBox.minX, vehicleBounds.minX),
					boundingBox.minY,
					Math.min(boundingBox.minZ, vehicleBounds.minZ),
					Math.max(boundingBox.maxX, vehicleBounds.maxX),
					boundingBox.maxY,
					Math.max(boundingBox.maxZ, vehicleBounds.maxZ)
			);
		}

		return boundingBox.inflate(reach, 0, reach);
	}
}
