package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.ai.ExtendedGoal;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;

public class MoveToWaterGoal<T extends Mob> extends ExtendedGoal<T> {
	protected Vec3 targetPos = null;
	protected final double speedMod;

	public MoveToWaterGoal(T entity) {
		this(entity, 1);
	}

	public MoveToWaterGoal(T entity, double speedModifier) {
		super(entity);

		this.speedMod = speedModifier;
	}

	@Override
	public boolean canUse() {
		if (!super.canUse())
			return false;

		if (!this.entity.level.isDay() || this.entity.isInWater())
			return false;

		this.targetPos = findWaterPos();

		return this.targetPos != null;
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() && !this.entity.getNavigation().isDone();
	}

	@Override
	public void start() {
		super.start();

		this.entity.getNavigation().moveTo(this.targetPos.x(), this.targetPos.y(), this.targetPos.z(), this.speedMod);
	}

	@Nullable
	protected Vec3 findWaterPos() {
		BlockPos pos = this.entity.blockPosition();
		BlockPos targetPos = RandomUtil.getRandomPositionWithinRange(pos, 10, 6, 10, 0, 0, 0, false, this.entity.level, 10, (state, statePos) -> state.getFluidState().is(FluidTags.WATER));

		return targetPos != pos ? Vec3.atBottomCenterOf(targetPos) : null;
	}
}
