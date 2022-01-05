package net.tslat.aoa3.client.model;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class BaseGeoModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
	private final ResourceLocation model;
	private final ResourceLocation texture;
	private final ResourceLocation animations;

	public BaseGeoModel(String assetSubpath) {
		this.model = AdventOfAscension.id("geo/" + subtype() + "/" + assetSubpath + ".geo.json");
		this.texture = AdventOfAscension.id("textures/" + subtype() + "/" + assetSubpath + ".png");
		this.animations = AdventOfAscension.id("animations/" + subtype() + "/" + assetSubpath + ".animation.json");
	}

	public BaseGeoModel(String modelSubpath, String textureSubpath) {
		this.model = AdventOfAscension.id("geo/" + subtype() + "/" + modelSubpath + ".geo.json");
		this.texture = AdventOfAscension.id("textures/" + subtype() + "/" + textureSubpath + ".png");
		this.animations = AdventOfAscension.id("animations/" + subtype() + "/" + modelSubpath + ".animation.json");
	}

	public BaseGeoModel(String modelSubpath, String textureSubpath, String animationSubpath) {
		this.model = AdventOfAscension.id("geo/" + subtype() + "/" + modelSubpath + ".geo.json");
		this.texture = AdventOfAscension.id("textures/" + subtype() + "/" + textureSubpath + ".png");
		this.animations = AdventOfAscension.id("animations/" + subtype() + "/" + animationSubpath + ".animation.json");
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
