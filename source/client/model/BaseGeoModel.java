package net.tslat.aoa3.client.model;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class BaseGeoModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
	private ResourceLocation model;
	private final ResourceLocation texture;
	private ResourceLocation animations;

	public BaseGeoModel(String assetSubpath) {
		this.model = AdventOfAscension.id("geo/" + subtype() + "/" + assetSubpath + ".geo.json");
		this.texture = AdventOfAscension.id("textures/" + subtype() + "/" + assetSubpath + ".png");
		this.animations = AdventOfAscension.id("animations/" + subtype() + "/" + assetSubpath + ".animation.json");
	}

	public BaseGeoModel<T> withModel(String modelPath) {
		this.model = AdventOfAscension.id("geo/" + subtype() + "/" + modelPath + ".geo.json");

		return this;
	}

	public BaseGeoModel<T> withAnimations(String modelPath) {
		this.animations = AdventOfAscension.id("animations/" + subtype() + "/" + modelPath + ".animation.json");

		return this;
	}

	protected abstract String subtype();

	@Override
	public ResourceLocation getModelLocation(T object) {
		return model;
	}

	@Override
	public ResourceLocation getTextureLocation(T object) {
		return texture;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(T animatable) {
		return animations;
	}
}
