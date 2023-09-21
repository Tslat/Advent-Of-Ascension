package net.tslat.aoa3.client.model.entity.mob;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.entity.mob.precasia.VeloraptorEntity;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationProcessor;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;

public class VeloraptorModel extends MultiStageHeadModel<VeloraptorEntity> {
	public VeloraptorModel() {
		super(AdventOfAscension.id("mob/precasia/veloraptor"));

		withStages(
				new Stage("head", FloatFloatPair.of(0, 42.5f), FloatFloatPair.of(-7, 7)),
				new Stage("neck", FloatFloatPair.of(0, 75), FloatFloatPair.of(-20, 20))
		);
	}

	@Override
	public void setCustomAnimations(VeloraptorEntity animatable, long instanceId, AnimationState<VeloraptorEntity> animationState) {
		if (true)
			return;
		// TODO Look into why this is weird for veloraptor?

		final AnimationProcessor<VeloraptorEntity> processor = getAnimationProcessor();
		final EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
		float pitch = entityData.headPitch();
		float yaw = entityData.netHeadYaw();

		for (Stage stage : this.stages) {
			CoreGeoBone bone = processor.getBone(stage.boneName());

			if (bone == null) {
				Logging.logMessage(Level.ERROR, "Invalid bone name " + stage.boneName() + " for multi-stage head model");

				return;
			}

			float bonePitch = Mth.clamp(pitch, stage.pitchBounds().leftFloat(), stage.pitchBounds().rightFloat());
			float boneYaw = Mth.clamp(yaw, stage.yawBounds().leftFloat(), stage.yawBounds().rightFloat());
			pitch -= bonePitch * Math.signum(bonePitch);
			yaw -= boneYaw * Math.signum(boneYaw);

			bone.setRotX(bonePitch * Mth.DEG_TO_RAD);
			bone.setRotY(boneYaw * Mth.DEG_TO_RAD);
		}
	}

	@Override
	public ResourceLocation getTextureResource(VeloraptorEntity animatable) {
		final ResourceLocation texture = super.getTextureResource(animatable);

		return new ResourceLocation(texture.getNamespace(), texture.getPath().substring(0, texture.getPath().indexOf(".")) + "_" + animatable.getVariant().name + ".png");
	}
}
