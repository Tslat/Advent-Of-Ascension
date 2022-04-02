package net.tslat.aoa3.client.model.entity;

import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.client.model.BaseGeoModel;
import software.bernie.geckolib3.core.IAnimatable;

public class EntityGeoModel<T extends Entity & IAnimatable> extends BaseGeoModel<T> {
	public EntityGeoModel(String assetSubpath) {
		super(assetSubpath);
	}

	@Override
	protected String subtype() {
		return "entity";
	}

	@Override
	public EntityGeoModel<T> withModel(String modelPath) {
		return (EntityGeoModel<T>)super.withModel(modelPath);
	}

	@Override
	public EntityGeoModel<T> withAnimations(String modelPath) {
		return (EntityGeoModel<T>)super.withAnimations(modelPath);
	}
}
