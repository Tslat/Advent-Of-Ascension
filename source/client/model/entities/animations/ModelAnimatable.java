package net.tslat.aoa3.client.model.entities.animations;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.tslat.aoa3.entity.base.AnimatableEntity;

import java.util.HashMap;

public abstract class ModelAnimatable extends ModelBase {
	private HashMap<String, EntityAnimation> animations = null;

	public void addAnimation(String name, EntityAnimation animation) {
		if (animations == null)
			animations = new HashMap<String, EntityAnimation>();

		animations.put(name, animation);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		if (animations != null && entity instanceof AnimatableEntity) {
			AnimatableEntity animatableEntity = (AnimatableEntity)entity;
			String currentAnimation = animatableEntity.getCurrentAnimation();

			if (currentAnimation != null && animations.containsKey(currentAnimation))
				animations.get(currentAnimation).animate(entity, animatableEntity.getCurrentAnimationTicks(), ageInTicks - entity.ticksExisted);
		}
	}
}
