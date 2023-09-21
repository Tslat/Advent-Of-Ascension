package net.tslat.aoa3.content.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;

public abstract class AoAMeleeMob<T extends AoAMeleeMob<T>> extends AoAMonster<T> {
	protected double attackReach;

	protected AoAMeleeMob(EntityType<? extends AoAMeleeMob> entityType, Level world) {
		super(entityType, world);

		this.attackReach = getBbWidth() + getAttackVectorPositionOffset() * 0.25f * 1.75d + (getEyeHeight() / 3.6d * 0.25d);
	}

	@Override
	public int calculateKillXp() {
  		return !this.hasDrops ? 0 : (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 1.5f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);
	}

	@Override
	public BrainActivityGroup<? extends T> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>(),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	protected float getAttackVectorPositionOffset() {
		return 0;
	}

	@Override
	public double getMeleeAttackRangeSqr(LivingEntity target) {
		double targetBBOffset = target.getBbWidth() * 0.5d;

		return this.attackReach * this.attackReach + targetBBOffset * targetBBOffset;
	}
}
