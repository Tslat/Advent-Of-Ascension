package net.tslat.aoa3.client.model.entity;

import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.advent.AdventOfAscension;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AoAEntityGeoModel<T extends Entity & GeoEntity> extends DefaultedEntityGeoModel<T> {
	private final boolean turnsHead;

	public AoAEntityGeoModel(String assetSubpath) {
		this(assetSubpath, false);
	}

	public AoAEntityGeoModel(String assetSubpath, boolean turnsHead) {
		super(AdventOfAscension.id(assetSubpath));

		this.turnsHead = turnsHead;
	}

	@Override
	protected String subtype() {
		return "entity";
	}

	public AoAEntityGeoModel<T> withModel(String modelPath) {
		return (AoAEntityGeoModel<T>)super.withAltModel(AdventOfAscension.id(modelPath));
	}

	public AoAEntityGeoModel<T> withAnimations(String modelPath) {
		return (AoAEntityGeoModel<T>)super.withAltAnimations(AdventOfAscension.id(modelPath));
	}

	@Override
	public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

		if (!this.turnsHead)
			animatable.setYBodyRot(animatable.getYHeadRot());
	}
}
