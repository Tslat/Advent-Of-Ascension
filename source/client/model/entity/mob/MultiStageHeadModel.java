package net.tslat.aoa3.client.model.entity.mob;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationProcessor;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MultiStageHeadModel<T extends GeoAnimatable> extends DefaultedEntityGeoModel<T> {
	private Stage[] stages = new Stage[0];

	public MultiStageHeadModel(ResourceLocation assetSubpath) {
		super(assetSubpath);
	}

	public MultiStageHeadModel(ResourceLocation assetSubpath, boolean turnsHead) {
		super(assetSubpath, turnsHead);
	}

	public MultiStageHeadModel<T> withStages(Stage... stages) {
		this.stages = stages;

		return this;
	}

	@Override
	public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
		final AnimationProcessor<T> processor = getAnimationProcessor();
		final EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
		float pitch = entityData.headPitch();
		float yaw = entityData.netHeadYaw();

		for (Stage stage : this.stages) {
			CoreGeoBone bone = processor.getBone(stage.boneName);

			if (bone == null) {
				Logging.logMessage(Level.ERROR, "Invalid bone name " + stage.boneName + " for multi-stage head model");

				return;
			}

			float bonePitch = Mth.clamp(pitch, stage.pitchBounds.leftFloat(), stage.pitchBounds.rightFloat());
			float boneYaw = Mth.clamp(yaw, stage.yawBounds.leftFloat(), stage.yawBounds.rightFloat());
			pitch -= bonePitch * Math.signum(bonePitch);
			yaw -= boneYaw * Math.signum(boneYaw);

			bone.setRotX(bonePitch * Mth.DEG_TO_RAD);
			bone.setRotY(boneYaw * Mth.DEG_TO_RAD);
		}
	}

	public record Stage(String boneName, FloatFloatPair pitchBounds, FloatFloatPair yawBounds) {}
}
