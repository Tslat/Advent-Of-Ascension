package net.tslat.aoa3.client.model.entity.boss;

import net.minecraft.world.phys.Vec2;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.boss.nethengeic_wither.NethengeicWitherEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class NethengeicWitherModel extends DefaultedEntityGeoModel<NethengeicWitherEntity> {
	private Vec2 defaultSecondHeadRot = Vec2.ZERO;
	private Vec2 defaultThirdHeadRot = Vec2.ZERO;

	public NethengeicWitherModel() {
		super(AdventOfAscension.id("boss/nethengeic_wither/nethengeic_wither"), true);
	}

	@Override
	public void setCustomAnimations(NethengeicWitherEntity animatable, long instanceId, AnimationState<NethengeicWitherEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

		/*CoreGeoBone headLeft = getAnimationProcessor().getBone("head_left");
		CoreGeoBone headRight = getAnimationProcessor().getBone("head_right");

		if (headLeft != null) {
			if (this.defaultSecondHeadRot == Vec2.ZERO)
				this.defaultSecondHeadRot = new Vec2(headLeft.getRotX(), headLeft.getRotY());

			int secondTargetId = NethengeicWitherEntity.SECOND_HEAD_TARGET.get(animatable);
			Entity target;

			if (secondTargetId >= 0 && (target = animatable.level.getEntity(secondTargetId)) != null) {
				Vec3 angleToTarget = target.getEyePosition().subtract(PositionAndMotionUtil.moveRelativeToFacing(animatable.getEyePosition(), animatable.getYRot(), -1.25f, 0, -0.25f));

				headLeft.setRotX((float)Mth.atan2(angleToTarget.z, angleToTarget.x) - 90);
				headLeft.setRotY((float)-Mth.atan2(angleToTarget.y, Math.sqrt(angleToTarget.x * angleToTarget.x + angleToTarget.z * angleToTarget.z)));
			}
		}

		if (headRight != null) {
			if (this.defaultThirdHeadRot == Vec2.ZERO)
				this.defaultThirdHeadRot = new Vec2(headRight.getRotX(), headRight.getRotY());

			int thirdTargetId = NethengeicWitherEntity.THIRD_HEAD_TARGET.get(animatable);
			Entity target;

			if (thirdTargetId >= 0 && (target = animatable.level.getEntity(thirdTargetId)) != null) {
				Vec3 angleToTarget = target.getEyePosition().subtract(PositionAndMotionUtil.moveRelativeToFacing(animatable.getEyePosition(), animatable.getYRot(), 1.25f, 0, -0.25f));

				headRight.setRotX((float)Mth.atan2(angleToTarget.z, angleToTarget.x) - 90);
				headRight.setRotY((float)-Mth.atan2(angleToTarget.y, Math.sqrt(angleToTarget.x * angleToTarget.x + angleToTarget.z * angleToTarget.z)));
			}
		}*/
	}
}
