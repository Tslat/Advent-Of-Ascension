package net.tslat.aoa3.client.model.entity.animal;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.animal.CorateeEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.Nullable;

public class CorateeModel extends AnimatedGeoModel<CorateeEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/animals/lborean/coratee.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/animals/coratee.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/animals/lborean/coratee.animation.json");

	@Override
	public ResourceLocation getModelLocation(CorateeEntity anglerEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(CorateeEntity anglerEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(CorateeEntity anglerEntity) {
		return ANIMATIONS;
	}

	@Override
	public void setLivingAnimations(CorateeEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		if (entity.isBaby()) {
			IBone root = getAnimationProcessor().getBone("bone");
			IBone head = getAnimationProcessor().getBone("head");

			root.setScaleX(0.5f);
			root.setScaleY(0.5f);
			root.setScaleZ(0.5f);
			head.setScaleX(1.5f);
			head.setScaleY(1.5f);
			head.setScaleZ(1.5f);
		}
	}
}
