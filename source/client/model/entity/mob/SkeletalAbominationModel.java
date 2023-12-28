package net.tslat.aoa3.client.model.entity.mob;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.minecraft.util.Mth;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.entity.mob.precasia.SkeletalAbominationEntity;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationProcessor;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;

public class SkeletalAbominationModel extends MultiStageHeadModel<SkeletalAbominationEntity> {
	public SkeletalAbominationModel() {
		super(AdventOfAscension.id("mob/precasia/skeletal_abomination"));

		withStages(
				new Stage("neck2", FloatFloatPair.of(-60f, 10), FloatFloatPair.of(-20, 20)),
				new Stage("neck1", FloatFloatPair.of(-12.5f, 27.5f), FloatFloatPair.of(-20, 20))
		);
	}

	@Override
	public void setCustomAnimations(SkeletalAbominationEntity animatable, long instanceId, AnimationState<SkeletalAbominationEntity> animationState) {
		final AnimationProcessor<SkeletalAbominationEntity> processor = getAnimationProcessor();
		final EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
		float yaw = entityData.netHeadYaw();

		if (true)
			return;

		for (Stage stage : this.stages) {
			CoreGeoBone bone = processor.getBone(stage.boneName());

			if (bone == null) {
				Logging.logMessage(Level.ERROR, "Invalid bone name " + stage.boneName() + " for multi-stage head model");

				return;
			}

			//float boneYaw = Mth.clamp(yaw, stage.yawBounds().leftFloat(), stage.yawBounds().rightFloat());
			float boneYaw = Mth.clamp(yaw, -9999999, 9999999);
			yaw -= boneYaw * Math.signum(boneYaw);

			bone.setRotX(Math.abs(boneYaw) * -Mth.DEG_TO_RAD * 0.5f);
			bone.setRotY(boneYaw * Mth.DEG_TO_RAD * 0.65f);
			bone.setRotZ(boneYaw * -Mth.DEG_TO_RAD * 0.65f);
		}
	}
}
