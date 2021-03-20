package net.tslat.aoa3.client.model.entity.boss;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.boss.KrorEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.Nullable;

public class KrorModel extends AnimatedGeoModel<KrorEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/bosses/kror/kror.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entities/boss/kror/kror.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/bosses/kror/kror.animation.json");

	@Override
	public ResourceLocation getModelLocation(KrorEntity krorEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(KrorEntity krorEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(KrorEntity krorEntity) {
		return ANIMATIONS;
	}

	@Override
	public void setLivingAnimations(KrorEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		IBone root = getAnimationProcessor().getBone("body");

		root.setScaleX(1.25f);
		root.setScaleY(1.25f);
		root.setScaleZ(1.25f);
	}
}
